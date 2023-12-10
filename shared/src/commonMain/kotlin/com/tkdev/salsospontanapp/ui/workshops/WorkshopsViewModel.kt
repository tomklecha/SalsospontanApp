package com.tkdev.salsospontanapp.ui.workshops

import com.tkdev.salsospontanapp.db.workshops
import com.tkdev.salsospontanapp.domain.artists.Artist
import com.tkdev.salsospontanapp.domain.artists.ArtistDataSource
import com.tkdev.salsospontanapp.domain.fullworkshop.FullWorkshop
import com.tkdev.salsospontanapp.domain.venues.Venue
import com.tkdev.salsospontanapp.domain.venues.VenuesDataSource
import com.tkdev.salsospontanapp.domain.workshops.Workshop
import com.tkdev.salsospontanapp.domain.workshops.WorkshopsDataSource
import com.tkdev.salsospontanapp.util.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WorkshopsViewModel(
    private val workshopsDataSource: WorkshopsDataSource,
    private val artistDataSource: ArtistDataSource,
    private val venuesDataSource: VenuesDataSource,
    private val coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(WorkshopState())
    val state = combine(
        _state,
        workshopsDataSource.getAllWorkshops()
    ) { state, workshops ->
        state.copy(
            workshopsList = workshops,
            presentationState = state.presentationState
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), WorkshopState())
        .toCommonStateFlow()

    private val _fullWorkshopState = MutableStateFlow(FullWorkshopState())
    val fullWorkshopState = combine(
        _fullWorkshopState,
        workshopsDataSource.getAllWorkshops(),
        artistDataSource.getAllArtists(),
        venuesDataSource.getAllVenues()
    ) { state, workshops, artists, venues ->
        state.copy(
            fullWorkshopsList = generateFullWorkshopsList(workshops, artists, venues)
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FullWorkshopState())
        .toCommonStateFlow()

    private fun generateFullWorkshopsList(
        workshops: List<Workshop>,
        artists: List<Artist>,
        venues: List<Venue>
    ): List<FullWorkshop> {
        val list: MutableList<FullWorkshop> = mutableListOf()
        workshops.forEach { workshop ->
            list.add(
                FullWorkshop(
                    workshop = workshop,
                    artistPrimary = artists.first { it.uid == workshop.artistPrimaryUid },
                    artistSecondary = artists.firstOrNull { it.uid == workshop.artistPrimaryUid },
                    venue = venues.first { it.uid == workshop.venueUid }
                )
            )
        }
        return list.toList()
    }

    fun onEvent(event: WorkshopEvent) {
        when (event) {
            is WorkshopEvent.AddWorkshop -> {
                // cannot add venue with same uid - it is unique so it wont increment
                viewModelScope.launch {
                    workshops.forEach {
                        workshopsDataSource.insertWorkshop(it)
                    }
                    _state.update {
                        it.copy()
                    }
                }
            }
            else -> {}
        }
    }
}
