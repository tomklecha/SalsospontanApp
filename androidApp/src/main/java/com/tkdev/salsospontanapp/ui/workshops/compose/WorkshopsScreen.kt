package com.tkdev.salsospontanapp.ui.workshops.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.tkdev.salsospontanapp.domain.fullworkshop.FullWorkshop
import com.tkdev.salsospontanapp.ui.workshops.FullWorkshopState
import com.tkdev.salsospontanapp.ui.workshops.WorkshopEvent
import com.tkdev.salsospontanapp.ui.workshops.filter.FilterElement

@Composable
fun WorkshopsScreen(
    uiState: FullWorkshopState,
    onEvent: (WorkshopEvent) -> Unit
) {
    val workshopList = uiState.fullWorkshopsList

    var shouldOpen by remember {
        mutableStateOf(false)
    }

    if (shouldOpen) {
        OpenDialog(
            fiters = uiState.filters,
            onEvent = onEvent
        ) {
            shouldOpen = it
        }
    }

    Column {
        Button(
            onClick = {
                shouldOpen = true
            }
        ) {
            Text(text = "Open Filter")
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(workshopList, itemContent = {
                WorkshopItem(it, onEvent)
            })
        }
    }
}

@Composable
fun OpenDialog(fiters: List<FilterElement>, onEvent: (WorkshopEvent) -> Unit, onDismss: (Boolean) -> Unit) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = {
            onDismss(false)
        }
    ) {
        Box(
            Modifier.fillMaxWidth().fillMaxHeight(0.8f).background(Color.Black)
        ) {
            LazyColumn {
                items(fiters, itemContent = {
                    FilterItem(it, onEvent)
                })
            }
        }
    }
}

@Composable
fun WorkshopItem(workshop: FullWorkshop, onEvent: (WorkshopEvent) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = dimens.paddingHorizontal, vertical = dimens.paddingColumnVertical).background(if (workshop.isSelected) Color.Yellow else Color.Transparent),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column {
            Text(
                text = workshop.workshop.uid.toString()
            )
            Spacer(modifier = Modifier.height(dimens.paddingColumnVertical))
            Text(
                text = workshop.workshop.name
            )
        }
        Button(
            onClick = {
                onEvent(WorkshopEvent.AddToFavourite(workshop.workshop.uid, !workshop.workshop.isFavourite))
            },
            content = {
                Icon(
                    imageVector = if (workshop.workshop.isFavourite) Icons.Filled.Add else Icons.Outlined.AccountBox,
                    contentDescription = null
                )
            }
        )
        Text(
            text = "${workshop.workshop.isFavourite}"
        )
    }
}

@Composable
fun FilterItem(filterElement: FilterElement, onEvent: (WorkshopEvent) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = dimens.paddingHorizontal, vertical = dimens.paddingColumnVertical),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = filterElement.name
        )
        Button(
            onClick = {
                onEvent(WorkshopEvent.UpdateFilter(filterElement.uid, !filterElement.isSelected))
            },
            content = {
                Icon(
                    imageVector = if (filterElement.isSelected) Icons.Filled.Add else Icons.Outlined.AccountBox,
                    contentDescription = null
                )
            }
        )
        Text(
            text = "${filterElement.isSelected}"
        )
    }
}

private val dimens = object {
    val paddingColumnVertical = 8.dp
    val paddingHorizontal = 16.dp
}
