package io.github.gufeczek.feature.counter.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.gufeczek.feature.counter.design.Button
import io.github.gufeczek.feature.counter.R
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CounterScreen(viewModel: CounterViewModel = koinViewModel()) {
    val uiState by viewModel.viewState.collectAsStateWithLifecycle()

    CounterScreen(
        viewState = uiState,
        onAction = viewModel::reduce
    )
}

@Composable
fun CounterScreen(
    viewState: CounterViewState,
    onAction: (CounterAction) -> Unit
) {
    when (viewState) {
        is CounterViewState.Loading -> {
            CircularProgressIndicator()
        }
        is CounterViewState.Content -> {
            Column {
                Row {
                    Button(onClick = { onAction(CounterAction.OnPlusClicked) }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color.Blue
                        )
                    }

                    Button(onClick = { onAction(CounterAction.OnPlusClicked) }) {
                        Text(text = viewState.content.rowCount.toString())
                    }

                    Button(onClick = { onAction(CounterAction.OnMinusClicked) }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.minus),
                            contentDescription = "Remove",
                            tint = Color.Blue
                        )
                    }
                }
                Button(
                    onClick = { onAction(CounterAction.OnMicrophoneClicked) }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.microphone),
                        contentDescription = "Microphone",
                        tint = if (viewState.content.areVoiceCommandsActive) Color.Blue else Color.Gray
                    )
                }
            }
        }
    }

}
