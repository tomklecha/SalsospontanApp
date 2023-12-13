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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tkdev.salsospontanapp.navigation.NavigationActions
import com.tkdev.salsospontanapp.navigation.NavigationRoute
import com.tkdev.salsospontanapp.navigation.TopLevelDestination
import com.tkdev.salsospontanapp.navigation.navigationBarItems
import com.tkdev.salsospontanapp.ui.artists.AndroidArtistViewModel
import com.tkdev.salsospontanapp.ui.artists.compose.ArtistsScreen
import com.tkdev.salsospontanapp.ui.info.compose.HomeScreen
import com.tkdev.salsospontanapp.ui.splash.AndroidSplashViewModel
import com.tkdev.salsospontanapp.ui.splash.compose.SplashScreen
import com.tkdev.salsospontanapp.ui.venues.AndroidVenuesViewModel
import com.tkdev.salsospontanapp.ui.venues.compose.VenuesScreen
import com.tkdev.salsospontanapp.ui.workshops.AndroidWorkshopsViewModel
import com.tkdev.salsospontanapp.ui.workshops.compose.WorkshopsScreen
import org.koin.compose.koinInject

@Composable
fun MainActivityScreen() {
    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        NavigationActions(navController)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination =
        navBackStackEntry?.destination?.route ?: TopLevelDestination.Home

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navigationBarItems.forEach { destination ->
                    NavigationBarItem(
                        selected = selectedDestination == destination.route,
                        onClick = {
                            navigationActions.navigateTo(destination)
                        },
                        label = {
                            Text(text = stringResource(destination.title))
                        },
                        icon = {
                            Icon(
                                imageVector = destination.selectedIcon,
                                contentDescription = stringResource(destination.title)
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = NavigationRoute.SPLASH_ROUTE,
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
                ArtistsScreen(state, vm::onEvent)
            }
            composable(route = TopLevelDestination.Venues.route) {
                val vm = koinInject<AndroidVenuesViewModel>()
                val state by vm.state.collectAsState()
                VenuesScreen(state, { })
            }
            composable(route = "Splash") {
                val vm = koinInject<AndroidSplashViewModel>()
                val state by vm.state.collectAsState()
                SplashScreen(
                    uiState = state,
                    onEvent = vm::onEvent
                ) {
                    navController.navigate(TopLevelDestination.Home.route) {
                        popUpTo(NavigationRoute.SPLASH_ROUTE) { inclusive = true }
                    }
                }
            }
        }
    }
}
