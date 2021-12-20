import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler


fun DependencyHandler.addAppModuleDependencies() {
    implementation(Deps.HILT)
    kapt(Deps.HILT_COMPILER)

    implementation(Deps.KOTLIN_CORE_KTX)
    implementation(Deps.APP_COMPAT)
}

fun DependencyHandler.addAndroidLibraryDependencies() {
    implementation(Deps.KOTLIN_CORE_KTX)
    implementation(Deps.APP_COMPAT)
}

fun DependencyHandler.addDynamicFeatureDependencies() {
    implementation(Deps.HILT)
    kapt(Deps.HILT_COMPILER)

    implementation(Deps.DAGGER)
    kapt(Deps.DAGGER_COMPILER)

    implementation(Deps.KOTLIN_CORE_KTX)
    implementation(Deps.APP_COMPAT)
}

fun DependencyHandler.addCoroutinesDependency(){
    implementation(Deps.COROUTINES)
    implementation(Deps.COROUTINES_CORE)
}

fun DependencyHandler.addAndroidUiDependencies() {
    implementation(Deps.MATERIAL)
    implementation(Deps.CONSTRAINT_LAYOUT)
}

fun DependencyHandler.addLifeCycleDependencies() {
    implementation(Deps.LIFECYCLE_VIEW_MODEL_KTX)
    implementation(Deps.LIFECYCLE_LIVE_DATA_KTX)
    implementation(Deps.LIFECYCLE_RUNTIME_KTX)
    kapt(Deps.LIFECYCLE_COMPILER)
}

fun DependencyHandler.addNavigationDependencies() {
    implementation(Deps.NAVIGATION_FRAGMENT_KTX)
    implementation(Deps.NAVIGATION_UI_KTX)
    implementation(Deps.NAVIGATION_DYNAMIC_FEATURE)
}

fun DependencyHandler.addUnitTestDependencies() {
    testImplementation(TestDeps.JUNIT)
    testImplementation(TestDeps.MOCKITO_KOTLIN)
    testImplementation(TestDeps.MOCKITO_INLINE)

    testImplementation(TestDeps.ARCH_CORE)
    testImplementation(TestDeps.COROUTINES_TEST)
}

fun DependencyHandler.addAndroidTestDependencies() {
    androidTestImplementation(TestDeps.JUNIT_EXT)
}

private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

private fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

private fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)


private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)