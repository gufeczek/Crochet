import com.android.build.api.dsl.LibraryExtension
import io.github.gufeczek.crochet.convention.dependency.configureKtor
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class NetworkPlugin : Plugin<Project> {
    override fun apply(project: Project) = with (project) {
        extensions.configure<LibraryExtension> {
            dependencies {
                configureKtor(project)
            }
        }
    }
}