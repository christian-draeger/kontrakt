package codes.draeger.utils

import codes.draeger.kontrakt.extensions.spring.foobar
import kotlin.test.Test
import kotlin.test.assertEquals

internal class UtilitiesKtTest {

    @Test
    fun exampleTest() {
        assertEquals(foobar(), "bar")
    }
}
