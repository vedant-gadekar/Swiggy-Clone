package com.example.swiggyy.feature.instamart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.swiggyy.R
import com.example.swiggyy.ui.theme.SwiggyFontFamily

data class GroceryCategory(
    val name: String,
    val imageUrl: String,
    val iconRes: Int? = null
)
//
data class ProductItem(
    val name: String,
    val imageUrl: String,
    val weight: String,
    val rating: Double,
    val reviewCount: String,
    val originalPrice: Int,
    val discountedPrice: Int,
    val discount: String? = null,
    val badge: String? = null
)

data class FeaturedCategory(
    val title: String,
    val subtitle: String,
    val imageUrl: String,
    val backgroundColor: Color,
    val textColor: Color = Color.Black
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstaMart2() {
    val groceryCategories = listOf(
        GroceryCategory("Fresh Vegetables", "https://via.placeholder.com/80x80/90EE90/000000?text=🥬", R.drawable.ic_grocery),
        GroceryCategory("Fresh Fruits", "https://via.placeholder.com/80x80/FFD700/000000?text=🍌", R.drawable.ic_grocery),
        GroceryCategory("Dairy, Bread and Eggs", "https://via.placeholder.com/80x80/F5F5DC/000000?text=🥛", R.drawable.ic_dining),
        GroceryCategory("Cereals and Breakfast", "https://via.placeholder.com/80x80/DDA0DD/000000?text=🥣", R.drawable.ic_grocery),
        GroceryCategory("Atta, Rice and Dal", "https://via.placeholder.com/80x80/F4A460/000000?text=🌾", R.drawable.ic_grocery),
        GroceryCategory("Oils and Ghee", "https://via.placeholder.com/80x80/FFE4B5/000000?text=🛢️", R.drawable.ic_beverage),
        GroceryCategory("Masalas", "https://via.placeholder.com/80x80/CD853F/000000?text=🌶️", R.drawable.ic_grocery),
        GroceryCategory("Dry Fruits and Seeds Mix", "https://via.placeholder.com/80x80/8FBC8F/000000?text=🥜", R.drawable.ic_grocery),
        GroceryCategory("Biscuits and Cakes", "https://via.placeholder.com/80x80/DEB887/000000?text=🍪", R.drawable.ic_grocery),
        GroceryCategory("Tea, Coffee and Milk drinks", "https://via.placeholder.com/80x80/D2B48C/000000?text=☕", R.drawable.ic_beverage),
        GroceryCategory("Sauces and Spreads", "https://via.placeholder.com/80x80/F08080/000000?text=🍯", R.drawable.ic_grocery),
        GroceryCategory("Meat and Seafood", "https://via.placeholder.com/80x80/FFB6C1/000000?text=🐟", R.drawable.ic_dining)
    )

    val categoryTabs = listOf(
        GroceryCategory("All", "", R.drawable.ic_grocery),
        GroceryCategory("Maxxsaver", "", R.drawable.ic_grocery),
        GroceryCategory("Festivals", "", R.drawable.ic_grocery),
        GroceryCategory("Fresh", "", R.drawable.ic_grocery),
        GroceryCategory("Electronics", "", R.drawable.ic_grocery),
        GroceryCategory("Home", "", R.drawable.ic_grocery)
    )

    val hotDeals = listOf(
        ProductItem(
            "Gemini Refined Sunflower Oil",
            "https://via.placeholder.com/120x150/FFD700/000000?text=Oil",
            "870 g",
            4.6,
            "(42.7k)",
            180,
            152,
            "15% OFF",
            "₹150 SHOP FOR ₹399"
        ),
        ProductItem(
            "Amul Masti Dahi",
            "https://via.placeholder.com/120x150/90EE90/000000?text=Dahi",
            "390 g",
            4.6,
            "(37.0k)",
            0,
            35
        ),
        ProductItem(
            "Kurkure Namkeen Masala Munch",
            "https://via.placeholder.com/120x150/FF6347/000000?text=Kurkure",
            "75 g",
            4.5,
            "(34.2k)",
            0,
            20
        ),
        ProductItem(
            "Amul Fresh Paneer",
            "https://via.placeholder.com/120x150/F0F8FF/000000?text=Paneer",
            "200 g",
            4.5,
            "(26.8k)",
            0,
            91,
            badge = "🔒 ₹90"
        )
    )

    val featuredCategories = listOf(
        FeaturedCategory(
            "Fruits & Vegetables",
            "FEATURED",
            "https://via.placeholder.com/150x100/FFB6C1/000000?text=🍓",
            Color(0xFFFFB6C1)
        ),
        FeaturedCategory(
            "Dairy Bread & Eggs",
            "FEATURED",
            "https://via.placeholder.com/150x100/F5DEB3/000000?text=🥖",
            Color(0xFFF5DEB3)
        ),
        FeaturedCategory(
            "Munchies & Cold Drinks",
            "FEATURED",
            "https://via.placeholder.com/150x100/E6E6FA/000000?text=🥤",
            Color(0xFFE6E6FA)
        ),
        FeaturedCategory(
            "Cooking Essentials",
            "FEATURED",
            "https://via.placeholder.com/150x100/F0FFF0/000000?text=🧄",
            Color(0xFFF0FFF0)
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Header
            Text(
                text = "Grocery & Kitchen",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = SwiggyFontFamily,
                modifier = Modifier.padding(16.dp),
                color = Color.Black
            )
        }

        item {
            // Grocery Categories Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(400.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(groceryCategories) { category ->
                    GroceryCategoryItem(category = category)
                }
            }
        }

        item {
            // Category Tabs
            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(categoryTabs) { tab ->
                    CategoryTab(tab = tab, isSelected = tab.name == "All")
                }
            }
        }

        item {
            // Hot Deals Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Hot deals",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = SwiggyFontFamily,
                    color = Color.Black
                )
                Text(
                    text = "See All >",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF4285F4)
                )
            }
        }

        item {
            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(hotDeals) { product ->
                    ProductCard(product = product)
                }
            }
        }

        item {
            // In the Spotlight Section
            Text(
                text = "In the Spotlight",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = SwiggyFontFamily,
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.Black
            )
        }

        item {
            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(featuredCategories) { featured ->
                    FeaturedCategoryCard(featured = featured)
                }
            }
        }

        item {
            // Bottom padding
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun GroceryCategoryItem(category: GroceryCategory) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = category.imageUrl,
                contentDescription = category.name,
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = category.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = SwiggyFontFamily,
            textAlign = TextAlign.Center,
            color = Color.Black,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 14.sp
        )
    }
}

