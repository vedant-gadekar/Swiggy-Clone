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
import com.example.swiggyy.feature.instamart.utils.InstamartConstants

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
            .width(InstamartConstants.Dimensions.ProductCardWidth)
            .height(InstamartConstants.Dimensions.ProductCardHeight)
            .clip(RoundedCornerShape(InstamartConstants.Dimensions.CornerRadiusLarge))
            .background(InstamartConstants.Colors.White)
            .clickable { onProductClick() }
            .padding(InstamartConstants.Dimensions.SpacingXSmall)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Product Image Container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(InstamartConstants.Dimensions.ProductImageHeight)
                    .clip(RoundedCornerShape(InstamartConstants.Dimensions.CornerRadiusMedium))
                    .background(InstamartConstants.Colors.LightGray),
                contentAlignment = Alignment.Center
            ) {
                if (productData.imageUrl!=0) {
                    AsyncImage(
                        model = productData.imageUrl,
                        contentDescription = productData.name,
                        modifier = Modifier.size(InstamartConstants.Dimensions.ProductImageSize),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            // Product Information
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = InstamartConstants.Dimensions.SpacingSmall, vertical = InstamartConstants.Dimensions.SpacingLarge)
            ) {
                // Product Name
                Text(
                    text = productData.name,
                    fontSize = InstamartConstants.Typography.FontSizeXLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = InstamartConstants.Colors.Black
                )

                // Rating Row
                Row(
                    modifier = Modifier.padding(top = InstamartConstants.Dimensions.SpacingSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = InstamartConstants.Colors.StarYellow,
                        modifier = Modifier.size(InstamartConstants.Dimensions.IconSizeMedium)
                    )

                    Text(
                        text = productData.rating,
                        fontSize = InstamartConstants.Typography.FontSizeLarge,
                        fontWeight = FontWeight.Medium,
                        color = InstamartConstants.Colors.Black,
                        modifier = Modifier.padding(start = InstamartConstants.Dimensions.SpacingSmall)
                    )
                }

                // Price
                Text(
                    text = productData.price,
                    fontSize = InstamartConstants.Typography.FontSizeXLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = InstamartConstants.Colors.Black,
                    modifier = Modifier.padding(top = InstamartConstants.Dimensions.SpacingSmall)
                )
            }
        }

        // Add/Quantity Control Button
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = InstamartConstants.Dimensions.SpacingNormal)
                .size(InstamartConstants.Dimensions.AddButtonSize)
                .shadow(
                    elevation = InstamartConstants.Dimensions.ShadowElevation,
                    shape = CircleShape,
                    ambientColor = InstamartConstants.Colors.ShadowBlack
                )
                .clip(CircleShape)
                .background(InstamartConstants.Colors.White)
                .border(InstamartConstants.Dimensions.BorderWidth, InstamartConstants.Colors.BorderGray, CircleShape)
        ) {
            if (quantity == 0) {
                // Add Button
                IconButton(
                    onClick = { onQuantityChange(1) },
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = InstamartConstants.Strings.AddToCart,
                        tint = InstamartConstants.Colors.Black,
                        modifier = Modifier.size(InstamartConstants.Dimensions.IconSizeLarge)
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
                        modifier = Modifier.size(InstamartConstants.Dimensions.IconSizeLarge)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = InstamartConstants.Strings.Remove,
                            tint = InstamartConstants.Colors.Black,
                            modifier = Modifier.size(InstamartConstants.Dimensions.IconSizeSmall)
                        )
                    }

                    // Quantity Display
                    Text(
                        text = quantity.toString(),
                        fontSize = InstamartConstants.Typography.FontSizeLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = InstamartConstants.Colors.Black
                    )

                    // Increase Button
                    IconButton(
                        onClick = { onQuantityChange(quantity + 1) },
                        modifier = Modifier.size(InstamartConstants.Dimensions.IconSizeLarge)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = InstamartConstants.Strings.AddMore,
                            tint = InstamartConstants.Colors.Black,
                            modifier = Modifier.size(InstamartConstants.Dimensions.IconSizeSmall)
                        )
                    }
                }
            }
        }
    }
}
