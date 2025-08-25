package com.example.instamart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.example.swiggyy.feature.instamart.utils.InstamartConstants.Colors.BorderGray
import com.example.swiggyy.feature.instamart.utils.InstamartConstants.Dimensions.SpacingMedium
import com.example.swiggyy.feature.instamart.utils.InstamartConstants.Dimensions.SpacingXSmall
import com.example.swiggyy.feature.instamart.utils.InstamartConstants.Strings.ONE
import com.example.swiggyy.feature.instamart.utils.InstamartConstants.Strings.ZERO


@Composable
fun ProductSection(
    products: List<com.example.instamart.state.ProductData>,
    cartItems: Map<String, Int>,
    onProductClick: (String) -> Unit,
    onQuantityChange: (String, Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues( SpacingXSmall),
        horizontalArrangement = Arrangement.spacedBy(SpacingMedium)
    ) {
        items(products) { product ->
            ProductCard(
                productData = product,
                quantity = cartItems[product.id] ?: ZERO,
                onProductClick = { onProductClick(product.id) },
                onQuantityChange = { quantity ->
                    onQuantityChange(product.id, quantity)
                }
            )
        }
    }
}
@Composable
fun ProductCard(
    productData: ProductData,
    quantity: Int,
    onProductClick: () -> Unit,
    onQuantityChange: (Int) -> Unit,
    modifier: Modifier = Modifier
)
{
    Box(
        modifier = modifier
            .width(InstamartConstants.Dimensions.ProductCardWidth)
            .height(InstamartConstants.Dimensions.ProductCardHeight)
            .background(InstamartConstants.Colors.White)
            .border(InstamartConstants.Dimensions.BorderWidth,BorderGray)
            .clickable { onProductClick() }
    )
    {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Product Image Container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(InstamartConstants.Dimensions.ProductImageHeight)
                    .background(InstamartConstants.Colors.LightGray)
                    .border(InstamartConstants.Dimensions.BorderWidth,BorderGray),
                contentAlignment = Alignment.Center
            )
            {
                if (productData.imageUrl!=ZERO) {
                    AsyncImage(
                        model = productData.imageUrl,
                        contentDescription = productData.name,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
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
        )
        {
            if (quantity == ZERO) {
                // Add Button
                IconButton(
                    onClick = { onQuantityChange(ONE) },
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
                )
                {
                    // Decrease Button
                    IconButton(
                        onClick = {
                            if (quantity > ZERO) {
                                onQuantityChange(quantity - ONE)
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
                        onClick = { onQuantityChange(quantity + ONE) },
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
