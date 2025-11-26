import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("version-management-convention")
    id("kotlin-library-convention")
}

tasks {
    val detektAll by registering {
        description = "runs detekt on all src sets"
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
