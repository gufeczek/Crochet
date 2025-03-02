import java.util.UUID

plugins {
    alias(libs.plugins.convention.common.library)
}

android {
    namespace = "io.github.gufeczek.common.speech.recognition.model"
    compileSdk = 35

    sourceSets["main"].assets.srcDirs("$buildDir/generated/assets")
}

dependencies {
    implementation(libs.vosk)
}

tasks.register("genUUID") {
    val uuid = UUID.randomUUID().toString()
    val odir = project.file("${project.buildDir}/generated/assets/model-en-us")
    val ofile = File(odir, "uuid")
    doLast {
        odir.mkdirs()
        ofile.writeText(uuid)
    }
}

tasks.named("preBuild").configure {
    dependsOn("genUUID")
}