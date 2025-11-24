package codes.draeger.example.core

public class Example {
    public fun foo(): String = "bar".also { logger.info { "foo called $it" } }
}
