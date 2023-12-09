package com.tkdev.salsospontanapp.ui.workshops

import com.tkdev.salsospontanapp.db.workshops
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