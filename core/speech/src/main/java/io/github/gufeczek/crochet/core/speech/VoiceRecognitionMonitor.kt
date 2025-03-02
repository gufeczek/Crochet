package io.github.gufeczek.crochet.core.speech

import io.github.gufeczek.crochet.model.SpeechEvent
import io.github.gufeczek.crochet.speech.recogniton.adapter.SpeechAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.runningFold
import org.koin.core.annotation.Single

const val WINDOW_SIZE = 3

@OptIn(ExperimentalCoroutinesApi::class)
@Single
class VoiceRecognitionMonitor(speechProvider: SpeechAdapter) {
    val isActive = speechProvider.speechEvents
        .flatMapConcat {
            when (it) {
                is SpeechEvent.Preparing -> flow { repeat(WINDOW_SIZE) { emit(true) } }
                is SpeechEvent.Content -> flowOf(true)
                else -> flowOf(false)
            }
        }
        .runningFold(emptyList<Boolean>()) { acc, value -> (acc + value).takeLast(WINDOW_SIZE) }
        .map { window -> window.all { it } }
}