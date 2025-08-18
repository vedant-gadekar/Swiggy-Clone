package com.example.swiggyy.feature_food.intent

import com.example.swiggyy.feature_food.model.*

/**
 * Sealed class representing all possible user intents in the Food feature.
 */
sealed class FoodIntent {
    /**
     * Triggered to load initial data or refresh all data.
     */
    object LoadData : FoodIntent()
    
    /**
     * Triggered when the user types in the search bar.
     * @property query The current search query
     */
    data class SearchQueryChanged(val query: String) : FoodIntent()
    
    /**
     * Triggered when a category is clicked.
     * @property category The clicked category
     */
    data class CategoryClicked(val category: Category) : FoodIntent()
    
    /**
     * Triggered when a restaurant is clicked.
     * @property restaurant The clicked restaurant
     */
    data class RestaurantClicked(val restaurant: Restaurant) : FoodIntent()
    
    /**
     * Triggered when a store item is clicked.
     * @property item The clicked store item
     */
    data class StoreItemClicked(val item: StoreItem) : FoodIntent()
    
    /**
     * Triggered when a Swiggy option is clicked (e.g., Swiggy One).
     * @property option The clicked Swiggy option
     */
    data class SwiggyOptionClicked(val option: SwiggyOption) : FoodIntent()
    
    /**
     * Triggered when a filter is selected.
     * @property filter The selected filter
     */
    data class FilterSelected(val filter: String) : FoodIntent()
    
    /**
     * Triggered when a sort option is selected.
     * @property sort The selected sort option
     */
    data class SortSelected(val sort: String) : FoodIntent()
    
    /**
     * Triggered when the location is clicked.
     */
    object LocationClicked : FoodIntent()
    
    /**
     * Triggered when "See All" is clicked for the 99 Store section.
     */
    object SeeAllNinetyNineStore : FoodIntent()
    
    /**
     * Triggered when the favorite status of a restaurant is toggled.
     * @property restaurantId The ID of the restaurant
     * @property isFavorite The new favorite status
     */
    data class ToggleFavorite(val restaurantId: String, val isFavorite: Boolean) : FoodIntent()
    
    /**
     * Triggered when the user wants to retry a failed operation.
     */
    object Retry : FoodIntent()
}
