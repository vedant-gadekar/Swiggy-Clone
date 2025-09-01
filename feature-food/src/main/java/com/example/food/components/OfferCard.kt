package com.example.food.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_food.presentation.constants.Animation
import com.example.feature_food.presentation.constants.Colors
import com.example.swiggyy.ui.theme.SwiggyFontFamily

@Composable
fun OfferCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Colors.OFFER_CARD_BG,
    isSpecialOffer: Boolean = false,
    index: Int = 0 // Add index parameter for staggered animation
) {
    var isPressed by remember { mutableStateOf(false) }
    var visible by remember { mutableStateOf(false) }
    
    // Entrance animations with staggered delay based on index
    val cardScale by animateFloatAsState(
        targetValue = if (visible) Animation.CARD_SCALE_EXPANDED else Animation.CARD_SCALE_COLLAPSED,
        animationSpec = spring(dampingRatio = Animation.SPRING_DAMPING_RATIO, stiffness = Animation.SPRING_STIFFNESS)
    )
    val cardAlpha by animateFloatAsState(
        targetValue = if (visible) Animation.CARD_SCALE_EXPANDED else 0f,
        animationSpec = tween(durationMillis = Animation.CARD_ALPHA_DURATION, delayMillis = Animation.CARD_ALPHA_DELAY_PER_INDEX * index)
    )
    val clickScale by animateFloatAsState(
        targetValue = if (isPressed) Animation.CARD_SCALE_PRESSED else Animation.CARD_SCALE_EXPANDED,
        animationSpec = tween(Animation.CARD_CLICK_ANIMATION_DURATION)
    )
    
    // Create gradient based on the background color with enhanced vibrancy
    val gradientColors = if (isSpecialOffer) {
        listOf(
            backgroundColor,
            backgroundColor.copy(red = backgroundColor.red * 1.3f, green = backgroundColor.green * 1.3f, blue = backgroundColor.blue * 1.3f)
        )
    } else {
        listOf(
            backgroundColor.copy(alpha = 0.85f),
            backgroundColor
        )
    }
    
    // Trigger entrance animation
    LaunchedEffect(Unit) {
        visible = true
    }
    
    Card(
        modifier = modifier
            .padding(4.dp)
            .widthIn(min = 140.dp)
            .heightIn(min = 96.dp)
            .graphicsLayer(
                scaleX = cardScale * clickScale,
                scaleY = cardScale * clickScale,
                alpha = cardAlpha,
                // Add subtle rotation for more dynamic appearance
                rotationZ = if (isPressed) -1f else 0f
            )
            .clickable { isPressed = !isPressed },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isPressed) 2.dp else 8.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(colors = gradientColors),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontFamily = SwiggyFontFamily,
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = subtitle,
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = SwiggyFontFamily,
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
