package com.tkdev.salsospontanapp.ui.workshops

import com.tkdev.salsospontanapp.domain.artists.Artist
import com.tkdev.salsospontanapp.domain.fullworkshop.FullWorkshop
import com.tkdev.salsospontanapp.domain.workshops.Workshop
import com.tkdev.salsospontanapp.ui.workshops.filter.FilterElement

// All actions available within artist view
data class FullWorkshopState(
    val fullWorkshopsList: List<FullWorkshop> = emptyList(),
    val error: String? = null, //  error state handling
    val filters: List<FilterElement> = emptyList(),
    val artists: List<Artist> = emptyList(),
    val workshops: List<Workshop> = emptyList()
)
