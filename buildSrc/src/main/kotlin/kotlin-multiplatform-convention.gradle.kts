plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    explicitApi()
    jvm()
    /*js {
        browser()
        nodejs()
    }*/
}