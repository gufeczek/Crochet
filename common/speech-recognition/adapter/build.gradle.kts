plugins {
    alias(libs.plugins.convention.common.library)
}

android {
    namespace = "io.github.gufeczek.common.speech.recognition.adapter"
    compileSdk = 35

    defaultConfig {
        minSdk = 27
    }
    lint {
        disable.add("FlowOperatorInvokedInComposition")
    }
}
dependencies {
    implementation(projects.common.speechRecognition.model)

}
