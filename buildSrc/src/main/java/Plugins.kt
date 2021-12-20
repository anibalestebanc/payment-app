object Plugins {
    const val CLASSPATH_GRADLE = "com.android.tools.build:gradle:${PluginVersion.GRADLE}"
    const val CLASSPATH_HILT = "com.google.dagger:hilt-android-gradle-plugin:${Version.HILT}"
    const val CLASSPATH_NAV_SAFE_ARGS =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${PluginVersion.NAV_SAFE_ARGS}"

    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_LIBRARY = "com.android.library"
    const val ANDROID_DYNAMIC_FEATURE = "com.android.dynamic-feature"

    const val KOTLIN_ANDROID = "kotlin-android"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val KOTLIN_PARCELIZE = "kotlin-parcelize"
    const val NAVIGATION_SAFE_ARGS = "androidx.navigation.safeargs.kotlin"
    const val DAGGER_HILT = "dagger.hilt.android.plugin"
}