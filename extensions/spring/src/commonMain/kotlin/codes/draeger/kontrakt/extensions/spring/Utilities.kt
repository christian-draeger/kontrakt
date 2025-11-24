package codes.draeger.kontrakt.extensions.spring

import codes.draeger.kontrakt.core.Example

public fun foobar(): String = Example().foo().also(::println)
