package com.example.swiggy.feature_home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LocationBar(state.locationName, state.locationAddress)

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
        Text(name, fontWeight = FontWeight.Bold)
        Text(address, color = Color.Gray, fontSize = MaterialTheme.typography.bodySmall.fontSize)
    }
}

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Search for 'Milk'") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
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
