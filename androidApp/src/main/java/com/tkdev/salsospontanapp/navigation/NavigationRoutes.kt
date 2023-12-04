package com.tkdev.salsospontanapp.navigation

sealed class NavigationRoutes(val route: String) {

    data object Artists : NavigationRoutes(ARTIST_ROUTE)
    data object Venues : NavigationRoutes(VENUE_ROUTE)
    data object Workshops : NavigationRoutes(WORKSHOPS_ROUTE)
    data object Info : NavigationRoutes(INFO_ROUTE)
}

const val ARTIST_ROUTE = "Artists"
const val VENUE_ROUTE = "Venues"
const val WORKSHOPS_ROUTE = "Workshops"
const val INFO_ROUTE = "Info"
