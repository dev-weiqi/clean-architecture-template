import dev.weiqi.Dependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Dependencies.Version.compileSdk

    defaultConfig {
        applicationId = "dev.weiqi.courseschedule"
        minSdk = Dependencies.Version.minSdk
        targetSdk = Dependencies.Version.targetSdk
        versionCode = Dependencies.Version.versionCode
        versionName = Dependencies.Version.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Dependencies.Version.jvmTarget
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":ui-schedule"))

    implementation(Dependencies.Libs.coreKtx)
    implementation(Dependencies.Libs.appCompat)
    implementation(Dependencies.Libs.material)
    implementation(Dependencies.Libs.constraintLayout)
    implementation(Dependencies.Libs.logcat)
    implementation(Dependencies.Libs.hilt)
    kapt(Dependencies.Libs.hiltCompiler)
}