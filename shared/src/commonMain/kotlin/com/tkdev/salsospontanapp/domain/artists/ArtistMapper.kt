package com.tkdev.salsospontanapp.domain.artists

import database.ArtistEntity

fun ArtistEntity.toArtist(): Artist? =
    when (this.type) {
        ArtistType.Dancer.type -> this.toDancer()
        ArtistType.Musician.type -> this.toMusician()
        ArtistType.DeeJay.type -> this.toDeeJay()
        else -> { null }
    }

fun ArtistEntity.toDancer(): Dancer {
    return Dancer(
        uid = uid,
        artistName = artistName,
        artistDescription = artistDescription
    )
}

fun ArtistEntity.toMusician(): Musician {
    return Musician(
        uid = uid,
        artistName = artistName,
        artistDescription = artistDescription
    )
}

fun ArtistEntity.toDeeJay(): DeeJay {
    return DeeJay(
        uid = uid,
        artistName = artistName,
        artistDescription = artistDescription
    )
}
