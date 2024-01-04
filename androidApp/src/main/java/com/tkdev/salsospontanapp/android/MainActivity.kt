package com.tkdev.salsospontanapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.tkdev.salsospontanapp.android.compose.MainActivityScreen
import com.tkdev.salsospontanapp.domain.artists.ArtistDataSource
import com.tkdev.salsospontanapp.domain.venues.VenuesDataSource
import com.tkdev.salsospontanapp.domain.workshops.WorkshopsDataSource
import com.tkdev.salsospontanapp.remote.RemoteClient
import com.tkdev.salsospontanapp.ui.splash.AndroidSplashViewModel
import com.tkdev.salsospontanapp.ui.splash.SplashEvent
import com.tkdev.salsospontanapp.ui.splash.SplashState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    private val workshopsData: WorkshopsDataSource by inject()
    private val artistsData: ArtistDataSource by inject()
    private val venueData: VenuesDataSource by inject()
    private val remoteClient: RemoteClient by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO to be improved - silly injection
        val vm = AndroidSplashViewModel(workshopsData, artistsData, venueData, remoteClient)
        var uiState: SplashState by mutableStateOf(SplashState())

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.state
                    .onEach { uiState = it }
                    .collect()
            }
        }

        // TODO improve the logic ?? cannot be interface - for ios issues
        installSplashScreen().setKeepOnScreenCondition {
            when {
                uiState.loadingCompleted == null && uiState.isLoading.not() -> {
                    vm.onEvent(SplashEvent.PrepopulateData)
                    true
                }
                uiState.loadingCompleted != true -> {
                    true
                }
                uiState.loadingCompleted == true -> false
                else -> true
            }
        }

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    KoinContext {
                        MainActivityScreen()
                    }
                }
            }
        }
    }
}
