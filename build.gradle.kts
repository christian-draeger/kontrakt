import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("version-management-convention")
    id("kotlin-library-convention")
}

// aggregate dependents to an all-in-one library
kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core)
            api(projects.example)
        }
    }
}

tasks {
    // detektAll is a task that runs detekt on all src sets
    val detektAll by registering {
        allprojects {
            this@registering.dependsOn(tasks.withType<Detekt>())
        }
    }
    build {
        dependsOn(detektAll)
        finalizedBy(koverXmlReport, koverHtmlReport)
    }
}

allprojects {
    apply(plugin = "kotlin-library-convention")
}
