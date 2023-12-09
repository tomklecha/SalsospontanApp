package com.tkdev.salsospontanapp.db

import com.tkdev.salsospontanapp.domain.artists.Artist
import com.tkdev.salsospontanapp.domain.artists.Dancer
import com.tkdev.salsospontanapp.domain.artists.DeeJay
import com.tkdev.salsospontanapp.domain.artists.Musician

/*
 temporary solution, injection in DB works, but here is simpler to
 update any data. The type of artist was incorrect. Be careful while typing in db / json
 */

val artists = listOf<Artist>(
    Dancer(901, "Madeline", "Cuban"),
    Dancer(902, "Sheyla", "Cuban"),
    Dancer(903, "Sandra", "Cuban"),
    Dancer(904, "Alfredo", "Cuban"),
    Dancer(905, "Relampago", "Cuban"),
    Dancer(906, "Reynaldo", "Cuban"),
    Dancer(907, "Luis", "Portugal"),
    Dancer(908, "Ania", "Polish"),
    Dancer(909, "Eryk", "Polish"),
    Dancer(910, "Jacek", "Polish"),
    Dancer(911, "Oliwia", "Polish"),
    Musician(921, "Machito", "Cuban"),
    Musician(922, "Molina", "Cuban"),
    Musician(923, "Mustafa", "Polish"),
    Musician(924, "Mery", "Polish"),
    DeeJay(931, "Grzybek", "Polish"),
    DeeJay(932, "Punto", "Polish"),
    DeeJay(933, "Wasia", "Polish")
)
