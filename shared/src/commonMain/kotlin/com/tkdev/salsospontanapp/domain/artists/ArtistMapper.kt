package com.tkdev.salsospontanapp.domain.artists

import database.ArtistEntity

fun ArtistEntity.toArtist(): Artist {
    return Artist(
        uid = uid,
        artistName = artistName,
        artistDescription = artistDescription
    )
}
