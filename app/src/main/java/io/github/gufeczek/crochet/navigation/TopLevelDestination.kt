package io.github.gufeczek.crochet.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.gufeczek.feature.counter.nav.CounterBaseRoute
import io.github.gufeczek.feature.counter.nav.CounterRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val title: String,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
) {
    COUNTER(
        selectedIcon = Icons.Default.Build,
        unselectedIcon = Icons.Default.Call,
        title = "Counter",
        route = CounterRoute::class,
        baseRoute = CounterBaseRoute::class
    )
}