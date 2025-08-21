package com.example.swiggyy.feature_food.model

data class Restaurant(
    val id: String,
    val name: String,
    val imageRes: Int,
    val rating: Float,
    val reviewCount: Int,
    val deliveryTime: String,
    val cuisines: List<String>,
    val offer: String = "",
    val isAd: Boolean = false,
    val hasFreeDelivery: Boolean = false,
    val hasOneBenefits: Boolean = false,
    val isFavorite: Boolean = false
) {
    /**
     * Returns the first 2-3 cuisines as a formatted string
     */
    fun getFormattedCuisines(maxCuisines: Int = 3): String {
        return cuisines.take(maxCuisines).joinToString(" • ")
    }
    
    /**
     * Returns the rating as a formatted string (e.g., "4.2 (500+)")
     */
    fun getFormattedRating(): String {
        return "$rating (${reviewCount}+)"
    }
}

data class StoreItem(
    val id: String,
    val name: String,
    val imageRes: Int,
    val originalPrice: Int,
    val discountedPrice: Int,
    val rating: Float,
    val reviewCount: Int,
    val description: String
)

data class SwiggyOption(
    val id: String,
    val title: String,
    val imageRes: Int,
    val description: String = ""
)

data class Category(
    val id: String,
    val name: String,
    val imageRes: Int
)
