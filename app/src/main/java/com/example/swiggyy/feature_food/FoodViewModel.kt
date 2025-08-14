package com.example.swiggyy.feature_food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.example.swiggyy.R

class FoodViewModel : ViewModel() {

    private val _state = MutableStateFlow(FoodState())
    val state: StateFlow<FoodState> = _state.asStateFlow()

    init {
        handleIntent(FoodIntent.LoadData)
    }

    fun handleIntent(intent: FoodIntent) {
        when (intent) {
            is FoodIntent.LoadData -> {
                loadDummyData()
            }
            is FoodIntent.SearchQueryChanged -> {
                _state.update { it.copy(searchQuery = intent.query) }
            }


            is FoodIntent.CategoryClicked -> {
                // Handle category click
            }
            is FoodIntent.RestaurantClicked -> {
                // Handle restaurant click
            }
            is FoodIntent.StoreItemClicked -> {
                // Handle store item click
            }
            is FoodIntent.SwiggyOptionClicked -> {
                // Handle swiggy option click
            }
            is FoodIntent.FilterSelected -> {
                _state.update { it.copy(selectedFilter = intent.filter) }
            }
            is FoodIntent.SortSelected -> {
                _state.update { it.copy(selectedSort = intent.sort) }
            }

            is FoodIntent.LocationClicked -> {
                // Handle location click
            }
            is FoodIntent.SeeAllNinetyNineStore -> {
                // Handle see all 99 store
            }
        }
    }

    private fun loadDummyData() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    isLoading = false,

