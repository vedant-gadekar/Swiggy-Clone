package com.example.swiggyy.feature_food.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OfferCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFF1E4A4A),
    isSpecialOffer: Boolean = false
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 16.sp
            )
            Text(
                text = subtitle,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}
