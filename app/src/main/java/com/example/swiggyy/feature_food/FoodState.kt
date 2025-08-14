package com.example.swiggyy.feature_food

import com.example.swiggyy.R

data class FoodState(
    val isLoading: Boolean = false,
    val locationName: String = "Amar Business Zone",
    val locationAddress: String = "Baner - Mahalunge Road, Veerbhadra...",
    val searchQuery: String = "",
    val searchHint: String = "Search for 'Cake' 'Sweets'",

    val categories: List<FoodCategory> = emptyList(),
    val reorderRestaurants: List<Restaurant> = emptyList(),
    val quickDeliveryRestaurants: List<Restaurant> = emptyList(),
    val ninetyNineStoreItems: List<StoreItem> = emptyList(),
    val moreOnSwiggyOptions: List<SwiggyOption> = emptyList(),
    val featuredRestaurants: List<Restaurant> = emptyList(),
    val selectedFilter: String = "",
    val selectedSort: String = "",

)



data class FoodCategory(
    val id: String,
    val name: String,
    val imageRes: Int = R.drawable.carousel
)

data class Restaurant(
    val id: String,
    val name: String,
    val rating: Double,
    val reviewCount: String,
    val deliveryTime: String,
    val cuisines: List<String>,
    val location: String = "",
    val distance: String = "",
    val offer: String = "",
    val hasFreeDelivery: Boolean = false,
    val hasOneBenefits: Boolean = false,
    val imageRes: Int = R.drawable.carousel,
    val isAd: Boolean = false
)

data class StoreItem(
    val id: String,
    val name: String,
    val originalPrice: Int,
    val discountedPrice: Int,
    val rating: Double,
    val reviewCount: String,
    val description: String,
    val imageRes: Int = R.drawable.carousel
)

data class SwiggyOption(
    val id: String,
    val title: String,
    val subtitle: String,
    val imageRes: Int = R.drawable.carousel
)
