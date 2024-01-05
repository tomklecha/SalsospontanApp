package com.tkdev.salsospontanapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tkdev.salsospontanapp.ui.artists.AndroidArtistViewModel
import com.tkdev.salsospontanapp.ui.artists.compose.ArtistsScreen
import com.tkdev.salsospontanapp.ui.info.compose.HomeScreen
import com.tkdev.salsospontanapp.ui.venues.AndroidVenuesViewModel
import com.tkdev.salsospontanapp.ui.venues.compose.VenuesScreen
import com.tkdev.salsospontanapp.ui.workshops.AndroidWorkshopsViewModel
import com.tkdev.salsospontanapp.ui.workshops.compose.WorkshopsScreen
import org.koin.compose.koinInject

@Composable
fun NavAppHost(navAppState: NavAppState, paddingValues: PaddingValues) {
    NavHost(
        navController = navAppState.navController,
        startDestination = NavigationRoute.HOME_ROUTE,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable(route = TopLevelDestination.Home.route) {
            HomeScreen()
        }
        composable(route = TopLevelDestination.Workshops.route) {
            val vm = koinInject<AndroidWorkshopsViewModel>()
            val state by vm.state.collectAsState()
            WorkshopsScreen(state, vm::onEvent)
        }
        composable(route = TopLevelDestination.Artists.route) {
            val vm = koinInject<AndroidArtistViewModel>()
            val state by vm.state.collectAsState()
            val uriHandler = LocalUriHandler.current
            ArtistsScreen(state, vm::onEvent, navAppState::navigateToWorkshops, uriHandler::openUri)
        }
        composable(route = TopLevelDestination.Venues.route) {
            val vm = koinInject<AndroidVenuesViewModel>()
            val state by vm.state.collectAsState()
            VenuesScreen(state, { })
        }
//        TODO Implement differently splashScreen as this method do not work
//        composable(route = NavigationRoute.SPLASH_ROUTE) {
//            val vm = koinInject<AndroidSplashViewModel>()
//            val state by vm.state.collectAsState()
//            SplashScreen(
//                uiState = state,
//                onEvent = vm::onEvent
//            ) {
//                navAppState.navigateToHome()
//            }
//        }
    }
}
