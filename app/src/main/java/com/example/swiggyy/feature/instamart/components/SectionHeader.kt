package com.example.instamart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swiggyy.feature.instamart.utils.InstamartConstants

@Composable
fun SectionHeader(
    title: String,
    onSeeAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(InstamartConstants.Colors.White)
            .padding(horizontal = InstamartConstants.Dimensions.SpacingXXLarge, vertical = InstamartConstants.Dimensions.SpacingLarge + InstamartConstants.Dimensions.SpacingSmall),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = InstamartConstants.Typography.FontSizeXLarge,
            fontWeight = FontWeight.SemiBold,
            color = InstamartConstants.Colors.DarkGray
        )

        Text(
            text = InstamartConstants.Strings.SeeAll,
            fontSize = InstamartConstants.Typography.FontSizeLarge,
            fontWeight = FontWeight.SemiBold,
            color = InstamartConstants.Colors.PrimaryGreen,
            modifier = Modifier.clickable { onSeeAllClick() }
        )
    }
}
