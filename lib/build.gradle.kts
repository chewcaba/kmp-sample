import org.jetbrains.kotlin.gradle.tasks.*

val iosFrameworkName = "SharedFramework"

plugins {
    kotlin("multiplatform") version "1.4.0"
    id("com.android.library")
    id("kotlin-android-extensions")
    id("maven-publish")
    kotlin("native.cocoapods") version "1.4.0"
}
group = "com.chewcaba"
version = "1.0"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
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
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

kotlin {
    android() {
        publishAllLibraryVariants()
    }

    val iosX64 = iosX64("ios")
    val iosArm64 = iosArm64("iosArm64")

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

        val iosMain by getting {
            dependsOn(commonMain)
        }
        val iosArm64Main by getting {
            dependsOn(iosMain)
        }
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

publishing {
    repositories {
        mavenLocal()
        maven {
            name = "KmpSample"
            url = uri("file://${buildDir}/repo")
        }
    }
}