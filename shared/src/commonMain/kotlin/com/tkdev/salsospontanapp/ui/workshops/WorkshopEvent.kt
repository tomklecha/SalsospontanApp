package com.tkdev.salsospontanapp.ui.workshops

sealed class WorkshopEvent {

    data object AddWorkshop : WorkshopEvent()
    data class AddToFavourite(val workshopUid: Long, val isFavourite: Boolean) : WorkshopEvent()

    data class UpdateFilter(val uid: Long, val isSelected: Boolean) : WorkshopEvent()

    data object ClearAllFilters : WorkshopEvent()

    data object OnError : WorkshopEvent()
}
