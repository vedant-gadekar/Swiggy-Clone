package com.example.swiggyy.feature_food

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.swiggyy.R
import com.example.swiggyy.shared.components.LocationBar
import com.example.swiggyy.shared.components.SearchBar
import com.example.swiggyy.ui.theme.SwiggyFontFamily

@Composable
fun Food() {
    val viewModel: FoodViewModel = viewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    )
    {
        // Top Bar with Location and Profile - Exact Home screen implementation
        Row(Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .weight(0.80f)
                .padding(8.dp))
            {
                LocationBar(state.locationName, state.locationAddress)
            }
            Column (Modifier
                .weight(0.20f)
                .padding(8.dp),
                horizontalAlignment = Alignment.End)
            {
                 Image(
                     painter = painterResource(R.drawable.profile),
                     contentDescription = "Profile Picture",
                     modifier = Modifier
                         .size(36.dp)
                         .clip(CircleShape)
                         .border(1.dp, Color.LightGray, CircleShape),
                     contentScale = ContentScale.Crop
                 )
            }
        }

        // Search Bar - Exact Home screen implementation
        SearchBar(
            query = state.searchQuery,
            onQueryChange = { viewModel.handleIntent(FoodIntent.SearchQueryChanged(it)) }
        )

        Spacer(Modifier.height(20.dp))

        // Promotional Banner
        FeastivalBanner()



        Spacer(Modifier.height(20.dp))

        // What's on your mind? Categories
        CategoriesSection(
            categories = state.categories,
            onCategoryClick = { viewModel.handleIntent(FoodIntent.CategoryClicked(it)) }
        )

        Spacer(Modifier.height(20.dp))

        // Restaurant Sections
        RestaurantSection(
            title = "REORDER",
            restaurants = state.reorderRestaurants,
            onRestaurantClick = { viewModel.handleIntent(FoodIntent.RestaurantClicked(it)) }
        )

        Spacer(Modifier.height(20.dp))

        RestaurantSection(
            title = "FOOD IN 10 MINS",
            restaurants = state.quickDeliveryRestaurants,
            onRestaurantClick = { viewModel.handleIntent(FoodIntent.RestaurantClicked(it)) }
        )

        Spacer(Modifier.height(20.dp))

        // 99 Store Section
        NinetyNineStoreSection(
            items = state.ninetyNineStoreItems,
            onItemClick = { viewModel.handleIntent(FoodIntent.StoreItemClicked(it)) },
            onSeeAllClick = { viewModel.handleIntent(FoodIntent.SeeAllNinetyNineStore) }
        )

        Spacer(Modifier.height(20.dp))

        // More on Swiggy Section
        MoreOnSwiggySection(
            options = state.moreOnSwiggyOptions,
            onOptionClick = { viewModel.handleIntent(FoodIntent.SwiggyOptionClicked(it)) }
        )

        Spacer(Modifier.height(20.dp))

        // Filter and Sort Row
        FilterSortRow(
            selectedFilter = state.selectedFilter,
            selectedSort = state.selectedSort,
            onFilterClick = { viewModel.handleIntent(FoodIntent.FilterSelected(it)) },
            onSortClick = { viewModel.handleIntent(FoodIntent.SortSelected(it)) }
        )

        Spacer(Modifier.height(20.dp))

        // Featured Restaurants Title
        Text(
            text = "Top 2090 restaurants to explore",
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = SwiggyFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Text(
            text = "FEATURED RESTAURANTS",
            style = MaterialTheme.typography.labelSmall.copy(
                fontFamily = SwiggyFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        // Featured Restaurants List
        state.featuredRestaurants.forEach { restaurant ->
            FeaturedRestaurantCard(
                restaurant = restaurant,
                onRestaurantClick = { viewModel.handleIntent(FoodIntent.RestaurantClicked(it)) }
            )
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}



@Composable
fun FeastivalBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1A4A5C),
                            Color(0xFF2B5F5F),
                            Color(0xFF1E4A4A)
                        )
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                // Top section with FEAST-IVAL text and floating icons
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ) {
                    // Floating decorative icons with better positioning
                    Text(
                        "🍕", 
                        fontSize = 24.sp,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 8.dp, top = 4.dp)
                    )
                    Text(
                        "🍦", 
                        fontSize = 20.sp,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(end = 16.dp, top = 8.dp)
                    )
                    Text(
                        "🎁", 
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 4.dp)
                    )
                    Text(
                        "🍩", 
                        fontSize = 22.sp,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 8.dp)
                    )
                    Text(
                        "🍿", 
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 12.dp, bottom = 4.dp)
                    )
                    Text(
                        "🍔", 
                        fontSize = 20.sp,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 4.dp, bottom = 8.dp)
                    )
                    
                    // Center content with enhanced styling
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // FLAVOURFUL badge with gradient
                        Box(
                            modifier = Modifier
                                .background(
                                    brush = androidx.compose.ui.graphics.Brush.horizontalGradient(
                                        colors = listOf(Color.White, Color(0xFFFFF8DC))
                                    ),
                                    shape = RoundedCornerShape(25.dp)
                                )
                                .padding(horizontal = 20.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "✨ FLAVOURFUL ✨",
                                color = Color(0xFF2B5F5F),
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 13.sp,
                                fontFamily = SwiggyFontFamily
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        // FEAST-IVAL text with shadow effect
                        Text(
                            text = "FEAST-IVAL",
                            color = Color(0xFFFFD700),
                            fontSize = 36.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = SwiggyFontFamily,
                            style = androidx.compose.ui.text.TextStyle(
                                shadow = androidx.compose.ui.graphics.Shadow(
                                    color = Color.Black.copy(alpha = 0.3f),
                                    offset = androidx.compose.ui.geometry.Offset(2f, 2f),
                                    blurRadius = 4f
                                )
                            )
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(20.dp))
                
                // Enhanced FLAT ₹150 OFF text
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .background(
                            Color(0xFFFF6B35).copy(alpha = 0.9f),
                            RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "🎉 FLAT ₹150 OFF 🎉",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = SwiggyFontFamily
                    )
                }
                
                Spacer(modifier = Modifier.height(20.dp))
                
                // Enhanced offer cards row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Dishes From ₹29
                    OfferCard(
                        title = "Dishes\nFrom ₹29",
                        subtitle = "FLASH\nDEALS",
                        backgroundColor = Color(0xFFE91E63),
                        modifier = Modifier.weight(1f)
                    )
                    
                    // Flat ₹150 OFF
                    OfferCard(
                        title = "Flat ₹150\nOFF",
                        subtitle = "💰",
                        backgroundColor = Color(0xFF4CAF50),
                        modifier = Modifier.weight(1f)
                    )
                    
                    // Food In 10 Mins
                    OfferCard(
                        title = "Food In\n10 Mins",
                        subtitle = "⚡ Bolt",
                        backgroundColor = Color(0xFF2196F3),
                        modifier = Modifier.weight(1f)
                    )
                    
                    // Meals at ₹99
                    OfferCard(
                        title = "Meals\nat ₹99",
                        subtitle = "₹99",
                        backgroundColor = Color(0xFFFF9800),
                        modifier = Modifier.weight(1f)
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Enhanced HDFC Bank offer
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E3A8A)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // HDFC Bank logo with better styling
                        Box(
                            modifier = Modifier
                                .background(
                                    Color.White,
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "🏦 HDFC",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF1E3A8A),
                                fontFamily = SwiggyFontFamily
                            )
                        }
                        
                        Spacer(modifier = Modifier.width(12.dp))
                        
                        Column {
                            Text(
                                text = "💳 Flat ₹75 OFF*",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = SwiggyFontFamily
                            )
                            Text(
                                text = "using HDFC Bank Credit card",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 11.sp,
                                fontFamily = SwiggyFontFamily
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OfferCard(
    title: String,
    subtitle: String,
    backgroundColor: Color = Color(0xFF1E4A4A),
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(90.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                        colors = listOf(
                            backgroundColor,
                            backgroundColor.copy(alpha = 0.8f)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = SwiggyFontFamily,
                    lineHeight = 13.sp,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = androidx.compose.ui.graphics.Shadow(
                            color = Color.Black.copy(alpha = 0.2f),
                            offset = androidx.compose.ui.geometry.Offset(1f, 1f),
                            blurRadius = 2f
                        )
                    )
                )
                
                Text(
                    text = subtitle,
                    color = if (subtitle.contains("FLASH") || subtitle.contains("Bolt") || subtitle.contains("⚡")) 
                        Color(0xFFFFD700) else Color.White,
                    fontSize = when {
                        subtitle.contains("₹99") -> 18.sp
                        subtitle.contains("💰") -> 16.sp
                        subtitle.contains("⚡") -> 13.sp
                        else -> 12.sp
                    },
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = SwiggyFontFamily,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = androidx.compose.ui.graphics.Shadow(
                            color = Color.Black.copy(alpha = 0.2f),
                            offset = androidx.compose.ui.geometry.Offset(1f, 1f),
                            blurRadius = 2f
                        )
                    )
                )
            }
        }
    }
}