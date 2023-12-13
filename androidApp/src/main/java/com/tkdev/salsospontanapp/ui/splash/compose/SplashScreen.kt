package com.tkdev.salsospontanapp.ui.splash.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.tkdev.salsospontanapp.ui.splash.SplashEvent
import com.tkdev.salsospontanapp.ui.splash.SplashState

@Composable
fun SplashScreen(
    uiState: SplashState,
    onEvent: (SplashEvent) -> Unit,
    navigateToHome: () -> Unit
) {
    Box() {
        when (uiState.loadingCompleted) {
            false -> {
                onEvent(SplashEvent.PrepopulateData)
            }
            true -> navigateToHome.invoke()
            else -> {
            }
        }
    }
}
