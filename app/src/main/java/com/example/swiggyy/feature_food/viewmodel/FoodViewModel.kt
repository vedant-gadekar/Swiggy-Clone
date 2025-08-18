package com.example.swiggyy.feature_food.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swiggyy.feature_food.intent.FoodIntent
import com.example.swiggyy.feature_food.model.*
import com.example.swiggyy.feature_food.state.FoodState
import com.example.swiggyy.feature_food.state.UiState
import com.example.swiggyy.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FoodViewModel : ViewModel() {
    
    private val _state = MutableStateFlow(FoodState())
    val state: StateFlow<FoodState> = _state.asStateFlow()
    
    init {
        loadInitialData()
    }
    
    fun handleIntent(intent: FoodIntent) {
        when (intent) {
            is FoodIntent.LoadData -> loadInitialData()
            is FoodIntent.SearchQueryChanged -> updateSearchQuery(intent.query)
            is FoodIntent.CategoryClicked -> handleCategoryClick(intent.category)
            is FoodIntent.RestaurantClicked -> handleRestaurantClick(intent.restaurant)
            is FoodIntent.StoreItemClicked -> handleStoreItemClick(intent.item)
            is FoodIntent.SwiggyOptionClicked -> handleSwiggyOptionClick(intent.option)
            is FoodIntent.FilterSelected -> updateFilter(intent.filter)
            is FoodIntent.SortSelected -> updateSort(intent.sort)
            is FoodIntent.LocationClicked -> handleLocationClick()
            is FoodIntent.SeeAllNinetyNineStore -> handleSeeAllNinetyNineStore()
            is FoodIntent.ToggleFavorite -> toggleFavorite(intent.restaurantId, intent.isFavorite)
            is FoodIntent.Retry -> loadInitialData()
        }
    }
    
    private fun loadInitialData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            try {
                // Load categories
                _state.update { 
                    it.copy(
                        categories = UiState.Success(getSampleCategories()),
                        isLoading = false
                    )
                }
                
                // Load restaurants
                _state.update {
                    it.copy(
                        reorderRestaurants = UiState.Success(getSampleReorderRestaurants()),
                        quickDeliveryRestaurants = UiState.Success(getSampleQuickDeliveryRestaurants()),
                        featuredRestaurants = UiState.Success(getSampleFeaturedRestaurants()),
                        ninetyNineStoreItems = UiState.Success(getSampleStoreItems()),
                        swiggyOptions = UiState.Success(getSampleSwiggyOptions())
                    )
                }
            } catch (e: Exception) {
                _state.update { 
                    it.copy(
                        error = e.message ?: "An error occurred",
                        isLoading = false
                    )
                }
            }
        }
    }
    
    private fun updateSearchQuery(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }
    
    private fun handleCategoryClick(category: Category) {
        // Handle category click - could navigate to category-specific screen
        // For now, just update search query
        _state.update { it.copy(searchQuery = category.name) }
    }
    
    private fun handleRestaurantClick(restaurant: Restaurant) {
        // Handle restaurant click - could navigate to restaurant details
        // For now, just log or show a toast
    }
    
    private fun handleStoreItemClick(item: StoreItem) {
        // Handle store item click
    }
    
    private fun handleSwiggyOptionClick(option: SwiggyOption) {
        // Handle Swiggy option click
    }
    
    private fun updateFilter(filter: String) {
        _state.update { it.copy(selectedFilter = filter) }
    }
    
    private fun updateSort(sort: String) {
        _state.update { it.copy(selectedSort = sort) }
    }
    
    private fun handleLocationClick() {
        // Handle location click
    }
    
    private fun handleSeeAllNinetyNineStore() {
        // Handle see all 99 store click
    }
    
    private fun toggleFavorite(restaurantId: String, isFavorite: Boolean) {
        // Update the favorite status of the restaurant
        val currentRestaurants = _state.value.featuredRestaurants.getOrNull() ?: emptyList()
        val updatedRestaurants = currentRestaurants.map { restaurant ->
            if (restaurant.id == restaurantId) {
                restaurant.copy(isFavorite = isFavorite)
            } else {
                restaurant
            }
        }
        _state.update { 
            it.copy(featuredRestaurants = UiState.Success(updatedRestaurants))
        }
    }
    
    // Sample data methods
    private fun getSampleCategories(): List<Category> {
        return listOf(
            Category("1", "Pizza", R.drawable.foodimage1),
            Category("2", "Burgers", R.drawable.foodimage2),
            Category("3", "Biryani", R.drawable.foodimage1),
            Category("4", "Desserts", R.drawable.foodimage2),
            Category("5", "Beverages", R.drawable.foodimage1),
            Category("6", "Chinese", R.drawable.foodimage2),
            Category("7", "South Indian", R.drawable.foodimage1),
            Category("8", "North Indian", R.drawable.foodimage2)
        )
    }
    
    private fun getSampleReorderRestaurants(): List<Restaurant> {
        return listOf(
            Restaurant(
                id = "1",
                name = "Wendy's Burgers",
                imageRes = R.drawable.foodimage1,
                rating = 4.1f,
                reviewCount = 450,
                deliveryTime = "10-15 mins",
                cuisines = listOf("Burgers"),
                offer = "30% OFF",
                hasOneBenefits = true,
                isAd = true,
                isFavorite = false
            ),
            Restaurant(
                id = "2",
                name = "Domino's Pizza",
                imageRes = R.drawable.foodimage2,
                rating = 4.4f,
                reviewCount = 600,
                deliveryTime = "20-25 mins",
                cuisines = listOf("Pizzas"),
                offer = "Domino's Free Delivery",
                hasOneBenefits = true,
                isAd = false,
                isFavorite = true
            ),
            Restaurant(
                id = "3",
                name = "M M Restaurant",
                imageRes = R.drawable.foodimage1,
                rating = 4.2f,
                reviewCount = 380,
                deliveryTime = "50-55 mins",
                cuisines = listOf("Indian"),
                offer = "ITEMS AT ₹49",
                hasOneBenefits = false,
                isAd = false,
                isFavorite = false
            )
        )
    }
    
    private fun getSampleQuickDeliveryRestaurants(): List<Restaurant> {
        return listOf(
            Restaurant(
                id = "4",
                name = "Subway",
                imageRes = R.drawable.foodimage1,
                rating = 3.8f,
                reviewCount = 200,
                deliveryTime = "10-15 min",
                cuisines = listOf("Sandwiches"),
                offer = "30% OFF",
                hasOneBenefits = false,
                isAd = false,
                isFavorite = false
            ),
            Restaurant(
                id = "5",
                name = "KFC",
                imageRes = R.drawable.foodimage2,
                rating = 4.1f,
                reviewCount = 400,
                deliveryTime = "12-18 min",
                cuisines = listOf("Chicken"),
                offer = "45% OFF",
                hasOneBenefits = true,
                isAd = true,
                isFavorite = true
            ),
            Restaurant(
                id = "6",
                name = "Pizza Express",
                imageRes = R.drawable.foodimage1,
                rating = 4.0f,
                reviewCount = 320,
                deliveryTime = "8-12 min",
                cuisines = listOf("Pizza"),
                offer = "ITEMS AT ₹99",
                hasOneBenefits = false,
                isAd = false,
                isFavorite = false
            )
        )
    }
    
    private fun getSampleFeaturedRestaurants(): List<Restaurant> {
        return listOf(
            Restaurant(
                id = "5",
                name = "Pizza Hut",
                imageRes = R.drawable.foodimage1,
                rating = 4.3f,
                reviewCount = 600,
                deliveryTime = "35-40 min",
                cuisines = listOf("Pizza", "Italian", "Fast Food"),
                offer = "60% OFF up to ₹120",
                hasOneBenefits = true,
                isFavorite = true
            ),
            Restaurant(
                id = "6",
                name = "Burger King",
                imageRes = R.drawable.foodimage2,
                rating = 4.0f,
                reviewCount = 350,
                deliveryTime = "28-33 min",
                cuisines = listOf("Burgers", "Fast Food", "American"),
                offer = "35% OFF up to ₹70"
            ),
            Restaurant(
                id = "7",
                name = "Haldiram's",
                imageRes = R.drawable.foodimage1,
                rating = 4.4f,
                reviewCount = 450,
                deliveryTime = "25-30 min",
                cuisines = listOf("North Indian", "Sweets", "Snacks"),
                offer = "25% OFF up to ₹50",
                hasFreeDelivery = true
            )
        )
    }
    
    private fun getSampleStoreItems(): List<StoreItem> {
        return listOf(
            StoreItem(
                id = "1",
                name = "Organic Bananas",
                imageRes = R.drawable.foodimage1,
                originalPrice = 80,
                discountedPrice = 49,
                rating = 4.2f,
                reviewCount = 150,
                description = "Fresh organic bananas, 6 pieces"
            ),
            StoreItem(
                id = "2",
                name = "Milk 1L",
                imageRes = R.drawable.foodimage2,
                originalPrice = 60,
                discountedPrice = 45,
                rating = 4.0f,
                reviewCount = 200,
                description = "Fresh cow milk, 1 liter"
            )
        )
    }
    
    private fun getSampleSwiggyOptions(): List<SwiggyOption> {
        return listOf(
            SwiggyOption(
                id = "1",
                title = "Swiggy One",
                imageRes = R.drawable.foodimage1,
                description = "Free delivery on all orders"
            ),
            SwiggyOption(
                id = "2",
                title = "Dineout",
                imageRes = R.drawable.foodimage2,
                description = "Book tables at restaurants"
            )
        )
    }
}
