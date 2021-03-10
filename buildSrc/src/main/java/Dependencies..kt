const val kotlinVersion = "1.4.10"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "4.1.1"
        const val safeArgsVersion = "2.3.0"
        const val gmsVersion = "4.2.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val safeArgsPlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgsVersion}"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kaptPlugin = "kotlin-kapt"
    const val androidLibrary = "com.android.library"
    const val javaLibraryPlugin = "java-library"
    const val kotlinPlugin = "kotlin"
    const val safeArgs = "androidx.navigation.safeargs.kotlin"
    const val gms = "com.google.gms:google-services:${Versions.gmsVersion}"
    const val gmsPlugin = "com.google.gms.google-services"

}


object AndroidSdk {
    const val minSdk = 21
    const val compileSdk = 29
    const val targetSdk = compileSdk
    const val applicationId = "com.sanaebadi.foursquare"
}

object Libraries {
    private object Versions {
        const val appCompatVersion = "1.2.0"
        const val constraintLayoutVersion = "2.0.0"
        const val ktxVersion = "1.3.0"
        const val materialVersion = "1.3.0-alpha02"
        const val arrowVersion = "1.0.0"
        const val glideVersion = "4.11.0"
        const val locationVersion = "17.0.0"
        const val multiDexVersion = "2.0.1"
        const val annotationVersion = "1.1.0"

    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktxVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val arrow = "com.fernandocejas:arrow:${Versions.arrowVersion}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    const val location = "com.google.android.gms:play-services-location:${Versions.locationVersion}"
    const val multiDex = "androidx.multidex:multidex:${Versions.multiDexVersion}"
    const val annotation = "androidx.annotation:annotation:${Versions.annotationVersion}"


}

object JetPackLibraries {
    private object Versions {
        const val lifeCycleVersion = "2.2.0"
        const val navigationVersion = "2.3.0"
        const val roomVersion = "2.2.5"

    }

    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycleVersion}"
    const val lifecycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycleVersion}"

    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigationUi =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"

    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycleVersion}"
    const val lifecycleReactiveStreams =
        "androidx.lifecycle:lifecycle-reactivestreams:${Versions.lifeCycleVersion}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val roomRxJava = "androidx.room:room-rxjava2:${Versions.roomVersion}"
    const val roomMigration = "androidx.room:room-migration:${Versions.roomVersion}"
}

object TestLibraries {
    private object Versions {
        const val junitVersion = "4.12"
        const val testRunnerVersion = "1.1.0-alpha4"
        const val espressoVersion = "3.1.0-alpha4"
        const val mockitoKotlinVersion = "2.2.0"
    }

    const val junit4 = "junit:junit:${Versions.junitVersion}"
    const val testRunner = "androidx.test:runner:${Versions.testRunnerVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    const val mockitoKotlin =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlinVersion}"
}


object Networking {
    private object Versions {
        const val retrofitVersion = "2.3.0"
        const val rxRetrofitAdapterVersion = retrofitVersion
        const val converterScalarsVersion = "2.5.0"
        const val okHttpVersion = "3.12.3"
        const val gsonVersion = "2.8.5"
    }

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val rxRetrofitAdapter =
        "com.squareup.retrofit2:adapter-rxjava2:${Versions.rxRetrofitAdapterVersion}"

    const val converterScalars =
        "com.squareup.retrofit2:converter-scalars:${Versions.converterScalarsVersion}"
    const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofitVersion}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"
    const val mockServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttpVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
}


object RXLibraries {
    private object Versions {
        const val rxAndroidVersion = "2.0.2"
        const val rxJavaVersion = "2.1.16"
    }

    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
}

object DaggerLib {
    private object Versions {
        const val daggerVersion = "2.25.2"
    }

    const val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    const val daggerProcessor =
        "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"
    const val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"

}
