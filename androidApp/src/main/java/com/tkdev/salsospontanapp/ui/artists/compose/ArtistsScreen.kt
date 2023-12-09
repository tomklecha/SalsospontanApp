package com.tkdev.salsospontanapp.ui.artists.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tkdev.salsospontanapp.ui.artists.ArtistState

@Composable
fun ArtistsScreen(
    uiState: State<ArtistState>,
    onEvent: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().background(
            color = Color.Magenta
        )
    ) {
        Text(
            text = "uistate state is ${uiState.value.artistList.size}"
        )
        Button(
            onClick = onEvent,
            content = {
                Text(
                    text = "Populate DB"
                )
            }
        )
    }
}
