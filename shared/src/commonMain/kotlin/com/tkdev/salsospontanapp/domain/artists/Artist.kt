package com.tkdev.salsospontanapp.domain.artists

const val TYPE_DANCER = "dancer"
const val TYPE_MUSICIAN = "musician"
const val TYPE_DEEJAY = "dj"
const val TYPE_PLACEHOLDER = "placeholder"

abstract class Artist(
    open val uid: Long,
    open val name: String,
    open val description: String,
    open val instagramLink: String = "",
    private val type: String
) {
    abstract fun getArtistType(): String
}

data class Dancer(
    override val uid: Long,
    override val name: String,
    override val description: String,
    override val instagramLink: String = ""
) : Artist(
    uid = uid,
    name = name,
    description = description,
    instagramLink = instagramLink,
    type = TYPE_DANCER
) {
    override fun getArtistType() = TYPE_DANCER
}

data class Musician(
    override val uid: Long,
    override val name: String,
    override val description: String
) : Artist(
    uid = uid,
    name = name,
    description = description,
    type = TYPE_MUSICIAN
) {
    override fun getArtistType() = TYPE_MUSICIAN
}

data class DeeJay(
    override val uid: Long,
    override val name: String,
    override val description: String
) : Artist(
    uid = uid,
    name = name,
    description = description,
    type = TYPE_DEEJAY
) {
    override fun getArtistType() = TYPE_DEEJAY
}

data class Placeholder(
    override val uid: Long = -1,
    override val name: String = "",
    override val description: String = ""
) : Artist(
    uid = uid,
    name = name,
    description = description,
    type = TYPE_PLACEHOLDER
) {
    override fun getArtistType() = TYPE_PLACEHOLDER
}

sealed class ArtistType(val type: String) {
    data object Dancer : ArtistType(TYPE_DANCER)
    data object Musician : ArtistType(TYPE_MUSICIAN)
    data object DeeJay : ArtistType(TYPE_DEEJAY)
}
