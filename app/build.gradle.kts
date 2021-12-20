import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.NAVIGATION_SAFE_ARGS)
    id(Plugins.DAGGER_HILT)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.KOTLIN_PARCELIZE)
}

val apiKey : String = gradleLocalProperties(rootDir)
    .getProperty("API_KEY")


android {
    compileSdk = AndroidVersions.COMPILE_SDK
    defaultConfig {
        applicationId = AndroidVersions.APPLICATION_ID
        minSdk = AndroidVersions.MIN_SDK
        targetSdk = AndroidVersions.TARGET_SDK
        versionCode = AndroidVersions.VERSION_CODE
        versionName = AndroidVersions.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_KEY", apiKey)
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }

    kapt {
        correctErrorTypes = true
    }

    dynamicFeatures.apply {
        add(Modules.DynamicFeatures.PAYMENT)
    }
}

dependencies {
    addAppModuleDependencies()
    addAndroidUiDependencies()
    addNavigationDependencies()

    addCoroutinesDependency()

    addLifeCycleDependencies()

    api(project(Modules.AndroidLibraries.NETWORK))
    api(project(Modules.AndroidLibraries.NAVIGATION))
    api(project(Modules.AndroidLibraries.UTILS))
}