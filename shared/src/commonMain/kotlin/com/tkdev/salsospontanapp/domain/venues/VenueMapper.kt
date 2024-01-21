import com.tkdev.salsospontanapp.domain.venues.PartyVenue
import com.tkdev.salsospontanapp.domain.venues.Venue
import com.tkdev.salsospontanapp.domain.venues.VenueType
import com.tkdev.salsospontanapp.domain.venues.WorkshopRoom
import database.VenueEntity

fun VenueEntity.toVenue(): Venue? =
    when (this.type) {
        VenueType.Workshop.type -> this.toWorkshopRoom()
        VenueType.Party.type -> this.toPartyVenue()
        else -> null
    }

private fun VenueEntity.toWorkshopRoom(): WorkshopRoom = WorkshopRoom(uid, name, description, location, mapsLink)

private fun VenueEntity.toPartyVenue(): PartyVenue = PartyVenue(uid, name, description, location, mapsLink, startDate)
