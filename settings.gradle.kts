pluginManagement {

    val kotlinVersion: String by settings
    val spotlessVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        id("com.diffplug.spotless") version spotlessVersion
        kotlin("plugin.serialization") version kotlinVersion
    }
}
rootProject.name = "Autoway"
