package com.tkdev.salsospontanapp.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkdev.salsospontanapp.domain.artists.ArtistDataSource
import com.tkdev.salsospontanapp.domain.venues.VenuesDataSource
import com.tkdev.salsospontanapp.domain.workshops.WorkshopsDataSource

class AndroidSplashViewModel(
    private val workshopsData: WorkshopsDataSource,
    private val artistsData: ArtistDataSource,
    private val venueData: VenuesDataSource
) : ViewModel() {

    private val viewModel by lazy {
        SplashViewModel(
            workshopsDataSource = workshopsData,
            artistDataSource = artistsData,
            venuesDataSource = venueData,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: SplashEvent) {
        viewModel.onEvent(event)
    }
}
