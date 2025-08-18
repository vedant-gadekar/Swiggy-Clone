package com.example.swiggyy.feature_food.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .padding(vertical = 16.dp)
    ) {
        // Toggle Section with two tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // REORDER Tab
            TabButton(
                text = "REORDER",
                isSelected = selectedTab == RestaurantTab.REORDER,
                onClick = { selectedTab = RestaurantTab.REORDER },
                modifier = Modifier.weight(1f)
            )
            
            // FOOD IN 10 MINS Tab
            TabButton(
                text = "FOOD IN 10 MINS",
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
            containerColor = if (isSelected) Color.White else Color.Transparent
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = if (isSelected) CardDefaults.cardElevation(defaultElevation = 2.dp) else CardDefaults.cardElevation(defaultElevation = 0.dp)
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
