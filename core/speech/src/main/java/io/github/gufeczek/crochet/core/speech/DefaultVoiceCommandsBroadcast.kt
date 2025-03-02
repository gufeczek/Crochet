package io.github.gufeczek.crochet.core.speech

import io.github.gufeczek.crochet.model.SpeechEvent
import io.github.gufeczek.crochet.speech.recogniton.adapter.SpeechAdapter
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

//TODO handle errors here, extract words from json, etc.
@Single
class DefaultVoiceCommandsBroadcast(private val speechProvider: SpeechAdapter) {
    val voiceCommands: Flow<SpeechEvent> = speechProvider.speechEvents

    fun initialize(): Boolean = speechProvider.initialize()
    fun stop(): Boolean = speechProvider.stop()
}

