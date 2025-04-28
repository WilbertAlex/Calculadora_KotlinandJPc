plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt") //Agregado
    id("dagger.hilt.android.plugin") //Agregado
}

android {
    namespace = "pe.edu.upeu.proyecto_turismo"
    compileSdk = 35

    defaultConfig {
        applicationId = "pe.edu.upeu.proyecto_turismo"
        minSdk = 28
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //Navegacion
    implementation("androidx.navigation:navigation-compose:2.8.9")
//Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.11.0")
//Formularios
    implementation ("com.github.k0shk0sh:compose-easyforms:0.2.0")
//Agregados Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.52") //old 2.47
    kapt ("com.google.dagger:hilt-compiler:2.52") //old 2.47
//Agregado Dagger - Hilt Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0") //old 1.0.0
    implementation("com.valentinilk.shimmer:compose-shimmer:1.3.2") //old 1.0.5, 1.3.1
//implementation ("io.coil-kt:coil-compose:2.7.0") //old 2.4.0
    implementation("io.coil-kt.coil3:coil-compose:3.1.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.1.0")
//Agregado LiveData compose//implementation ("androidx.compose.ui:uitooling")
    implementation ("androidx.compose.foundation:foundation")
    implementation ("androidx.compose.runtime:runtime-livedata")
//App Compact para detectar modo dia noche
    val appcompat_version = "1.7.0" //old 1.6.1
    implementation("androidx.appcompat:appcompat:$appcompat_version")//Agrega  do recien
    tasks.withType<JavaCompile>().configureEach {
        options.compilerArgs.add("-Xlint:deprecation")

        implementation("androidx.navigation:navigation-compose:2.7.3")
    }
    implementation("androidx.compose.ui:ui:1.4.0") // O la versión más reciente
    implementation("androidx.compose.material3:material3:1.0.0") // Para Material3
    implementation("androidx.compose.foundation:foundation:1.4.0") // Para las bases de diseño
    implementation("androidx.compose.material:material:1.4.0") // Para Material components
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1") // Requiere Lifecycle para Compose
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("androidx.compose.material3:material3:1.0.0")

}