plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.example.tapYouLegacy"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tapYoulegacy"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":common-ui"))
    implementation(project(":common-util"))
    implementation(project(":entity"))
    implementation(project(":api"))

    // Base
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.material)

    // Moxy
    implementation(libs.moxy.core)
    implementation(libs.moxy.ktx)
    implementation(libs.moxy.android)
    implementation(libs.moxy.androidx)
    implementation(libs.moxy.compiler)

    // Rx
    implementation(libs.rx.android)
    implementation(libs.rx.java)

    // Cicerone
    implementation(libs.cicerone)

    // Dagger
    implementation(libs.dagger.core)
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support)
    kapt(libs.dagger.compiler)
    kapt(libs.dagger.android.processor)

    // Retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.retrofit.rxjava.adapter)
    implementation(libs.retrofit.int)
    implementation(libs.gson)
}

kapt {
    correctErrorTypes = true
}