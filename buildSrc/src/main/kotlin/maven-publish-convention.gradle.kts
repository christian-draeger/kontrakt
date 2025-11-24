plugins {
    id("com.vanniktech.maven.publish")
}

val rootProjectName = rootProject.name

mavenPublishing {
    val artifactId = if (project.name == rootProjectName) project.name else "${rootProjectName}-${project.name}"
    coordinates(artifactId = artifactId)
    pom {
        name.set(artifactId)
    }
}