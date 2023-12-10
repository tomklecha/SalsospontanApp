package com.tkdev.salsospontanapp.domain.workshops

import database.WorkshopEntity

fun WorkshopEntity.toWorkshop(): Workshop =
    Workshop(uid, name, description, timeSchedule, artistPrimaryUid, artistSecondaryUid, venueUid)
