# Kotlin Multiplatform Sample Project

A cross platform mobile project utilizing Kotlin Multiplatform. Created a single library that can be shared by both iOS and Android using pure Kotlin code. Front-ends for iOS and Android are developed using Swift UI and Jetpack Compose respectively. The project can be published locally to Maven and CocoaPods.

## Usage

Go to the root folder of the `lib` project. To build the project run the following, or run in IntelliJ:
```
$ ./gradlew build
``` 

To publish to your local maven installation run the following command
```
$ ./gradlew publishToMavenLocal
```

To publish to CocoaPods locally, run the following command
```
$ ./gradlew podspec
```
