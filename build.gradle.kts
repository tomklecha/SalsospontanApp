buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.kotlin.tools)
        classpath(libs.kotlin.gradle.plugin)
    }
}

plugins {
    // trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
    alias(libs.plugins.ktlint).apply(false)
    alias(libs.plugins.sqlDelight).apply(false)
    alias(libs.plugins.hilt).apply(false)
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    repositories {
        google()
        mavenCentral()
    }
}
