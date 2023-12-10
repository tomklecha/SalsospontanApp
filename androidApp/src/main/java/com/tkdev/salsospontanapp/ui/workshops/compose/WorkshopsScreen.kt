package com.tkdev.salsospontanapp.ui.workshops.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tkdev.salsospontanapp.ui.ComposableTestContent
import com.tkdev.salsospontanapp.ui.workshops.FullWorkshopState

@Composable
fun WorkshopsScreen(
    uiState: State<FullWorkshopState>,
    onEvent: () -> Unit
) {
    ComposableTestContent(
        modifier = Modifier.fillMaxSize().background(color = Color.Yellow),
        uiState.value.fullWorkshopsList.size.toString(),
        onEvent
    )
}
