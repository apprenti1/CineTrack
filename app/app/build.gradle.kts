plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "fr.hainu.cinetrack"
    compileSdk = 36

    defaultConfig {
        applicationId = "fr.hainu.cinetrack"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Charger les variables depuis local.properties
        val properties = com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir, providers)

        buildConfigField("String", "TMDB_API_KEY", "\"${properties.getProperty("tmdb.api.key", "")}\"")
        buildConfigField("String", "TMDB_BASE_URL", "\"${properties.getProperty("tmdb.base.url", "https://api.themoviedb.org/3/")}\"")
        buildConfigField("String", "TMDB_IMAGE_URL", "\"${properties.getProperty("tmdb.image.url", "https://image.tmdb.org/t/p/")}\"")
        buildConfigField("String", "TMDB_IMAGE_POSTERSIZE", "\"${properties.getProperty("tmdb.image.postersize", "w500")}\"")
        buildConfigField("String", "TMDB_IMAGE_BACKDROPSIZE", "\"${properties.getProperty("tmdb.image.backdropsize", "w500")}\"")
        buildConfigField("String", "CINETRACK_API_URL", "\"${properties.getProperty("cinetrack.api.url", "http://10.0.2.2:3000")}\"")
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
}