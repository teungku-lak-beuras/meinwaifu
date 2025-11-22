import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class LocalPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with (target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            with (pluginManager) {
                apply("com.google.devtools.ksp")
            }

            dependencies {
                add("implementation", libs.findLibrary("room-runtime").get())
                add("ksp", libs.findLibrary("room-compiler").get())
            }
        }
    }
}
