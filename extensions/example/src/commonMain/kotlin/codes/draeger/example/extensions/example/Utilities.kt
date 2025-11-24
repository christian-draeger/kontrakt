package codes.draeger.example.extensions.example

import codes.draeger.example.core.Example

public fun foobar(): String = Example().foo().also(::println)