                    categories = getDummyCategories(),
                    reorderRestaurants = getDummyReorderRestaurants(),
                    quickDeliveryRestaurants = getDummyQuickDeliveryRestaurants(),
                    ninetyNineStoreItems = getDummyStoreItems(),
                    moreOnSwiggyOptions = getDummySwiggyOptions(),
                    featuredRestaurants = getDummyFeaturedRestaurants()
                )
            }
        }
    }



    private fun getDummyCategories(): List<FoodCategory> = listOf(
        FoodCategory(
            id = "1",
            name = "Burgers",
            imageRes = R.drawable.foodimage1
        ),
        FoodCategory(
            id = "2",
            name = "Pizza",
            imageRes = R.drawable.foodimage2
        ),
        FoodCategory(
            id = "3",
            name = "North Indian",
            imageRes = R.drawable.homeimage1
        ),
        FoodCategory(
            id = "4",
            name = "Biryani",
            imageRes = R.drawable.homeimage2
        ),
        FoodCategory(
            id = "5",
            name = "Chinese",
            imageRes = R.drawable.homeimage3
        ),
        FoodCategory(
            id = "6",
            name = "South Indian",
            imageRes = R.drawable.homeimage4
        ),
        FoodCategory(
            id = "7",
            name = "Desserts",
            imageRes = R.drawable.homeslide1
        ),
        FoodCategory(
            id = "8",
            name = "Beverages",
            imageRes = R.drawable.homeslide2
        ),
        FoodCategory(
            id = "9",
            name = "Fast Food",
            imageRes = R.drawable.homeslide3
        ),
        FoodCategory(
            id = "10",
            name = "Healthy",
            imageRes = R.drawable.homeslide4
        )
    )

    private fun getDummyReorderRestaurants(): List<Restaurant> = listOf(
        Restaurant(
            id = "1",
            name = "Wendy's Burgers",
            rating = 4.1,
            reviewCount = "10-15 mins",
            deliveryTime = "10-15 mins",
            cuisines = listOf("Burgers"),
            offer = "30% OFF UPTO ₹75",
            isAd = true,
            imageRes = R.drawable.foodimage1
        ),
        Restaurant(
            id = "2",
            name = "Domino's Pizza",
            rating = 4.4,
            reviewCount = "20-25 mins",
            deliveryTime = "20-25 mins",
            cuisines = listOf("Pizzas"),
            hasFreeDelivery = true,
            imageRes = R.drawable.foodimage2
        ),
        Restaurant(
            id = "3",
            name = "M M Restaurant",
            rating = 4.2,
            reviewCount = "50-55 mins",
            deliveryTime = "50-55 mins",
            cuisines = listOf("Indian"),
            hasOneBenefits = true,
            imageRes = R.drawable.homeimage1
        )
    )

    private fun getDummyQuickDeliveryRestaurants(): List<Restaurant> = listOf(
        Restaurant(
            id = "4",
            name = "Biktgane Biryani",
            rating = 4.3,
            reviewCount = "7.2K+",
            deliveryTime = "20-25 mins",
            cuisines = listOf("Biryani", "Hyderabadi", "Andhra"),
            location = "Baner",
            distance = "1.5 km",
            hasFreeDelivery = true,
            hasOneBenefits = true,
            imageRes = R.drawable.homeimage2
        ),
        Restaurant(
            id = "5",
            name = "Domino's Pizza",
            rating = 4.4,
            reviewCount = "16K+",
            deliveryTime = "20-25 mins",
            cuisines = listOf("Pizzas", "Italian", "Pastas"),
            location = "Baner",
            distance = "2.4 km",
            hasFreeDelivery = true,
            hasOneBenefits = true,
            imageRes = R.drawable.homeimage3
        ),
        Restaurant(
            id = "6",
            name = "The Good Bowl",
            rating = 4.3,
            reviewCount = "5.1K+",
            deliveryTime = "10-15 mins",
            cuisines = listOf("Biryani", "Pastas", "Punjabi"),
            location = "Balewadi",
            distance = "1.1 km",
            hasFreeDelivery = true,
            hasOneBenefits = true,
            imageRes = R.drawable.homeimage4
        )
    )

    private fun getDummyStoreItems(): List<StoreItem> = listOf(
        StoreItem(
            id = "1",
            name = "Aloo Paratha",
            originalPrice = 100,
            discountedPrice = 59,
            rating = 4.4,
            reviewCount = "84",
            description = "Akshay Pure Veg",
            imageRes = R.drawable.homeslide1
        ),
        StoreItem(
            id = "2",
            name = "Shegaon Kachori",
            originalPrice = 25,
            discountedPrice = 19,
            rating = 4.5,
            reviewCount = "763",
            description = "Shegaon Kachori",
            imageRes = R.drawable.homeslide2
        ),
        StoreItem(
            id = "3",
            name = "Balloons Heart Shape...",
            originalPrice = 69,
            discountedPrice = 49,
            rating = 3.3,
            reviewCount = "1",
            description = "Schokolade Pati...",
            imageRes = R.drawable.homeslide3
        )
    )

    private fun getDummySwiggyOptions(): List<SwiggyOption> = listOf(
        SwiggyOption(
            id = "1",
            title = "OFFER",
            subtitle = "ZONE"
        ),
        SwiggyOption(
            id = "2",
            title = "BOLT",
            subtitle = "FOOD IN 10 MINS"
        ),
        SwiggyOption(
            id = "3",
            title = "99 STORE",
            subtitle = "MEALS AT ₹99"
        ),
        SwiggyOption(
            id = "4",
            title = "HIGH PROTEIN",
            subtitle = "30+ GRAMS"
        )
    )

    private fun getDummyFeaturedRestaurants(): List<Restaurant> = listOf(
        Restaurant(
            id = "7",
            name = "Honest Bowl",
            rating = 4.1,
            reviewCount = "10-15 mins",
            deliveryTime = "10-15 mins",
            cuisines = listOf("Kebabs", "Fast Food", "Snacks"),
            offer = "3 DEALS LEFT",
            hasFreeDelivery = true,
            hasOneBenefits = true,
            imageRes = R.drawable.homeslide4
        ),
        Restaurant(
            id = "8",
            name = "Mama Song",
            rating = 4.9,
            reviewCount = "30-35 mins",
            deliveryTime = "30-35 mins",
            cuisines = listOf("Chinese", "Thai", "Asian"),
            offer = "60% OFF UPTO ₹120",
            hasFreeDelivery = true,
            hasOneBenefits = true,
            imageRes = R.drawable.homeslide5
        ),
        Restaurant(
            id = "9",
            name = "FLYP Cafe",
            rating = 4.2,
            reviewCount = "25-30 mins",
            deliveryTime = "25-30 mins",
            cuisines = listOf("Coffee", "Wraps", "Sandwiches"),
            hasFreeDelivery = true,
            hasOneBenefits = true,
            imageRes = R.drawable.homeslide6
        ),
        Restaurant(
            id = "10",
            name = "LunchBox - Meals and Thalis",
            rating = 4.3,
            reviewCount = "10-15 mins",
            deliveryTime = "10-15 mins",
            cuisines = listOf("North Indian", "Biryani"),
            hasFreeDelivery = true,
            hasOneBenefits = true,
            imageRes = R.drawable.homeslide7
        )
    )
}
