package com.tkdev.salsospontanapp

class IOSPlatform : Platform {
    override val name: String = "iOS"
// TODO cocoapods UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()
