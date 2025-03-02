package io.github.gufeczek.crochet

import androidx.lifecycle.ViewModel
import org.koin.android.annotation.KoinViewModel

sealed interface MainActivityViewState {
    data object Loading : MainActivityViewState
}

@KoinViewModel
class MainActivityViewModel : ViewModel()

