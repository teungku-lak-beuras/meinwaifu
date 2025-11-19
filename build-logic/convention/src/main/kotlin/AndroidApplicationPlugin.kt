
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import hemel.van.buildlogic.configureAndroidApplication
import hemel.van.buildlogic.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with (target) {
            //val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            with (pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<BaseAppModuleExtension> {
                configureAndroidApplication(this)
            }

            extensions.configure<KotlinAndroidProjectExtension> {
                configureKotlin(this)
            }
        }
    }
}
