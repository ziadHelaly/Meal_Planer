plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "eg.edu.iti.mealplaner"
    compileSdk = 35

    defaultConfig {
        applicationId = "eg.edu.iti.mealplaner"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        viewBinding=true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //lottie animation
    implementation (libs.lottie)

    //Retrofit
    implementation(libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.okhttp)

    //room
    implementation (libs.room.runtime)
    annotationProcessor (libs.room.compiler)

    //Glide
    implementation (libs.glide)
    annotationProcessor(libs.compiler)

}