package com.tkdev.salsospontanapp.domain.workshops

class Workshop(
    val uid: Long,
    val name: String,
    val description: String,
    val timeSchedule: String,
    val artistPrimaryUid: Long,
    val artistSecondaryUid: Long? = null,
    val venueUid: Long
)
