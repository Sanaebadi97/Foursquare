plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kaptPlugin)
}

android {
    compileSdkVersion(AndroidSdk.compileSdk)
    defaultConfig {
        minSdkVersion(AndroidSdk.minSdk)
        targetSdkVersion(AndroidSdk.targetSdk)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        
    }
    buildFeatures {
        viewBinding = true

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    // For Kotlin projects
    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        exclude("META-INF/gradle/incremental.annotation.processors")

    }
}


dependencies {
    implementation(project(mapOf("path" to ":data")))
    api(project(mapOf("path" to ":domain")))

    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompat)
    implementation(Libraries.ktxCore)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.material)

    implementation(Libraries.glide)
    implementation(Libraries.glideCompiler)

    implementation(JetPackLibraries.lifecycleViewModel)
    implementation(JetPackLibraries.lifecycleLiveData)
    implementation(JetPackLibraries.lifecycleExtensions)
    implementation(JetPackLibraries.lifecycleReactiveStreams)

    implementation(Networking.retrofit)
    implementation(Networking.gson)

    implementation(JetPackLibraries.navigationFragment)
    implementation(JetPackLibraries.navigationUi)


    implementation(RXLibraries.rxAndroid)
    implementation(RXLibraries.rxJava)

    implementation(DaggerLib.dagger)
    implementation(DaggerLib.daggerSupport)
    kapt(DaggerLib.daggerCompiler)
    kapt(DaggerLib.daggerProcessor)

    implementation(Libraries.annotation)


    implementation(Libraries.location)

    testImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}