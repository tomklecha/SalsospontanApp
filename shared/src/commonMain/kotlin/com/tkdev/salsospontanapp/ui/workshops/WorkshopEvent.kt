package com.tkdev.salsospontanapp.ui.workshops

import com.tkdev.salsospontanapp.domain.workshops.Workshop

sealed class WorkshopEvent {

    data object AddWorkshop : WorkshopEvent()
    data class ShowWorkshop(val workshop: Workshop) : WorkshopEvent()
    data class SortByType(val presentationState: Int) : WorkshopEvent()
    data object OnError : WorkshopEvent()
}
