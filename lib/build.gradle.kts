import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

val iosFrameworkName = "SharedFramework"

object Versions {
    const val androidCoreKtx = "1.2.0"
    const val coroutine = "1.3.9"
    const val kotlin = "1.4.0"
    const val ktor = "1.4.0"
    const val serialization = "1.0.0-RC"
    const val sqlDelight = "1.4.1"
}

plugins {
    id("com.android.library") version "1.4.0"
    kotlin("multiplatform") version "1.4.0"
    kotlin("plugin.serialization") version "1.4.0"
    id("kotlin-android-extensions")
    id("maven-publish")
    id("com.squareup.sqldelight") version "1.4.1"
    kotlin("native.cocoapods") version "1.4.0"
}
group = "com.aj"
version = "1.0.2"

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

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            java.srcDirs("src/androidMain/kotlin")
            res.srcDirs("src/androidMain/resources")
            this.setRoot("src/androidMain")
        }
    }
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
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:${Versions.ktor}")
                implementation("io.ktor:ktor-client-serialization:${Versions.ktor}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.serialization}")
                implementation("com.squareup.sqldelight:runtime:${Versions.sqlDelight}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidDebug by getting
        val androidRelease by getting
        val androidMain by getting {
            this.dependsOn(commonMain)
            androidDebug.dependsOn(this)
            androidRelease.dependsOn(this)
            dependencies {
                implementation("androidx.core:core-ktx:${Versions.androidCoreKtx}")
                implementation("com.squareup.sqldelight:android-driver:${Versions.sqlDelight}")
                implementation("io.ktor:ktor-client-android:${Versions.ktor}")
            }
        }
        val androidTest by getting
        val iosMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:${Versions.sqlDelight}")
                implementation("io.ktor:ktor-client-ios:${Versions.ktor}")
            }
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

//sqldelight {
//    database("AppDatabase") {
//        packageName = "com.aj.lib.data.db"
//    }
//}