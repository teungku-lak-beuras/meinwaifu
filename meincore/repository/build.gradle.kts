import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "hemel.van.meincore.repository"

    compileSdk {
        version = release(36)
    }

    buildFeatures {
        buildConfig = true
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
    implementation(project(":meincore:entity"))
    implementation(project(":meincore:network"))

    implementation(libs.kotlinx.coroutines.android) // Kotlin Coroutines.
    implementation(libs.retrofit)                   // Retrofit.
    implementation(libs.converter.gson)             // Retrofit serialiser.
    implementation(libs.logging.interceptor)        // Logging interceptor.
}
