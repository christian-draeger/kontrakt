import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode

plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    explicitApi()
    jvm()
    js {
        browser()
        nodejs()
    }
}