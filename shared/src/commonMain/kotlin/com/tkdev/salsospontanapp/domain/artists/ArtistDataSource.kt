package com.tkdev.salsospontanapp.domain.artists

interface ArtistDataSource {
    fun getAllArtists()

    fun insertAll(artists: List<Artist>)
}