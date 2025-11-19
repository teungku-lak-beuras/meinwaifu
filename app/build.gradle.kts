plugins {
    alias(libs.plugins.meinwaifu.android.application)
}

android {
    namespace = "hemel.van.meinwaifu"

    buildFeatures {
        compose = true
    }

    defaultConfig {
        applicationId = "hemel.van.meinwaifu"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":meincore:entity"))
    implementation(project(":meincore:repository"))

    implementation(libs.kotlinx.coroutines.android)                                                 // Kotlin Coroutines.

    implementation(libs.coil.compose)                                                               // Coil Compose.
    implementation(libs.coil.network.okhttp)                                                        // Coil network fetcher.

    implementation(libs.androidx.core.ktx)                                                          // AndroidX Core component.
    implementation(libs.androidx.core.splashscreen)                                                 // AndroidX Core component.

    androidTestImplementation(libs.androidx.espresso.core)                                          // AndroidX test component.
    androidTestImplementation(libs.androidx.junit)                                                  // AndroidX test component.
    testImplementation(libs.junit)                                                                  // AndroidX test component.

    implementation(libs.androidx.navigation.compose)                                                // AndroidX NavHost support.

    implementation(libs.material)                                                                   // Google Material 3.

    androidTestImplementation(platform(libs.androidx.compose.bom))                                  // Compose B.O.M. for Junit, etc.
    implementation(platform(libs.androidx.compose.bom))                                             // Compose B.O.M.

    implementation(libs.androidx.compose.foundation.layout)                                         // Drop shadow support.

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
