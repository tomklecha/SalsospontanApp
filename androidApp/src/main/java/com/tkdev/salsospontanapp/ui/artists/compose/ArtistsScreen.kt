package com.tkdev.salsospontanapp.ui.artists.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tkdev.salsospontanapp.ui.ComposableTestContent
import com.tkdev.salsospontanapp.ui.artists.ArtistState

@Composable
fun ArtistsScreen(
    uiState: State<ArtistState>,
    onEvent: () -> Unit
) {
    ComposableTestContent(
        modifier = Modifier.fillMaxSize().background(
            color = Color.Magenta
        ),
        data = uiState.value.artistList.size.toString(),
        onEvent = onEvent
    )
}
