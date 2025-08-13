package com.example.swiggy.feature_home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.swiggyy.R
import com.example.swiggyy.ui.theme.SwiggyFontFamily


@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    )
    {
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

        SearchBar(
            query = state.searchQuery,
            onQueryChange = { viewModel.handleIntent(HomeIntent.SearchQueryChanged(it)) }
        )

        Spacer(Modifier.height(20.dp))


        CategoryGrid(
            categories = state.categories,
            onCategoryClick = {}
        )
        Spacer(Modifier.height(20.dp))

        FeaturedHeader(text = "FEATURED FOR YOU")

        Spacer(Modifier.height(20.dp))

        Carousel(
            state.carouselItems,
            state.currentPage,
            onPageChanged = { viewModel.handleIntent(HomeIntent.PageChanged(it)) },
            onButtonClick = {item ->
                viewModel.handleIntent(HomeIntent.CarouselItemClicked(item))
            }
        )

        Spacer(Modifier.height(70.dp))
        AppFooter()

        Spacer(Modifier.height(100.dp))

    }
}

@Composable
fun FeaturedHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = Color.Gray
        )

        Icon(
            painter = painterResource(id = R.drawable.swirl),
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier
                .graphicsLayer(scaleX = -1f)
                .padding(horizontal = 8.dp)
                .size(40.dp)
        )

        Text(
            text = text,
            color = Color.DarkGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            textAlign = TextAlign.Center
        )

        Icon(
            painter = painterResource(id = R.drawable.swirl),
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(40.dp)
        )

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = Color.Gray
        )
    }
}
@Composable
fun LocationBar(name: String, address: String) {
    Column {
        Row {
            Image(
                painter = painterResource(R.drawable.icon_location),
                contentDescription = "Location arrow",
                modifier = Modifier
                    .size(20.dp),
                contentScale = ContentScale.Crop
            )
            Text(name,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = SwiggyFontFamily
            )


            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = "Dropdown arrow",
                tint = Color.Gray,
                modifier = Modifier
                    .size(20.dp)

            )
        }

        Text(address,
            color = Color.Gray,
            fontFamily = SwiggyFontFamily,
            fontSize = MaterialTheme.typography.bodySmall.fontSize)


    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(24.dp))
            .background(Color.White)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Search Icon
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = Color.Gray
        )

        Spacer(modifier = Modifier.width(10.dp))

        // Text Field without underline
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            singleLine = true,
            decorationBox = { innerTextField ->
                if (query.isEmpty()) {
                    Text(
                        text = "Search for 'Milk'",
                        color = Color.Gray,
                        fontFamily = SwiggyFontFamily,

                    )
                }
                innerTextField()
            },
            modifier = Modifier.weight(1f)
        )

        // Divider between text and mic icon
        Box(
            modifier = Modifier
                .width(1.dp)
                .height(24.dp)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Mic Icon
        IconButton(onClick = {}) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter=painterResource(R.drawable.icon_mic),
                contentDescription = "Voice Search",
                tint = Color(0xFFFF6F00) // Orange
            )
        }
    }
}


@Composable
fun CategoryGrid(categories: List<Category>, onCategoryClick: (Category) -> Unit) {
    Column {
        categories.chunked(2).forEach { rowItems ->
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                rowItems.forEach { category ->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(140.dp)
                            .clickable { onCategoryClick(category) },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(top=12.dp, start = 12.dp)
                        ){
                            Column(
                                Modifier
                                    .align(Alignment.TopStart),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text= category.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                                Text(
                                    text = category.subtitle,
                                    color = Color.Gray,
                                    fontSize = 13.sp,
                                    modifier = Modifier.padding(top = 3.dp, bottom = 3.dp)
                                    )
                                if (category.offer!=null){
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                brush = Brush.linearGradient(
                                                    colors = listOf(
                                                        Color(0xFFFFECB3),
                                                        Color(0xFFFFFFFF),
                                                    )
                                                ),
                                                shape = RoundedCornerShape(8.dp)
                                            )

                                            .padding(4.dp)
                                    ) {
                                        Text(
                                            text = category.offer,
                                            color = Color(0xFFFF6F00),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 10.sp
                                        )
                                    }
                                }

                            }

                            Image(
                                painterResource(id=category.imageRes),
                                contentDescription = category.title,
                                modifier = Modifier
                                    .size(100.dp)
                                    .align(Alignment.BottomEnd)
                                    .offset(12.dp,17.dp)
                            )
                        }

                    }
                }
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}


@Composable
fun Carousel(
    items: List<CarouselItem>,
    currentPage: Int,
    onPageChanged: (Int) -> Unit,
    onButtonClick: (CarouselItem) -> Unit
) {
    val pagerState= rememberPagerState(initialPage = currentPage, pageCount = {items.size})

    LaunchedEffect(pagerState.currentPage) {
        onPageChanged(pagerState.currentPage)
    }

    Column {
        HorizontalPager(
            state = pagerState,
            pageSpacing = 12.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentPadding = PaddingValues(horizontal = 7.dp)
        ) { page ->
            CarouselCard(item = items[page], onButtonClick = onButtonClick)
        }

    }
}


@Composable
fun CarouselCard(
    item: CarouselItem,
    onButtonClick: (CarouselItem) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(
            modifier = Modifier
                .background(Color(0xFFE5E5E5))
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            item.chip?.let {
                Box(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFFFE0B2),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Medium,
                            fontFamily = SwiggyFontFamily,
                            color = Color(0xFFFF5722)
                        )
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    // Overline
                    item.overline?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontFamily = SwiggyFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Gray
                            ),
                            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                        )
                    }

                    // Main Title
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontFamily = SwiggyFontFamily,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )

                    // Subtitle
                    item.subtitle?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontFamily = SwiggyFontFamily,
                                fontWeight = FontWeight.Normal,
                                color = Color.Gray
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Button(
                onClick = { onButtonClick(item) },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .height(36.dp)
            ) {
                Text(
                    text = item.buttonText,
                    color = Color.White,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontFamily = SwiggyFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Composable
fun AppFooter(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(24.dp) // Space between the two text blocks
        ) {

            Text(
                text = "Live \nit up!",
                color = Color(0xFF757575), // Dark Gray
                fontSize = 60.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = SwiggyFontFamily,
                textAlign = TextAlign.Start,
                lineHeight = 54.sp // Set line height smaller than font size for overlap
            )

            Text(
                text = "Crafted with ❤️ in Baner, India",
                color = Color(0xFF757575), // Medium Gray
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = SwiggyFontFamily,
                textAlign = TextAlign.Start
            )
        }
    }
}

