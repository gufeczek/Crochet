plugins {
    alias(libs.plugins.convention.feature)
}

android {
    namespace = "io.github.gufeczek.feature.counter"
}

dependencies {
    implementation(projects.common.designSystem)
}
