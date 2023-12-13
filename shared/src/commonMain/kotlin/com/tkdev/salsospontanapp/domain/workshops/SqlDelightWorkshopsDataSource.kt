package com.tkdev.salsospontanapp.domain.workshops

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.tkdev.salsospontanapp.database.SpontanDatabase
import com.tkdev.salsospontanapp.util.CommonFlow
import com.tkdev.salsospontanapp.util.toCommonFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map

class SqlDelightWorkshopsDataSource(
    db: SpontanDatabase
) : WorkshopsDataSource {

    private val queries = db.timetableQueries
    override fun getAllWorkshops(): CommonFlow<List<Workshop>> {
        return queries
            .getAllWorkshops()
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { workshops ->
                workshops.map { it.toWorkshop() }
            }
            .toCommonFlow()
    }

    override suspend fun insertWorkshop(workshop: Workshop) {
        queries.insertWorkshop(
            uid = workshop.uid,
            name = workshop.name,
            description = workshop.description,
            timeSchedule = workshop.timeSchedule,
            artistPrimaryUid = workshop.artistPrimaryUid,
            artistSecondaryUid = workshop.artistSecondaryUid,
            venueUid = workshop.venueUid,
            isFavourite = workshop.isFavourite
        )
    }

    override suspend fun updateFavourite(workshopUid: Long, isFavourite: Boolean) {
        queries.updateFavourite(workshopUid = workshopUid, isFavourite = isFavourite)
    }
}
