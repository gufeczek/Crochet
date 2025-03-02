plugins {
    alias(libs.plugins.convention.core.library)
    alias(libs.plugins.convention.compose.library)
}

android {
    namespace = "io.github.gufeczek.crochet.common.design.system"
    compileSdk = 35
    defaultConfig {
        minSdk = 27
    }
}