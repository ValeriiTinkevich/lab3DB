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
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.0.1")

}


tasks.register<Jar>("fatJar") {
    manifest {
        // Update this line to include the full package name
        attributes["Main-Class"] = "ru.valerit.Main"
    }
    archiveBaseName.set("${project.name}-all")
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.getByName("jar") as CopySpec)
    destinationDirectory.set(file({rootDir}))
}

tasks.create("deploy") {

    doLast {
        val user = (System.getenv("DEPLOYUSER") ?: "ERROR")
        val userAndHost : String = user + "@" + (System.getenv ("DEPLOYHOST")  ?: "ERROR")

        val pwd : String = System.getenv("DEPLOYPWD") ?: "ERROR"

        println("$userAndHost :$pwd")

        exec {
            workingDir(".")
            commandLine("pscp", "-pw", pwd, "-P", 2222, "C:/Users/User/Desktop/lab7out/**.jar", "$userAndHost:/home/studs/$user/lab3db")
//            commandLine("putty.exe", "-ssh", userAndHost, "-P", "2222", "-pw", pwd)
        }
    }
}



tasks.test {
    useJUnitPlatform()
}