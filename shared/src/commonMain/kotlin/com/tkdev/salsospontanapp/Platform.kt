package com.tkdev.salsospontanapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
