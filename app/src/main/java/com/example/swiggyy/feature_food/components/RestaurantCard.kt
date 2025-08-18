package com.example.swiggyy.feature_food.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swiggyy.feature_food.model.Restaurant
import com.example.swiggyy.ui.theme.SwiggyFontFamily

@Composable
fun RestaurantCard(
    restaurant: Restaurant,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Image section with overlays
            Box {
                Image(
                    painter = painterResource(restaurant.imageRes),
                    contentDescription = restaurant.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    contentScale = ContentScale.Crop
                )
                
                // Top-left "one" badge
                if (restaurant.hasOneBenefits) {
                    Card(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "one",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFF6F00),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                
                // Top-right heart icon
                IconButton(
                    onClick = onFavoriteClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(32.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.9f),
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    Icon(
                        imageVector = if (restaurant.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (restaurant.isFavorite) Color.Red else Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                }
                
                // Bottom-left discount overlay
                if (restaurant.offer.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp)
                    ) {
                        if (restaurant.offer.contains("%")) {
                            // For percentage offers like "30% OFF"
                            Text(
                                text = restaurant.offer,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                            Text(
                                text = "UPTO ₹75",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        } else if (restaurant.offer.contains("ITEMS AT")) {
                            // For "ITEMS AT ₹49" type offers
                            Text(
                                text = "ITEMS",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 1.dp)
                            )
                            Text(
                                text = restaurant.offer.replace("ITEMS AT ", "AT "),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 1.dp)
                            )
                        } else if (restaurant.offer.contains("Free Delivery")) {
                            // For "Domino's Free Delivery" type offers
                            Text(
                                text = "Domino's",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 1.dp)
                            )
                            Text(
                                text = "Free Delivery",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 1.dp)
                            )
                        } else {
                            // Default offer display
                            Text(
                                text = restaurant.offer,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
                
                // Bottom-right "AD" label
                if (restaurant.isAd) {
                    Card(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.7f)),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "AD",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
            
            // Restaurant details section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(12.dp)
            ) {
                // Restaurant name
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = SwiggyFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    maxLines = 1
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                // Rating and delivery time
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        modifier = Modifier.size(14.dp),
                        tint = Color(0xFF4CAF50)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${restaurant.rating} • ${restaurant.deliveryTime}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                // Cuisine type
                Text(
                    text = restaurant.cuisines.firstOrNull() ?: "",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }
        }
    }
}
