package codes.draeger.example.core

import io.github.oshai.kotlinlogging.KotlinLogging

internal inline val <reified T> T.logger
    get() = KotlinLogging.logger(T::class.simpleName ?: "Unknown")
