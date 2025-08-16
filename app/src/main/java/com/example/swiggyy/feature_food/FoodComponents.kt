package com.example.swiggyy.feature_food

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swiggyy.R
import com.example.swiggyy.ui.theme.SwiggyFontFamily
import com.example.swiggyy.feature_food.model.*

@Composable
fun TopBar(
    locationName: String,
    locationAddress: String,
    onLocationClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .clickable { onLocationClick() }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    modifier = Modifier.size(16.dp),
                    tint = Color(0xFFFF6F00)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = locationName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = SwiggyFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown",
                    modifier = Modifier.size(20.dp),
                    tint = Color.Black
                )
            }
            Text(
                text = locationAddress,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = SwiggyFontFamily,
                    color = Color.Gray
                ),
                modifier = Modifier.padding(start = 20.dp)
            )
        }
        
        // ONE Badge
        Card(
            modifier = Modifier.padding(end = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFF6F00)),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = "one",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
        
        // Profile Picture
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .border(1.dp, Color.LightGray, CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun SearchSection(
    searchQuery: String,
    searchHint: String,
    isVegFilterEnabled: Boolean,
    onSearchQueryChange: (String) -> Unit,
    onVegFilterToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Search Bar
        Card(
            modifier = Modifier.weight(1f),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BasicTextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChange,
                    modifier = Modifier.weight(1f),
                    decorationBox = { innerTextField ->
                        if (searchQuery.isEmpty()) {
                            Text(
                                text = searchHint,
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                        innerTextField()
                    }
                )
                Icon(
                    painter = painterResource(R.drawable.carousel),
                    contentDescription = "Voice Search",
                    tint = Color(0xFFFF6F00),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        
        // VEG Filter Button
        Card(
            modifier = Modifier.clickable { onVegFilterToggle() },
            colors = CardDefaults.cardColors(
                containerColor = if (isVegFilterEnabled) Color(0xFF4CAF50) else Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, if (isVegFilterEnabled) Color(0xFF4CAF50) else Color.Gray)
        ) {
            Text(
                text = "VEG",
                color = if (isVegFilterEnabled) Color.White else Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            )
        }
    }
}



@Composable
fun CategoriesSection(
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        // Simple title matching reference design
        Text(
            text = "What's on your mind?",
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = SwiggyFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3D4152)
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        
        // Simple horizontal scroll matching reference design
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categories) { category ->
                CategoryItem(
                    category = category,
                    onClick = { onCategoryClick(category) }
                )
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .clickable { onClick() }
    ) {
        // Simple circular image matching reference design
        Image(
            painter = painterResource(category.imageRes),
            contentDescription = category.name,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Simple text matching reference design
        Text(
            text = category.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = SwiggyFontFamily,
            textAlign = TextAlign.Center,
            color = Color(0xFF686B78),
            maxLines = 1
        )
    }
}

@Composable
fun RestaurantSection(
    title: String,
    restaurants: List<Restaurant>,
    onRestaurantClick: (Restaurant) -> Unit
) {
    if (restaurants.isNotEmpty()) {
        Column(modifier = Modifier.padding(vertical = 16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = SwiggyFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(restaurants) { restaurant ->
                    RestaurantCard(
                        restaurant = restaurant,
                        onClick = { onRestaurantClick(restaurant) }
                    )
                }
            }
        }
    }
}

@Composable
fun RestaurantCard(
    restaurant: Restaurant,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box {
                Image(
                    painter = painterResource(restaurant.imageRes),
                    contentDescription = restaurant.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    contentScale = ContentScale.Crop
                )
                
                if (restaurant.offer.isNotEmpty()) {
                    Card(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Text(
                            text = restaurant.offer,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFF6F00),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                
                if (restaurant.isAd) {
                    Card(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.7f))
                    ) {
                        Text(
                            text = "AD",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                    }
                }
            }
            
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    maxLines = 1
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 2.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.carousel),
                        contentDescription = "Rating",
                        modifier = Modifier.size(12.dp),
                        tint = Color(0xFF4CAF50)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "${restaurant.rating} • ${restaurant.deliveryTime}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                
                Text(
                    text = restaurant.cuisines.joinToString(", "),
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 1
                )
                
                if (restaurant.hasFreeDelivery || restaurant.hasOneBenefits) {
                    Row(
                        modifier = Modifier.padding(top = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        if (restaurant.hasFreeDelivery) {
                            Text(
                                text = "FREE DELIVERY",
                                fontSize = 10.sp,
                                color = Color(0xFF4CAF50),
                                fontWeight = FontWeight.Bold
                            )
                        }
                        if (restaurant.hasOneBenefits) {
                            Text(
                                text = "one BENEFITS",
                                fontSize = 10.sp,
                                color = Color(0xFFFF6F00),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NinetyNineStoreSection(
    items: List<StoreItem>,
    onItemClick: (StoreItem) -> Unit,
    onSeeAllClick: () -> Unit
) {
    if (items.isNotEmpty()) {
        Column(modifier = Modifier.padding(vertical = 16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "99",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFD700)
                        )
                    )
                    Text(
                        text = " store",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                }
                
                TextButton(onClick = onSeeAllClick) {
                    Text(
                        text = "See All >",
                        color = Color(0xFF2196F3),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            
            Text(
                text = "✓ Free delivery with ecosaver mode",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
            
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items) { item ->
                    StoreItemCard(
                        item = item,
                        onClick = { onItemClick(item) }
                    )
                }
            }
        }
    }
}

@Composable
fun StoreItemCard(
    item: StoreItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box {
                Image(
                    painter = painterResource(item.imageRes),
                    contentDescription = item.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentScale = ContentScale.Crop
                )
                
                Card(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = CircleShape
                ) {
                    Icon(
                        painter = painterResource(R.drawable.carousel),
                        contentDescription = "Add",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(4.dp),
                        tint = Color(0xFF4CAF50)
                    )
                }
            }
            
            Column(modifier = Modifier.padding(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.carousel),
                        contentDescription = "Veg",
                        modifier = Modifier.size(12.dp),
                        tint = Color(0xFF4CAF50)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = item.name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        maxLines = 1
                    )
                }
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 2.dp)
                ) {
                    Text(
                        text = "₹${item.originalPrice}",
                        fontSize = 10.sp,
                        color = Color.Gray,
                        textDecoration = TextDecoration.LineThrough
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "₹${item.discountedPrice}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.carousel),
                        contentDescription = "Rating",
                        modifier = Modifier.size(10.dp),
                        tint = Color(0xFF4CAF50)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "${item.rating} (${item.reviewCount})",
                        fontSize = 10.sp,
                        color = Color.Gray
                    )
                }
                
                Text(
                    text = item.description,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun MoreOnSwiggySection(
    options: List<SwiggyOption>,
    onOptionClick: (SwiggyOption) -> Unit
) {
    if (options.isNotEmpty()) {
        Column(modifier = Modifier.padding(vertical = 16.dp)) {
            Text(
                text = "More on Swiggy",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = SwiggyFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(options) { option ->
                    SwiggyOptionCard(
                        option = option,
                        onClick = { onOptionClick(option) }
                    )
                }
            }
        }
    }
}

@Composable
fun SwiggyOptionCard(
    option: SwiggyOption,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(80.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(option.imageRes),
                contentDescription = option.title,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = option.title,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            if (option.description.isNotEmpty()) {
                Text(
                    text = option.description,
                    fontSize = 8.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun FilterSortRow(
    selectedFilter: String,
    selectedSort: String,
    onFilterClick: (String) -> Unit,
    onSortClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Filter Button
        OutlinedButton(
            onClick = { onFilterClick("filter") },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            border = BorderStroke(1.dp, Color.Gray)
        ) {
            Icon(
                painter = painterResource(R.drawable.carousel),
                contentDescription = "Filter",
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Filter", fontSize = 14.sp)
        }
        
        // Sort By Button
        OutlinedButton(
            onClick = { onSortClick("sort") },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            border = BorderStroke(1.dp, Color.Gray)
        ) {
            Text(text = "Sort By", fontSize = 14.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Sort",
                modifier = Modifier.size(16.dp)
            )
        }
        
        // 99 Store Button
        OutlinedButton(
            onClick = { onFilterClick("99store") },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            border = BorderStroke(1.dp, Color.Gray)
        ) {
            Text(text = "99 Store", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        
        // Bolt Food Button
        OutlinedButton(
            onClick = { onFilterClick("bolt") },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            border = BorderStroke(1.dp, Color.Gray)
        ) {
            Text(text = "Bolt", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = " Food", fontSize = 14.sp)
        }
    }
}

@Composable
fun FeaturedRestaurantCard(
    restaurant: Restaurant,
    onRestaurantClick: (Restaurant) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onRestaurantClick(restaurant) },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Restaurant Image
            Image(
                painter = painterResource(restaurant.imageRes),
                contentDescription = restaurant.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // Restaurant Details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    maxLines = 1
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 2.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.carousel),
                        contentDescription = "Rating",
                        modifier = Modifier.size(12.dp),
                        tint = Color(0xFF4CAF50)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "${restaurant.rating} (${restaurant.reviewCount}) • ${restaurant.deliveryTime}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                
                Text(
                    text = restaurant.cuisines.joinToString(", "),
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 1
                )
                

                
                if (restaurant.hasFreeDelivery || restaurant.hasOneBenefits) {
                    Row(
                        modifier = Modifier.padding(top = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (restaurant.hasFreeDelivery) {
                            Text(
                                text = "FREE DELIVERY",
                                fontSize = 10.sp,
                                color = Color(0xFF4CAF50),
                                fontWeight = FontWeight.Bold
                            )
                        }
                        if (restaurant.hasOneBenefits) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Card(
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFF6F00)),
                                    shape = RoundedCornerShape(2.dp)
                                ) {
                                    Text(
                                        text = "one",
                                        color = Color.White,
                                        fontSize = 8.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    text = "BENEFITS",
                                    fontSize = 10.sp,
                                    color = Color(0xFFFF6F00),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
