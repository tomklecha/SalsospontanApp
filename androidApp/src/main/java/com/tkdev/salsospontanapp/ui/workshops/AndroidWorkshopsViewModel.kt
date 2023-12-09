package com.tkdev.salsospontanapp.ui.workshops

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkdev.salsospontanapp.domain.workshops.WorkshopsDataSource

class AndroidWorkshopsViewModel(
    private val db: WorkshopsDataSource
) : ViewModel() {

    private val viewModel by lazy {
        WorkshopsViewModel(
            workshopsDataSource = db,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: WorkshopEvent) {
        viewModel.onEvent(event)
    }
}
