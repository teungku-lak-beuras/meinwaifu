
import com.android.build.gradle.LibraryExtension
import hemel.van.buildlogic.configureAndroidLibrary
import hemel.van.buildlogic.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with (target) {
            with (pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureAndroidLibrary(this)
            }

            extensions.configure<KotlinAndroidProjectExtension> {
                configureKotlin(this)
            }
        }
    }
}
