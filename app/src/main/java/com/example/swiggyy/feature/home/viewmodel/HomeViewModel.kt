package com.example.swiggyy.feature_home

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

            is HomeIntent.SearchQueryChanged -> {
                _state.update { it.copy(searchQuery = intent.query) }
            }
            is HomeIntent.CarouselItemClicked -> {
                // Handle carousel item click
            }
            is HomeIntent.PageChanged -> {
                _state.update { it.copy(currentPage = intent.page) }
            }

        }
    }
}

sealed class HomeIntent {
    data class SearchQueryChanged(val query: String) : HomeIntent()

    data class CarouselItemClicked(val item: CarouselItem) : HomeIntent()
    data class PageChanged(val page: Int) : HomeIntent()

}
