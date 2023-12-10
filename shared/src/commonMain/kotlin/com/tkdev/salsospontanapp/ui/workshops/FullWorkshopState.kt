package com.tkdev.salsospontanapp.ui.workshops

import com.tkdev.salsospontanapp.domain.fullworkshop.FullWorkshop

// All actions available within artist view
data class FullWorkshopState(
    val fullWorkshopsList: List<FullWorkshop> = emptyList(),
    val error: String? = null, //  error state handling
    val presentationState: Int = 0 // sorting mechanism ? to present dancers/musics etc
)
