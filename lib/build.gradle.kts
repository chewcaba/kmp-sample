import org.jetbrains.kotlin.gradle.tasks.*

val iosFrameworkName = "iosFramework"

plugins {
    id("com.android.library")
    kotlin("multiplatform") version "1.4.0"
    id("kotlin-android-extensions")
    kotlin("native.cocoapods") version "1.4.0"
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

    val iosX64 = iosX64("ios")
    val iosArm64 = iosArm64("iosArm64")

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

    // Create a task to build a fat framework.
//    tasks.create("debugFatFramework", FatFrameworkTask::class) {
//        // The fat framework must have the same base name as the initial frameworks.
//        baseName = iosFrameworkName
//        // The default destination directory is '<build directory>/fat-framework'.
//        destinationDir = buildDir.resolve("fat-framework/debug")
//        // Specify the frameworks to be merged.
//        from(
//            iosX64.binaries.getFramework("DEBUG"),
//            iosArm64.binaries.getFramework("DEBUG")
//        )
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

        val iosArm64Main by getting
        val iosMain by getting {
            iosArm64Main.dependsOn(this)
        }
        val iosTest by getting
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