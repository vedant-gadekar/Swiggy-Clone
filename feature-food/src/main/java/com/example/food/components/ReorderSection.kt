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

@Composable
fun ReorderSection(
    restaurants: List<Restaurant>,
    onRestaurantClick: (Restaurant) -> Unit,
    onFavoriteClick: (Restaurant) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimensions.REORDER_SECTION_VERTICAL_PADDING)
    ) {
        // Section Header - REORDER button/tab
        Card(
            modifier = Modifier
                .padding(horizontal = Dimensions.REORDER_CARD_HORIZONTAL_PADDING, vertical = Dimensions.REORDER_CARD_VERTICAL_PADDING)
                .clickable { },
            colors = CardDefaults.cardColors(containerColor = Colors.WHITE),
            shape = RoundedCornerShape(Dimensions.REORDER_CARD_CORNER_RADIUS),
            elevation = CardDefaults.cardElevation(defaultElevation = Dimensions.REORDER_CARD_ELEVATION)
        ) {
            Text(
                text = Strings.REORDER,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = SwiggyFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Colors.ORANGE
                ),
                modifier = Modifier.padding(horizontal = Dimensions.REORDER_CARD_TEXT_HORIZONTAL_PADDING, vertical = Dimensions.REORDER_CARD_TEXT_VERTICAL_PADDING),
                textAlign = TextAlign.Center
            )
        }
        
        // Horizontal scrollable restaurant cards
        LazyRow(
            contentPadding = PaddingValues(horizontal = Dimensions.REORDER_LAZYROW_HORIZONTAL_PADDING),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.REORDER_LAZYROW_ITEM_SPACING)
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
