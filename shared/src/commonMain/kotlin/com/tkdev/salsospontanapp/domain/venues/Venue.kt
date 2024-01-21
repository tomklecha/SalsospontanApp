package com.tkdev.salsospontanapp.domain.venues

const val TYPE_WORKSHOP_ROOM = "workshop_room"
const val TYPE_PARTY_VENUE = "party_venue"
abstract class Venue(
    open val uid: Long,
    open val name: String,
    open val description: String,
    open val location: String = "",
    open val mapsLink: String,
    open val startDate: String = "",
    private val type: String
) {
    abstract fun getVenueType(): String
}

data class WorkshopRoom(
    override val uid: Long,
    override val name: String,
    override val description: String,
    override val location: String = "",
    override val mapsLink: String = ""
) : Venue(
    uid = uid,
    name = name,
    description = description,
    location = location,
    mapsLink = mapsLink,
    type = TYPE_WORKSHOP_ROOM
) {
    override fun getVenueType(): String = TYPE_WORKSHOP_ROOM
}

data class PartyVenue(
    override val uid: Long,
    override val name: String,
    override val description: String,
    override val location: String = "",
    override val mapsLink: String,
    override val startDate: String
) : Venue(
    uid = uid,
    name = name,
    description = description,
    location = location,
    mapsLink = mapsLink,
    startDate = startDate,
    type = TYPE_PARTY_VENUE
) {
    override fun getVenueType(): String = TYPE_PARTY_VENUE
}

sealed class VenueType(val type: String) {
    data object Workshop : VenueType(TYPE_WORKSHOP_ROOM)
    data object Party : VenueType(TYPE_PARTY_VENUE)
}
