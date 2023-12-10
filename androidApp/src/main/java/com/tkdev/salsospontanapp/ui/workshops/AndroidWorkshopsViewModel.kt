package com.tkdev.salsospontanapp.ui.workshops

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkdev.salsospontanapp.domain.artists.ArtistDataSource
import com.tkdev.salsospontanapp.domain.venues.VenuesDataSource
import com.tkdev.salsospontanapp.domain.workshops.WorkshopsDataSource

class AndroidWorkshopsViewModel(
    private val workshopsData: WorkshopsDataSource,
    private val artistsData: ArtistDataSource,
    private val venueData: VenuesDataSource
) : ViewModel() {

    private val viewModel by lazy {
        WorkshopsViewModel(
            workshopsDataSource = workshopsData,
            artistDataSource = artistsData,
            venuesDataSource = venueData,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.fullWorkshopState

    fun onEvent(event: WorkshopEvent) {
        viewModel.onEvent(event)
    }
}
