package com.tkdev.salsospontanapp.ui.artists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkdev.salsospontanapp.domain.artists.ArtistDataSource

class AndroidArtistViewModel(
    private val db: ArtistDataSource
) : ViewModel() {

    private val viewModel by lazy {
        ArtistViewModel(
            artistDataSource = db,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: ArtistEvent) {
        viewModel.onEvent(event)
    }
}
