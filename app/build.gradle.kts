plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
}

android {
    namespace = "com.movieland"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.movieland"
        minSdk = 24
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

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    flavorDimensions += "env"
    productFlavors {
        create("production") {
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"https://api.themoviedb.org/\"",
            )
            buildConfigField(
                type = "String",
                name = "API_KEY",
                value = "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMzJiZWY2N2I1ZDEwZGQ2MTI1ZWMzMTAxODhiYTVjYiIsInN1YiI6IjY2NDIxZTM2YTIyZjExYjAzZjJjYjYwYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.YM4O2LqKntoutt9pEB8XqbF44p5wp5XYHYflWrPBHnU\"",
            )
        }
        create("integration") {
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"https://api.themoviedb.org/\"",
            )
            buildConfigField(
                type = "String",
                name = "API_KEY",
                value = "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMzJiZWY2N2I1ZDEwZGQ2MTI1ZWMzMTAxODhiYTVjYiIsInN1YiI6IjY2NDIxZTM2YTIyZjExYjAzZjJjYjYwYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.YM4O2LqKntoutt9pEB8XqbF44p5wp5XYHYflWrPBHnU\"",
            )
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.coil)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.fragment.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.room.ktx)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.paging.common.android)
    implementation(libs.androidx.leanback.paging)
    implementation(libs.androidx.paging.runtime.ktx)
    testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
    ksp(libs.room.compiler)
    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.koin.android)
    implementation ("com.facebook.shimmer:shimmer:0.5.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    testImplementation(libs.mockk.agent)
    androidTestImplementation(libs.mockk.android)
    testImplementation(libs.coroutine.test)
    testImplementation(libs.turbine)
    testImplementation(libs.core.testing)
}
