package com.tkdev.salsospontanapp.ui.venues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkdev.salsospontanapp.domain.venues.VenuesDataSource

class AndroidVenuesViewModel(
    private val db: VenuesDataSource
) : ViewModel() {

    private val viewModel by lazy {
        VenuesViewModel(
            venueDataSource = db,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: VenueEvent) {
        viewModel.onEvent(event)
    }
}
