import com.tkdev.salsospontanapp.domain.venues.Venue
import database.VenueEntity

fun VenueEntity.toVenue(): Venue =
    Venue(uid, name, description)
