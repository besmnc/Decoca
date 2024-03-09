import net.minecraftforge.gradle.userdev.UserDevExtension
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

plugins {
    eclipse
    idea
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("net.minecraftforge.gradle") version "6.+"
}

group = "com.algorithmlx"
version = "1.19.2_1.0"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

configure<UserDevExtension> {
    mappings("official", "1.19.2")

    accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))

    copyIdeResources.set(true)

    runs {
        create("client") {
            workingDirectory (project.file("run"))

            jvmArg("-XX:+AllowEnhancedClassRedefinition")
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            property("forge.enabledGameTestNamespaces", "decoca")

            mods {
                create("decoca") {
                    sources(the<JavaPluginExtension>().sourceSets.getByName("main"))
                }
            }
        }

        create("server") {
            workingDirectory (project.file("run"))

            jvmArg("-XX:+AllowEnhancedClassRedefinition")
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            property("forge.enabledGameTestNamespaces", "decoca")

            mods {
                create("decoca") {
                    sources(the<JavaPluginExtension>().sourceSets.getByName("main"))
                }
            }
        }

        create("data") {
            workingDirectory (project.file("run"))

            jvmArg("-XX:+AllowEnhancedClassRedefinition")
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            args("--mod", "decoca", "--all", "--output", file("src/generated/resources/"), "--existing", file("src/main/resources/"))

            mods {
                create("decoca") {
                    sources(the<JavaPluginExtension>().sourceSets.getByName("main"))
                }
            }
        }
    }
}

repositories {
    mavenCentral()
    maven("https://thedarkcolour.github.io/KotlinForForge/") {
        content {
            includeGroup("thedarkcolour")
        }
    }
}

dependencies {
    minecraft("net.minecraftforge:forge:1.19.2-43.2.3")

    implementation("thedarkcolour:kotlinforforge:3.12.0")

    implementation(kotlin("stdlib"))
    implementation(kotlin("serialization"))
}

tasks {
    withType<Jar> {
        from(sourceSets.main.get().output)
        manifest {
            attributes(
                mapOf(
                    "Specification-Title" to "decoca",
                    "Specification-Vendor" to "AlgorithmLX",
                    "Specification-Version" to "1",
                    "Implementation-Title" to project.name,
                    "Implementation-Version" to version,
                    "Implementation-Timestamp" to ZonedDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")),
                )
            )
        }
        finalizedBy("reobfJar")
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}
