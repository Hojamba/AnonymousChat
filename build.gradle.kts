buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.3.10") // Firebase 설정
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20") // Kotlin Gradle Plugin
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
