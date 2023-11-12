package com.tkdev.salsospontanapp.domain.artists

import com.tkdev.salsospontanapp.util.CommonFlow

interface ArtistDataSource {
    fun getAllArtists(): CommonFlow<List<Artist>>

    suspend fun insertArtist(artist: Artist)
}
