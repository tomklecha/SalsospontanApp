package com.tkdev.salsospontanapp.ui.workshops

import com.tkdev.salsospontanapp.domain.workshops.Workshop

// All actions available within artist view
data class WorkshopState(
    val workshopsList: List<Workshop> = emptyList(),
    val error: String? = null, //  error state handling
    val presentationState: Int = 0 // sorting mechanism ? to present dancers/musics etc
)
