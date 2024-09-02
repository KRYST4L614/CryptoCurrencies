plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.googleDevtoolsKsp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.cryptocurrencies"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cryptocurrencies"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.com.google.dagger)
    ksp(libs.com.google.dagger.compiler)
    implementation(libs.com.squareup.retrofit2.gson)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.io.coil)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.org.junit.jupiter.api)
    testImplementation(libs.org.junit.jupiter.params)
    testRuntimeOnly(libs.org.junit.jupiter.engine)
    testImplementation(libs.org.mockito.kotlin)
    testImplementation(libs.org.jetbrains.kotlinx.coroutines.test)
}