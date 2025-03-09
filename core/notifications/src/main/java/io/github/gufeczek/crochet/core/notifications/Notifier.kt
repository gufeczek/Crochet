package io.github.gufeczek.crochet.core.notifications

import androidx.compose.runtime.Composable

interface Notifier {
    fun postNotification(content: @Composable () -> Unit)
}