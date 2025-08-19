package com.example.swiggyy.feature_food

import androidx.compose.animation.core.FastOutSlowInEasing
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
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.geometry.Offset
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
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.swiggyy.R
import com.example.swiggyy.feature_food.components.OfferCard
import com.example.swiggyy.shared.components.LocationBar
import com.example.swiggyy.shared.components.SearchBar
import com.example.swiggyy.ui.theme.SwiggyFontFamily
import com.example.swiggyy.feature_food.viewmodel.FoodViewModel
import com.example.swiggyy.feature_food.intent.FoodIntent
import com.example.swiggyy.feature_food.state.FoodState
import com.example.swiggyy.feature_food.state.UiState
import com.example.swiggyy.feature_food.components.RestaurantCarousel

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
        val categoriesState = state.categories
        when (categoriesState) {
            is UiState.Success -> {
                CategoriesSection(
                    categories = categoriesState.data,
                    onCategoryClick = { viewModel.handleIntent(FoodIntent.CategoryClicked(it)) }
                )
            }
            is UiState.Loading -> {
                // Show loading state
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            is UiState.Error -> {
                // Show error state
                Text(
                    text = categoriesState.message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            is UiState.Empty -> {
                // Show empty state
                Text(
                    text = "No categories available",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        // Restaurant Carousel with Toggle Tabs
        val reorderRestaurantsState = state.reorderRestaurants
        val quickDeliveryRestaurantsState = state.quickDeliveryRestaurants
        
        when {
            reorderRestaurantsState is UiState.Success && quickDeliveryRestaurantsState is UiState.Success -> {
                RestaurantCarousel(
                    reorderRestaurants = reorderRestaurantsState.data,
                    quickDeliveryRestaurants = quickDeliveryRestaurantsState.data,
                    onRestaurantClick = { viewModel.handleIntent(FoodIntent.RestaurantClicked(it)) },
                    onFavoriteClick = { restaurant -> 
                        viewModel.handleIntent(FoodIntent.ToggleFavorite(restaurant.id, !restaurant.isFavorite))
                    }
                )
            }
            reorderRestaurantsState is UiState.Loading || quickDeliveryRestaurantsState is UiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            reorderRestaurantsState is UiState.Error -> {
                Text(
                    text = reorderRestaurantsState.message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            quickDeliveryRestaurantsState is UiState.Error -> {
                Text(
                    text = quickDeliveryRestaurantsState.message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            reorderRestaurantsState is UiState.Empty && quickDeliveryRestaurantsState is UiState.Empty -> {
                Text(
                    text = "No restaurants available",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        // 99 Store Section
        val ninetyNineStoreItemsState = state.ninetyNineStoreItems
        when (ninetyNineStoreItemsState) {
            is UiState.Success -> {
                NinetyNineStoreSection(
                    items = ninetyNineStoreItemsState.data,
                    onItemClick = { viewModel.handleIntent(FoodIntent.StoreItemClicked(it)) },
                    onSeeAllClick = { viewModel.handleIntent(FoodIntent.SeeAllNinetyNineStore) }
                )
            }
            is UiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            is UiState.Error -> {
                Text(
                    text = ninetyNineStoreItemsState.message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            is UiState.Empty -> {
                Text(
                    text = "No store items available",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        // More on Swiggy Section
        val swiggyOptionsState = state.swiggyOptions
        when (swiggyOptionsState) {
            is UiState.Success -> {
                MoreOnSwiggySection(
                    options = swiggyOptionsState.data,
                    onOptionClick = { viewModel.handleIntent(FoodIntent.SwiggyOptionClicked(it)) }
                )
            }
            is UiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            is UiState.Error -> {
                Text(
                    text = swiggyOptionsState.message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            is UiState.Empty -> {
                Text(
                    text = "No Swiggy options available",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }


        Spacer(Modifier.height(20.dp))

        // Featured Restaurants Header
        SectionHeader(
            title = "FEATURED",
            subtitle = "Top 2090 restaurants to explore",

        )


        // Featured Restaurants List
        val featuredRestaurantsState = state.featuredRestaurants
        when (featuredRestaurantsState) {
            is UiState.Success -> {
                featuredRestaurantsState.data.forEach { restaurant ->
                    FeaturedRestaurantCard(
                        restaurant = restaurant,
                        onRestaurantClick = { viewModel.handleIntent(FoodIntent.RestaurantClicked(it)) }
                    )
                }
            }
            is UiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            is UiState.Error -> {
                Text(
                    text = featuredRestaurantsState.message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            is UiState.Empty -> {
                Text(
                    text = "No featured restaurants available",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun SectionHeader(
    title: String,
    subtitle: String? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = SwiggyFontFamily
                )
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray,
                        fontFamily = SwiggyFontFamily
                    )
                )
            }
        }

    }
}




@Composable
fun FeastivalBanner() {
    var isPressed by remember { mutableStateOf(false) }

    var visible by remember { mutableStateOf(false) }
    val bannerScale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.8f,
        animationSpec = spring(dampingRatio = 0.6f, stiffness = 300f),
        label = "BannerScale"
    )
    val bannerAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "BannerAlpha"
    )

    LaunchedEffect(Unit) {
        visible = true
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .graphicsLayer(
                scaleX = bannerScale,
                scaleY = bannerScale,
                alpha = bannerAlpha
            )
            .clickable { isPressed = !isPressed },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF0D2B36),
                            Color(0xFF1A4A5C),
                            Color(0xFF2B5F5F),
                            Color(0xFF1E4A4A)
                        )
                    ),
                    shape = RoundedCornerShape(24.dp)
                )
        ) {
            // ✨ FIX: Create ONE infinite transition to manage all child animations
            val infiniteTransition = rememberInfiniteTransition(label = "BannerInfiniteAnimations")

            Column(modifier = Modifier.padding(20.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ) {
                    val animatedRotation1 by infiniteTransition.animateFloat(
                        initialValue = -5f, targetValue = 5f,
                        animationSpec = infiniteRepeatable(tween(2000, easing = LinearEasing), RepeatMode.Reverse),
                        label = "Rotation1"
                    )
                    val animatedRotation2 by infiniteTransition.animateFloat(
                        initialValue = 5f, targetValue = -5f,
                        animationSpec = infiniteRepeatable(tween(2500, easing = LinearEasing), RepeatMode.Reverse),
                        label = "Rotation2"
                    )

                    // Floating food icons
                    Text("🍕", fontSize = 28.sp, modifier = Modifier.align(Alignment.TopStart).padding(start = 12.dp, top = 8.dp).graphicsLayer(rotationZ = animatedRotation1))
                    Text("🍦", fontSize = 24.sp, modifier = Modifier.align(Alignment.TopEnd).padding(end = 20.dp, top = 12.dp).graphicsLayer(rotationZ = animatedRotation2))
                    Text("🎁", fontSize = 22.sp, modifier = Modifier.align(Alignment.CenterStart).padding(start = 8.dp).graphicsLayer(rotationZ = animatedRotation2))
                    Text("🍩", fontSize = 26.sp, modifier = Modifier.align(Alignment.CenterEnd).padding(end = 12.dp).graphicsLayer(rotationZ = animatedRotation1))
                    Text("🍿", fontSize = 20.sp, modifier = Modifier.align(Alignment.BottomStart).padding(start = 16.dp, bottom = 8.dp).graphicsLayer(rotationZ = animatedRotation1))
                    Text("🍔", fontSize = 24.sp, modifier = Modifier.align(Alignment.BottomEnd).padding(end = 8.dp, bottom = 12.dp).graphicsLayer(rotationZ = animatedRotation2))

                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            shape = RoundedCornerShape(25.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(Brush.horizontalGradient(listOf(Color.White, Color(0xFFFFF8DC))))
                                    .padding(horizontal = 24.dp, vertical = 8.dp)
                            ) {
                                Text("✨ FLAVOURFUL ✨", color = Color(0xFF2B5F5F), fontWeight = FontWeight.ExtraBold, fontSize = 14.sp, fontFamily = SwiggyFontFamily)
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        val animatedScale by infiniteTransition.animateFloat(
                            initialValue = 1f, targetValue = 1.05f,
                            animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
                            label = "FeastivalScale"
                        )
                        Text(
                            text = "FEAST-IVAL",
                            color = Color(0xFFFFD700),
                            fontSize = 40.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = SwiggyFontFamily,
                            style = TextStyle(shadow = Shadow(
                                Color.Black.copy(alpha = 0.5f),
                                Offset(3f, 3f),
                                6f
                            )
                            ),
                            modifier = Modifier.graphicsLayer(scaleX = animatedScale, scaleY = animatedScale)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                val animatedAlpha by infiniteTransition.animateFloat(
                    initialValue = 0.9f, targetValue = 1f,
                    animationSpec = infiniteRepeatable(tween(800, easing = FastOutSlowInEasing), RepeatMode.Reverse),
                    label = "OfferAlpha"
                )
                Card(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(Brush.horizontalGradient(listOf(Color(0xFFFF4500), Color(0xFFFF6B35), Color(0xFFFF4500))))
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                            .graphicsLayer(alpha = animatedAlpha)
                    ) {
                        Text("🎉 FLAT ₹150 OFF 🎉", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, fontFamily = SwiggyFontFamily)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OfferCard(title = "Dishes\nFrom ₹29", subtitle = "FLASH\nDEALS", backgroundColor = Color(0xFFE91E63), modifier = Modifier.weight(1f), index = 0)
                    OfferCard(title = "Flat ₹150\nOFF", subtitle = "💰", backgroundColor = Color(0xFF4CAF50), modifier = Modifier.weight(1f), index = 1)
                    OfferCard(title = "Food In\n10 Mins", subtitle = "⚡ Bolt", backgroundColor = Color(0xFF2196F3), modifier = Modifier.weight(1f), index = 2)
                    OfferCard(title = "Meals\nat ₹99", subtitle = "₹99", backgroundColor = Color(0xFFFF9800), modifier = Modifier.weight(1f), index = 3)
                }
                Spacer(modifier = Modifier.height(16.dp))
                val shimmerEffect by infiniteTransition.animateFloat(
                    initialValue = -1000f, targetValue = 1000f,
                    animationSpec = infiniteRepeatable(tween(2000, easing = LinearEasing), RepeatMode.Restart),
                    label = "Shimmer"
                )
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E3A8A)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color.Transparent, Color.White.copy(alpha = 0.3f), Color.Transparent),
                                    start = Offset(x = shimmerEffect - 300f, y = 0f),
                                    end = Offset(x = shimmerEffect, y = 0f)
                                )
                            )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(Color.White, RoundedCornerShape(8.dp))
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text("🏦 HDFC", fontSize = 12.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF1E3A8A), fontFamily = SwiggyFontFamily)
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text("💳 Flat ₹75 OFF*", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold, fontFamily = SwiggyFontFamily)
                                Text("using HDFC Bank Credit card", color = Color.White.copy(alpha = 0.8f), fontSize = 11.sp, fontFamily = SwiggyFontFamily)
                            }
                        }
                    }
                }
            }
        }
    }
}

