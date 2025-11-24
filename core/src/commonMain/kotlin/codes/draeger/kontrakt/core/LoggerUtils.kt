package codes.draeger.kontrakt.core

import io.github.oshai.kotlinlogging.KotlinLogging

internal inline val <reified T> T.logger
    get() = KotlinLogging.logger(T::class.simpleName ?: "Unknown")
