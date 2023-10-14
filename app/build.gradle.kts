@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    kotlin("kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.faezolfp.githubapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.faezolfp.githubapp"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures.viewBinding = true
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //androidX
    implementation(libs.livedata.ktx)
    implementation(libs.activityktx)
    implementation(libs.fragmentktx)
    //hilt
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)
    //room
//    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)
    //kotlin corountinus
    implementation(libs.kotlin.corountine.core)
    implementation(libs.kotlin.corountine)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.convert.gson)
    implementation(libs.loging.interceptor)
    //image
    implementation(libs.circle.image)
    implementation(libs.glide.image)

}