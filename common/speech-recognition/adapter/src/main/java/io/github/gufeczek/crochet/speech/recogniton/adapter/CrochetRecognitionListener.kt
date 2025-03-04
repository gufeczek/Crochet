package io.github.gufeczek.crochet.speech.recogniton.adapter

import android.content.Context
import io.github.gufeczek.crochet.model.SpeechEvent
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import org.vosk.LibVosk
import org.vosk.LogLevel
import org.vosk.Model
import org.vosk.Recognizer
import org.vosk.android.RecognitionListener
import org.vosk.android.SpeechService
import org.vosk.android.StorageService
import kotlin.coroutines.resume

internal suspend fun initializeSpeechRecognitionModel(
    context: Context,
    logLevel: LogLevel
): Model? {
    LibVosk.setLogLevel(logLevel)

    return suspendCancellableCoroutine { continuation ->
        StorageService.unpack(
            context,
            "model-en-us",
            "model",
            { model -> continuation.resume(model) },
            { exception ->
                run {
                    println("exception: $exception")
                    continuation.resume(null)
                }
            }
        )
    }
}

fun initRecognitionListener(
    model: Model
): Flow<SpeechEvent> = callbackFlow {
    trySend(SpeechEvent.Preparing)

    val recognitionListener = object : RecognitionListener {
        override fun onPartialResult(result: String?) {
            if (result == null) return

            trySend(SpeechEvent.Content(word = result.replace("\n", "")))
        }

        override fun onResult(result: String?) {
            if (result == null) return

            trySend(SpeechEvent.Content(word = result.replace("\n", "")))
        }

        override fun onFinalResult(result: String?) {
            if (result == null) return

            trySend(SpeechEvent.Content(word = result.replace("\n", "")))
        }

        override fun onError(exception: Exception?) {
            trySend(SpeechEvent.Error(0))
        }

        override fun onTimeout() {
            trySend(SpeechEvent.Error(0))
        }

    }
    val recognizer = Recognizer(model, 16000f) // TODO: set appropriate sample rate
    val speechService = SpeechService(recognizer, 16000f)
    val status = speechService.startListening(recognitionListener)
    println("status: $status")

    awaitClose()
}