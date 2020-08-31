import org.jetbrains.kotlin.gradle.tasks.*

val frameworkName = "iosFramework"

plugins {
    id("com.android.library")
    kotlin("multiplatform") version "1.4.0"
    id("kotlin-android-extensions")
    id("maven-publish")
}
group = "com.chewcaba"
version = "1.0"

publishing {
    repositories {
        mavenLocal()
        maven {
            name = "KmpSample"
            url = uri("file://${buildDir}/repo")
        }
    }
}

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}
kotlin {
    android() {
        publishLibraryVariants("release", "debug")
    }
    iosX64("ios") {
        binaries {
            framework {
                baseName = "library"
            }
        }
    }
//    val iosArm64 = iosArm64("iosArm64")
//    val iosX64 = iosX64("iosX64")
//    configure(listOf(iosArm64, iosX64)) {
//        binaries.framework {
//            baseName = "iosKmpFramework"
//        }
//    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.core:core-ktx:1.2.0")
            }
        }
        val androidTest by getting
        val iosMain by getting
        val iosTest by getting
//        val iosArm64Main by getting
//        val iosX64Main by getting
//        val iosMain by creating
//        configure(listOf(iosArm64Main, iosX64Main)) {
//            dependsOn(iosMain)
//        }
//        val iosTest by creating



//        tasks.register<FatFrameworkTask>("debugFatFramework") {
//            baseName = frameworkName
//            group = "Universal framework"
//            description = "Builds a universal (fat) debug framework"
//
//            from(iosX64.binaries.getFramework("DEBUG"))
//        }
//
//        tasks.register<FatFrameworkTask>("releaseFatFramework") {
//            baseName = frameworkName
//            group = "Universal framework"
//            description = "Builds a universal (release) debug framework"
//
//            from(iosArm64.binaries.getFramework("RELEASE"), iosArm32.binaries.getFramework("RELEASE"))
//        }
    }
}
android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}