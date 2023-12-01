package com.tkdev.salsospontanapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.tkdev.salsospontanapp.ui.artists.AndroidArtistViewModel
import com.tkdev.salsospontanapp.ui.artists.ArtistEvent
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Tested building of artists viewModel
                    val viewModel = koinInject<AndroidArtistViewModel>()
                    val state = viewModel.state.collectAsState()
                    GreetingView(viewModel::onEvent, state.value.artistList.size.toString())
                }
            }
        }
    }
}

@Composable
fun GreetingView(onEvent: (ArtistEvent) -> Unit, text: String) {
    Button(
        onClick = { onEvent(ArtistEvent.AddArtist) },
        content =
        {
            Text(text = text)
        }
    )
}
