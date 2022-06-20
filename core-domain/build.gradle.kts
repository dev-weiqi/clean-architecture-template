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
}

dependencies {
    coreLibraryDesugaring(Dependencies.Libs.desugarJdkLibs)

    implementation(project(":core-data"))
    implementation(project(":core-model"))
    implementation(project(":core-common"))

    testImplementation(project(":core-testing"))

    implementation(Dependencies.Libs.retrofit)
    implementation(Dependencies.Libs.retrofitMoshiConverter)
    implementation(Dependencies.Libs.okhttp)
    implementation(Dependencies.Libs.loggingInterceptor)
    implementation(Dependencies.Libs.coroutinesCore)
    implementation(Dependencies.Libs.hilt)
    kapt(Dependencies.Libs.hiltCompiler)
    implementation(Dependencies.Libs.logcat)
}