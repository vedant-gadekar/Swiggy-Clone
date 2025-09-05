package com.example.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.core.state.PopularCuisineData
import com.example.core.state.PopularItemData
import com.example.core.state.SearchEvent
import com.example.core.state.SearchEffect
import com.example.core.state.TrendingSearchData
import com.example.core.viewmodel.SearchViewModel
import com.example.swiggyy.ui.theme.SwiggyFontFamily
import com.example.core.Strings as CoreStrings
import kotlinx.coroutines.flow.collectLatest
import com.example.core.theme.LocalDimensions

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel(),
    onNavigateBack: () -> Unit = {},
    onNavigateToFood: (String?) -> Unit = {},
    onNavigateToInstamart: (String?) -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    val dims = LocalDimensions.current

    // Handle effects
    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is SearchEffect.NavigateBack -> onNavigateBack()
                is SearchEffect.NavigateToFood -> onNavigateToFood(effect.cuisineFilter)
                is SearchEffect.NavigateToInstamart -> onNavigateToInstamart(effect.itemFilter)
                is SearchEffect.ShowToast -> {
                    // Handle toast if needed
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        // Header with back button and search bar
        HeaderSection(
            query = state.searchQuery,
            onQueryChange = { viewModel.handleEvent(SearchEvent.SearchQueryChanged(it)) },
            onBackClick = { viewModel.handleEvent(SearchEvent.BackClicked) }
        )

        // Main content
        Column(
            modifier = Modifier.padding(horizontal = dims.paddingMedium)
        ) {
            // Search suggestion text

            Spacer(modifier = Modifier.height(dims.spacingMedium))
            
            // Trending searches section
            TrendingSearchesSection(
                trendingSearches = state.trendingSearches,
                onTrendingSearchClick = { searchQuery ->
                    viewModel.handleEvent(SearchEvent.TrendingSearchClicked(searchQuery))
                }
            )
            
            Spacer(modifier = Modifier.height(dims.spacingLarge))
            
            // Popular on Instamart section
            PopularInstamartSection(
                popularItems = state.popularItems,
                onItemClick = { item ->
                    viewModel.handleEvent(SearchEvent.PopularItemClicked(item.id))
                }
            )

            Spacer(modifier = Modifier.height(dims.spacingLarge))

            // Popular Cuisines section
            PopularCuisinesSection(
                cuisines = state.popularCuisines,
                onCuisineClick = { cuisine ->
                    viewModel.handleEvent(SearchEvent.CuisineClicked(cuisine.id))
                }
            )
            
            Spacer(modifier = Modifier.height(dims.spacingExtraLarge))
        }
    }
}

@Composable
private fun HeaderSection(
    query: String,
    onQueryChange: (String) -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(LocalDimensions.current.paddingMedium)
    ) {
        // Top section with back button and "Search for" text
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val dims = LocalDimensions.current
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(dims.iconSizeMedium)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF424444)
                )
            }
            
            Spacer(modifier = Modifier.width(dims.spacingSmall))
            
            Text(
                text = CoreStrings.SEARCH_HEADER_PROMPT,
                color = Color(0xFF424444),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = SwiggyFontFamily
            )
        }
        
        Spacer(modifier = Modifier.height(LocalDimensions.current.spacingMedium))
        
        // Use the common SearchBar component (without onClick to allow typing)
        SearchBar(
            query = query,
            onQueryChange = onQueryChange
        )
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TrendingSearchesSection(
    trendingSearches: List<TrendingSearchData>,
    onTrendingSearchClick: (String) -> Unit
) {
    Column {
        Text(
            text = CoreStrings.TRENDING_SEARCHES,
            color = Color(0xFF454747),
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = SwiggyFontFamily
        )

        Spacer(modifier = Modifier.height(16.dp))

//         Trending search chips in a grid layout
        val chunkedSearches = trendingSearches.chunked(2)
        chunkedSearches.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { search ->
                    TrendingSearchChip(
                        text = search.text,
                        onClick = { onTrendingSearchClick(search.text) },
                        modifier = Modifier.weight(1f)
                    )
                }
                // Fill remaining space if odd number of items
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }



    }
}

@Composable
private fun TrendingSearchChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color(0xFFE0E0E0)), // Light gray border
        colors = CardDefaults.cardColors(containerColor = Color.White), // White background
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Search trend icon (small arrow)
            Icon(
                painter = painterResource(com.example.core.R.drawable.arrow_up),
                contentDescription = null,
                tint = Color(0xFF666666),
                modifier = Modifier.size(14.dp)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = text,
                color = Color(0xFF5E6062),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = SwiggyFontFamily
            )
        }
    }
}

@Composable
private fun PopularInstamartSection(
    popularItems: List<PopularItemData>,
    onItemClick: (PopularItemData) -> Unit
) {
    Column {
        // Section header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = CoreStrings.POPULAR,
                color = Color(0xFF30343B),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = SwiggyFontFamily
            )
            Text(
                text = CoreStrings.ON,
                color = Color(0xFF3A3844),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = SwiggyFontFamily
            )
            Text(
                text = CoreStrings.INSTAMART,
                color = Color(0xFF8F1C4E),
                fontSize = 19.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = SwiggyFontFamily
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Popular items horizontal scroll
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(popularItems) { item ->
                PopularItemCard(
                    item = item,
                    onClick = { onItemClick(item) }
                )
            }
        }
    }
}

@Composable
private fun PopularItemCard(
    item: PopularItemData,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Item image with background circle
        Box(
            modifier = Modifier
                .size(67.dp)
                .background(Color(android.graphics.Color.parseColor(item.backgroundColor)), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.name,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = item.name,
            color = Color(0xFF3F3F3F),
            fontSize = 11.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = SwiggyFontFamily,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun PopularCuisinesSection(
    cuisines: List<PopularCuisineData>,
    onCuisineClick: (PopularCuisineData) -> Unit
) {
    Column {
        // Section header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = CoreStrings.POPULAR,
                color = Color(0xFF141618),
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = SwiggyFontFamily
            )
            Text(
                text = CoreStrings.CUISINES,
                color = Color(0xFF07090D),
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = SwiggyFontFamily
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Cuisines horizontal scroll
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(cuisines) { cuisine ->
                CuisineCard(
                    cuisine = cuisine,
                    onClick = { onCuisineClick(cuisine) }
                )
            }
        }
    }
}

@Composable
private fun CuisineCard(
    cuisine: PopularCuisineData,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cuisine image
        AsyncImage(
            model = cuisine.imageUrl,
            contentDescription = cuisine.name,
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .border(0.5.dp, Color(0xFFE0E0E0), CircleShape),
            contentScale = ContentScale.Crop
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = cuisine.name,
            color = Color(0xFF050505),
            fontSize = 11.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = SwiggyFontFamily,
            textAlign = TextAlign.Center
        )
    }
}
