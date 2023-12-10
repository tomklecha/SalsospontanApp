package com.tkdev.salsospontanapp.ui.workshops

import com.tkdev.salsospontanapp.domain.workshops.Workshop

sealed class WorkshopEvent {

    data object AddWorkshop : WorkshopEvent()
    data class ShowWorkshop(val workshop: Workshop) : WorkshopEvent()
    data class SortByType(val presentationState: Int) : WorkshopEvent()
    data class AddToFavourite(val workshopUid: Long, val isFavourite: Boolean) : WorkshopEvent()

    data object OnError : WorkshopEvent()
}
