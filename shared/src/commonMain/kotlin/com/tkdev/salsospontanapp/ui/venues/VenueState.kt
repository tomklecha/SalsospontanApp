package com.tkdev.salsospontanapp.ui.venues

import com.tkdev.salsospontanapp.domain.venues.Venue

// All actions available within artist view
data class VenueState(
    val venueList: List<Venue> = emptyList(),
    val error: String? = null, //  error state handling
    val presentationState: Int = 0 // sorting mechanism ? to present dancers/musics etc
)
