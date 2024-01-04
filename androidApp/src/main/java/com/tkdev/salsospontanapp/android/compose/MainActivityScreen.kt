package com.tkdev.salsospontanapp.android.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tkdev.salsospontanapp.navigation.NavAppBottomBar
import com.tkdev.salsospontanapp.navigation.NavAppHost
import com.tkdev.salsospontanapp.navigation.rememberNavState

@Composable
fun MainActivityScreen() {
    val navAppState = rememberNavState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavAppBottomBar(navAppState)
        }
    ) { paddingValues ->

        NavAppHost(navAppState, paddingValues)
    }
}
