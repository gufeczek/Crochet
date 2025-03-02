import com.android.build.api.dsl.LibraryExtension
import io.github.gufeczek.crochet.convention.dependency.configureAndroidCore
import io.github.gufeczek.crochet.convention.dependency.configureArrow
import io.github.gufeczek.crochet.convention.dependency.configureJUnit
import io.github.gufeczek.crochet.convention.dependency.configureKoin
import io.github.gufeczek.crochet.convention.plugin.ANDROID_LIBRARY
import io.github.gufeczek.crochet.convention.plugin.KOTLIN_ANDROID
import io.github.gufeczek.crochet.convention.plugin.KSP
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class CoreLibraryPlugin : Plugin<Project> {
    override fun apply(project: Project) = with (project) {
        apply(plugin = ANDROID_LIBRARY)
        apply(plugin = KOTLIN_ANDROID)
        apply(plugin = KSP)

        extensions.configure<LibraryExtension> {
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            dependencies {
                configureKoin(project)
                configureArrow(project)
                configureJUnit(project)
            }
            lint {
                disable.add("FlowOperatorInvokedInComposition")
            }
        }
    }
}