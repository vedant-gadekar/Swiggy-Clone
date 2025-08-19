package com.example.swiggyy.feature_food.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swiggyy.feature_food.model.Restaurant
import com.example.swiggyy.ui.theme.SwiggyFontFamily
import com.example.swiggyy.feature_food.components.RestaurantCard

@Composable
fun QuickDeliverySection(
    restaurants: List<Restaurant>,
    onRestaurantClick: (Restaurant) -> Unit,
    onFavoriteClick: (Restaurant) -> Unit
) {
    // Animation for the section
    var isVisible by remember { mutableStateOf(false) }
    
    LaunchedEffect(key1 = true) {
        isVisible = true
    }
    
    val slideInOffset by animateFloatAsState(
        targetValue = if (isVisible) 0f else 100f,
        animationSpec = tween(500)
    )
    
    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(700)
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .graphicsLayer(
                translationX = slideInOffset,
                alpha = alpha
            ),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            // Enhanced Section Header with icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Timer icon
                Card(
                    shape = androidx.compose.foundation.shape.CircleShape,
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFF6F00)),
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(
                            painter = painterResource(com.example.swiggyy.R.drawable.carousel),
                            contentDescription = "Timer",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                // Enhanced text with shadow
                Text(
                    text = "FOOD IN 10 MINS",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = SwiggyFontFamily,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = Color(0xFFFF6F00),
                        shadow = androidx.compose.ui.graphics.Shadow(
                            color = Color.Gray.copy(alpha = 0.3f),
                            offset = androidx.compose.ui.geometry.Offset(1f, 1f),
                            blurRadius = 1f
                        )
                    ),
                    textAlign = TextAlign.Center
                )
            }
            
            // Subtitle
            Text(
                text = "Quick delivery to your doorstep",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = SwiggyFontFamily,
                    color = Color.Gray
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
            )
            
            // Horizontal scrollable restaurant cards with improved spacing
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
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
}


