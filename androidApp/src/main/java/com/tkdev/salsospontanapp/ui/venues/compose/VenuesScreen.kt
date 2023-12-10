package com.tkdev.salsospontanapp.ui.venues.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tkdev.salsospontanapp.ui.ComposableTestContent
import com.tkdev.salsospontanapp.ui.venues.VenueState
import com.tkdev.salsospontanapp.ui.workshops.WorkshopEvent

@Composable
fun VenuesScreen(
    uiState: VenueState,
    onEvent: (WorkshopEvent) -> Unit
) {
    ComposableTestContent(
        modifier = Modifier.fillMaxSize(),
        uiState.venueList.size.toString(),
        onEvent
    )
}
