package io.github.gufeczek.crochet.feature.counter

import android.util.Log
import io.github.gufeczek.crochet.core.speech.DefaultVoiceCommandsBroadcast
import io.github.gufeczek.crochet.core.speech.VoiceRecognitionMonitor
import io.github.gufeczek.crochet.data.CounterRepository
import io.github.gufeczek.crochet.model.SpeechEvent
import io.github.gufeczek.feature.counter.CounterCommander
import io.github.gufeczek.feature.counter.CounterDataSource
import io.github.gufeczek.feature.counter.VoiceCommand
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import org.koin.core.annotation.Single

const val COUNTER_TAG = "Counter"

@Single
class Counter(
    private val voiceCommandsBroadcast: DefaultVoiceCommandsBroadcast,
    private val counterRepository: CounterRepository,
    voiceRecognitionMonitor: VoiceRecognitionMonitor
): CounterCommander, CounterDataSource {
    override val areVoiceCommandsActive: Flow<Boolean> = voiceRecognitionMonitor.isActive

    override val voiceCommands: Flow<VoiceCommand> =
        voiceCommandsBroadcast.voiceCommands.mapNotNull { event ->
            when (event) {
                is SpeechEvent.Content -> {
                    Log.d(COUNTER_TAG, "SpeechEvent: ${event.word}")
                    when {
                        event.word.contains("add") -> VoiceCommand.Increment
                        event.word.contains("back") -> VoiceCommand.Decrement
                        event.word.contains("count") -> VoiceCommand.SayCount
                        else -> null
                    }
                }
                else -> null
            }
        }

    override suspend fun create() = counterRepository.create()

    override suspend fun incrementCount() = counterRepository.incrementCount()

    override suspend fun decrementCount() = counterRepository.decrementCount()

    override suspend fun enableVoiceCommands(): Boolean = voiceCommandsBroadcast.initialize()

    override suspend fun disableVoiceCommands() = voiceCommandsBroadcast.stop()

    override fun getCounter(id: String): Flow<Int> {
        return counterRepository.getCounter(id)
    }
}