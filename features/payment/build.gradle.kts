plugins {
    id (Plugins.ANDROID_DYNAMIC_FEATURE)
    id (Plugins.KOTLIN_ANDROID)
    id (Plugins.DAGGER_HILT )
    id (Plugins.NAVIGATION_SAFE_ARGS)
    id (Plugins.KOTLIN_KAPT)
    id (Plugins.KOTLIN_PARCELIZE)
}

android {
    compileSdk = AndroidVersions.COMPILE_SDK
    defaultConfig {
        minSdk = AndroidVersions.MIN_SDK
        /*applicationId = "com.example.android.payment"*/
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
    kapt {
        correctErrorTypes = true
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.APP))

    addDynamicFeatureDependencies()

    implementation(Deps.FRAGMENT_KTX)

    addAndroidUiDependencies()

    addCoroutinesDependency()

    addLifeCycleDependencies()

    implementation(Deps.COIL)
    implementation(Deps.COIL_GIF)

    addNavigationDependencies()

    addUnitTestDependencies()
    addAndroidTestDependencies()
}