plugins {
    kotlin("plugin.spring") version "2.2.21"
    id("org.springframework.boot") version "4.0.0"
    id("io.spring.dependency-management") version "1.1.7"
}

kotlin {
    sourceSets {
        jvmMain.dependencies {
            api(projects.spring)
            implementation("org.springframework.boot:spring-boot-starter-webmvc")
            implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
            implementation("org.springframework.boot:spring-boot-starter-validation")

            implementation("org.jetbrains.kotlin:kotlin-reflect")
        }
        jvmTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
