package io.github.gufeczek.crochet.notifications

import android.Manifest.permission
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

private const val NOTIFICATION_CHANNEL_ID = "default"


class SystemTrayNotifier(
    private val context: Context
) : Notifier {
    override fun postNotification(
        content: @Composable () -> Unit
    ) = with(context) {
        if (checkSelfPermission(permission.POST_NOTIFICATIONS) != PERMISSION_GRANTED) {
            return
        }

    }

    private fun Context.createNotification(
        block: NotificationCompat.Builder.() -> Unit
    ) : Notification {
        ensureNotificationChannelExists()

        val notificationBuilder = NotificationCompat.Builder(
            this,
            NOTIFICATION_CHANNEL_ID
        )
        return with (notificationBuilder) {
            priority = NotificationCompat.PRIORITY_DEFAULT
            apply(block)
            build()
        }

    }

    private fun Context.ensureNotificationChannelExists() {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            "placeholder",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "placeholder"
        }

        NotificationManagerCompat.from(this).createNotificationChannel(channel)
    }
}