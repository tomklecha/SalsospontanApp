package com.tkdev.salsospontanapp.ui.artists

import com.tkdev.salsospontanapp.domain.artists.Artist
import com.tkdev.salsospontanapp.domain.artists.Placeholder

// All actions available within artist view
data class ArtistState(
    val artistList: List<Artist> = emptyList(),
    val error: String? = null, //  error state handling
    val presentationState: Int = 0, // sorting mechanism ? to present dancers/musics etc
    val presentArtist: Artist = Placeholder(),
    val showBottomSheet: Boolean = false // sorting mechanism ? to present dancers/musics etc
)
