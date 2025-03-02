plugins {
    alias(libs.plugins.convention.core.library)
    alias(libs.plugins.android.library)
}

android {
    namespace = "io.github.gufeczek.core.speech"
    compileSdk = 35
    defaultConfig { // TODO why it's applied from convention plugin?
        minSdk = 27
    }
}

dependencies {
    implementation(projects.common.speechRecognition.adapter)
    implementation(projects.common.speechRecognition.model)
}