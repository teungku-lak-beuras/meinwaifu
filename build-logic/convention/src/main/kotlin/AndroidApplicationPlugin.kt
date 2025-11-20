
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import hemel.van.buildlogic.configureAndroidApplication
import hemel.van.buildlogic.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with (target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            with (pluginManager) {
                // Android mandatory
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")

                // Compose
                apply("org.jetbrains.kotlin.plugin.compose")

                // Hilt
                apply("com.google.devtools.ksp")
                apply("com.google.dagger.hilt.android")
            }

            extensions.configure<BaseAppModuleExtension> {
                configureAndroidApplication(this)
            }

            extensions.configure<KotlinAndroidProjectExtension> {
                configureKotlin(this)
            }

            dependencies {
                // Hilt: https://developer.android.com/training/dependency-injection/hilt-android#setup
                add("implementation", libs.findLibrary("hilt-android").get())
                add("ksp", libs.findLibrary("hilt-compiler").get())
            }
        }
    }
}
