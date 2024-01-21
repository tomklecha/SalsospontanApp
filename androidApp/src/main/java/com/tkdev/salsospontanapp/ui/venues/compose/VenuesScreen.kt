package com.tkdev.salsospontanapp.ui.venues.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tkdev.salsospontanapp.domain.venues.TYPE_PARTY_VENUE
import com.tkdev.salsospontanapp.domain.venues.TYPE_WORKSHOP_ROOM
import com.tkdev.salsospontanapp.ui.venues.VenueEvent
import com.tkdev.salsospontanapp.ui.venues.VenueState

@Composable
fun VenuesScreen(
    uiState: VenueState,
    onEvent: (VenueEvent) -> Unit,
    openExternal: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        val workshopRooms =
            uiState.venueList.filter { venue -> venue.getVenueType() == TYPE_WORKSHOP_ROOM }

        val partyVenues =
            uiState.venueList.filter { venue -> venue.getVenueType() == TYPE_PARTY_VENUE }

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            content = {
                item(
                    span = {
                        GridItemSpan(maxLineSpan)
                    }
                ) {
                    Text(text = TYPE_WORKSHOP_ROOM, modifier = Modifier.padding(vertical = 16.dp))
                }
                items(workshopRooms.size) { index ->
                    VenueCard(workshopRooms[index].name) {}
                }
                item(
                    span = {
                        GridItemSpan(maxLineSpan)
                    }
                ) {
                    Text(text = TYPE_PARTY_VENUE, modifier = Modifier.padding(vertical = 16.dp))
                }
                items(partyVenues.size) { index ->
                    VenueCard(partyVenues[index].name) {
                        openExternal(partyVenues[index].mapsLink)
                    }
                }
            }
        )
    }
}
