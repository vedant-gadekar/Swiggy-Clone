package com.example.swiggyy.feature.instamart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swiggyy.ui.theme.AppColors

// Bottom Navigation
@Composable
fun BottomNavigationBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = listOf(
        BottomNavItem("Shop", Icons.Default.Store, 0),
        BottomNavItem("Explore", Icons.Default.Search, 1),
        BottomNavItem("Cart", Icons.Default.ShoppingCart, 2),
        BottomNavItem("Favourite", Icons.Default.Favorite, 3),
        BottomNavItem("Account", Icons.Default.Person, 4)
    )

    NavigationBar(
        containerColor = Color.White,
        contentColor = AppColors.Primary,
        modifier = Modifier.height(83.dp)
    ) {
        tabs.forEach { tab ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = tab.title,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                selected = selectedTab == tab.index,
                onClick = { onTabSelected(tab.index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AppColors.Primary,
                    selectedTextColor = AppColors.Primary,
                    unselectedIconColor = AppColors.TextSecondary,
                    unselectedTextColor = AppColors.TextSecondary,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val index: Int
)

// Custom Button
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = AppColors.Primary,
    contentColor: Color = Color.White
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = AppColors.BorderColor,
            disabledContentColor = AppColors.TextSecondary
        ),
        shape = RoundedCornerShape(19.dp)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

// Loading indicator
@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = AppColors.Primary,
            strokeWidth = 3.dp
        )
    }
}

// Empty state
@Composable
fun EmptyState(
    title: String,
    description: String,
    icon: ImageVector = Icons.Default.ShoppingBag,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = AppColors.TextSecondary
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppColors.TextPrimary
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = description,
            fontSize = 16.sp,
            color = AppColors.TextSecondary
        )
        
        if (actionText != null && onActionClick != null) {
            Spacer(modifier = Modifier.height(24.dp))
            
            PrimaryButton(
                text = actionText,
                onClick = onActionClick
            )
        }
    }
}

// Price display component
@Composable
fun PriceText(
    price: Double,
    originalPrice: Double? = null,
    modifier: Modifier = Modifier,
    fontSize: Int = 18
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$${String.format("%.2f", price)}",
            fontSize = fontSize.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppColors.TextPrimary
        )
        
        if (originalPrice != null && originalPrice > price) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$${String.format("%.2f", originalPrice)}",
                fontSize = (fontSize - 2).sp,
                color = AppColors.TextSecondary,
                style = androidx.compose.ui.text.TextStyle(
                    textDecoration = androidx.compose.ui.text.style.TextDecoration.LineThrough
                )
            )
        }
    }
}

// Quantity selector
@Composable
fun QuantitySelector(
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    minQuantity: Int = 1,
    maxQuantity: Int = 99
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                if (quantity > minQuantity) {
                    onQuantityChange(quantity - 1)
                }
            },
            enabled = quantity > minQuantity
        ) {
            Icon(
                Icons.Default.Remove,
                contentDescription = "Decrease quantity",
                tint = if (quantity > minQuantity) AppColors.Primary else AppColors.TextSecondary
            )
        }
        
        Text(
            text = quantity.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppColors.TextPrimary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        
        IconButton(
            onClick = {
                if (quantity < maxQuantity) {
                    onQuantityChange(quantity + 1)
                }
            },
            enabled = quantity < maxQuantity
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Increase quantity",
                tint = if (quantity < maxQuantity) AppColors.Primary else AppColors.TextSecondary
            )
        }
    }
}
