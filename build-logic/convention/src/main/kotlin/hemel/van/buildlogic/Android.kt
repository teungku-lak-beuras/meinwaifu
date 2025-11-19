package hemel.van.buildlogic

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

fun configureAndroidApplication(extension: BaseAppModuleExtension) {
    extension.apply {
        compileSdk = meinwaifuTargetSdk

        defaultConfig {
            minSdk = meinwaifuMinSdk
            targetSdk = meinwaifuTargetSdk
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = meinwaifuJavaVersion
            targetCompatibility = meinwaifuJavaVersion
        }
    }
}

fun configureAndroidLibrary(extension: LibraryExtension) {
    extension.apply {
        compileSdk = meinwaifuTargetSdk

        compileOptions {
            sourceCompatibility = meinwaifuJavaVersion
            targetCompatibility = meinwaifuJavaVersion
        }
    }
}
