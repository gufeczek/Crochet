package io.github.gufeczek.core.speech

import io.github.gufeczek.model.SpeechEvent
import io.github.gufeczek.crochet.speechprovider.DefaultSpeechProvider
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

//TODO handle errors here, extract words from json, etc.
@Single
class DefaultVoiceCommandsBroadcast(private val speechProvider: DefaultSpeechProvider) {
    val voiceCommands: Flow<SpeechEvent> = speechProvider.speechEvents

    fun initialize(): Boolean = speechProvider.initialize()
    fun stop(): Boolean = speechProvider.stop()
}

