package io.github.gufeczek.feature.counter.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.github.gufeczek.feature.counter.ui.CounterScreen
import kotlinx.serialization.Serializable

@Serializable data object CounterBaseRoute

@Serializable data object CounterRoute

fun NavController.navigateToCounter(
    navOptions: NavOptions? = null
) = navigate(CounterRoute, navOptions)

fun NavGraphBuilder.counterScreen(){
    composable<CounterRoute> { CounterScreen() }
}