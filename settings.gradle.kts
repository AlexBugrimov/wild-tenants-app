pluginManagement {
    val kotlinVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinVersion
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

includeBuild("lessons")
includeBuild("wild-tenants-be")
includeBuild("wild-tenants-libs")
includeBuild("wild-tenants-states")
includeBuild("wild-tenants-tests")

rootProject.name = "wild-tenants-app"