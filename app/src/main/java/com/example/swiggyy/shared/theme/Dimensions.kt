package com.example.swiggyy.shared.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A class to define all the dimensions used in the app.
 * This helps maintain consistency across the app and makes it easier to adjust sizes.
 */
@Immutable
data class Dimensions(
    // Spacing
    val spacingExtraSmall: Dp = 4.dp,
    val spacingSmall: Dp = 8.dp,
    val spacingMedium: Dp = 16.dp,
    val spacingLarge: Dp = 24.dp,
    val spacingExtraLarge: Dp = 32.dp,
    
    // Padding
    val paddingExtraSmall: Dp = 4.dp,
    val paddingSmall: Dp = 8.dp,
    val paddingMedium: Dp = 16.dp,
    val paddingLarge: Dp = 24.dp,
    val paddingExtraLarge: Dp = 32.dp,
    
    // Elevation
    val elevationSmall: Dp = 2.dp,
    val elevationMedium: Dp = 4.dp,
    val elevationLarge: Dp = 8.dp,
    
    // Sizes
    val iconSizeSmall: Dp = 16.dp,
    val iconSizeMedium: Dp = 24.dp,
    val iconSizeLarge: Dp = 32.dp,
    
    // Corner radius
    val radiusSmall: Dp = 4.dp,
    val radiusMedium: Dp = 8.dp,
    val radiusLarge: Dp = 16.dp,
    val radiusExtraLarge: Dp = 24.dp,
    
    // Button
    val buttonHeight: Dp = 48.dp,
    val buttonCornerRadius: Dp = radiusMedium,
    val buttonElevation: Dp = elevationSmall,
    
    // Card
    val cardElevation: Dp = elevationSmall,
    val cardCornerRadius: Dp = radiusMedium,
    
    // Image
    val imageSmall: Dp = 48.dp,
    val imageMedium: Dp = 80.dp,
    val imageLarge: Dp = 120.dp,
    
    // Divider
    val dividerThickness: Dp = 1.dp,
    
    // Border
    val borderWidth: Dp = 1.dp
)

/**
 * Default dimensions for the app.
 */
val defaultDimensions = Dimensions()

/**
 * CompositionLocal for dimensions to be used throughout the app.
 */
val LocalDimensions = staticCompositionLocalOf { defaultDimensions }
