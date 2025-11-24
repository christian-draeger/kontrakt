kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
