package hemel.van.buildlogic

import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

fun configureKotlin(extension: KotlinAndroidProjectExtension) {
    extension.apply {
        compilerOptions {
            jvmTarget.set(meinwaifuJvmTarget)
        }
    }
}
