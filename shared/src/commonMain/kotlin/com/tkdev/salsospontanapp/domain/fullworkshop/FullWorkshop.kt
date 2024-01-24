package com.tkdev.salsospontanapp.domain.fullworkshop

import com.tkdev.salsospontanapp.domain.artists.Artist
import com.tkdev.salsospontanapp.domain.venues.Venue
import com.tkdev.salsospontanapp.domain.workshops.Workshop

data class FullWorkshop(
    val workshop: Workshop,
    val artistPrimary: Artist,
    val artistSecondary: Artist? = null,
    val venue: Venue,
    var isSelected: Boolean = false
)
