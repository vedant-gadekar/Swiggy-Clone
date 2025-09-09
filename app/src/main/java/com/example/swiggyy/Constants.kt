package com.example.swiggyy

import androidx.compose.ui.unit.dp

/**
 * App module specific constants.
 * Keep only app-scoped values here (navigation keys, request codes, etc.).
 * UI colors/typography should live in theme, shared items in core.
 */
object AppConstants {
    // Navigation route names or keys used by the app shell
    const val ROUTE_HOME = "home"
    const val ROUTE_FOOD = "food"
    const val ROUTE_INSTAMART = "instamart"
    const val ROUTE_DINEOUT = "dineout"
    const val ROUTE_AUTH = "auth"
}

object AppNumbers {
    const val SPLASH_DELAY_MS = 1200
}

object AppDimensions {
    val BOTTOM_NAV_ELEVATION = 6.dp
}

object AppBooleans {
    const val ENABLE_LOGGING = true
}


