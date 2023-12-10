package com.tkdev.salsospontanapp.ui.venues

import com.tkdev.salsospontanapp.db.venues
import com.tkdev.salsospontanapp.domain.venues.VenuesDataSource
import com.tkdev.salsospontanapp.util.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VenuesViewModel(
    private val venueDataSource: VenuesDataSource,
    private val coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(VenueState())
    val state = combine(
        _state,
        venueDataSource.getAllVenues()
    ) { state, venues ->
        state.copy(
            venueList = venues,
            presentationState = state.presentationState
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), VenueState())
        .toCommonStateFlow()

    fun onEvent(event: VenueEvent) {
        when (event) {
            is VenueEvent.AddVenue -> {
                // cannot add venue with same uid - it is unique so it wont increment
                viewModelScope.launch {
                    venues.forEach {
                        venueDataSource.insertVenue(it)
                    }
                }
            }
            else -> {}
        }
    }
}
