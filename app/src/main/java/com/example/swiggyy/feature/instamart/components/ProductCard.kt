package com.example.instamart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.instamart.state.ProductData

@Composable
fun ProductCard(
    productData: ProductData,
    quantity: Int,
    onProductClick: () -> Unit,
    onQuantityChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(170.dp)
            .height(245.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .clickable { onProductClick() }
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Product Image Container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(147.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFF6F6F6)),
                contentAlignment = Alignment.Center
            ) {
                if (productData.imageUrl!=0) {
                    AsyncImage(
                        model = productData.imageUrl,
                        contentDescription = productData.name,
                        modifier = Modifier.size(146.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            // Product Information
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 14.dp)
            ) {
                // Product Name
                Text(
                    text = productData.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                // Rating Row
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFD500),
                        modifier = Modifier.size(16.dp)
                    )

                    Text(
                        text = productData.rating,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                // Price
                Text(
                    text = productData.price,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        // Add/Quantity Control Button
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 13.dp)
                .size(38.dp)
                .shadow(
                    elevation = 7.dp,
                    shape = CircleShape,
                    ambientColor = Color.Black.copy(alpha = 0.05f)
                )
                .clip(CircleShape)
                .background(Color.White)
                .border(1.dp, Color(0xFFECECEC), CircleShape)
        ) {
            if (quantity == 0) {
                // Add Button
                IconButton(
                    onClick = { onQuantityChange(1) },
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add to cart",
                        tint = Color.Black,
                        modifier = Modifier.size(18.dp)
                    )
                }
            } else {
                // Quantity Controls
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Decrease Button
                    IconButton(
                        onClick = {
                            if (quantity > 0) {
                                onQuantityChange(quantity - 1)
                            }
                        },
                        modifier = Modifier.size(18.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Remove",
                            tint = Color.Black,
                            modifier = Modifier.size(12.dp)
                        )
                    }

                    // Quantity Display
                    Text(
                        text = quantity.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )

                    // Increase Button
                    IconButton(
                        onClick = { onQuantityChange(quantity + 1) },
                        modifier = Modifier.size(18.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add more",
                            tint = Color.Black,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }
            }
        }
    }
}
