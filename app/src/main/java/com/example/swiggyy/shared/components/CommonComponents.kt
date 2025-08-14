package com.example.swiggyy.shared.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swiggyy.R
import com.example.swiggyy.ui.theme.SwiggyFontFamily

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
                imageVector = Icons.Default.KeyboardArrowDown,
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
                painter = painterResource(R.drawable.icon_mic),
                contentDescription = "Voice Search",
                tint = Color(0xFFFF6F00) // Orange
            )
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
