package io.github.gufeczek.feature.counter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.gufeczek.crochet.design.system.Button
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
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is CounterViewState.Content -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray)
                    .padding(horizontal = 32.dp, vertical = 64.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { onAction(CounterAction.OnMinusClicked) },
                        colors = if (viewState.content.areVoiceCommandsActive) {
                            ButtonDefaults.buttonColors(containerColor = Color.Gray)
                        } else {
                            ButtonDefaults.buttonColors(containerColor = Color.Gray)
                        },
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.minus),
                            contentDescription = "Remove",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Text(
                        text = viewState.content.rowCount.toString(),
                        fontSize = 32.sp,
                        color = Color.White.copy(alpha = 0.5f)
                    )
                    Button(
                        onClick = { onAction(CounterAction.OnPlusClicked) },
                        colors = if (viewState.content.areVoiceCommandsActive) {
                            ButtonDefaults.buttonColors(containerColor = Color.Gray)
                        } else {
                            ButtonDefaults.buttonColors(containerColor = Color.Gray)
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
                Button(
                    onClick = { onAction(CounterAction.OnMicrophoneClicked) },
                    colors = if (viewState.content.areVoiceCommandsActive) {
                        ButtonDefaults.buttonColors(containerColor = Color.Green)
                    } else {
                        ButtonDefaults.buttonColors(containerColor = Color.Gray)
                    },
                    modifier = Modifier.size(80.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.microphone),
                        contentDescription = "Microphone",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    }
}