package io.github.gufeczek.feature.counter

interface CounterCommander {
    suspend fun create()
    suspend fun incrementCount()
    suspend fun decrementCount()
    suspend fun enableVoiceCommands(): Boolean
    suspend fun disableVoiceCommands(): Boolean
}