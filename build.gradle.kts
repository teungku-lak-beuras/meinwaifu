plugins {
    val agp = "8.13.0"
    val kotlin = "2.0.21"

    id("com.android.application")
        .version(agp)
        .apply(false)
    id("org.jetbrains.kotlin.android")
        .version(kotlin)
        .apply(false)
    id("org.jetbrains.kotlin.jvm")
        .version(kotlin)
        .apply(false)
    id("org.jetbrains.kotlin.plugin.compose")
        .version(kotlin)
        .apply(false)
}
