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
        multiDexEnabled = true

        minSdk = Dependencies.Version.minSdk
        targetSdk = Dependencies.Version.targetSdk

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

    implementation(project(":core-common"))
    implementation(project(":core-model"))
    implementation(project(":core-domain"))

    testImplementation(project(":core-testing"))

    implementation(Dependencies.Libs.coreKtx)
    implementation(Dependencies.Libs.appCompat)
    implementation(Dependencies.Libs.material)
    implementation(Dependencies.Libs.coordinatorLayout)
    implementation(Dependencies.Libs.viewpager2)
    implementation(Dependencies.Libs.fragmentKtx)
    implementation(Dependencies.Libs.viewModel)
    implementation(Dependencies.Libs.hilt)
    kapt(Dependencies.Libs.hiltCompiler)
    implementation(Dependencies.Libs.coroutinesCore)
    implementation(Dependencies.Libs.coroutinesAndroid)
    implementation(Dependencies.Libs.logcat)
}