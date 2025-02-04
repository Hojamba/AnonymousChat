pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AnonymousChat"
include(":app")

// ✅ Java & Kotlin JVM 버전 일치시키기
val jvmTargetVersion = "17"  // 또는 11 사용 가능

tasks.withType<JavaCompile> {
    sourceCompatibility = jvmTargetVersion
    targetCompatibility = jvmTargetVersion
}
