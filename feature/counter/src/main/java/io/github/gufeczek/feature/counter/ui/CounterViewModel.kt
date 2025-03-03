package io.github.gufeczek.feature.counter.ui

import androidx.lifecycle.viewModelScope
import io.github.gufeczek.feature.counter.Action
import io.github.gufeczek.feature.counter.CounterCommander
import io.github.gufeczek.feature.counter.CounterDataSource
import io.github.gufeczek.feature.counter.Effect
import io.github.gufeczek.feature.counter.FeatureViewModel
import io.github.gufeczek.feature.counter.ViewState
import io.github.gufeczek.feature.counter.VoiceCommand
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

data class CounterScreenContent(
    val rowCount: Int,
    val areVoiceCommandsActive: Boolean
)

sealed interface CounterViewState : ViewState {
    data object Loading : CounterViewState
    data class Content(val content: CounterScreenContent) : CounterViewState
}

sealed interface CounterAction : Action {
    data object OnPlusClicked : CounterAction
    data object OnMinusClicked : CounterAction
    data object OnMicrophoneClicked : CounterAction
}

sealed interface CounterEffect : Effect {
    data object NavigateToScreenX : CounterEffect
    data object NavigateToScreenY : CounterEffect
}

@KoinViewModel
class CounterViewModel(
    private val counterCommander: CounterCommander,
    counterDataSource: CounterDataSource
) : FeatureViewModel<CounterViewState, CounterAction, CounterEffect>() {

    init {
        GlobalScope.launch {
            counterDataSource.voiceCommands.collect { it: VoiceCommand ->
                when (it) {
                    VoiceCommand.Decrement -> reduce(CounterAction.OnMinusClicked)
                    VoiceCommand.Increment -> reduce(CounterAction.OnPlusClicked)
                    else -> Unit
                }
            }
        }
    }

    override val viewState: StateFlow<CounterViewState> =
        combine(
            counterDataSource.getCounter(""),
            counterDataSource.areVoiceCommandsActive,
            ::Pair
        ).map { (counter, areVoiceCommandsActive) ->
            CounterViewState.Content(
                content = CounterScreenContent(
                    rowCount = counter,
                    areVoiceCommandsActive = areVoiceCommandsActive
                )
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CounterViewState.Loading
        )

    override fun reduce(action: CounterAction) {
        when (val state = viewState.value) {
            is CounterViewState.Loading -> Unit
            is CounterViewState.Content ->
                when (action) {
                    CounterAction.OnPlusClicked -> updateCount(increase = true)
                    CounterAction.OnMinusClicked -> updateCount(increase = false)
                    CounterAction.OnMicrophoneClicked -> toggleMicrophone(state)
                }
        }
    }

    private fun updateCount(increase: Boolean) =
        viewModelScope.launch {
            if (increase)
                counterCommander.incrementCount()
            else
                counterCommander.decrementCount()
        }

    private fun toggleMicrophone(state: CounterViewState.Content) =
        viewModelScope.launch {
            if (state.content.areVoiceCommandsActive)
                counterCommander.disableVoiceCommands()
             else
                counterCommander.enableVoiceCommands()
        }
}