plugins {
    alias(libs.plugins.meinwaifu.android.library)
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
    implementation(libs.retrofit)                   // Retrofit.
    implementation(libs.converter.gson)             // Retrofit serialiser.
    implementation(libs.logging.interceptor)        // Logging interceptor.
}
