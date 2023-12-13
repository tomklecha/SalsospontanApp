package com.tkdev.salsospontanapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.tkdev.salsospontanapp.android.R
import com.tkdev.salsospontanapp.navigation.NavigationRoute.ARTIST_ROUTE
import com.tkdev.salsospontanapp.navigation.NavigationRoute.HOME_ROUTE
import com.tkdev.salsospontanapp.navigation.NavigationRoute.VENUE_ROUTE
import com.tkdev.salsospontanapp.navigation.NavigationRoute.WORKSHOPS_ROUTE

sealed class TopLevelDestination(
    val route: String,
    val title: Int,
//    val iconTextId: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {

    data object Artists : TopLevelDestination(
        route = ARTIST_ROUTE,
        title = R.string.artists_screen_title,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )
    data object Venues : TopLevelDestination(
        route = VENUE_ROUTE,
        title = R.string.venues_screen_title,
        selectedIcon = Icons.Filled.Place,
        unselectedIcon = Icons.Outlined.Place
    )
    data object Workshops : TopLevelDestination(
        route = WORKSHOPS_ROUTE,
        title = R.string.workshops_screen_title,
        selectedIcon = Icons.Filled.Place,
        unselectedIcon = Icons.Outlined.Place
    )
    data object Home : TopLevelDestination(
        route = HOME_ROUTE,
        title = R.string.home_screen_title,
        selectedIcon = Icons.Filled.Info,
        unselectedIcon = Icons.Outlined.Info
    )
}

object NavigationRoute {
    const val ARTIST_ROUTE = "Artists"
    const val VENUE_ROUTE = "Venues"
    const val WORKSHOPS_ROUTE = "Workshops"
    const val HOME_ROUTE = "Home"
    const val SPLASH_ROUTE = "Splash"
}

val navigationBarItems: List<TopLevelDestination> = listOf(
    TopLevelDestination.Home,
    TopLevelDestination.Workshops,
    TopLevelDestination.Artists,
    TopLevelDestination.Venues
)
