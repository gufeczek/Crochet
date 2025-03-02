package io.github.gufeczek.model

sealed class SpeechEvent {
    data object Preparing : SpeechEvent()
    data class Content(val word: String) : SpeechEvent()
    data class Error(val statusCode: Int) : SpeechEvent()
    data object Finished : SpeechEvent()
}