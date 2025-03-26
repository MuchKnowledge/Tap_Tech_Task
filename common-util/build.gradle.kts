plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.commonUtil"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        targetSdk = 35
        buildConfigField("String", "BASE_URL", "\"https://hr-challenge.dev.tapyou.com/\"")
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