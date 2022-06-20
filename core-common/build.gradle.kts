import dev.weiqi.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Dependencies.Version.compileSdk

    defaultConfig {
        minSdk = Dependencies.Version.minSdk
        targetSdk = Dependencies.Version.targetSdk

        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true

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
    coreLibraryDesugaring(Dependencies.Libs.desugarJdkLibs)

    implementation(Dependencies.Libs.coreKtx)
    implementation(Dependencies.Libs.appCompat)
    implementation(Dependencies.Libs.runtime)

    implementation(Dependencies.Libs.hilt)
    kapt(Dependencies.Libs.hiltCompiler)
    implementation(Dependencies.Libs.logcat)
}