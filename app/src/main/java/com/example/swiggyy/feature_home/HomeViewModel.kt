package com.example.swiggy.feature_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _effect = Channel<HomeEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadHome -> loadHome()
            is HomeIntent.SearchQueryChanged -> {
                _state.update { it.copy(searchQuery = intent.query) }
            }
            is HomeIntent.BannerClicked -> {
                sendEffect(HomeEffect.ShowToast("Banner clicked"))
            }
            is HomeIntent.CategoryClicked -> {
                sendEffect(HomeEffect.NavigateToCategory(intent.category))
            }
        }
    }

    private fun loadHome() {
        // Static UI, so nothing dynamic for now
    }

    private fun sendEffect(effect: HomeEffect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }
}
