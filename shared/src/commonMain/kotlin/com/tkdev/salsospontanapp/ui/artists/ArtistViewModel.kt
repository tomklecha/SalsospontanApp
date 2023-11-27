package com.tkdev.salsospontanapp.ui.artists

import com.tkdev.salsospontanapp.domain.artists.ArtistDataSource
import com.tkdev.salsospontanapp.util.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class ArtistViewModel(
    private val artistDataSource: ArtistDataSource,
    private val coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(ArtistState())
    val state = combine(
        _state,
        artistDataSource.getAllArtists()
    ) { state, artists ->
        if (state.artistList != artists) {
            state.copy(
                artistList = artists
            )
        } else {
            state
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ArtistState())
        .toCommonStateFlow()

    fun onEvent(event: ArtistEvent) {
        // TODO create proper events
    }
}
