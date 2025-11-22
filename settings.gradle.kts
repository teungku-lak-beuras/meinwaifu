pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    includeBuild("build-logic")
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Mein Waifu"

include(":app")
include(":meincore:entity")
include(":meincore:local")
include(":meincore:network")
include(":meincore:repository")
