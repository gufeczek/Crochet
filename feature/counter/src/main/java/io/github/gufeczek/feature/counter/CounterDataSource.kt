package io.github.gufeczek.feature.counter

import kotlinx.coroutines.flow.Flow

interface CounterDataSource {
    val areVoiceCommandsActive: Flow<Boolean>
    val voiceCommands: Flow<VoiceCommand>

    fun getCounter(id: String): Flow<Int>
}

sealed interface VoiceCommand {
    data object Increment : VoiceCommand
    data object Decrement : VoiceCommand
    data object SayCount : VoiceCommand
}