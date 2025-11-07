plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "hemel.van.meinwaifu"

    compileSdk {
        version = release(36)
    }

    buildFeatures {
        compose = true
    }

    defaultConfig {
        applicationId = "hemel.van.meinwaifu"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// FUCK YOU TOML!!!
//noinspection UseTomlInstead
dependencies {
    implementation("androidx.core:core-ktx:1.17.0")                                                 // AndroidX Core component.
    implementation("androidx.core:core-splashscreen:1.0.1")                                         // AndroidX Core component.

    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")                         // AndroidX test component.
    androidTestImplementation("androidx.test.ext:junit:1.3.0")                                      // AndroidX test component.
    testImplementation("junit:junit:4.13.2")                                                        // AndroidX test component.

    implementation("androidx.navigation:navigation-compose:2.9.5")                                  // AndroidX NavHost support.

    implementation("com.google.android.material:material:1.13.0")                                   // Google Material 3.

    val composeBom = platform("androidx.compose:compose-bom:2025.10.01")                            // Compose B.O.M.
    implementation(composeBom)                                                                      // Compose B.O.M.
    androidTestImplementation(composeBom)                                                           // Compose B.O.M.

    implementation("androidx.compose.material:material-icons-core")                                 // Compose Icon.
    implementation("androidx.compose.material3:material3")                                          // Compose Material 3 components.
    implementation("androidx.compose.material3:material3-window-size-class")                        // Compose WindowSizeClass.

    implementation("androidx.compose.ui:ui-tooling-preview")                                        // Compose preview support in Android Studio.
    debugImplementation("androidx.compose.ui:ui-tooling")                                           // Compose preview support in Android Studio.

    androidTestImplementation("androidx.compose.ui:ui-test-junit4")                                 // Compose UI Test.
    debugImplementation("androidx.compose.ui:ui-test-manifest")                                     // Compose UI Test.

    implementation("androidx.activity:activity-compose:1.11.0")                                     // Compose integration with activity.
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5")                          // Compose integration with view model.
    implementation("androidx.compose.runtime:runtime-livedata")                                     // Compose integration with live data.
}
