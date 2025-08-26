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
import com.example.swiggyy.feature_food.model.Restaurant
import com.example.swiggyy.ui.theme.SwiggyFontFamily

@Composable
fun ReorderSection(
    restaurants: List<Restaurant>,
    onRestaurantClick: (Restaurant) -> Unit,
    onFavoriteClick: (Restaurant) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        // Section Header - REORDER button/tab
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable { },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Text(
                text = "REORDER",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = SwiggyFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF6F00)
                ),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                textAlign = TextAlign.Center
            )
        }
        
        // Horizontal scrollable restaurant cards
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
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


