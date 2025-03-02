package io.github.gufeczek.crochet.speech.recogniton.adapter

import android.app.AppOpsManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.speech.tts.TextToSpeech
import androidx.core.content.getSystemService
import io.github.gufeczek.crochet.model.SpeechEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.vosk.LogLevel

private const val NOTIFICATION_ID = 1
private const val CHANNEL_ID = "SpeechProvider"

internal class SpeechProviderService: Service(), TextToSpeech.OnInitListener {
    private val binder = SpeechProviderBinder()
    private lateinit var textToSpeech: TextToSpeech
    private val _speechEvents: MutableStateFlow<SpeechEvent> = MutableStateFlow(SpeechEvent.Preparing)
    val speech = _speechEvents.asStateFlow()

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(NOTIFICATION_ID, createNotification())
        textToSpeech = TextToSpeech(this@SpeechProviderService,  this@SpeechProviderService)

        val appOps = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_RECORD_AUDIO,
            android.os.Process.myUid(),
            packageName
        )
        if (mode != AppOpsManager.MODE_ALLOWED) {
            // TODO handle denial, intent to battery optimisation
        }

        GlobalScope.launch(Dispatchers.Default) {
            val model = initializeSpeechRecognitionModel(this@SpeechProviderService, LogLevel.DEBUG)
            launch(Dispatchers.Main) {
                initRecognitionListener(model!!).flowOn(Dispatchers.IO).collect(_speechEvents::emit)
            }
        }

        return START_STICKY
    }

    override fun onInit(status: Int) {
        when (status) {
            TextToSpeech.SUCCESS -> {}
            else -> {}
        }
    }

    private fun createNotification(): Notification {
        val notificationManager: NotificationManager? = getSystemService<NotificationManager>()
        notificationManager?.createNotificationChannel(
            NotificationChannel(
                CHANNEL_ID,
                "Channel 1",
                NotificationManager.IMPORTANCE_HIGH
            )
        )

        return Notification.Builder(this, CHANNEL_ID)
            .setContentTitle("My Service")
            .setContentText("Running in the foreground")
            .build()
    }

    inner class SpeechProviderBinder : Binder() {
        val service: SpeechProviderService
            get() = this@SpeechProviderService
    }
}