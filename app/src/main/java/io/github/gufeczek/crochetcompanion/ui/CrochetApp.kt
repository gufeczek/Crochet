package io.github.gufeczek.crochetcompanion.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.github.gufeczek.crochetcompanion.navigation.CrochetNavHost

@Composable
fun CrochetApp(
    appState: CrochetAppState,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            CrochetTopAppBar(appState = appState)
            CrochetNavHost(appState = appState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrochetTopAppBar(
    modifier: Modifier = Modifier,
    appState: CrochetAppState
) {
    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            CenterAlignedTopAppBar(title = { Text("title") })
            CrochetNavHost(appState = appState)
        }
    }
}