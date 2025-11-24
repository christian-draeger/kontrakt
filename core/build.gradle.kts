kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.kotlinxEcosystem)
            implementation(libs.bundles.logging)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
