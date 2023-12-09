package com.tkdev.salsospontanapp.domain.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.tkdev.salsospontanapp.database.SpontanDatabase
import com.tkdev.salsospontanapp.domain.artists.Artist
import com.tkdev.salsospontanapp.domain.artists.ArtistDataSource
import com.tkdev.salsospontanapp.domain.artists.toArtist
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

    override suspend fun insertArtist(artist: Artist) {
        queries.insertArtist(
            uid = artist.uid,
            name = artist.artistName,
            description = artist.artistDescription,
            type = artist.getArtistType()
        )
    }
}
