package com.tkdev.salsospontanapp.ui.workshops.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tkdev.salsospontanapp.domain.fullworkshop.FullWorkshop
import com.tkdev.salsospontanapp.ui.ComposableTestContent
import com.tkdev.salsospontanapp.ui.workshops.FullWorkshopState
import com.tkdev.salsospontanapp.ui.workshops.WorkshopEvent

@Composable
fun WorkshopsScreen(
    uiState: FullWorkshopState,
    onEvent: (WorkshopEvent) -> Unit
) {
    val workshopList = uiState.fullWorkshopsList
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ComposableTestContent(
            modifier = Modifier,
            workshopList.size.toString(),
            onEvent
        )
        Spacer(modifier = Modifier.height(30.dp))
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
fun WorkshopItem(workshop: FullWorkshop, onEvent: (WorkshopEvent) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = dimens.paddingHorizontal, vertical = dimens.paddingColumnVertical),
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

private val dimens = object {
    val paddingTextSpacer = 4.dp
    val paddingColumnVertical = 8.dp
    val paddingHorizontal = 16.dp
}
