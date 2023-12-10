package com.tkdev.salsospontanapp.ui.artists.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tkdev.salsospontanapp.ui.ComposableTestContent
import com.tkdev.salsospontanapp.ui.artists.ArtistEvent
import com.tkdev.salsospontanapp.ui.artists.ArtistState

@Composable
fun ArtistsScreen(
    uiState: ArtistState,
    onEvent: (ArtistEvent) -> Unit
) {
    ComposableTestContent(
        modifier = Modifier.fillMaxSize(),
        data = uiState.artistList.size.toString(),
        onEvent = { }
    )
}
