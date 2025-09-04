package com.example.core.state

sealed class SearchEvent {
    data class SearchQueryChanged(val query: String) : SearchEvent()
    data class TrendingSearchClicked(val searchQuery: String) : SearchEvent()
    data class PopularItemClicked(val itemId: String) : SearchEvent()
    data class CuisineClicked(val cuisineId: String) : SearchEvent()
    object BackClicked : SearchEvent()
    object ClearError : SearchEvent()
}
