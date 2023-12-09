package com.tkdev.salsospontanapp.ui.venues.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tkdev.salsospontanapp.ui.ComposableTestContent
import com.tkdev.salsospontanapp.ui.venues.VenueState

@Composable
fun VenuesScreen(
    uiState: State<VenueState>,
    onEvent: () -> Unit
) {
    ComposableTestContent(
        modifier = Modifier.fillMaxSize().background(color = Color.Green),
        uiState.value.venueList.size.toString(),
        onEvent
    )
}
