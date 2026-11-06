import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

// Android Studio ya crea local.properties con la ubicación de tu SDK.
// Agregá ahí otra línea: TMDB_TOKEN=TU_TOKEN_DE_LECTURA
val localProperties = Properties()
val localFile = rootProject.file("local.properties")
if (localFile.exists()) {
    localFile.inputStream().use { localProperties.load(it) }
}
val tmdbToken = localProperties.getProperty("TMDB_TOKEN", "")
val escapedToken = tmdbToken.replace("\\", "\\\\").replace("\"", "\\\"")

android {
    namespace = "ar.edu.unicen.loverboxd"
    compileSdk = 36

    defaultConfig {
        applicationId = "ar.edu.unicen.loverboxd"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        // Genera BuildConfig.TMDB_TOKEN para leer el token desde Kotlin.
        buildConfigField("String", "TMDB_TOKEN", "\"$escapedToken\"")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2025.09.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.11.0")

    // ViewModel + StateFlow: el estado llega a Compose de forma segura.
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.9.4")

    // Navegación entre las dos pantallas.
    implementation("androidx.navigation:navigation-compose:2.9.5")

    // API HTTP y conversión de respuestas JSON a objetos Kotlin.
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // Descarga y muestra los posters desde una URL.
    implementation("io.coil-kt:coil-compose:2.7.0")
}
