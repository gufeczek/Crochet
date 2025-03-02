package io.github.gufeczek.crochet.speechprovider

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.core.content.ContextCompat
import io.github.gufeczek.model.SpeechEvent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single

@Single
class DefaultSpeechProvider(private val appContext: Context) {
    private var boundService: SpeechProviderService? = null
    private val _speechEvents = MutableStateFlow<SpeechEvent>(value = SpeechEvent.Finished)
    val speechEvents: Flow<SpeechEvent> = _speechEvents.asStateFlow().also { GlobalScope.launch { it.collect {
        print(it)
    }  }}

    fun initialize(): Boolean {
        val connection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val binder = service as SpeechProviderService.SpeechProviderBinder
                boundService = binder.service
                GlobalScope.launch {
                    boundService?.speech?.collect(_speechEvents::emit)
                }
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                boundService = null
                GlobalScope.launch {
                    _speechEvents.emit(SpeechEvent.Finished)
                }

            }
        }

        val serviceIntent = Intent(appContext, SpeechProviderService::class.java)
        ContextCompat.startForegroundService(appContext, serviceIntent)
        return appContext.bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE)
    }

    fun stop(): Boolean {
        val isServiceStopped = appContext.stopService(Intent(appContext, SpeechProviderService::class.java))
        GlobalScope.launch {
            if (isServiceStopped) {
                _speechEvents.emit(SpeechEvent.Finished)
            }
        }
        return !isServiceStopped
    }
}