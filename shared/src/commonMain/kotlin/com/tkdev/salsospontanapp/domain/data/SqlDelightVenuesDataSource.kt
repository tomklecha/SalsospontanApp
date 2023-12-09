package com.tkdev.salsospontanapp.domain.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.tkdev.salsospontanapp.database.SpontanDatabase
import com.tkdev.salsospontanapp.domain.venues.Venue
import com.tkdev.salsospontanapp.domain.venues.VenuesDataSource
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
                venues.map { it.toVenue() }
            }
            .toCommonFlow()
    }

    override suspend fun insertVenue(venue: Venue) {
        queries.insertVenue(
            uid = venue.uid,
            venueName = venue.venueName,
            venueDescription = venue.venueDescription
        )
    }
}
