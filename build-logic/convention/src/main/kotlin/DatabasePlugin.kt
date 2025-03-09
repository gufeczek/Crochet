import com.android.build.api.dsl.LibraryExtension
import io.github.gufeczek.crochet.convention.dependency.configureRoom
import io.github.gufeczek.crochet.convention.plugin.KSP
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class DatabasePlugin : Plugin<Project> {
    override fun apply(project: Project) = with (project) {
        apply(plugin = KSP)

        extensions.configure<LibraryExtension> {
            dependencies {
                configureRoom(project)
            }
        }
    }
}