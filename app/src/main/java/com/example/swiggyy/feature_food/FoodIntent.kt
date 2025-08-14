package com.example.swiggyy.feature_food

sealed class FoodIntent {
    object LoadData : FoodIntent()
    data class SearchQueryChanged(val query: String) : FoodIntent()

    data class CategoryClicked(val category: FoodCategory) : FoodIntent()
    data class RestaurantClicked(val restaurant: Restaurant) : FoodIntent()
    data class StoreItemClicked(val item: StoreItem) : FoodIntent()
    data class SwiggyOptionClicked(val option: SwiggyOption) : FoodIntent()
    data class FilterSelected(val filter: String) : FoodIntent()
    data class SortSelected(val sort: String) : FoodIntent()

    object LocationClicked : FoodIntent()
    object SeeAllNinetyNineStore : FoodIntent()
}
