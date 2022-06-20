pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "CourseSchedule"
include(":app")
include(":core-common")
include(":core-domain")
include(":core-data")
include(":core-model")
include(":core-testing")
include(":ui-schedule")
