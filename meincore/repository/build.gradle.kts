plugins {
    alias(libs.plugins.meinwaifu.android.library)
    alias(libs.plugins.meinwaifu.meincore.network)
}

android {
    namespace = "hemel.van.meincore.repository"
}

dependencies {
    implementation(project(":meincore:entity"))
    implementation(project(":meincore:network"))
}
