package com.tkdev.salsospontanapp.ui.venues

import com.tkdev.salsospontanapp.domain.venues.Venue

sealed class VenueEvent {

    data object AddVenue : VenueEvent()
    data class ShowVenue(val venue: Venue) : VenueEvent()
    data class SortByType(val presentationState: Int) : VenueEvent()
    data object OnError : VenueEvent()
}
