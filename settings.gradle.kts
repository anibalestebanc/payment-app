rootProject.name = "PaymentApp"
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
include(
    ":app",
    ":core:network",
    ":common:utils",
    ":core:navigation",
    ":features:payment"
)