package com.example.swiggy.feature_home


sealed class HomeIntent {
    object LoadHome : HomeIntent()
    data class SearchQueryChanged(val query: String) : HomeIntent()
    object BannerClicked : HomeIntent()
    data class CategoryClicked(val category: Category) : HomeIntent()
}
