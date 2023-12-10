package com.tkdev.salsospontanapp.domain.workshops

import com.tkdev.salsospontanapp.util.CommonFlow

interface WorkshopsDataSource {

    fun getAllWorkshops(): CommonFlow<List<Workshop>>

    suspend fun insertWorkshop(workshop: Workshop)

    suspend fun updateFavourite(workshopUid: Long, isFavourite: Boolean)
}
