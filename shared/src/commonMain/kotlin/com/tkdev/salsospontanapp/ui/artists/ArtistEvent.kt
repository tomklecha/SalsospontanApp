package com.tkdev.salsospontanapp.ui.artists

import com.tkdev.salsospontanapp.domain.artists.Artist

sealed class ArtistEvent {

    data class ShowArtist(val artist: Artist) : ArtistEvent()
    data class SortByType(val presentationState: Int) : ArtistEvent()
    data object OnError : ArtistEvent()
}
