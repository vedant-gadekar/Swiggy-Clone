package com.example.core.state

import androidx.compose.ui.graphics.Color
import com.example.core.R

data class CarouselItem(
    val overline:String?,
    val chip:String? ,
    val title: String,
    val subtitle: String,
    val buttonText: String,
    val imageRes: Int
)

data class TrendingSearch(
    val text: String
)

data class PopularItem(
    val id: String,
    val name: String,
    val imageUrl: Int,
    val backgroundColor: Color = Color(0xFFF0F0F0)
)

data class PopularCuisine(
    val id: String,
    val name: String,
    val imageUrl: Int
)

val trendingSearches = listOf(
    TrendingSearch("Irani Cafe"),
    TrendingSearch("Cafe Goodluck"),
    TrendingSearch("Upma"),
    TrendingSearch("Katakirrr Misal"),
    TrendingSearch("Sabudana Khichdi")
)

val popularItems = listOf(
    PopularItem(
        id = "vegetables",
        name = "Vegetables",
        imageUrl = R.drawable.vegies,
        backgroundColor = Color(0xFFE8F5E8)
    ),
    PopularItem(
        id = "fruits",
        name = "Fruits",
        R.drawable.bananas,
        backgroundColor = Color(0xFFFFF3E0)
    ),
    PopularItem(
        id = "chips",
        name = "Chips",
        R.drawable.snacks,
        backgroundColor = Color(0xFFE3F2FD)
    ),
    PopularItem(
        id = "ice_cream",
        name = "Ice Cream",
        R.drawable.icon_swiggy,
        backgroundColor = Color(0xFFF3E5F5)
    )
)

val popularCuisines = listOf(
    PopularCuisine(
        id = "biryani",
        name = "Biryani",
        R.drawable.foodimage1
    ),
    PopularCuisine(
        id = "rolls",
        name = "Rolls",
        R.drawable.foodimage1
    ),
    PopularCuisine(
        id = "pizzas",
        name = "Pizzas",
        R.drawable.foodimage1
    ),
    PopularCuisine(
        id = "tea",
        name = "Tea",
        R.drawable.foodimage1
    )
)
