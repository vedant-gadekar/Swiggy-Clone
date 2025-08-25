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
)
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
