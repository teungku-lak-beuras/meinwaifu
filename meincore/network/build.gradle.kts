plugins {
    alias(libs.plugins.meinwaifu.android.library)
    alias(libs.plugins.meinwaifu.meincore.network)
}

android {
    namespace = "hemel.van.meincore.network"

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
