plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    alias(libs.plugins.hilt)
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.zekierciyas.github_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zekierciyas.github_app"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        enable = true
    }
}

dependencies {

    //Core, Androidx
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    //Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Hilt, Dependency Injection
    implementation(libs.hilt)
    kapt(libs.hiltCompiler)

    //Navigation Component
    implementation(libs.navigation.ui.ktx)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.testing)

    //Room, Caching
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    implementation ("androidx.room:room-runtime:2.6.1")

    //Retrofit, Networking
    implementation(libs.retrofit)
    implementation(libs.retrofit.mock)
    implementation(libs.converter.gson)
    implementation(libs.gson)
    implementation(libs.interceptor)

    //Coroutine, Lightweight Threading
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    //Glide, Image Loading
    implementation(libs.glide.lib)

}