plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.parcelize)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.example.tapYouTT"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tapYouTT"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

    }

    flavorDimensions += "environment"

    productFlavors {
        create("dev") {

            buildConfigField("String", "BASE_URL", "\"https://hr-challenge.dev.tapyou.com/\"")
        }
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

    // Base
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    // Kotlin
    implementation(libs.kotlin.stdlib)

    // Moxy
    implementation(libs.moxy.core)
    implementation(libs.moxy.ktx)
    implementation(libs.moxy.android)
    implementation(libs.moxy.androidx)
    implementation(libs.moxy.compiler)

    // Retrofit
    implementation(libs.gson)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.retrofit.rxjava.adapter)
    implementation(libs.retrofit.int)

    // Rx
    implementation(libs.rx.android)
    implementation(libs.rx.java)

    // Cicerone
    implementation(libs.cicerone)

    // Timber
    implementation(libs.timber)

    // Dagger
    implementation(libs.dagger.core)
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support)
    kapt(libs.dagger.compiler)
    kapt(libs.dagger.android.processor)
}

kapt {
    correctErrorTypes = true
}