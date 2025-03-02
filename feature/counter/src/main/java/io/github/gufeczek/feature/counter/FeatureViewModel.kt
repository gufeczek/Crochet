package io.github.gufeczek.feature.counter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

interface ViewState
interface Action
interface Effect

abstract class FeatureViewModel<ViewState, Action, Effect> : ViewModel() {
    abstract val viewState: StateFlow<ViewState>

    abstract fun reduce(action: Action)
}