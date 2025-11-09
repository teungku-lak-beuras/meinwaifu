plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose)
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
}

dependencies {
    implementation(libs.androidx.core.ktx)                                                          // AndroidX Core component.
    implementation(libs.androidx.core.splashscreen)                                                 // AndroidX Core component.

    androidTestImplementation(libs.androidx.espresso.core)                                          // AndroidX test component.
    androidTestImplementation(libs.androidx.junit)                                                  // AndroidX test component.
    testImplementation(libs.junit)                                                                  // AndroidX test component.

    implementation(libs.androidx.navigation.compose)                                                // AndroidX NavHost support.

    implementation(libs.material)                                                                   // Google Material 3.

    androidTestImplementation(platform(libs.androidx.compose.bom))                                  // Compose B.O.M.

    implementation(libs.androidx.compose.material.icons.core)                                       // Compose Icon.
    implementation(libs.androidx.compose.material3)                                                 // Compose Material 3 components.
    implementation(libs.androidx.compose.material3.window.size.class1)                              // Compose WindowSizeClass.

    implementation(libs.androidx.compose.ui.tooling.preview)                                        // Compose preview support in Android Studio.
    debugImplementation(libs.androidx.compose.ui.tooling)                                           // Compose preview support in Android Studio.

    androidTestImplementation(libs.androidx.compose.ui.test.junit4)                                 // Compose UI Test.
    debugImplementation(libs.androidx.compose.ui.test.manifest)                                     // Compose UI Test.

    implementation(libs.androidx.activity.compose)                                                  // Compose integration with activity.
    implementation(libs.androidx.lifecycle.viewmodel.compose)                                       // Compose integration with view model.
    implementation(libs.androidx.compose.runtime.livedata)                                          // Compose integration with live data.
}
