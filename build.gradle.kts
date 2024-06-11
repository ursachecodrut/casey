// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Android
    id("com.android.application") version "8.2.1" apply false

    // Kotlin Android
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false

    // Google Services
    id("com.google.gms.google-services") version "4.4.0" apply false

    // Dagger Hilt
    id("com.google.dagger.hilt.android") version "2.48" apply false

    // Kotlin Symbol Processing
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false

    // Firebase Performance Monitoring
    id("com.google.firebase.firebase-perf") version "1.4.2" apply false
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}