pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.minecraftforge.net/")
        maven("https://repo.spongepowered.org/repository/maven-public/")
        maven("https://maven.parchmentmc.org")
    }
    plugins {
        kotlin("jvm") version "1.8.21"
        kotlin("plugin.serialization") version "1.8.21"
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "Decoca"
