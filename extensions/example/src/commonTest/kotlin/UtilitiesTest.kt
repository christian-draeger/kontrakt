package codes.draeger.utils

import codes.draeger.example.extensions.example.foobar
import kotlin.test.Test
import kotlin.test.assertEquals

internal class UtilitiesKtTest {

    @Test
    fun exampleTest() {
        assertEquals(foobar(), "bar")
    }
}
