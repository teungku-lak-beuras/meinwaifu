plugins {
    alias(libs.plugins.meinwaifu.android.library)
}

android {
    namespace = "hemel.van.meincore.repository"
}

dependencies {
    implementation(project(":meincore:entity"))
    implementation(project(":meincore:network"))

    implementation(libs.ktor.client.core)                   // Ktor Core
    implementation(libs.ktor.client.cio)                    // Ktor Coroutine-based IO
    implementation(libs.ktor.client.logging)                // Ktor Logger
    implementation(libs.ktor.client.content.negotitaion)    // Ktor Content Negotiation
    implementation(libs.ktor.serialization.kotlinx.json)    // Ktor KotlinX JSON Serialiser support
    implementation(libs.kotlinx.serialization.json)         // KotlinX JSON Serialiser
}
