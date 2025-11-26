import java.io.File
import kotlin.test.Test

class GenerateContractTest {
    @Test
    fun `can generate contract based on spring boot controllers and DTos`() {
        Kontrakt.generate("com.example.demo", File("./frontend/src/gen/contract.ts"))
    }
}
