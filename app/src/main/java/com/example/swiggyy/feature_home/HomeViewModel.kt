package com.example.swiggy.feature_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()


    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadHome -> loadHome()
            is HomeIntent.SearchQueryChanged -> {
                _state.update { it.copy(searchQuery = intent.query) }
            }

        }
    }

    private fun loadHome() {
        // Static UI, so nothing dynamic for now
    }


}


sealed class HomeIntent {
    object LoadHome : HomeIntent()
    data class SearchQueryChanged(val query: String) : HomeIntent()

}
