plugins {
    id("fabric-loom") version "1.6.13"
    id("maven-publish")
    kotlin("jvm") version "1.9.24"
}

version = project.property("mod_version")!!
group = project.property("maven_group")!!

repositories {
    mavenCentral()
    maven("https://api.modrinth.com/maven") {
        name = "Modrinth"
    }
    maven("https://maven.fabricmc.net")
    maven("https://maven.neoforged.net/releases")
}

dependencies {
    minecraft("com.mojang:minecraft:${project.property("minecraft_version")}")
    mappings("net.fabricmc:yarn:${project.property("yarn_mappings")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${project.property("loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_version")}")
    
    // Optional mod dependencies - won't force installation
    modCompileOnly("maven.modrinth:skyhanni:${project.property("skyhanni_version")}")
    modCompileOnly("maven.modrinth:odin:${project.property("odin_version")}")
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}
