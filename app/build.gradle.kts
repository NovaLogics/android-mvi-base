import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "novalogics.android.mvibase"
    compileSdk = 34

    defaultConfig {
        applicationId = "novalogics.android.mvibase"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Read from local.properties
        val localProperties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            localProperties.load(FileInputStream(localPropertiesFile))
        }
        val apiKeyExample: String = localProperties.getProperty("API_KEY", "")

        manifestPlaceholders["API_KEY"] = apiKeyExample
        buildConfigField("String", "API_KEY", "\"$apiKeyExample\"")

        // Read from gradle.properties
        buildConfigField("String", "APP_DATABASE_NAME", "\"${project.properties["APP_DATABASE_NAME"]}\"")
        buildConfigField("String", "APP_DATASTORE_FILE_NAME", "\"${project.properties["APP_DATASTORE_FILE_NAME"]}\"")
        buildConfigField("String", "BASE_URL_QUOTES", "\"${project.properties["BASE_URL_QUOTES"]}\"")
        buildConfigField("String", "ENDPOINT_RANDOM_QUOTES", "\"${project.properties["ENDPOINT_RANDOM_QUOTES"]}\"")
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // AndroidX Core and Lifecycle
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.swipe.refresh.layout)
    implementation(libs.androidx.core.splashscreen)

    // Compose UI and Navigation
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.animation)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)

    // Database & DataStore
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.sqlcipher)
    implementation(libs.sqlite.ktx)
    implementation(libs.datastore.preferences)

    // Material and UI Components
    implementation(libs.material)
    implementation(libs.androidx.material3)

    //Coroutines
    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)

    // Networking
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.gson)
    implementation(libs.okHttp)
    implementation(libs.okhttp.logging.interceptor)

    // Dependency Injection
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Image Loading
    implementation(libs.coil)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}
