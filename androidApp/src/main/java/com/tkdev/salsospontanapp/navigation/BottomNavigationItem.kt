package com.tkdev.salsospontanapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val artistNavigationItem = BottomNavigationItem(
    title = NavigationRoutes.Artists.route,
    selectedIcon = Icons.Filled.Person,
    unselectedIcon = Icons.Outlined.Person
)

val workshopsNavigationItem = BottomNavigationItem(
    title = NavigationRoutes.Workshops.route,
    selectedIcon = Icons.Filled.Place,
    unselectedIcon = Icons.Outlined.Place
)

val venueNavigationItem = BottomNavigationItem(
    title = NavigationRoutes.Venues.route,
    selectedIcon = Icons.Filled.LocationOn,
    unselectedIcon = Icons.Outlined.LocationOn
)

val infoNavigationItem = BottomNavigationItem(
    title = NavigationRoutes.Info.route,
    selectedIcon = Icons.Filled.Info,
    unselectedIcon = Icons.Outlined.Info
)

val navigationBarItems: List<BottomNavigationItem> = listOf(
    artistNavigationItem,
    workshopsNavigationItem,
    venueNavigationItem,
    infoNavigationItem
)
