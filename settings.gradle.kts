rootProject.name = "kotlin-library-template"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include(":core")
include(":example")
project(":example").projectDir = file("extensions/example")

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
    id("org.jetbrains.kotlinx.kover.aggregation") version "0.9.1"
}
kover {
    enableCoverage()
}
