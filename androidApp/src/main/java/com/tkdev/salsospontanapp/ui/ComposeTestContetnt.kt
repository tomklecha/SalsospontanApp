package com.tkdev.salsospontanapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ComposableTestContent(
    modifier: Modifier,
    data: String,
    onEvent: (Any) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "uistate state is $data"
        )
        Button(
            onClick = { onEvent },
            content = {
                Text(
                    text = "Populate DB"
                )
            }
        )
    }
}
