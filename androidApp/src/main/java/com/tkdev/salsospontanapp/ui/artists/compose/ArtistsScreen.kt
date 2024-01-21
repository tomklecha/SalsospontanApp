@file:OptIn(ExperimentalMaterial3Api::class)

package com.tkdev.salsospontanapp.ui.artists.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tkdev.salsospontanapp.domain.artists.TYPE_DANCER
import com.tkdev.salsospontanapp.domain.artists.TYPE_DEEJAY
import com.tkdev.salsospontanapp.domain.artists.TYPE_MUSICIAN
import com.tkdev.salsospontanapp.ui.artists.ArtistEvent
import com.tkdev.salsospontanapp.ui.artists.ArtistState

@Composable
fun ArtistsScreen(
    uiState: ArtistState,
    onEvent: (ArtistEvent) -> Unit,
    navigateToWorkshops: () -> Unit,
    openExternal: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        if (uiState.showBottomSheet) {
            ArtistBottomSheet(
                artist = uiState.presentArtist,
                onClick = {
                    onEvent(ArtistEvent.HideArtist)
                    navigateToWorkshops()
                },
                openExternal = openExternal,
                onDismiss = {
                    onEvent(ArtistEvent.HideArtist)
                }
            )
        }

        val dancerList =
            uiState.artistList.filter { artist -> artist.getArtistType() == TYPE_DANCER }

        val musicianList =
            uiState.artistList.filter { artist -> artist.getArtistType() == TYPE_MUSICIAN }

        val deeJayList =
            uiState.artistList.filter { artist -> artist.getArtistType() == TYPE_DEEJAY }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                item(
                    span = {
                        GridItemSpan(maxLineSpan)
                    }
                ) {
                    Text(text = TYPE_DANCER, modifier = Modifier.padding(vertical = 16.dp))
                }
                items(dancerList.size) { index ->
                    ArtistCard(dancerList[index].name) {
                        onEvent(ArtistEvent.ShowArtist(dancerList[index]))
                    }
                }
                item(
                    span = {
                        GridItemSpan(maxLineSpan)
                    }
                ) {
                    Text(text = TYPE_MUSICIAN, modifier = Modifier.padding(vertical = 16.dp))
                }
                items(musicianList.size) { index ->
                    ArtistCard(musicianList[index].name) {
                        onEvent(ArtistEvent.ShowArtist(musicianList[index]))
                    }
                }
                item(
                    span = {
                        GridItemSpan(maxLineSpan)
                    }
                ) {
                    Text(text = TYPE_DEEJAY, modifier = Modifier.padding(vertical = 16.dp))
                }
                items(deeJayList.size) { index ->
                    ArtistCard(deeJayList[index].name) {
                        onEvent(ArtistEvent.ShowArtist(deeJayList[index]))
                    }
                }
            }
        )
    }
}
