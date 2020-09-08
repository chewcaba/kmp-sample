package com.chewcaba.lib

import platform.UIKit.UIDevice

actual class Device {
    actual fun platformName(): String {
        return UIDevice.currentDevice.systemName() +
                " " +
                UIDevice.currentDevice.systemVersion
    }
}