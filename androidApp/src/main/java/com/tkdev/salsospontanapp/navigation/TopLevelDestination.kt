package com.tkdev.salsospontanapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.tkdev.salsospontanapp.navigation.NavigationRoute.ARTIST_ROUTE
import com.tkdev.salsospontanapp.navigation.NavigationRoute.INFO_ROUTE
import com.tkdev.salsospontanapp.navigation.NavigationRoute.VENUE_ROUTE
import com.tkdev.salsospontanapp.navigation.NavigationRoute.WORKSHOPS_ROUTE

sealed class TopLevelDestination(
    val route: String,
    val title: String,
//    val iconTextId: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {

    data object Artists : TopLevelDestination(
        route = ARTIST_ROUTE,
        title = ARTIST_ROUTE,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )
    data object Venues : TopLevelDestination(
        route = VENUE_ROUTE,
        title = VENUE_ROUTE,
        selectedIcon = Icons.Filled.Place,
        unselectedIcon = Icons.Outlined.Place
    )
    data object Workshops : TopLevelDestination(
        route = WORKSHOPS_ROUTE,
        title = WORKSHOPS_ROUTE,
        selectedIcon = Icons.Filled.Place,
        unselectedIcon = Icons.Outlined.Place
    )
    data object Info : TopLevelDestination(
        route = INFO_ROUTE,
        title = INFO_ROUTE,
        selectedIcon = Icons.Filled.Info,
        unselectedIcon = Icons.Outlined.Info
    )
}

object NavigationRoute {
    const val ARTIST_ROUTE = "Artists"
    const val VENUE_ROUTE = "Venues"
    const val WORKSHOPS_ROUTE = "Workshops"
    const val INFO_ROUTE = "Info"
}

val navigationBarItems: List<TopLevelDestination> = listOf(
    TopLevelDestination.Artists,
    TopLevelDestination.Venues,
    TopLevelDestination.Workshops,
    TopLevelDestination.Info
)
