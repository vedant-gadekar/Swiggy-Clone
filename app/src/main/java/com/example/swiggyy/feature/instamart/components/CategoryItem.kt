package com.example.instamart.components

import android.provider.SyncStateContract
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.instamart.state.CategoryData
import com.example.swiggyy.feature.instamart.utils.InstamartConstants.Dimensions.CategoryItemSize
import com.example.swiggyy.feature.instamart.utils.InstamartConstants.Dimensions.SpacingLarge
import com.example.swiggyy.feature.instamart.utils.InstamartConstants.Dimensions.SpacingXLarge
import com.example.swiggyy.feature.instamart.utils.InstamartConstants.Typography.FontSizeMedium

@Composable
fun CategoriesSection(
    categories: List<com.example.instamart.state.CategoryData>,
    onCategoryClick: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = SpacingXLarge),
        horizontalArrangement = Arrangement.spacedBy(SpacingXLarge)
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
fun CategoryItem(
    categoryData: CategoryData,
    onCategoryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(CategoryItemSize)
            .clickable { onCategoryClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = categoryData.imageUrl,
            contentDescription = categoryData.name,
            modifier = Modifier
                .size(CategoryItemSize)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Text(
            text = categoryData.name,
            fontSize = FontSizeMedium,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF5A5555),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(SpacingLarge)
        )
    }
}
