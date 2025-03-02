import com.android.build.api.dsl.LibraryExtension
import io.github.gufeczek.crochet.convention.dependency.configureAndroidCore
import io.github.gufeczek.crochet.convention.dependency.configureCompose
import io.github.gufeczek.crochet.convention.dependency.configureKoin
import io.github.gufeczek.crochet.convention.dependency.configureArrow
import io.github.gufeczek.crochet.convention.dependency.configureJUnit
import io.github.gufeczek.crochet.convention.dependency.configureKotlinSerialization
import io.github.gufeczek.crochet.convention.dependency.configureNavigation
import io.github.gufeczek.crochet.convention.plugin.ANDROID_LIBRARY
import io.github.gufeczek.crochet.convention.plugin.COMPOSE
import io.github.gufeczek.crochet.convention.plugin.KOTLIN_ANDROID
import io.github.gufeczek.crochet.convention.plugin.KOTLIN_SERIALIZATION
import io.github.gufeczek.crochet.convention.plugin.KSP
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        apply(plugin = ANDROID_LIBRARY)
        apply(plugin = KOTLIN_ANDROID)
        apply(plugin = COMPOSE)
        apply(plugin = KOTLIN_SERIALIZATION)
        apply(plugin = KSP)

        extensions.configure<LibraryExtension> {
            compileSdk = 35
            defaultConfig {
                minSdk = 27
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
            dependencies {
                configureCompose(project)
                configureAndroidCore(project)
                configureKoin(project)
                configureArrow(project)
                configureKotlinSerialization(project)
                configureNavigation(project)
                configureJUnit(project)
            }
        }
    }
}
