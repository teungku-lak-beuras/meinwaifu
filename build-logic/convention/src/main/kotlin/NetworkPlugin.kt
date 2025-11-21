import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class NetworkPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with (target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            with (pluginManager) {
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            dependencies {
                add("implementation", libs.findLibrary("ktor-client-core").get())
                add("implementation", libs.findLibrary("ktor-client-cio").get())
                add("implementation", libs.findLibrary("ktor-client-logging").get())
                add("implementation", libs.findLibrary("ktor-client-content-negotiation").get())
                add("implementation", libs.findLibrary("ktor-serialization-kotlinx-json").get())
                add("implementation", libs.findLibrary("kotlinx-serialization-json").get())
            }
        }
    }
}
