package com.tkdev.salsospontanapp.domain.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.tkdev.salsospontanapp.database.SpontanDatabase
import com.tkdev.salsospontanapp.domain.workshops.Workshop
import com.tkdev.salsospontanapp.domain.workshops.WorkshopsDataSource
import com.tkdev.salsospontanapp.domain.workshops.toWorkshop
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
            description = workshop.description
        )
    }
}
