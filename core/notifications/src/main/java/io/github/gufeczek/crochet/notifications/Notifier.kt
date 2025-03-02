package io.github.gufeczek.crochet.notifications

import androidx.compose.runtime.Composable

interface Notifier {
    fun postNotification(content: @Composable () -> Unit)
}