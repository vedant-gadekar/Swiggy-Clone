package com.example.instamart.state

import androidx.compose.ui.graphics.Color
import com.example.core.state.CarouselItem
import com.example.feature_instamart.utils.InstamartConstants
import com.example.feature_instamart.utils.InstamartConstants.InstamartDefaults.DEFAULT_SEARCH_HINT
import com.example.feature_instamart.utils.InstamartConstants.Strings.ZERO

data class InstamartState(
    val locationName: String = InstamartConstants.InstamartDefaults.DEFAULT_LOCATION_NAME,
    val locationAddress: String = InstamartConstants.InstamartDefaults.DEFAULT_LOCATION_ADDRESS,
    val searchQuery: String = "",
    val searchHint: String = DEFAULT_SEARCH_HINT,
    val isLoading: Boolean = false,
    val categories: List<CategoryData> = emptyList(),
    val fruits: List<ProductData> = emptyList(),
    val detergents: List<ProductData> = emptyList(),
    val biscuits: List<ProductData> = emptyList(),
    val promotionalSliders: List<SliderData> = emptyList(),
    val cartItems: Map<String, Int> = emptyMap(),
    val error: String? = null,

    val carouselItems: List<CarouselItem> = InstamartConstants.InstamartDefaults.defaultCarouselItems,
    val currentPage: Int = ZERO

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