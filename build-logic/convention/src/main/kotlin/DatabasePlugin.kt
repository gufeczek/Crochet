import androidx.room.gradle.RoomExtension
import com.android.build.api.dsl.LibraryExtension
import com.google.devtools.ksp.gradle.KspExtension
import io.github.gufeczek.crochet.convention.dependency.configureRoom
import io.github.gufeczek.crochet.convention.plugin.KSP
import io.github.gufeczek.crochet.convention.plugin.ROOM
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class DatabasePlugin : Plugin<Project> {
    override fun apply(project: Project) = with (project) {
        apply(plugin = ROOM)
        apply(plugin = KSP)

        extensions.configure<KspExtension> {
            arg("room.generateKotlin", "true")
        }

        extensions.configure<LibraryExtension> {
            dependencies {
                configureRoom(project)
            }
        }

        extensions.configure<RoomExtension> {
            schemaDirectory("$projectDir/schemas")
        }
    }
}