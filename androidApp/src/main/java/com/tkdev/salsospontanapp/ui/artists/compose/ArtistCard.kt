package com.tkdev.salsospontanapp.ui.artists.compose

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistCard(artistName: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.height(200.dp).padding(bottom = 8.dp),
        onClick = { onClick() }
    ) {
        Text(text = artistName)
    }
}
