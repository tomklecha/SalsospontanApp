package com.tkdev.salsospontanapp.domain.venues

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.tkdev.salsospontanapp.database.SpontanDatabase
import com.tkdev.salsospontanapp.util.CommonFlow
import com.tkdev.salsospontanapp.util.toCommonFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import toVenue

class SqlDelightVenuesDataSource(
    db: SpontanDatabase
) : VenuesDataSource {

    private val queries = db.timetableQueries
    override fun getAllVenues(): CommonFlow<List<Venue>> {
        return queries
            .getAllVenues()
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { venues ->
                venues.mapNotNull { it.toVenue() }
            }
            .toCommonFlow()
    }

    override suspend fun prepopulateVenue(venue: Venue) {
        queries.prepopulateVenue(
            uid = venue.uid,
            name = venue.name,
            description = venue.description,
            location = venue.location,
            mapsLink = venue.mapsLink,
            startDate = venue.startDate,
            type = venue.getVenueType()
        )
    }
}
