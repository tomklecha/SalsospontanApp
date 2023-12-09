package com.tkdev.salsospontanapp.domain.venues

import com.tkdev.salsospontanapp.util.CommonFlow

// Idea - add Workshop Room as separate venue place

interface VenuesDataSource {
    fun getAllVenues(): CommonFlow<List<Venue>>

    suspend fun insertVenue(venue: Venue)
}
