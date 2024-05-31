plugins {
    id("java")
}

group = "ru.valerit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.7.3")
}


tasks.register<Jar>("fatJar") {
    manifest {
        attributes["Main-Class"] = "Main"
    }
    archiveBaseName.set("${project.name}-all")
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    destinationDirectory.set(file("C:/Users/User/Desktop/lab7out"))
}


tasks.test {
    useJUnitPlatform()
}