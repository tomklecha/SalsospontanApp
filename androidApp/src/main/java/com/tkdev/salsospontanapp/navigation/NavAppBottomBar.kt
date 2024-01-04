package com.tkdev.salsospontanapp.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun NavAppBottomBar(
    navAppState: NavAppState
) {
    NavigationBar {
        navAppState.topLevelDestinations.forEach { destination ->
            NavigationBarItem(
                selected = navAppState.currentDestination.isTopLevelDestinationInHierarchy(
                    destination
                ),
                onClick = {
                    navAppState.navigateTo(destination)
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
