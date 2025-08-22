package com.example.instamart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.instamart.viewmodel.InstamartViewModel
import com.example.swiggyy.R
import com.example.swiggyy.feature.instamart.state.InstamartEffect
import com.example.swiggyy.feature.instamart.state.InstamartEvent
import com.example.swiggyy.shared.components.LocationBar
import com.example.swiggyy.shared.components.SearchBar
import kotlinx.coroutines.flow.collectLatest

@Composable
fun InstaMart(
    viewModel: InstamartViewModel = viewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()


    // Handle effects
    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is InstamartEffect.NavigateToCategory -> {
                    // Handle navigation to category
                }
                is InstamartEffect.NavigateToProduct -> {
                    // Handle navigation to product
                }
                is InstamartEffect.NavigateToSection -> {
                    // Handle navigation to section
                }
                is InstamartEffect.NavigateToPromotion -> {
                    // Handle navigation to promotion
                }
                is InstamartEffect.ShowToast -> {
                    // Show toast message
                }
                is InstamartEffect.NavigateToCart -> {
                    // Handle navigation to cart
                }
            }
        }
    }

    val backgroundColor = Color(0xFFFFFFFF)

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(17.dp)
        )
        {

            Row (Modifier.fillMaxWidth()) {
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
            // Promotional Sliders
            SearchBar(
                query = state.searchQuery,
                onQueryChange = { viewModel.handleEvent(InstamartEvent.SearchQueryChanged(it)) }
            )



            // Categories Section

                CategoriesSection(
                    categories = state.categories,
                    onCategoryClick = { categoryId ->
                        viewModel.handleEvent(InstamartEvent.CategoryClicked(categoryId))
                    }
                )


            // Fruits Section
                SectionHeader(
                    title = "Fruits",
                    onSeeAllClick = {
                        viewModel.handleEvent(InstamartEvent.SeeAllClicked("fruits"))
                    }
                )


                ProductSection(
                    products = state.fruits,
                    cartItems = state.cartItems,
                    onProductClick = { productId ->
                        viewModel.handleEvent(InstamartEvent.ProductClicked(productId))
                    },
                    onQuantityChange = { productId, quantity ->
                        viewModel.handleEvent(InstamartEvent.UpdateQuantity(productId, quantity))
                    }
                )


            // Detergent Section
                SectionHeader(
                    title = "Detergent",
                    onSeeAllClick = {
                        viewModel.handleEvent(InstamartEvent.SeeAllClicked("detergent"))
                    }
                )


                ProductSection(
                    products = state.detergents,
                    cartItems = state.cartItems,
                    onProductClick = { productId ->
                        viewModel.handleEvent(InstamartEvent.ProductClicked(productId))
                    },
                    onQuantityChange = { productId, quantity ->
                        viewModel.handleEvent(InstamartEvent.UpdateQuantity(productId, quantity))
                    }
                )

            // Biscuit Section
                SectionHeader(
                    title = "Biscuit",
                    onSeeAllClick = {
                        viewModel.handleEvent(InstamartEvent.SeeAllClicked("biscuit"))
                    }
                )

                ProductSection(
                    products = state.biscuits,
                    cartItems = state.cartItems,
                    onProductClick = { productId ->
                        viewModel.handleEvent(InstamartEvent.ProductClicked(productId))
                    },
                    onQuantityChange = { productId, quantity ->
                        viewModel.handleEvent(InstamartEvent.UpdateQuantity(productId, quantity))
                    }
                )

            Spacer(Modifier.height(100.dp))
        }
    }

    // Error handling
    state.error?.let { error ->
        LaunchedEffect(error) {
            // Show error message
            viewModel.handleEvent(InstamartEvent.ClearError)
        }
    }
}

@Composable
private fun CategoriesSection(
    categories: List<com.example.instamart.state.CategoryData>,
    onCategoryClick: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(categories) { category ->
            CategoryItem(
                categoryData = category,
                onCategoryClick = { onCategoryClick(category.id) }
            )
        }
    }
}

@Composable
private fun ProductSection(
    products: List<com.example.instamart.state.ProductData>,
    cartItems: Map<String, Int>,
    onProductClick: (String) -> Unit,
    onQuantityChange: (String, Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(products) { product ->
            ProductCard(
                productData = product,
                quantity = cartItems[product.id] ?: 0,
                onProductClick = { onProductClick(product.id) },
                onQuantityChange = { quantity ->
                    onQuantityChange(product.id, quantity)
                }
            )
        }
    }
}
