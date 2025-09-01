package com.example.food.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.feature_food.presentation.constants.Colors
import com.example.feature_food.presentation.constants.Dimensions
import com.example.feature_food.presentation.constants.Strings
import com.example.swiggyy.feature_food.model.Restaurant
import com.example.swiggyy.ui.theme.SwiggyFontFamily

enum class RestaurantTab {
    REORDER,
    QUICK_DELIVERY
}

@Composable
fun RestaurantCarousel(
    reorderRestaurants: List<Restaurant>,
    quickDeliveryRestaurants: List<Restaurant>,
    onRestaurantClick: (Restaurant) -> Unit,
    onFavoriteClick: (Restaurant) -> Unit
) {
    var selectedTab by remember { mutableStateOf(RestaurantTab.REORDER) }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimensions.CAROUSEL_VERTICAL_PADDING)
    ) {
        // Toggle Section with two tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimensions.CAROUSEL_TAB_HORIZONTAL_PADDING, vertical = Dimensions.CAROUSEL_TAB_VERTICAL_PADDING),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.CAROUSEL_TAB_SPACING)
        ) {
            // REORDER Tab
            TabButton(
                text = Strings.TAB_REORDER,
                isSelected = selectedTab == RestaurantTab.REORDER,
                onClick = { selectedTab = RestaurantTab.REORDER },
                modifier = Modifier.weight(1f)
            )
            // QUICK DELIVERY Tab
            TabButton(
                text = Strings.TAB_QUICK_DELIVERY,
                isSelected = selectedTab == RestaurantTab.QUICK_DELIVERY,
                onClick = { selectedTab = RestaurantTab.QUICK_DELIVERY },
                modifier = Modifier.weight(1f)
            )
        }
        
        // Restaurant Carousel based on selected tab
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val restaurants = when (selectedTab) {
                RestaurantTab.REORDER -> reorderRestaurants
                RestaurantTab.QUICK_DELIVERY -> quickDeliveryRestaurants
            }
            
            items(restaurants) { restaurant ->
                RestaurantCard(
                    restaurant = restaurant,
                    onClick = { onRestaurantClick(restaurant) },
                    onFavoriteClick = { onFavoriteClick(restaurant) }
                )
            }
        }
    }
}

@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Colors.WHITE else Color.Transparent
        ),
        shape = RoundedCornerShape(Dimensions.RESTAURANT_CARD_CORNER_RADIUS),
        elevation = if (isSelected) CardDefaults.cardElevation(defaultElevation = Dimensions.RESTAURANT_CARD_ELEVATION_PRESSED) else CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = SwiggyFontFamily,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color(0xFFFF6F00) else Color.Gray
            ),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
            textAlign = TextAlign.Center
        )
    }
}
