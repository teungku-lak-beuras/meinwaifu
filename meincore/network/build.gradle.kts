plugins {
    alias(libs.plugins.meinwaifu.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
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

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(libs.ktor.client.core)                   // Ktor Core
    implementation(libs.ktor.client.cio)                    // Ktor Coroutine-based IO
    implementation(libs.ktor.client.logging)                // Ktor Logger
    implementation(libs.ktor.client.content.negotitaion)    // Ktor Content Negotiation
    implementation(libs.ktor.serialization.kotlinx.json)    // Ktor KotlinX JSON Serialiser support
    implementation(libs.kotlinx.serialization.json)         // KotlinX JSON Serialiser
}
