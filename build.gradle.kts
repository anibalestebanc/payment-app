buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Plugins.CLASSPATH_GRADLE)
        classpath(kotlin("gradle-plugin", version = PluginVersion.KOTLIN))
        classpath(Plugins.CLASSPATH_NAV_SAFE_ARGS)
        classpath(Plugins.CLASSPATH_HILT)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}