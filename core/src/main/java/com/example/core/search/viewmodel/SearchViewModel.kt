package com.example.core.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.search.state.SearchEvent
import com.example.core.search.state.SearchEffect
import com.example.core.search.state.SearchState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    
    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()
    
    private val _effect = MutableSharedFlow<SearchEffect>()
    val effect: SharedFlow<SearchEffect> = _effect.asSharedFlow()
    
    fun handleEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.SearchQueryChanged -> updateSearchQuery(event.query)
            is SearchEvent.TrendingSearchClicked -> onTrendingSearchClicked(event.searchQuery)
            is SearchEvent.PopularItemClicked -> onPopularItemClicked(event.itemId)
            is SearchEvent.CuisineClicked -> onCuisineClicked(event.cuisineId)
            is SearchEvent.BackClicked -> onBackClicked()
            is SearchEvent.ClearError -> clearError()
        }
    }
    
    private fun updateSearchQuery(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }
    
    private fun onTrendingSearchClicked(searchQuery: String) {
        viewModelScope.launch {
            // Update search query and navigate back
            updateSearchQuery(searchQuery)
            _effect.emit(SearchEffect.NavigateBack)
        }
    }
    
    private fun onPopularItemClicked(itemId: String) {
        viewModelScope.launch {
            _effect.emit(SearchEffect.NavigateToInstamart(itemFilter = itemId))
        }
    }
    
    private fun onCuisineClicked(cuisineId: String) {
        viewModelScope.launch {
            _effect.emit(SearchEffect.NavigateToFood(cuisineFilter = cuisineId))
        }
    }
    
    private fun onBackClicked() {
        viewModelScope.launch {
            _effect.emit(SearchEffect.NavigateBack)
        }
    }
    
    private fun clearError() {
        _state.update { it.copy(error = null) }
    }
}
