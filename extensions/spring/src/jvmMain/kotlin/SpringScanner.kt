import codes.draeger.kontrakt.core.model.*
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.core.type.filter.AnnotationTypeFilter
import org.springframework.web.bind.annotation.*
import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import jakarta.validation.constraints.*

public class SpringScanner(
    private val scanPackage: String
) {
    private val discoveredSchemas = mutableMapOf<String, SchemaDefinition>()

    public fun scan(): ApiDefinition {
        val routes = scanRoutes()
        return ApiDefinition(routes, discoveredSchemas.values.toList())
    }

    private fun scanRoutes(): List<RouteDefinition> {
        val scanner = ClassPathScanningCandidateComponentProvider(false)
        scanner.addIncludeFilter(AnnotationTypeFilter(RestController::class.java))
        val routes = mutableListOf<RouteDefinition>()

        for (beanDef in scanner.findCandidateComponents(scanPackage)) {
            val clz = Class.forName(beanDef.beanClassName)
            
            // Suche Base-Path am Controller (@RequestMapping("/api/users"))
            val basePath = clz.getAnnotation(RequestMapping::class.java)?.value?.firstOrNull() ?: ""
            
            clz.methods.forEach { method ->
                // Wir unterstützen explizite Annotationen
                // (Du kannst hier später deine @OrpcRoute wieder hinzufügen)
                
                var path = ""
                var httpMethod = ""
                
                // Naive Erkennung von Spring Mappings
                if (method.isAnnotationPresent(PostMapping::class.java)) {
                    path = method.getAnnotation(PostMapping::class.java).value.firstOrNull() ?: ""
                    httpMethod = "POST"
                } else if (method.isAnnotationPresent(GetMapping::class.java)) {
                    path = method.getAnnotation(GetMapping::class.java).value.firstOrNull() ?: ""
                    httpMethod = "GET"
                }
                // TODO: PutMapping, DeleteMapping ...

                if (httpMethod.isNotEmpty()) {
                    // Argumente analysieren -> Input (nur @RequestBody für MVP)
                    val bodyParam = method.parameters.firstOrNull { it.isAnnotationPresent(RequestBody::class.java) }
                    val inputType = if (bodyParam != null) {
                         analyzeType(bodyParam.parameterizedType)
                    } else {
                        DataType.VoidType
                    }

                    val outputType = analyzeType(method.genericReturnType)

                    // Pfade zusammenbauen (Vorsicht bei Slashes)
                    val fullPath = (basePath + path).replace("//", "/")

                    routes.add(RouteDefinition(
                        operationId = method.name,
                        method = httpMethod,
                        path = fullPath,
                        inputType = inputType,
                        outputType = outputType
                    ))
                }
            }
        }
        return routes
    }

    /**
     * Der rekursive Typ-Analysierer.
     * Nutzt 'java.lang.reflect.Type' statt 'Class', um Generics zu verstehen.
     */
    private fun analyzeType(type: Type): DataType {
        // primitives and basics
        if (type == Void.TYPE || type == Void::class.java) return DataType.VoidType
        if (type == String::class.java) return DataType.StringType
        if (type == Int::class.java || type == Integer::class.java) return DataType.NumberType
        if (type == Long::class.java || type == java.lang.Long::class.java) return DataType.NumberType
        if (type == Boolean::class.java || type == java.lang.Boolean::class.java) return DataType.BooleanType

        // generics and lists
        if (type is ParameterizedType) {
            val rawType = type.rawType as Class<*>
            if (List::class.java.isAssignableFrom(rawType)) {
                val innerType = type.actualTypeArguments[0]
                return DataType.ArrayType(analyzeType(innerType))
            }
        }

        // DTOs and complex objects
        val clazz = if (type is ParameterizedType) type.rawType as Class<*> else type as Class<*>
        
        // Safety Check: no java internal classes
        if (clazz.packageName.startsWith("java") || clazz.packageName.startsWith("org.springframework")) {
             return DataType.AnyType
        }

        val schemaName = clazz.simpleName
        
        // caching to avoid endless recursion
        if (!discoveredSchemas.containsKey(schemaName)) {
            discoveredSchemas[schemaName] = SchemaDefinition(schemaName, emptyList()) 
            
            val fields = clazz.declaredFields.map { field ->
                SchemaField(
                    name = field.name,
                    type = analyzeType(field.genericType),
                    isNullable = !field.isAnnotationPresent(NotNull::class.java),
                    validators = extractValidators(field)
                )
            }
            discoveredSchemas[schemaName] = SchemaDefinition(schemaName, fields)
        }
        
        return DataType.ReferenceType(schemaName)
    }

    private fun extractValidators(field: Field): List<Validator> {
        val list = mutableListOf<Validator>()
        if (field.isAnnotationPresent(Email::class.java)) list.add(Validator.Email)
        field.getAnnotation(Min::class.java)?.let { list.add(Validator.Min(it.value)) }
        field.getAnnotation(Size::class.java)?.let { 
            list.add(Validator.MinLength(it.min))
            list.add(Validator.MaxLength(it.max))
        }
        return list
    }
}
