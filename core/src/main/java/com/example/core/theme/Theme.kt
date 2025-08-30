package com.example.swiggyy.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import com.example.core.AppConstants


object AppColors {
    val Primary = AppConstants.BrandColors.SWIGGY_ORANGE
    val PrimaryDark = AppConstants.BrandColors.SWIGGY_ORANGE_DARK
    val Secondary = Color(0xFF181725)

    // Swiggy Brand Colors
    val SwiggyOrange = AppConstants.BrandColors.SWIGGY_ORANGE
    val SwiggyOrangeDark = AppConstants.BrandColors.SWIGGY_ORANGE_DARK
    val SwiggyOrangeLight = AppConstants.BrandColors.SWIGGY_ORANGE_LIGHT
    val Background = Color(0xFFFCFCFC)
    val Surface = Color(0xFFFFFFFF)
    val Error = Color(0xFFE74C3C)
    val OnPrimary = Color(0xFFFFFFFF)
    val OnSecondary = Color(0xFFFFFFFF)
    val OnBackground = Color(0xFF181725)
    val OnSurface = Color(0xFF181725)
    val OnError = Color(0xFFFFFFFF)

    // Additional colors
    val TextPrimary = Color(0xFF181725)
    val TextSecondary = Color(0xFF7C7C7C)
    val BorderColor = Color(0xFFE2E2E2)
    val InputBackground = Color(0xFFF2F3F2)

    // Category colors
    val CategoryGreen = Color(0xFFE8F5E8)
    val CategoryOrange = Color(0xFFFFF3E0)
    val CategoryRed = Color(0xFFFFEBEE)
    val CategoryPurple = Color(0xFFF3E5F5)
    val CategoryYellow = Color(0xFFFFF8E1)
    val CategoryBlue = Color(0xFFE3F2FD)
}
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    error = AppColors.Error,
    onPrimary = AppColors.OnPrimary,
    onSecondary = AppColors.OnSecondary,
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF),
    onError = AppColors.OnError
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = AppColors.Background,
    surface = AppColors.Surface,
    error = AppColors.Error,
    onPrimary = AppColors.OnPrimary,
    onSecondary = AppColors.OnSecondary,
    onBackground = AppColors.OnBackground,
    onSurface = AppColors.OnSurface,
    onError = AppColors.OnError
)

@Composable
fun SwiggyyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
