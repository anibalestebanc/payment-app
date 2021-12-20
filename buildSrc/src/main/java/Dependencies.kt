object Deps {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${PluginVersion.KOTLIN}"
    const val KOTLIN_CORE_KTX = "androidx.core:core-ktx:${Version.KOTLIN_CORE}"

    const val APP_COMPAT = "androidx.appcompat:appcompat:${Version.APP_COMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Version.FRAGMENT}"

    const val COIL = "io.coil-kt:coil:${Version.COIL}"
    const val COIL_GIF = "io.coil-kt:coil-gif:${Version.COIL}"

    const val HILT = "com.google.dagger:hilt-android:${Version.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${Version.HILT}"
    const val DAGGER = "com.google.dagger:dagger:${Version.HILT}"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Version.HILT}"

    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINES}"
    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINES}"

    const val LIFECYCLE_VIEW_MODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFE_CYCLE}"
    const val LIFECYCLE_LIVE_DATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.LIFE_CYCLE}"
    const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LIFE_CYCLE}"
    const val LIFECYCLE_COMPILER = "androidx.lifecycle:lifecycle-compiler:${Version.LIFE_CYCLE}"


    const val NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:${Version.NAVIGATION}"
    const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Version.NAVIGATION}"
    const val NAVIGATION_DYNAMIC_FEATURE = "androidx.navigation:navigation-dynamic-features-fragment:${Version.NAVIGATION}"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.RETROFIT}"
    const val RETROFIT_CONVERTOR = "com.squareup.retrofit2:converter-moshi:${Version.RETROFIT}"
    const val MOSHI_ADAPTER = "com.squareup.moshi:moshi-adapters:${Version.MOSHI}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Version.OKHTTP}"
    const val OKHTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${Version.OKHTTP}"
}

object TestDeps{
    const val JUNIT = "junit:junit:${TestVersion.JUNIT}"
    const val JUNIT_EXT = "androidx.test.ext:junit:${TestVersion.JUNIT_EXT}"
    const val MOCKITO_KOTLIN = "org.mockito.kotlin:mockito-kotlin:${TestVersion.MOCKITO}"
    const val MOCKITO_INLINE = "org.mockito:mockito-inline:${TestVersion.MOCKITO_INLINE}"
    const val ARCH_CORE = "androidx.arch.core:core-testing:${TestVersion.ARCH_CORE}"
    const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.COROUTINES}"
}