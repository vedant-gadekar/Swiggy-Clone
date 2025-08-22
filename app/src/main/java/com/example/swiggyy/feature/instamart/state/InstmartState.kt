package com.example.instamart.state

import androidx.compose.ui.graphics.Color
import com.example.swiggyy.R
import com.example.swiggyy.feature_home.CarouselItem

data class InstamartState(
    val locationName: String = "Amar Business Zone",
    val locationAddress: String = "Baner - Mahalunge Road, Veerbhadra...",
    val searchQuery: String = "",
    val searchHint: String = "Search for 'Cake' 'Sweets'",
    val isLoading: Boolean = false,
    val categories: List<CategoryData> = emptyList(),
    val fruits: List<ProductData> = emptyList(),
    val detergents: List<ProductData> = emptyList(),
    val biscuits: List<ProductData> = emptyList(),
    val promotionalSliders: List<SliderData> = emptyList(),
    val cartItems: Map<String, Int> = emptyMap(),
    val error: String? = null,

    val carouselItems: List<CarouselItem> = listOf(
        CarouselItem(
            "FRESH DEALS TODAY",
            "LIMITED TIME",
            "Up to 50% OFF",
            "On fresh fruits & vegetables!",
            "Shop Now",
            R.drawable.vegetables
        ),
        CarouselItem(
            "DAIRY ESSENTIALS",
            "STARTING AT ₹49",
            "Milk, Butter & More",
            "Delivered in minutes to your doorstep!",
            "Order Now",
            R.drawable.dairy_eggs
        ),
        CarouselItem(
            "INSTANT SNACKS",
            "BUY 1 GET 1 FREE",
            "Your favorite chips & biscuits",
            "Hurry! Limited stock available.",
            "Grab Now",
            R.drawable.snacks
        ),
        CarouselItem(
            "HOUSEHOLD MUST-HAVES",
            "EVERYDAY LOW PRICES",
            "Cleaning & Kitchen Essentials",
            "Never run out of daily needs!",
            "Shop Essentials",

            R.drawable.household
        )
    ),
    val currentPage: Int = 0

)

data class CategoryData(
    val id: String,
    val name: String,
    val imageUrl: Int
)

data class ProductData(
    val id: String,
    val name: String,
    val rating: String,
    val price: String,
    val imageUrl: Int,
    val category: String,
    val quantity: Int = 0
)

data class SliderData(
    val id: String,
    val title: String,
    val subtitle: String,
    val buttonText: String,
    val backgroundColor: Color,
    val textColor: Color,
    val buttonBackgroundColor: Color,
    val buttonTextColor: Color,
    val imageUrl: Int,
    val isLarge: Boolean
)
