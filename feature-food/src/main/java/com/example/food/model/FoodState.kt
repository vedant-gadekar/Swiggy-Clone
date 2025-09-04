package com.example.swiggyy.bottomNavBar.food.state

import com.example.swiggyy.feature_food.model.Category
import com.example.swiggyy.feature_food.model.Restaurant
import com.example.swiggyy.feature_food.model.StoreItem
import com.example.swiggyy.feature_food.model.SwiggyOption

/**
 * Represents the state of the Food feature UI.
 *
 * @property isLoading Indicates if data is being loaded
 * @property locationName The current location name
 * @property locationAddress The current location address
 * @property searchQuery The current search query
 * @property searchHint The hint text for the search bar
 * @property categories The list of food categories
 * @property reorderRestaurants The list of restaurants for reordering
 * @property quickDeliveryRestaurants The list of restaurants with quick delivery
 * @property featuredRestaurants The list of featured restaurants
 * @property ninetyNineStoreItems The list of items in the 99 Store section
 * @property moreOnSwiggyOptions The list of Swiggy options (e.g., Swiggy One, Dineout)
 * @property selectedFilter The currently selected filter
 * @property selectedSort The currently selected sort option
 * @property error The error message if an error occurred, null otherwise
 */
data class FoodState(
    val isLoading: Boolean = false,
    val locationName: String = "Amar Business Zone",
    val locationAddress: String = "Baner - Mahalunge Road, Veerbhadra...",
    val searchQuery: String = "",
    val searchHint: String = "Search for 'Cake' 'Sweets'",

    // UI states for different sections
    val categories: UiState<List<Category>> = UiState.Success(emptyList()),
    val reorderRestaurants: UiState<List<Restaurant>> = UiState.Success(emptyList()),
    val quickDeliveryRestaurants: UiState<List<Restaurant>> = UiState.Success(emptyList()),
    val featuredRestaurants: UiState<List<Restaurant>> = UiState.Success(emptyList()),
    val ninetyNineStoreItems: UiState<List<StoreItem>> = UiState.Success(emptyList()),
    val swiggyOptions: UiState<List<SwiggyOption>> = UiState.Success(emptyList()),

    // Selections
    val selectedFilter: String = "",
    val selectedSort: String = "",

    // Error state
    val error: String? = null
) {
    /**
     * Returns true if any of the data is currently loading.
     */
    val isLoadingAny: Boolean
        get() = isLoading ||
                categories is UiState.Loading ||
                reorderRestaurants is UiState.Loading ||
                quickDeliveryRestaurants is UiState.Loading ||
                featuredRestaurants is UiState.Loading ||
                ninetyNineStoreItems is UiState.Loading ||
                swiggyOptions is UiState.Loading

    /**
     * Returns true if there's an error in any of the data.
     */
    val hasError: Boolean
        get() = categories is UiState.Error ||
                reorderRestaurants is UiState.Error ||
                quickDeliveryRestaurants is UiState.Error ||
                featuredRestaurants is UiState.Error ||
                ninetyNineStoreItems is UiState.Error ||
                swiggyOptions is UiState.Error ||
                error != null

    /**
     * Returns the first error message found, or null if there are no errors.
     */
    fun getFirstErrorMessage(): String? {
        return when {
            error != null -> error
            categories is UiState.Error -> categories.message
            reorderRestaurants is UiState.Error -> reorderRestaurants.message
            quickDeliveryRestaurants is UiState.Error -> quickDeliveryRestaurants.message
            featuredRestaurants is UiState.Error -> featuredRestaurants.message
            ninetyNineStoreItems is UiState.Error -> ninetyNineStoreItems.message
            swiggyOptions is UiState.Error -> swiggyOptions.message
            else -> null
        }
    }
}