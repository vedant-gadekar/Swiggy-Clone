package com.example.swiggy.feature_home

sealed class HomeEffect {
    data class ShowToast(val message: String) : HomeEffect()
    data class NavigateToCategory(val category: Category) : HomeEffect()
}
