import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "io.github.gufeczek.crochet.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.compose.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("feature") {
            id = libs.plugins.convention.feature.get().pluginId
            implementationClass = "AndroidFeaturePlugin"
        }
        register("coreLibrary") {
            id = libs.plugins.convention.core.library.get().pluginId
            implementationClass = "CoreLibraryPlugin"
        }
        register("composeLibrary") {
            id = libs.plugins.convention.compose.library.get().pluginId
            implementationClass = "ComposeLibraryPlugin"
        }
        register("commonLibrary") {
            id = libs.plugins.convention.common.library.get().pluginId
            implementationClass = "CommonLibraryPlugin"
        }
    }
}
