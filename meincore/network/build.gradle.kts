import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "hemel.van.meincore.network"

    compileSdk {
        version = release(36)
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        val nekosBestApiEndPoint = "https://nekos.best/api/v2/"

        debug {
            buildConfigField("String", "NEKOS_BEST_API", "\"$nekosBestApiEndPoint\"")
        }

        release {
            buildConfigField("String", "NEKOS_BEST_API", "\"$nekosBestApiEndPoint\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }
}

dependencies {
    implementation(libs.retrofit)                   // Retrofit.
    implementation(libs.converter.gson)             // Retrofit serialiser.
    implementation(libs.logging.interceptor)        // Logging interceptor.
}
