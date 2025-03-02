package io.github.gufeczek.crochet.model

sealed class SpeechEvent {
    data object Preparing : SpeechEvent()
    data class Content(val word: String) : SpeechEvent()
    data class Error(val statusCode: Int) : SpeechEvent()
    data object Finished : SpeechEvent()
}