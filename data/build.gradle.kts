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
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "API_BASE_URL", "\"https://api.foursquare.com/v2/\"")
        buildConfigField(
            "String",
            "API_CLIENT_SECRET",
            "\"S0QW1FG02NARG34KDTITIEDY0BJ5FWCM1AJI55JFRHWCYLGG\""
        )
        buildConfigField(
            "String",
            "API_CLIENT_ID",
            "\"UI0ZJ4OV223EQMU2OYU4R21VY02SNRPZTCGTYVBAFWWYELA5\""
        )
        buildConfigField("String", "API_VERSION", "\"20200319\"")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.incremental"] = "true"
            }
        }
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


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    // For Kotlin projects
    kotlinOptions {
        jvmTarget = "1.8"
    }

}


dependencies {
    implementation(project(mapOf("path" to ":domain")))

    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.ktxCore)

    implementation(DaggerLib.dagger)
    implementation(DaggerLib.daggerSupport)
    kapt(DaggerLib.daggerCompiler)
    kapt(DaggerLib.daggerProcessor)

    implementation(Networking.retrofit)
    implementation(Networking.rxRetrofitAdapter)
    implementation(Networking.converterScalars)
    implementation(Networking.converterGson)

    implementation(Networking.okHttp)
    implementation(Networking.logging)
    implementation(Networking.mockServer)

    implementation(RXLibraries.rxAndroid)
    implementation(RXLibraries.rxJava)

    implementation(JetPackLibraries.roomRuntime)
    implementation(JetPackLibraries.roomRxJava)
    implementation(JetPackLibraries.roomCompiler)

    testImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
    testImplementation(TestLibraries.mockitoKotlin)


}