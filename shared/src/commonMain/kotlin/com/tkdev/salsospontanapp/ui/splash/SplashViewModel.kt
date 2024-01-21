package com.tkdev.salsospontanapp.ui.splash

import com.tkdev.salsospontanapp.db.artists
import com.tkdev.salsospontanapp.db.venues
import com.tkdev.salsospontanapp.db.workshops
import com.tkdev.salsospontanapp.domain.artists.ArtistDataSource
import com.tkdev.salsospontanapp.domain.venues.VenuesDataSource
import com.tkdev.salsospontanapp.domain.workshops.WorkshopsDataSource
import com.tkdev.salsospontanapp.remote.RemoteClient
import com.tkdev.salsospontanapp.util.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    private val workshopsDataSource: WorkshopsDataSource,
    private val artistDataSource: ArtistDataSource,
    private val venuesDataSource: VenuesDataSource,
    private val remoteClient: RemoteClient,
    private val coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(SplashState())
    val state = combine(
        _state,
        workshopsDataSource.getAllWorkshops()
    ) { state, workshops ->
        state.copy(
            loadingCompleted = workshops.isNotEmpty()
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SplashState())
        .toCommonStateFlow()

    fun onEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.PrepopulateData -> {
                // cannot add venue with same uid - it is unique so it wont increment
                if (state.value.loadingCompleted == null) {
                    _state.update {
                        it.copy(
                            isLoading = true,
                            loadingCompleted = false
                        )
                    }
                    viewModelScope.launch {
                        /*
                    Concurency - start adding artists and rest after call
//                    val result = remoteClient.fetchData()
                    TODO think about server side and costs
                     */

                        artists.forEach {
                            artistDataSource.prepopulateArtist(it)
                        }
                        venues.forEach {
                            venuesDataSource.prepopulateVenue(it)
                        }
                        workshops.forEach {
                            workshopsDataSource.prepopulateWorkshop(it)
                        }
                    }.invokeOnCompletion {
                        _state.update {
                            it.copy(
                                loadingCompleted = true
                            )
                        }
                    }
                }
            }
            else -> {}
        }
    }
}
