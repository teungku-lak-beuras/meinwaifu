plugins {
    alias(libs.plugins.meinwaifu.android.library)
}

android {
    namespace = "hemel.van.meincore.repository"
}

dependencies {
    implementation(project(":meincore:entity"))
    implementation(project(":meincore:network"))

    implementation(libs.kotlinx.coroutines.android) // Kotlin Coroutines.
    implementation(libs.retrofit)                   // Retrofit.
    implementation(libs.converter.gson)             // Retrofit serialiser.
    implementation(libs.logging.interceptor)        // Logging interceptor.
}
