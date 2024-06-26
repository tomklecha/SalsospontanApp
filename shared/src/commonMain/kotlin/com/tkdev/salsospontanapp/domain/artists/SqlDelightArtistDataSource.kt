package com.tkdev.salsospontanapp.domain.artists

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.tkdev.salsospontanapp.database.SpontanDatabase
import com.tkdev.salsospontanapp.util.CommonFlow
import com.tkdev.salsospontanapp.util.toCommonFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map

class SqlDelightArtistDataSource(
    db: SpontanDatabase
) : ArtistDataSource {

    private val queries = db.timetableQueries
    override fun getAllArtists(): CommonFlow<List<Artist>> {
        return queries
            .getAllArtists()
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { artists ->
                artists.mapNotNull { it.toArtist() }
            }
            .toCommonFlow()
    }

    override suspend fun prepopulateArtist(artist: Artist) {
        queries.prepopulateArtist(
            uid = artist.uid,
            name = artist.name,
            description = artist.description,
            instagramLink = artist.instagramLink,
            type = artist.getArtistType()
        )
    }
}
