package dev.weiqi

object Dependencies {
    object Version {
        const val minSdk = 23
        const val compileSdk = 32
        const val targetSdk = 32
        const val versionCode = 1
        const val versionName = "1.0.0"
        const val jvmTarget = "1.8"
    }

    object Libs {
        private const val lifecycleVersion = "2.4.0"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val compiler = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"
        const val process = "androidx.lifecycle:lifecycle-process:$lifecycleVersion"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"

        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val appCompat = "androidx.appcompat:appcompat:1.4.2"
        const val annotation = "androidx.annotation:annotation:1.2.0"

        private const val fragmentVersion = "1.3.3"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:$fragmentVersion"

        const val material = "com.google.android.material:material:1.3.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
        const val coordinatorLayout = "androidx.coordinatorlayout:coordinatorlayout:1.2.0"

        private const val coroutinesVersion = "1.6.2"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutinesVersion}"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${coroutinesVersion}"

        private const val retrofitVersion = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"

        private const val okhttpVersion = "4.9.0"
        const val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"

        const val logcat = "com.squareup.logcat:logcat:0.1"

        private const val hiltVersion = "2.41"
        const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
        const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$hiltVersion"

        const val moshi = "com.squareup.moshi:moshi:1.12.0"
        const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:1.1.5"

        const val junit = "junit:junit:4.13"
        const val mockk = "io.mockk:mockk:1.12.0"
        const val mockkAndroid = "io.mockk:mockk-android:1.12.0"
        const val turbine = "app.cash.turbine:turbine:0.7.0"
    }
}