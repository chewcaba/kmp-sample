package com.aj.lib

import io.ktor.client.*

actual class Device {
    actual fun platformName(): String {
        return "Android"
    }
}