@Composable
fun CategoryTab(tab: GroceryCategory, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { }
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(
                    color = if (isSelected) Color(0xFFFF8A00) else Color(0xFFF5F5F5),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = "https://via.placeholder.com/40x40/FF8A00/FFFFFF?text=${tab.name.first()}",
                contentDescription = tab.name,
                modifier = Modifier.size(40.dp),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = tab.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = SwiggyFontFamily,
            textAlign = TextAlign.Center,
            color = if (isSelected) Color(0xFFFF8A00) else Color.Black
        )
    }
}

@Composable
fun ProductCard(product: ProductItem) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .clickable { },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Box {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Fit
                )
                
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(32.dp)
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color(0xFF4285F4),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.weight,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                fontFamily = SwiggyFontFamily
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 2.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFF00C851),
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "${product.rating} ${product.reviewCount}",
                    fontSize = 11.sp,
                    color = Color.Gray,
                    fontFamily = SwiggyFontFamily
                )
            }

            Text(
                text = product.name,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontFamily = SwiggyFontFamily,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "₹${product.discountedPrice}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = SwiggyFontFamily
                )
                if (product.originalPrice > 0) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "₹${product.originalPrice}",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontFamily = SwiggyFontFamily,
                        textDecoration = androidx.compose.ui.text.style.TextDecoration.LineThrough
                    )
                }
            }

            product.discount?.let { discount ->
                Text(
                    text = discount,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00C851),
                    fontFamily = SwiggyFontFamily
                )
            }

            product.badge?.let { badge ->
                Box(
                    modifier = Modifier
                        .background(
                            Color(0xFFE8F5E8),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = badge,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00C851),
                        fontFamily = SwiggyFontFamily
                    )
                }
            }
        }
    }
}

@Composable
fun FeaturedCategoryCard(featured: FeaturedCategory) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(100.dp)
            .clickable { },
        colors = CardDefaults.cardColors(containerColor = featured.backgroundColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = featured.subtitle,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = featured.textColor.copy(alpha = 0.7f),
                    fontFamily = SwiggyFontFamily
                )
                Text(
                    text = featured.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = featured.textColor,
                    fontFamily = SwiggyFontFamily,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            
            AsyncImage(
                model = featured.imageUrl,
                contentDescription = featured.title,
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
