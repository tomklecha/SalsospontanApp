package com.tkdev.salsospontanapp.android.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tkdev.salsospontanapp.navigation.NavigationRoutes
import com.tkdev.salsospontanapp.navigation.navigationBarItems
import com.tkdev.salsospontanapp.ui.artists.AndroidArtistViewModel
import com.tkdev.salsospontanapp.ui.artists.ArtistEvent
import com.tkdev.salsospontanapp.ui.artists.compose.ArtistsScreen
import com.tkdev.salsospontanapp.ui.info.compose.InfoScreen
import com.tkdev.salsospontanapp.ui.venues.AndroidVenuesViewModel
import com.tkdev.salsospontanapp.ui.venues.VenueEvent
import com.tkdev.salsospontanapp.ui.venues.compose.VenuesScreen
import com.tkdev.salsospontanapp.ui.workshops.AndroidWorkshopsViewModel
import com.tkdev.salsospontanapp.ui.workshops.WorkshopEvent
import com.tkdev.salsospontanapp.ui.workshops.compose.WorkshopsScreen
import org.koin.compose.koinInject

@Composable
fun MainActivityScreen() {
    val navController = rememberNavController()

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navigationBarItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.title)
                        },
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                },
                                contentDescription = item.title
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->

        /*
        TODO a bit laggy ? currently kept it, as prefer to work on app itself. To be checked
        Handle backSwipe action - just top screen ?
         */

        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.Artists.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(NavigationRoutes.Artists.route) {
                val vm = koinInject<AndroidArtistViewModel>()
                val state = vm.state.collectAsState()
                ArtistsScreen(state) { vm.onEvent(ArtistEvent.AddArtist) }
            }
            composable(NavigationRoutes.Workshops.route) {
                val vm = koinInject<AndroidWorkshopsViewModel>()
                val state = vm.state.collectAsState()
                WorkshopsScreen(state) { vm.onEvent(WorkshopEvent.AddWorkshop) }
            }
            composable(NavigationRoutes.Venues.route) {
                val vm = koinInject<AndroidVenuesViewModel>()
                val state = vm.state.collectAsState()
                VenuesScreen(state) { vm.onEvent(VenueEvent.AddVenue) }
            }
            composable(NavigationRoutes.Info.route) {
                InfoScreen()
            }
        }
    }
}
