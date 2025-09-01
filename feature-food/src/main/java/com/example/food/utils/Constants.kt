package com.example.feature_food.presentation.constants

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Colors {
    val LIGHT_GRAY = Color.LightGray
    val RED = Color.Red
    val OFFER_CARD_BG = Color(0xFF1E4A4A)
    val WHITE = Color.White
    val CATEGORY_TITLE = Color(0xFF3D4152)
    val CATEGORY_ITEM_BORDER = Color(0xFFE8E8E8)
    val ORANGE = Color(0xFFFF6F00)
}

object Dimensions {
    val RESTAURANT_CARD_WIDTH = 300.dp
    val RESTAURANT_CARD_CORNER_RADIUS = 16.dp
    val RESTAURANT_CARD_ELEVATION_PRESSED = 2.dp
    val RESTAURANT_CARD_ELEVATION_DEFAULT = 8.dp
    val PADDING_MAIN = 16.dp
    val PADDING_SECTION = 8.dp
    val PROFILE_SIZE = 36.dp
    val BORDER_WIDTH = 1.dp
    val SPACER_HEIGHT = 20.dp
    val CAROUSEL_VERTICAL_PADDING = 16.dp
    val CAROUSEL_TAB_HORIZONTAL_PADDING = 16.dp
    val CAROUSEL_TAB_VERTICAL_PADDING = 8.dp
    val CAROUSEL_TAB_SPACING = 8.dp
    val REORDER_SECTION_VERTICAL_PADDING = 16.dp
    val REORDER_CARD_HORIZONTAL_PADDING = 16.dp
    val REORDER_CARD_VERTICAL_PADDING = 8.dp
    val REORDER_CARD_CORNER_RADIUS = 20.dp
    val REORDER_CARD_ELEVATION = 2.dp
    val REORDER_CARD_TEXT_HORIZONTAL_PADDING = 20.dp
    val REORDER_CARD_TEXT_VERTICAL_PADDING = 12.dp
    val CATEGORIES_SECTION_VERTICAL_PADDING = 16.dp
    val CATEGORIES_SECTION_TITLE_HORIZONTAL_PADDING = 16.dp
    val CATEGORIES_SECTION_TITLE_VERTICAL_PADDING = 8.dp
    val CATEGORIES_ROW_HORIZONTAL_PADDING = 16.dp
    val CATEGORIES_ROW_SPACING = 12.dp
    val CATEGORY_ITEM_WIDTH = 90.dp
    val CATEGORY_ITEM_SIZE = 80.dp
    val CATEGORY_ITEM_ELEVATION = 4.dp
    val CATEGORY_ITEM_BORDER_WIDTH = 1.dp
    val CATEGORY_ITEM_BORDER_COLOR = Color(0xFFE8E8E8)
    val CATEGORY_ITEM_BOTTOM_PADDING_PRESSED = 0.dp
    val CATEGORY_ITEM_BOTTOM_PADDING_DEFAULT = 2.dp
    val REORDER_LAZYROW_HORIZONTAL_PADDING = 16.dp
    val REORDER_LAZYROW_ITEM_SPACING = 12.dp
}

object Animation {
    const val SPRING_DAMPING_RATIO = 0.6f
    const val SPRING_STIFFNESS = 300f
    const val CARD_ALPHA_DURATION = 500
    const val CARD_ALPHA_DELAY_PER_INDEX = 100
    const val CARD_SCALE_COLLAPSED = 0.7f
    const val CARD_SCALE_PRESSED = 0.95f
    const val CARD_SCALE_EXPANDED = 1f
    const val CARD_CLICK_ANIMATION_DURATION = 150
    const val SCALE_PRESSED = 0.95f
    const val SCALE_DEFAULT = 1f
    const val SLIDE_IN_OFFSET = 100f
    const val CARD_ANIMATION_DURATION = 500
    const val CARD_ANIMATION_DELAY = 100
    const val CARD_PRESS_ANIMATION_DURATION = 100
}

object Strings {
    const val PROFILE_PICTURE = "Profile Picture"
    const val NO_CATEGORIES_AVAILABLE = "No categories available"
    const val NO_RESTAURANTS_AVAILABLE = "No restaurants available"
    const val TAB_REORDER = "REORDER"
    const val TAB_QUICK_DELIVERY = "QUICK DELIVERY"
    const val REORDER = "REORDER"
    const val WHATS_ON_YOUR_MIND = "What's on your mind?"
}
