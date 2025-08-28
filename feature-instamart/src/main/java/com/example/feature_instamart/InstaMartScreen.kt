package com.example.instamart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.components.LocationBar
import com.example.core.components.SearchBar
import com.example.instamart.viewmodel.InstamartViewModel
import com.example.feature_instamart.state.InstamartEffect
import com.example.feature_instamart.state.InstamartEvent
import com.example.feature_instamart.utils.InstamartConstants.Categories.BISCUIT
import com.example.feature_instamart.utils.InstamartConstants.Categories.DETERGENT
import com.example.feature_instamart.utils.InstamartConstants.Categories.FRUITS
import com.example.feature_instamart.utils.InstamartConstants.Dimensions.BorderWidth
import com.example.feature_instamart.utils.InstamartConstants.Dimensions.SECTION_HEIGHT
import com.example.feature_instamart.utils.InstamartConstants.Dimensions.SpacingLarge
import com.example.feature_instamart.utils.InstamartConstants.Dimensions.SpacingSmall
import com.example.feature_instamart.utils.InstamartConstants.Dimensions.SpacingXLarge2
import com.example.feature_instamart.utils.InstamartConstants.Dimensions.SpacingXXXLarge
import com.example.feature_instamart.utils.InstamartConstants.Dimensions.TEXT_WEIGHT_M
import com.example.feature_instamart.utils.InstamartConstants.Dimensions.TEXT_WEIGHT_S
import com.example.feature_instamart.utils.InstamartConstants.Strings.ProfilePicture
import kotlinx.coroutines.flow.collectLatest
import com.example.feature_instamart.R

@Composable
fun InstaMart(
    viewModel: InstamartViewModel = viewModel(),
    navController: androidx.navigation.NavHostController? = null
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
                .padding(SpacingLarge)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(SpacingXLarge2)
        )
        {

            Row (Modifier.fillMaxWidth()) {
                Column(modifier = Modifier
                    .weight(TEXT_WEIGHT_M)
                    .padding(SpacingSmall))
                {
                    LocationBar(state.locationName, state.locationAddress)
                }

                Column (Modifier
                    .weight(TEXT_WEIGHT_S)
                    .padding(SpacingSmall),
                    horizontalAlignment = Alignment.End)
                {
                    Image(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = ProfilePicture,
                        modifier = Modifier
                            .size(SpacingXXXLarge)
                            .clip(CircleShape)
                            .border(BorderWidth, Color.LightGray, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            // Promotional Sliders
            SearchBar(
                query = state.searchQuery,
                onQueryChange = { viewModel.handleEvent(InstamartEvent.SearchQueryChanged(it)) },
                onClick = {
                    navController?.navigate("search")
                }
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
                    title = FRUITS,
                    onSeeAllClick = {
                        viewModel.handleEvent(InstamartEvent.SeeAllClicked(FRUITS))
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
                    title = DETERGENT,
                    onSeeAllClick = {
                        viewModel.handleEvent(InstamartEvent.SeeAllClicked(DETERGENT))
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
                    title = BISCUIT,
                    onSeeAllClick = {
                        viewModel.handleEvent(InstamartEvent.SeeAllClicked(BISCUIT))
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

            Spacer(Modifier.height(SECTION_HEIGHT))
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
