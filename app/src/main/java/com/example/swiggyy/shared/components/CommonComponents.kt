package com.example.swiggyy.shared.components

import androidx.compose.animation.core.*
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swiggyy.R
import com.example.swiggyy.ui.theme.SwiggyFontFamily
import kotlinx.coroutines.launch
import kotlin.math.sin

@Composable
fun LocationBar(name: String, address: String, onLocationClick: (() -> Unit)? = null) {
    // Animation states
    var isHovered by remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(false) }
    
    // Entrance animation
    val locationBarScale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.95f,
        animationSpec = spring(dampingRatio = 0.8f, stiffness = 300f)
    )
    
    val locationBarAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 300, delayMillis = 100)
    )
    
    // Hover animation
    val locationBarElevation by animateFloatAsState(
        targetValue = if (isHovered) 4f else 0f,
        animationSpec = tween(durationMillis = 200)
    )
    
    // Arrow rotation animation
    val arrowRotation by animateFloatAsState(
        targetValue = if (isHovered) 180f else 0f,
        animationSpec = tween(durationMillis = 200)
    )
    
    // Trigger entrance animation
    LaunchedEffect(Unit) {
        isVisible = true
    }
    
    // Enhanced location bar with animations
    Card(
        modifier = Modifier
            .graphicsLayer {
                scaleX = locationBarScale
                scaleY = locationBarScale
                alpha = locationBarAlpha
                shadowElevation = locationBarElevation
            }
            .clickable {
                isHovered = !isHovered
                onLocationClick?.invoke()
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Animated location icon
                val iconColor by animateColorAsState(
                    targetValue = if (isHovered) Color(0xFFFF6F00) else Color.Black,
                    animationSpec = tween(durationMillis = 200)
                )
                
                Image(
                    painter = painterResource(R.drawable.icon_location),
                    contentDescription = "Location arrow",
                    modifier = Modifier
                        .size(20.dp)
                        .graphicsLayer {
                            if (isHovered) {
                                // Subtle pulse effect when hovered
                                val scale = 1f + (sin(System.currentTimeMillis() / 300f) * 0.05f).toFloat()
                                scaleX = scale
                                scaleY = scale
                            }
                        },
                    contentScale = ContentScale.Crop,
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(iconColor)
                )
                
                Spacer(modifier = Modifier.width(4.dp))
                
                Text(
                    text = name,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = SwiggyFontFamily,
                    color = if (isHovered) Color(0xFFFF6F00) else Color.Black
                )

                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown arrow",
                    tint = if (isHovered) Color(0xFFFF6F00) else Color.Gray,
                    modifier = Modifier
                        .size(20.dp)
                        .graphicsLayer { rotationZ = arrowRotation }
                )
            }

            Text(
                text = address,
                color = Color.Gray,
                fontFamily = SwiggyFontFamily,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Animation states
    var isVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }
    
    // Entrance animation
    val searchBarScale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.95f,
        animationSpec = spring(dampingRatio = 0.8f, stiffness = 300f)
    )
    
    val searchBarAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 300)
    )
    
    // Focus animation
    val searchBarElevation by animateFloatAsState(
        targetValue = if (isFocused) 8f else 2f,
        animationSpec = tween(durationMillis = 200)
    )
    
    val borderColor by animateColorAsState(
        targetValue = if (isFocused) Color(0xFFFF6F00) else Color.LightGray,
        animationSpec = tween(durationMillis = 200)
    )
    
    // Trigger entrance animation
    LaunchedEffect(Unit) {
        isVisible = true
    }
    
    // Enhanced search bar with shadow and animations
    Card(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = searchBarScale
                scaleY = searchBarScale
                alpha = searchBarAlpha
                shadowElevation = searchBarElevation
            },
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, borderColor, RoundedCornerShape(24.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .clickable { isFocused = !isFocused },
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Animated search icon
                val searchIconColor by animateColorAsState(
                    targetValue = if (isFocused) Color(0xFFFF6F00) else Color.Gray,
                    animationSpec = tween(durationMillis = 200)
                )
            
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = searchIconColor
            )

            Spacer(modifier = Modifier.width(10.dp))

            // Text Field without underline
            BasicTextField(
                value = query,
                onValueChange = { 
                    onQueryChange(it)
                    if (!isFocused) isFocused = true
                },
                singleLine = true,
                textStyle = TextStyle(
                    fontFamily = SwiggyFontFamily,
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                decorationBox = { innerTextField ->
                    if (query.isEmpty()) {
                        Text(
                            text = "Search for 'Milk'",
                            color = Color.Gray,
                            fontFamily = SwiggyFontFamily,
                            fontSize = 16.sp
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

        // Mic Icon with animation
        val microphoneScale = remember { Animatable(1f) }
        val coroutineScope = rememberCoroutineScope()
        var isPressed by remember { mutableStateOf(false) }
        
        LaunchedEffect(isFocused, isPressed) {
            if (isFocused || isPressed) {
                // Pulse animation when focused
                microphoneScale.animateTo(
                    targetValue = 0.8f,
                    animationSpec = tween(durationMillis = 50)
                )
                microphoneScale.animateTo(
                    targetValue = 1.2f,
                    animationSpec = spring(dampingRatio = 0.4f, stiffness = 300f)
                )
                microphoneScale.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = 100)
                )
            }
        }
        
        IconButton(
            onClick = {
                // Trigger pulse animation on click
                isPressed = true
            }
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .graphicsLayer {
                        scaleX = microphoneScale.value
                        scaleY = microphoneScale.value
                    },
                painter = painterResource(R.drawable.icon_mic),
                contentDescription = "Voice Search",
                tint = Color(0xFFFF6F00) // Orange
            )
        }
    }
}
}

@Composable
fun VegFilterButton(
    isVegFilterEnabled: Boolean,
    onVegFilterToggle: () -> Unit
) {
    Card(
        onClick = onVegFilterToggle,
        colors = CardDefaults.cardColors(
            containerColor = if (isVegFilterEnabled) Color(0xFF4CAF50) else Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, if (isVegFilterEnabled) Color(0xFF4CAF50) else Color.Gray)
    ) {
        Text(
            text = "VEG",
            color = if (isVegFilterEnabled) Color.White else Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}
