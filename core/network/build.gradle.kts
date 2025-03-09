plugins {
    alias(libs.plugins.convention.core.library)
    alias(libs.plugins.convention.network)
}

android {
    namespace = "io.github.gufeczek.data"
    compileSdk = 35
}