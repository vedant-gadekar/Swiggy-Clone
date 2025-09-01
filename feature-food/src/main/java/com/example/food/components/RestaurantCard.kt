package com.example.food.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.food.R
import com.example.feature_food.presentation.constants.Animation
import com.example.feature_food.presentation.constants.Colors
import com.example.feature_food.presentation.constants.Dimensions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swiggyy.feature_food.model.Restaurant
import com.example.swiggyy.ui.theme.SwiggyFontFamily
import kotlinx.coroutines.MainScope

@Composable
fun RestaurantCard(
    restaurant: Restaurant,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    // Press animation
    var isPressed by remember { mutableStateOf(false) }
    
    // Card entrance animation
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        isVisible = true
    }
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) Animation.SCALE_PRESSED else Animation.SCALE_DEFAULT,
        animationSpec = tween(Animation.CARD_PRESS_ANIMATION_DURATION)
    )
    val cardElevation by animateFloatAsState(
        targetValue = if (isPressed) Dimensions.RESTAURANT_CARD_ELEVATION_PRESSED.value else Dimensions.RESTAURANT_CARD_ELEVATION_DEFAULT.value,
        animationSpec = tween(Animation.CARD_PRESS_ANIMATION_DURATION)
    )
    val slideInOffset by animateFloatAsState(
        targetValue = if (isVisible) 0f else Animation.SLIDE_IN_OFFSET,
        animationSpec = tween(Animation.CARD_ANIMATION_DURATION, delayMillis = Animation.CARD_ANIMATION_DELAY)
    )
    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(Animation.CARD_ANIMATION_DURATION, delayMillis = Animation.CARD_ANIMATION_DELAY)
    )
    Card(
        modifier = Modifier
            .width(Dimensions.RESTAURANT_CARD_WIDTH)
            .graphicsLayer(alpha = alpha, scaleX = scale, scaleY = scale, translationY = slideInOffset)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        tryAwaitRelease()
                        isPressed = false
                        onClick()
                    }
                )
            },
        colors = CardDefaults.cardColors(containerColor = Colors.WHITE),
        shape = RoundedCornerShape(Dimensions.RESTAURANT_CARD_CORNER_RADIUS),
        elevation = CardDefaults.cardElevation(defaultElevation = cardElevation.dp)
    ) {
        Column {
            // Image section with overlays
            Box {
                // Enhanced image with rounded corners
                Image(
                    painter = painterResource(restaurant.imageRes),
                    contentDescription = restaurant.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    contentScale = ContentScale.Crop
                )
                
                // Gradient overlay for better text visibility
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.1f),
                                    Color.Black.copy(alpha = 0.3f)
                                ),
                                startY = 0f,
                                endY = Float.POSITIVE_INFINITY
                            )
                        )
                )
                
                // Enhanced Top-left "one" badge with animation
                if (restaurant.hasOneBenefits) {
                    // Badge animation
                    val badgeScale by animateFloatAsState(
                targetValue = if (isVisible) 1f else 0f,
                animationSpec = tween(500, delayMillis = 300)
            )
                    
                    Card(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(12.dp)
                            .graphicsLayer(alpha = badgeScale, scaleX = badgeScale, scaleY = badgeScale),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Text(
                            text = "one",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = SwiggyFontFamily,
                            color = Color(0xFFFF6F00),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
                
                // Enhanced Top-right heart icon with animation
                var heartScale by remember { mutableStateOf(1f) }
                
                // Heart animation
                val heartScaleAnim by animateFloatAsState(
                    targetValue = heartScale,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                
                // Entrance animation
                val heartEntrance by animateFloatAsState(
                    targetValue = if (isVisible) 1f else 0f,
                    animationSpec = tween(500, delayMillis = 400)
                )
                
                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .size(40.dp)
                        .graphicsLayer(
                            alpha = heartEntrance,
                            scaleX = heartScaleAnim,
                            scaleY = heartScaleAnim
                        )
                        .clickable {
                            heartScale = 1.3f
                            onFavoriteClick()
                            // Reset scale after animation
                            MainScope().launch {
                                delay(300)
                                heartScale = 1f
                            }
                        }
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = if (restaurant.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (restaurant.isFavorite) Color.Red else Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                
                // Enhanced Bottom-left discount overlay with animation and gradient background
                if (restaurant.offer.isNotEmpty()) {
                    // Offer animation
                    val offerSlideIn by animateFloatAsState(
                        targetValue = if (isVisible) 0f else 50f,
                        animationSpec = tween(700, delayMillis = 500)
                    )
                    
                    val offerAlpha by animateFloatAsState(
                        targetValue = if (isVisible) 1f else 0f,
                        animationSpec = tween(700, delayMillis = 500)
                    )
                    
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(12.dp)
                            .graphicsLayer(alpha = offerAlpha, translationX = offerSlideIn)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xFFFF6F00),
                                            Color(0xFFFF9800)
                                        )
                                    )
                                )
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Column {
                                if (restaurant.offer.contains("%")) {
                                    // For percentage offers like "30% OFF"
                                    Text(
                                        text = restaurant.offer,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontFamily = SwiggyFontFamily,
                                        color = Color.White,
                                        style = TextStyle(
                                            shadow = Shadow(
                                                color = Color.Black.copy(alpha = 0.3f),
                                                offset = Offset(1f, 1f),
                                                blurRadius = 2f
                                            )
                                        )
                                    )
                                    Text(
                                        text = "UPTO ₹75",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = SwiggyFontFamily,
                                        color = Color.White
                                    )
                                } else if (restaurant.offer.contains("ITEMS AT")) {
                                    // For "ITEMS AT ₹49" type offers
                                    Text(
                                        text = "ITEMS",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = SwiggyFontFamily,
                                        color = Color.White
                                    )
                                    Text(
                                        text = restaurant.offer.replace("ITEMS AT ", "AT "),
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontFamily = SwiggyFontFamily,
                                        color = Color.White,
                                        style = TextStyle(
                                            shadow = Shadow(
                                                color = Color.Black.copy(alpha = 0.3f),
                                                offset = Offset(1f, 1f),
                                                blurRadius = 2f
                                            )
                                        )
                                    )
                                } else if (restaurant.offer.contains("Free Delivery")) {
                                    // For "Domino's Free Delivery" type offers
                                    Text(
                                        text = "Domino's",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = SwiggyFontFamily,
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Free Delivery",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = SwiggyFontFamily,
                                        color = Color.White,
                                        style = TextStyle(
                                            shadow = Shadow(
                                                color = Color.Black.copy(alpha = 0.3f),
                                                offset = Offset(1f, 1f),
                                                blurRadius = 1f
                                            )
                                        )
                                    )
                                } else {
                                    // Default offer display
                                    Text(
                                        text = restaurant.offer,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontFamily = SwiggyFontFamily,
                                        color = Color.White,
                                        style = TextStyle(
                                            shadow = Shadow(
                                                color = Color.Black.copy(alpha = 0.3f),
                                                offset = Offset(1f, 1f),
                                                blurRadius = 2f
                                            )
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
                
                // Enhanced Bottom-right "AD" label with animation
                if (restaurant.isAd) {
                    // AD label animation
                    val adScale by animateFloatAsState(
                        targetValue = if (isVisible) 1f else 0f,
                        animationSpec = tween(500, delayMillis = 600)
                    )
                    
                    Card(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(12.dp)
                            .graphicsLayer(alpha = adScale, scaleX = adScale, scaleY = adScale),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF000000).copy(alpha = 0.8f)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Text(
                            text = "AD",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = SwiggyFontFamily,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
            
            // Enhanced Restaurant details section with animations
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White,
                                Color(0xFFF5F5F5)
                            )
                        )
                    )
                    .padding(16.dp)
            ) {
                // Restaurant name with animation
                val nameSlideIn by animateFloatAsState(
                    targetValue = if (isVisible) 0f else 30f,
                    animationSpec = tween(500, delayMillis = 300)
                )
                
                val nameAlpha by animateFloatAsState(
                    targetValue = if (isVisible) 1f else 0f,
                    animationSpec = tween(500, delayMillis = 300)
                )
                
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = SwiggyFontFamily,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = Color.Black
                    ),
                    maxLines = 1,
                    modifier = Modifier.graphicsLayer(alpha = nameAlpha, translationX = nameSlideIn)
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Rating and delivery time with enhanced styling
                val ratingSlideIn by animateFloatAsState(
                    targetValue = if (isVisible) 0f else 30f,
                    animationSpec = tween(500, delayMillis = 400)
                )
                
                val ratingAlpha by animateFloatAsState(
                    targetValue = if (isVisible) 1f else 0f,
                    animationSpec = tween(500, delayMillis = 400)
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.graphicsLayer(alpha = ratingAlpha, translationX = ratingSlideIn)
                ) {
                    // Enhanced rating display
                    Card(
                        shape = RoundedCornerShape(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF4CAF50)
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Rating",
                                modifier = Modifier.size(14.dp),
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "${restaurant.rating}",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = SwiggyFontFamily,
                                color = Color.White
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    // Delivery time with icon
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.carousel),
                            contentDescription = "Time",
                            modifier = Modifier.size(14.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = restaurant.deliveryTime,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = SwiggyFontFamily,
                            color = Color.Black
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Cuisine type with animation
                val cuisineSlideIn by animateFloatAsState(
                    targetValue = if (isVisible) 0f else 30f,
                    animationSpec = tween(500, delayMillis = 500)
                )
                
                val cuisineAlpha by animateFloatAsState(
                    targetValue = if (isVisible) 1f else 0f,
                    animationSpec = tween(500, delayMillis = 500)
                )
                
                Text(
                    text = restaurant.cuisines.firstOrNull() ?: "",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = SwiggyFontFamily,
                    color = Color.Gray,
                    modifier = Modifier.graphicsLayer(alpha = cuisineAlpha, translationX = cuisineSlideIn)
                )
            }
        }
    }
}
