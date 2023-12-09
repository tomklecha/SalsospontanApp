package com.tkdev.salsospontanapp.ui.workshops.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tkdev.salsospontanapp.ui.ComposableTestContent
import com.tkdev.salsospontanapp.ui.workshops.WorkshopState

@Composable
fun WorkshopsScreen(
    uiState: State<WorkshopState>,
    onEvent: () -> Unit
) {
    ComposableTestContent(
        modifier = Modifier.fillMaxSize().background(color = Color.Yellow),
        uiState.value.workshopsList.size.toString(),
        onEvent
    )
}
