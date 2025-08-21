package com.example.swiggyy.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

// Using your custom SwiggyFontFamily
val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = SwiggyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = SwiggyFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    ),
    titleLarge = TextStyle(
        fontFamily = SwiggyFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 22.sp
    ),
    labelMedium = TextStyle(
        fontFamily = SwiggyFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    )
)
