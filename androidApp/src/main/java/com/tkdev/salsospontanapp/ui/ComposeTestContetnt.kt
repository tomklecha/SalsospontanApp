package com.tkdev.salsospontanapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tkdev.salsospontanapp.ui.workshops.WorkshopEvent

@Composable
fun ComposableTestContent(
    modifier: Modifier,
    data: String,
    onEvent: (WorkshopEvent) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "uistate state is $data"
        )
        Button(
            onClick = {},
            content = {
                Text(
                    text = "Populate DB"
                )
            }
        )
    }
}
