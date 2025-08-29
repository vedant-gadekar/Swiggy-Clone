package com.example.core.search.state

sealed class SearchEffect {
    object NavigateBack : SearchEffect()
    data class NavigateToFood(val cuisineFilter: String? = null) : SearchEffect()
    data class NavigateToInstamart(val itemFilter: String? = null) : SearchEffect()
    data class ShowToast(val message: String) : SearchEffect()
}
