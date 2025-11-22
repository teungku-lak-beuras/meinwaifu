plugins {
    alias(libs.plugins.meinwaifu.android.library)
    alias(libs.plugins.meinwaifu.meincore.local)
    alias(libs.plugins.meinwaifu.meincore.network)
}

android {
    namespace = "hemel.van.meincore.repository"
}

dependencies {
    implementation(project(":meincore:entity"))
    implementation(project(":meincore:local"))
    implementation(project(":meincore:network"))
}
