kotlin {
    sourceSets {
        jvmMain.dependencies {
            api(projects.core)
            implementation("org.springframework.boot:spring-boot-starter-web:3.3.0")
            implementation("org.springframework.boot:spring-boot-starter-validation:3.3.0")

            implementation("org.jetbrains.kotlin:kotlin-reflect")
        }
        jvmTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
