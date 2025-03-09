plugins {
    alias(libs.plugins.convention.core.library)
    alias(libs.plugins.convention.database)
}

android {
    namespace = "io.github.gufeczek.data"
    compileSdk = 35
}