package com.example.swiggy.feature_home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.swiggyy.R
import com.example.swiggyy.ui.theme.SwiggyFontFamily


@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)


    ) {

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
                     painter = painterResource(R.drawable.profile_picture),
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

        BannerCard(
            title = state.bannerMessage,
            subtitle = state.bannerSubMessage,
            onClick = { viewModel.handleIntent(HomeIntent.BannerClicked) }
        )

        CategoryGrid(
            categories = state.categories,
            onCategoryClick = { viewModel.handleIntent(HomeIntent.CategoryClicked(it)) }
        )

        CreditCardOffer(state.creditCardOffer)
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

        Spacer(modifier = Modifier.width(8.dp))

        // Text Field without underline
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            singleLine = true,
            decorationBox = { innerTextField ->
                if (query.isEmpty()) {
                    Text(
                        text = "Search for 'Milk'",
                        color = Color.Gray
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
fun BannerCard(title: String, subtitle: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEDE7F6)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontWeight = FontWeight.Bold)
            Text(subtitle, color = Color.Gray)
        }
    }
}

@Composable
fun CategoryGrid(categories: List<Category>, onCategoryClick: (Category) -> Unit) {
    Column {
        categories.chunked(2).forEach { rowItems ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                rowItems.forEach { category ->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onCategoryClick(category) },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(category.title, fontWeight = FontWeight.Bold)
                            Text(category.subtitle, color = Color.Gray)
                            Text(category.offer, color = Color.Red, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun CreditCardOffer(offer: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))
    ) {
        Text(
            text = "Credit Card - $offer",
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF5722)
        )
    }
}
