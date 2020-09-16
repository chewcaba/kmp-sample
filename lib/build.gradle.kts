import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

val iosFrameworkName = "SharedFramework"

plugins {
    kotlin("multiplatform") version "1.4.0"
    id("com.android.library")
    id("kotlin-android-extensions")
    id("maven-publish")
    kotlin("native.cocoapods") version "1.4.0"
}
group = "com.chewcaba"
version = "1.0.1"

publishing {
    repositories {
        mavenLocal()
    }
}

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}

kotlin {
    android {
        publishAllLibraryVariants()
    }
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {}

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
    }

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "Kotlin/Native & Multiplatform Sample Module"
        homepage = "https://kotlinlang.org/docs/reference/native/cocoapods.html"
        ios.deploymentTarget = "9.0"
        // You can change the name of the produced framework.
        // By default, it is the name of the Gradle project.
        frameworkName = iosFrameworkName
        podfile = project.file("../ios/Podfile")
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