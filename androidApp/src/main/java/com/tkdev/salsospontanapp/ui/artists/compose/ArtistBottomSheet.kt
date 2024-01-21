package com.tkdev.salsospontanapp.ui.artists.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tkdev.salsospontanapp.domain.artists.Artist

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistBottomSheet(artist: Artist, onClick: () -> Unit, onDismiss: () -> Unit, openExternal: (String) -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        Box(
            modifier = Modifier.fillMaxHeight(1f)
        ) {
            Card(
                onClick = onClick,
                colors = CardDefaults.cardColors(contentColor = Color.Yellow)
            ) {
                Text(
                    text = artist.name
                )
                if (artist.instagramLink.isNotBlank()) {
                    Card(
                        onClick = {
                            openExternal(artist.instagramLink)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}
