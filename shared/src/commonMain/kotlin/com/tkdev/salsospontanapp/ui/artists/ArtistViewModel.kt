package com.tkdev.salsospontanapp.ui.artists

import com.tkdev.salsospontanapp.db.artists
import com.tkdev.salsospontanapp.domain.artists.ArtistDataSource
import com.tkdev.salsospontanapp.util.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
        state.copy(
            artistList = artists,
            presentationState = state.presentationState
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ArtistState())
        .toCommonStateFlow()

    fun onEvent(event: ArtistEvent) {
        when (event) {
            is ArtistEvent.AddArtist -> {
                // cannot add artist with same uid - it is unique so it wont increment
                viewModelScope.launch {
                    artists.forEach {
                        artistDataSource.insertArtist(it)
                    }
                    _state.update {
                        it.copy()
                    }
                }
            }
            else -> {}
        }
    }
}
