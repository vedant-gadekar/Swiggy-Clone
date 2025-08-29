package com.example.core.search.state
import com.example.core.R

data class SearchState(
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val trendingSearches: List<TrendingSearchData> = defaultTrendingSearches,
    val popularItems: List<PopularItemData> = defaultPopularItems,
    val popularCuisines: List<PopularCuisineData> = defaultPopularCuisines,
    val error: String? = null
)

data class TrendingSearchData(
    val id: String,
    val text: String
)

data class PopularItemData(
    val id: String,
    val name: String,
    val imageUrl: Int,
    val backgroundColor: String = "#F0F0F0"
)

data class PopularCuisineData(
    val id: String,
    val name: String,
    val imageUrl: Int
)

// Default data
private val defaultTrendingSearches = listOf(
    TrendingSearchData("1", "Irani Cafe"),
    TrendingSearchData("2", "Cafe Goodluck"),
    TrendingSearchData("3", "Upma"),
    TrendingSearchData("4", "Katakirrr Misal"),
    TrendingSearchData("5", "Sabudana Khichdi")
)

private val defaultPopularItems = listOf(
    PopularItemData(
        id = "vegetables",
        name = "Vegetables",
        R.drawable.vegies,
        backgroundColor = "#E8F5E8"
    ),
    PopularItemData(
        id = "fruits",
        name = "Fruits",
        R.drawable.bananas,
        backgroundColor = "#FFF3E0"
    ),
    PopularItemData(
        id = "snacks",
        name = "Snacks",
        R.drawable.snacks,
        backgroundColor = "#E3F2FD"
    ),
    PopularItemData(
        id = "ice_cream",
        name = "Ice Cream",
        R.drawable.foodimage1,
        backgroundColor = "#F3E5F5"
    )
)

private val defaultPopularCuisines = listOf(
    PopularCuisineData(
        id = "biryani",
        name = "Biryani",
        R.drawable.icon_swiggy
        ),
    PopularCuisineData(
        id = "rolls",
        name = "Rolls",
        R.drawable.icon_swiggy
    ),
    PopularCuisineData(
        id = "pizzas",
        name = "Pizzas",
        R.drawable.icon_swiggy
    ),
    PopularCuisineData(
        id = "tea",
        name = "Tea",
        R.drawable.icon_swiggy
    )
)
