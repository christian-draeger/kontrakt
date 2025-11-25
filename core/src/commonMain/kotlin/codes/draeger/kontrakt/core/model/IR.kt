package codes.draeger.kontrakt.core.model

public data class ApiDefinition(
    val routes: List<RouteDefinition>,
    val schemas: List<SchemaDefinition>
)

public data class RouteDefinition(
    val operationId: String,
    val method: String,
    val path: String,
    val inputType: DataType,
    val outputType: DataType
)

public data class SchemaDefinition(
    val name: String,
    val fields: List<SchemaField>
)

public data class SchemaField(
    val name: String,
    val type: DataType,
    val isNullable: Boolean = false,
    val validators: List<Validator> = emptyList()
)

public sealed interface DataType {
    public object StringType : DataType
    public object NumberType : DataType
    public object BooleanType : DataType
    public object VoidType : DataType
    public object AnyType : DataType

    public data class ReferenceType(val schemaName: String) : DataType

    public data class ArrayType(val wrapped: DataType) : DataType
}

public sealed interface Validator {
    public data class Min(val value: Long) : Validator
    public data class Max(val value: Long) : Validator
    public data class MinLength(val value: Int) : Validator
    public data class MaxLength(val value: Int) : Validator
    public object Email : Validator
}
