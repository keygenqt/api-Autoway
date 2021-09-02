plugins {
    application
    kotlin("jvm")
    id("com.diffplug.spotless")
    id("com.github.johnrengelman.shadow") version "7.0.0"
    kotlin("plugin.serialization")
}

spotless {
    kotlin {
        target("**/*.kt")
        licenseHeaderFile("${project.projectDir}/spotless.license")
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}

group = "com.keygenqt.autoway"
version = "0.0.1"

application {
    mainClass.set("com.keygenqt.autoway.ApplicationKt")
}

repositories {
    mavenCentral()
}

tasks {
    shadowJar {
        manifest {
            attributes(Pair("Main-Class", "com.keygenqt.autoway.ApplicationKt"))
        }
    }
}

val koinVersion: String by project
val ktorVersion: String by project
val kotlinVersion: String by project
val flywayVersion: String by project
val exposedVersion: String by project
val logbackVersion: String by project
val hikariCpVersion: String by project
val sqliteConnectorVersion: String by project

dependencies {
    // ktor
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-websockets:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    implementation("io.ktor:ktor-auth:$ktorVersion")
    implementation("io.ktor:ktor-locations:$ktorVersion")

    // exposed
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    // di
    implementation("io.insert-koin:koin-ktor:$koinVersion")

    // other
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.flywaydb:flyway-core:$flywayVersion")

    // db
    implementation("org.xerial:sqlite-jdbc:$sqliteConnectorVersion")
    implementation("com.zaxxer:HikariCP:$hikariCpVersion")
    implementation("com.google.code.gson:gson:2.8.8")

    // tests
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}
