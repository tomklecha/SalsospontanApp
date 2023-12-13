package com.tkdev.salsospontanapp.ui.splash

data class SplashState(
    val isLoading: Boolean = false,
    val loadingCompleted: Boolean? = null,
    val error: String? = null //  error state handling
)
