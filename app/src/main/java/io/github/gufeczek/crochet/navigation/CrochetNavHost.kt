package io.github.gufeczek.crochet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import io.github.gufeczek.feature.counter.nav.CounterRoute
import io.github.gufeczek.crochet.ui.CrochetAppState
import io.github.gufeczek.feature.counter.nav.counterScreen

@Composable
fun CrochetNavHost(
    modifier: Modifier = Modifier,
    appState: CrochetAppState
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = CounterRoute,
        modifier = modifier
    ) {
        counterScreen()
    }
}