plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "hemel.van.meincore.network"

    compileSdk {
        version = release(36)
    }

    buildFeatures {
        buildConfig = true
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
    implementation("com.squareup.retrofit2:retrofit:2.9.0")                                         // Retrofit.
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")                                   // Retrofit serialiser.
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")                               // Logging interceptor.
}