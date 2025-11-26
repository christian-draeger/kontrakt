import codes.draeger.kontrakt.core.generator.TypeScriptGenerator
import java.io.File

public object Kontrakt {
    
    public fun generate(
        scanPackage: String,
        outputFile: File
    ) {
        println("🚀 Kontrakt: Scanning package '$scanPackage'...")
        
        val scanner = SpringScanner(scanPackage)
        val apiDefinition = scanner.scan()
        
        println("   Found ${apiDefinition.routes.size} routes and ${apiDefinition.schemas.size} schemas.")
        
        val generator = TypeScriptGenerator()
        val content = generator.generate(apiDefinition)
        
        if (outputFile.parentFile != null && !outputFile.parentFile.exists()) {
            outputFile.parentFile.mkdirs()
        }
        outputFile.writeText(content)
        
        println("✅ Kontrakt generated successfully at: ${outputFile.absolutePath}")
    }
}
