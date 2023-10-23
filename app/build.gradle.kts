plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.org.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
    id(libs.plugins.secrets.get().pluginId)
    id(libs.plugins.protobuf.get().pluginId)
    kotlin("kapt")
}

android {
    namespace = "com.sunit.news"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sunit.news"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file(project.properties["RELEASE_STORE_FILE"] as String)
            storePassword = project.properties["RELEASE_STORE_PASSWORD"] as String
            keyAlias = project.properties["RELEASE_KEY_ALIAS"] as String
            keyPassword = project.properties["RELEASE_KEY_PASSWORD"] as String
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    // Specifies one flavor dimension.
    flavorDimensions += "environment"
    productFlavors {
        create("mock") {
            dimension = "environment"
            applicationIdSuffix = ".mock"
            versionNameSuffix = "-mock"
        }
        create("prod") {
            dimension = "environment"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    // Add this postBuild task to print the values
    afterEvaluate {
        tasks.create("printSigningConfig") {
            doLast {
                println("RELEASE_STORE_FILE=" + signingConfigs.getByName("release").storeFile)
                println("RELEASE_STORE_PASSWORD=" + signingConfigs.getByName("release").storePassword)
                println("RELEASE_KEY_ALIAS=" + signingConfigs.getByName("release").keyAlias)
                println("RELEASE_KEY_PASSWORD=" + signingConfigs.getByName("release").keyPassword)
            }
        }
    }
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.runtime.livedata)

    debugImplementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.util)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.metrics)
    implementation(libs.androidx.tracing.ktx)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.window.manager)
    implementation(libs.androidx.profileinstaller)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.ext.compiler)

    // Networking
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)

    // Room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    // Datastore
    implementation(libs.androidx.dataStore.core)
    implementation(libs.protobuf.kotlin.lite)

    // Work Manager
    implementation(libs.androidx.work.ktx)
    implementation(libs.hilt.ext.work)

    // Coil
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
    implementation(libs.coil.kt.compose)

    // Browser
    implementation(libs.androidx.browser)

    // Logging
    implementation(libs.timber)

    // Testing
    testImplementation(libs.junit4)
    kaptTest(libs.hilt.compiler)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.runner)

}
