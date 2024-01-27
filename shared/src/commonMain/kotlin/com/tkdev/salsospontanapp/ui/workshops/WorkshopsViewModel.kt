package com.tkdev.salsospontanapp.ui.workshops

import com.tkdev.salsospontanapp.domain.artists.Artist
import com.tkdev.salsospontanapp.domain.artists.ArtistDataSource
import com.tkdev.salsospontanapp.domain.fullworkshop.FullWorkshop
import com.tkdev.salsospontanapp.domain.venues.Venue
import com.tkdev.salsospontanapp.domain.venues.VenuesDataSource
import com.tkdev.salsospontanapp.domain.workshops.Workshop
import com.tkdev.salsospontanapp.domain.workshops.WorkshopsDataSource
import com.tkdev.salsospontanapp.ui.workshops.filter.FilterElement
import com.tkdev.salsospontanapp.ui.workshops.filter.FilterType
import com.tkdev.salsospontanapp.util.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class WorkshopsViewModel(
    private val workshopsDataSource: WorkshopsDataSource,
    private val artistDataSource: ArtistDataSource,
    private val venuesDataSource: VenuesDataSource,
    private val coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(FullWorkshopState(error = "Will this error be changed"))
    val state = combine(
        _state,
        workshopsDataSource.getAllWorkshops(),
        artistDataSource.getAllArtists(),
        venuesDataSource.getAllVenues()
    ) { state, workshops, artists, venues ->
        when {
            state.workshops.isEmpty() -> {
                _state.updateAndGet {
                    state.copy(
                        fullWorkshopsList = generateFullWorkshopsList(workshops, artists, venues),
                        error = null,
                        artists = artists,
                        workshops = workshops,
                        filters = generateFilterList(artists)
                    )
                }
            }
            state.workshops != workshops -> {
                state.copy(
                    workshops = workshops
                )
            }
            else -> state
        }
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FullWorkshopState())
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

    private fun generateFilterList(
        artists: List<Artist>
    ): List<FilterElement> =
        artists.map {
                artist ->
            FilterElement(
                uid = artist.uid,
                name = artist.name,
                isSelected = false,
                filterType = FilterType.DANCER
            )
        }

    fun onEvent(event: WorkshopEvent) {
        when (event) {
            is WorkshopEvent.AddWorkshop -> {
            }
            is WorkshopEvent.UpdateFilter -> {
                _state.update {
                    it.copy(
                        filters = it.filters.map { filterElement ->
                            if (filterElement.uid == event.uid) {
                                filterElement.copy(
                                    isSelected = event.isSelected
                                )
                            } else {
                                filterElement
                            }
                        },
                        fullWorkshopsList = it
                            .fullWorkshopsList
                            .map { fullWorkshop ->
                                if (fullWorkshop.artistPrimary.uid == event.uid || fullWorkshop.artistSecondary?.uid == event.uid) {
                                    fullWorkshop.copy(
                                        isSelected = event.isSelected
                                    )
                                } else {
                                    fullWorkshop
                                }
                            }

                    )
                }
            }
            is WorkshopEvent.AddToFavourite -> {
                viewModelScope.launch {
                    workshopsDataSource.updateFavourite(event.workshopUid, event.isFavourite)
                }.invokeOnCompletion {
                    _state.update {
                        it.copy(
                            fullWorkshopsList = it
                                .fullWorkshopsList
                                .map { fullWorkshop ->
                                    if (fullWorkshop.workshop.uid == event.workshopUid) {
                                        fullWorkshop.copy(
                                            workshop = fullWorkshop.workshop.copy(
                                                isFavourite = event.isFavourite
                                            )
                                        )
                                    } else {
                                        fullWorkshop
                                    }
                                }

                        )
                    }
                }
            }
            else -> {}
        }
    }
}
