package com.example.core

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Centralized constants for the entire application.
 * All hardcoded values should be moved here to ensure consistency and maintainability.
 */
object AppConstants {
    
}

/**
 * Brand Colors - Swiggy specific colors used throughout the app
 */
object BrandColors {
    val SWIGGY_ORANGE = Color(0xFFFF5722)
    val SWIGGY_ORANGE_DARK = Color(0xFFFF6F00)
    val SWIGGY_ORANGE_LIGHT = Color(0xFFFF6B35)
    val SWIGGY_GRADIENT_START = Color(0xFFFF4500)
    val SWIGGY_GRADIENT_MID = Color(0xFFFF6B35)
    val SWIGGY_GRADIENT_END = Color(0xFFFF5722)
}

/**
 * UI Colors - Common colors used in the app
 */
object Colors {
    val WHITE = Color(0xFFFFFFFF)
    val BLACK = Color(0xFF000000)
    val TRANSPARENT = Color.Transparent
    val RED = Color.Red

    // Gray variants
    val GRAY_LIGHT = Color(0xFFF5F5F5)
    val GRAY_MEDIUM = Color(0xFFE0E0E0)
    val GRAY_DARK = Color(0xFF757575)
    val GRAY_TEXT = Color(0xFF424444)
    val GRAY_TEXT_SECONDARY = Color(0xFF454747)
    val GRAY_TEXT_TERTIARY = Color(0xFF5E6062)
    val GRAY_SECONDARY = Color(0xFF7C7C7C)
    val GRAY_BORDER = Color(0xFFE5E5E5)
    val GRAY_BORDER_LIGHT = Color(0xFFE8E8E8)
    val GRAY_DROPDOWN = Color(0xFFB0B0B0)
    val GRAY_DISABLED = Color(0xFFADAAAA)

    // Status colors
    val SUCCESS_GREEN = Color(0xFF4CAF50)
    val ERROR_RED = Color(0xFFE74C3C)
    val WARNING_ORANGE = Color(0xFFFF9800)
    val INFO_BLUE = Color(0xFF2196F3)
    val DEEP_BLUE = Color(0xFF1E3A8A)

    // Background colors
    val BACKGROUND_PRIMARY = Color(0xFFFCFCFC)
    val BACKGROUND_SECONDARY = Color(0xFFF0F0F0)
    val SURFACE_PRIMARY = Color(0xFFFFFFFF)
    val FEASTIVAL_BACKGROUND = Color(0xFFFFFAF0)
    val CARD_BACKGROUND = Color(0xFFE5E5E5)

    // Category colors
    val CATEGORY_GREEN = Color(0xFFE8F5E8)
    val CATEGORY_GREEN_LIGHT = Color(0xFFE8F5E9)
    val CATEGORY_ORANGE = Color(0xFFFFF3E0)
    val CATEGORY_ORANGE_LIGHT = Color(0xFFFFE0B2)
    val CATEGORY_RED = Color(0xFFFFEBEE)
    val CATEGORY_PURPLE = Color(0xFFF3E5F5)
    val CATEGORY_YELLOW = Color(0xFFFFF8E1)
    val CATEGORY_YELLOW_LIGHT = Color(0xFFFFF8DC)
    val CATEGORY_BLUE = Color(0xFFE3F2FD)

    // Special colors
    val STAR_YELLOW = Color(0xFFFFD500)
    val STAR_YELLOW_BRIGHT = Color(0xFFFFD700)
    val VEG_GREEN = Color(0xFF4CAF50)
    val NON_VEG_RED = Color(0xFFE74C3C)
    val SHADOW_BLACK = Color.Black.copy(alpha = 0.05f)
    val PRICE_ORANGE = Color(0xFFFF6B35)

    // Gradient colors for special effects
    val FEASTIVAL_DARK = Color(0xFF0D2B36)
    val FEASTIVAL_MID_DARK = Color(0xFF1A4A5C)
    val FEASTIVAL_MID = Color(0xFF2B5F5F)
    val FEASTIVAL_LIGHT = Color(0xFF1E4A4A)
    val FEASTIVAL_TEXT = Color(0xFF2B5F5F)

    // Themed colors with alpha
    val BLACK_ALPHA_66 = Color.Black.copy(alpha = Alpha.SECONDARY_TEXT)
    val BLACK_ALPHA_56 = Color.Black.copy(alpha = Alpha.MEDIUM_TRANSPARENT)
    val BLACK_ALPHA_51 = Color.Black.copy(alpha = 0.51f)
    val BLACK_ALPHA_40 = Color.Black.copy(alpha = Alpha.HINT_TEXT)
    val BLACK_ALPHA_32 = Color.Black.copy(alpha = Alpha.DISABLED)
    val BLACK_ALPHA_20 = Color.Black.copy(alpha = Alpha.SEMI_TRANSPARENT)
    val WHITE_ALPHA_85 = Color.White.copy(alpha = 0.85f)
    val WHITE_ALPHA_80 = Color.White.copy(alpha = 0.8f)
    val WHITE_ALPHA_30 = Color.White.copy(alpha = 0.3f)
    val BLACK_ALPHA_70 = Color.Black.copy(alpha = 0.7f)
    val BLACK_ALPHA_50 = Color.Black.copy(alpha = 0.5f)
    val GRAY_ALPHA_50 = Color.Gray.copy(alpha = 0.5f)
}

/**
 * Typography - Font sizes used throughout the app
 */
object FontSizes {
    val EXTRA_SMALL = 8.sp
    val TINY = 9.sp
    val MICRO = 10.sp
    val SMALL = 11.sp
    val MEDIUM = 12.sp
    val BODY_SMALL = 13.sp
    val NORMAL = 14.sp
    val BODY = 15.sp
    val LARGE = 16.sp
    val EXTRA_LARGE = 18.sp
    val TITLE_SMALL = 20.sp
    val TITLE = 22.sp
    val SUBTITLE = 24.sp
    val HEADING = 25.sp
    val HEADING_LARGE = 28.sp
    val HERO_SMALL = 32.sp
    val HERO = 40.sp
    val BRAND = 48.sp
    val BRAND_LARGE = 60.sp
    val PHONE_TEXT = 15.sp
    val PHONE_HINT = 15.sp
    val LINE_HEIGHT = 30.sp
    val FOOTER_TEXT = 12.sp
}

/**
 * Spacing - Consistent spacing values
 */
object Spacing {
    val NONE = 0.dp
    val EXTRA_SMALL = 2.dp
    val TINY = 4.dp
    val SMALL = 6.dp
    val MEDIUM_SMALL = 8.dp
    val MEDIUM = 10.dp
    val NORMAL = 12.dp
    val MEDIUM_LARGE = 14.dp
    val LARGE = 15.dp
    val LARGE_MEDIUM = 16.dp
    val EXTRA_LARGE = 18.dp
    val LARGE_PLUS = 20.dp
    val HUGE = 21.dp
    val EXTRA_HUGE = 24.dp
    val MASSIVE_SMALL = 25.dp
    val MASSIVE = 27.dp
    val EXTRA_MASSIVE = 30.dp
    val GIANT = 32.dp
    val MEGA = 47.dp
    val ULTRA = 48.dp
    val SUPER = 49.dp
    val EXTREME = 55.dp
    val MAXIMUM = 59.dp
}

/**
 * Dimensions - UI component dimensions
 */
object Dimensions {
    // Icon sizes
    val ICON_MICRO = 12.dp
    val ICON_TINY = 14.dp
    val ICON_SMALL = 16.dp
    val ICON_MEDIUM = 18.dp
    val ICON_NORMAL = 20.dp
    val ICON_LARGE = 21.dp
    val ICON_EXTRA_LARGE = 24.dp
    val ICON_HUGE = 32.dp
    val ICON_MASSIVE = 36.dp
    val ICON_GIANT = 40.dp

    // Button dimensions
    val BUTTON_HEIGHT = 40.dp
    val BUTTON_HEIGHT_LARGE = 48.dp
    val BUTTON_WIDTH_TINY = 62.dp
    val BUTTON_WIDTH_SMALL = 64.dp
    val BUTTON_WIDTH_MEDIUM = 133.dp

    // Card dimensions
    val CARD_HEIGHT_TINY = 36.dp
    val CARD_HEIGHT_SMALL = 40.dp
    val CARD_HEIGHT_MEDIUM = 42.dp
    val CARD_HEIGHT_LARGE = 80.dp
    val CARD_HEIGHT_BANNER = 120.dp
    val CARD_HEIGHT_FEATURED = 200.dp
    val CARD_HEIGHT_STORE = 250.dp
    val CARD_ELEVATION_NONE = 0.dp
    val CARD_ELEVATION_TINY = 1.dp
    val CARD_ELEVATION_SMALL = 2.dp
    val CARD_ELEVATION_MEDIUM = 4.dp
    val CARD_ELEVATION_LARGE = 6.dp
    val CARD_ELEVATION_HUGE = 8.dp
    val CARD_ELEVATION_MASSIVE = 12.dp

    // Border and corner radius
    val BORDER_WIDTH = 1.dp
    val BORDER_WIDTH_MEDIUM = 2.dp
    val CORNER_RADIUS_TINY = 2.dp
    val CORNER_RADIUS_SMALL = 4.dp
    val CORNER_RADIUS_MEDIUM = 7.dp
    val CORNER_RADIUS_NORMAL = 8.dp
    val CORNER_RADIUS_LARGE = 12.dp
    val CORNER_RADIUS_EXTRA_LARGE = 15.dp
    val CORNER_RADIUS_HUGE = 16.dp
    val CORNER_RADIUS_MASSIVE = 20.dp
    val CORNER_RADIUS_GIANT = 24.dp
    val CORNER_RADIUS_MEGA = 25.dp
    val CORNER_RADIUS_ULTRA = 30.dp
    val CORNER_RADIUS_EXTREME = 50.dp

    // Screen sections
    val BANNER_HEIGHT = 367.dp
    val SECTION_HEADER_HEIGHT = 55.dp
    val BOTTOM_BAR_HEIGHT = 56.dp

    // Input dimensions
    val INPUT_HEIGHT = 40.dp
    val INPUT_PADDING_HORIZONTAL = 18.dp
    val OTP_INPUT_SIZE = 42.dp
    val PHONE_INPUT_HEIGHT = 40.dp
    val COUNTRY_CODE_WIDTH = 64.dp
    val COUNTRY_CODE_HORIZONTAL_PADDING = 8.dp
    val FLAG_WIDTH = 24.dp
    val FLAG_HEIGHT = 16.dp
    val DROPDOWN_ICON_SIZE = 14.dp
    val PHONE_INPUT_HORIZONTAL_PADDING = 10.dp
    val PHONE_INPUT_SPACER = 6.dp
    val SOCIAL_BUTTON_SIZE = 40.dp
    val SOCIAL_ICON_SIZE = 21.dp
    val DOT_SIZE = 2.5.dp
    val DOT_SPACING = 2.5.dp
    val FOOTER_LINK_SPACING = 10.dp
    val FOOTER_UNDERLINE_HEIGHT = 1.dp
    val FOOTER_LINK_BOTTOM_SPACING = 2.dp

    // Image sizes
    val IMAGE_TINY = 16.dp
    val IMAGE_SMALL = 48.dp
    val IMAGE_MEDIUM = 70.dp
    val IMAGE_NORMAL = 80.dp
    val IMAGE_LARGE = 90.dp
    val IMAGE_EXTRA_LARGE = 120.dp
    val IMAGE_BANNER = 150.dp
    val IMAGE_HUGE = 157.dp
    val IMAGE_MASSIVE = 180.dp
    val IMAGE_GIANT = 181.dp
    val IMAGE_MEGA = 199.dp
    val IMAGE_ULTRA = 212.dp
    val IMAGE_SUPER = 219.dp
    val IMAGE_EXTREME = 223.dp

    // Layout spacing
    val LAYOUT_PADDING = 16.dp
    val LAYOUT_MARGIN = 8.dp
    val SECTION_SPACING = 24.dp

    // Component specific sizes
    val CATEGORY_ITEM_WIDTH = 90.dp
    val RESTAURANT_CARD_WIDTH = 160.dp
    val RESTAURANT_CARD_WIDTH_LARGE = 280.dp
    val SWIGGY_OPTION_WIDTH = 100.dp
    val STORE_ITEM_WIDTH = 150.dp

    // Offset values
    val OFFSET_SMALL = (-10).dp
    val OFFSET_LARGE = (-35).dp
    val OFFSET_HUGE = (-41).dp
    val OFFSET_MASSIVE = (-94).dp
    val OFFSET_Y_SMALL = 5.dp
    val OFFSET_Y_MEDIUM = 138.dp
    val OFFSET_Y_LARGE = 170.dp
    val OFFSET_Y_HUGE = 175.dp
    val OFFSET_Y_MASSIVE = 177.dp
    val OFFSET_Y_GIANT = 200.dp
    val OFFSET_Y_ULTRA = 250.dp
    val OFFSET_Y_EXTREME = 290.dp

    // Pizza specific dimensions
    val PIZZA_WIDTH = 223.dp
    val PIZZA_HEIGHT = 212.dp
    val PIZZA_OFFSET_X = 250.dp
    val PIZZA_OFFSET_Y = (-41).dp
}

/**
 * Animation - Animation durations and delays
 */
object Animation {
    // Duration in milliseconds
    const val DURATION_INSTANT = 50
    const val DURATION_SHORT = 100
    const val DURATION_QUICK = 150
    const val DURATION_MEDIUM = 200
    const val DURATION_NORMAL = 300
    const val DURATION_LONG = 500
    const val DURATION_ENTRANCE = 800
    const val DURATION_EXTRA_LONG = 1000
    const val DURATION_SLOW = 1500

    // Delays in milliseconds
    const val DELAY_SHORT = 50
    const val DELAY_MEDIUM = 100
    const val DELAY_LONG = 400

    // Special animation durations
    const val SHIMMER_DURATION = 2000
    const val ROTATION_DURATION_SHORT = 2000
    const val ROTATION_DURATION_LONG = 2500
    const val INFINITE_REPEAT_DURATION = 2500
    const val FADE_DURATION = 200
    const val SLIDE_DURATION = 300
    const val PULSE_DURATION = 1000

    // Network simulation delays
    const val NETWORK_DELAY_SHORT = 1000
    const val NETWORK_DELAY_MEDIUM = 1500
    const val NETWORK_DELAY_LONG = 2000

    // Spring animation values
    const val SPRING_DAMPING_DEFAULT = 0.8f
    const val SPRING_DAMPING_BOUNCY = 0.6f
    const val SPRING_DAMPING_CRITICAL = 0.4f
    const val SPRING_STIFFNESS_LOW = 300f
    const val SPRING_STIFFNESS_MEDIUM = 500f
    const val SPRING_STIFFNESS_HIGH = 800f

    // Shimmer effect values
    const val SHIMMER_RANGE = 1000f
    const val SHIMMER_OFFSET_START = -1000f
    const val SHIMMER_OFFSET_END = 1000f
    const val SHIMMER_BLUR_START = -300f
}

/**
 * Timer - Timer and countdown values
 */
object Timer {
    const val OTP_RESEND_TIMER = 15
    const val OTP_RESEND_TIMER_EXTENDED = 30
    const val TIMER_TICK_INTERVAL = 1000L
    const val AUTO_LOGOUT_TIMER = 300000L // 5 minutes
}

/**
 * Network - Network related constants
 */
object Network {
    const val TIMEOUT_SECONDS = 30
    const val RETRY_ATTEMPTS = 3
    const val CACHE_SIZE = 10 * 1024 * 1024L // 10MB
}

/**
 * UI Text - Common UI strings
 */
object Strings {
    // Common actions
    const val CONTINUE = "Continue"
    const val CANCEL = "Cancel"
    const val OK = "OK"
    const val RETRY = "Retry"
    const val BACK = "Back"
    const val NEXT = "Next"
    const val DONE = "Done"
    const val SAVE = "Save"
    const val EDIT = "Edit"
    const val DELETE = "Delete"
    const val SEARCH = "Search"
    const val CLEAR = "Clear"
    const val REFRESH = "Refresh"
    const val LOADING = "Loading..."
    const val SEE_ALL = "See all"
    const val SEE_ALL_BUTTON = "See All"
    const val SHOW_MORE = "Show more"
    const val SHOW_LESS = "Show less"
    const val ADD = "Add"
    const val FREE_DELIVERY = "FREE DELIVERY"
    const val ONE_BENEFITS = "one BENEFITS"
    const val BENEFITS = "BENEFITS"
    const val AD = "AD"

    // Navigation
    const val HOME = "Home"
    const val FOOD = "Food"
    const val INSTAMART = "InstaMart"
    const val DINEOUT = "Dineout"

    // Auth related
    const val LOG_IN_OR_SIGN_UP = "Log in or sign up"
    const val OTP_VERIFICATION = "OTP Verification"
    const val OTP_SENT_MESSAGE = "We have sent a verification code to"
    const val RESEND_SMS = "Resend SMS"
    const val RESEND_SMS_IN = "Resend SMS in"
    const val TRY_OTHER_LOGIN_METHODS = "Try other login methods"
    const val ENTER_PHONE_NUMBER = "Enter Phone Number"
    const val PHONE_NUMBER_PLACEHOLDER = "+91"

    // Welcome screen
    const val WELCOME_TITLE = "India's #1 Food Delivery\nand Dining App"
    const val SWIGGY_BRAND = "SWIGGY"
    const val BY_CONTINUING_TEXT = "By continuing, you agree to our"
    const val TERMS_OF_SERVICE = "Terms of Services"
    const val PRIVACY_POLICY = "Privacy Policy"
    const val CONTENT_POLICY = "Content Policy"
    const val OR = "or"

    // Content descriptions for accessibility
    const val CONTENT_DESC_BACK = "Back"
    const val CONTENT_DESC_CLOSE = "Close"
    const val CONTENT_DESC_SEARCH = "Search"
    const val CONTENT_DESC_VOICE_SEARCH = "Voice Search"
    const val CONTENT_DESC_MENU = "Menu"
    const val CONTENT_DESC_PROFILE = "Profile"
    const val CONTENT_DESC_PROFILE_PICTURE = "Profile Picture"
    const val CONTENT_DESC_CART = "Cart"
    const val CONTENT_DESC_FAVORITE = "Favorite"
    const val CONTENT_DESC_FILTER = "Filter"
    const val CONTENT_DESC_SORT = "Sort"
    const val CONTENT_DESC_INDIA_FLAG = "India flag"
    const val CONTENT_DESC_DROPDOWN = "Dropdown"
    const val CONTENT_DESC_DROPDOWN_ARROW = "Dropdown arrow"
    const val CONTENT_DESC_LOCATION_ARROW = "Location arrow"
    const val CONTENT_DESC_GOOGLE = "Google"
    const val CONTENT_DESC_MORE_OPTIONS = "More options"
    const val CONTENT_DESC_BURGER = "Burger"
    const val CONTENT_DESC_FLOAT = "Float"
    const val CONTENT_DESC_CAKE = "Cake"
    const val CONTENT_DESC_PIZZA = "Pizza"
    const val CONTENT_DESC_RATING = "Rating"

    // Error messages
    const val ERROR_PHONE_EMPTY = "Phone number cannot be empty"
    const val ERROR_PHONE_INVALID = "Phone number must contain only digits"
    const val ERROR_PHONE_LENGTH = "Phone number must be 10 digits"
    const val ERROR_OTP_INCOMPLETE = "Please enter complete 6-digit OTP"
    const val ERROR_OTP_INVALID = "Invalid OTP. Please try again."
    const val ERROR_NETWORK = "Network error. Please check your connection."
    const val ERROR_GENERIC = "Something went wrong. Please try again."
    const val ERROR_RESEND_FAILED = "Failed to resend OTP. Please try again."
    const val ERROR_SOCIAL_LOGIN_FAILED = "Social login failed. Please try again."
    const val ERROR_SEND_OTP_FAILED = "Failed to send OTP. Please try again."

    // Success messages
    const val SUCCESS_OTP_SENT = "OTP sent successfully"
    const val SUCCESS_OTP_SENT_TO_PHONE = "OTP sent to +91-%s"
    const val SUCCESS_OTP_RESENT = "OTP resent successfully"
    const val SUCCESS_OTP_VERIFIED = "OTP verified successfully"
    const val SUCCESS_LOGIN = "Login successful"
    const val SUCCESS_LOGOUT = "Logged out successfully"

    // Placeholder and hints
    const val SEARCH_HINT = "Search for dishes, restaurants"
    const val SEARCH_HINT_GROCERY = "Search for groceries"
    const val SEARCH_PLACEHOLDER = "Search for 'Milk'"
    const val LOCATION_DEFAULT = "Current Location"

    // Section titles
    const val WHATS_ON_YOUR_MIND = "What's on your mind?"
    const val FEATURED = "FEATURED"
    const val FEATURED_SUBTITLE = "Top 2090 restaurants to explore"
    const val MORE_ON_SWIGGY = "More on Swiggy"
    const val NINETY_NINE_STORE = "99 store"
    const val NINETY_NINE_NUMBER = "99"
    const val STORE_TEXT = " store"
    const val FREE_DELIVERY_ECOSAVER = "Free delivery with ecosaver mode"

    // Promotional content
    const val FLAVOURFUL = "✨ FLAVOURFUL ✨"
    const val FEASTIVAL = "FEAST-IVAL"
    const val FLAT_150_OFF = "🎉 FLAT ₹150 OFF 🎉"
    const val DISHES_FROM_29 = "Dishes\nFrom ₹29"
    const val FLASH_DEALS = "FLASH\nDEALS"
    const val FLAT_150_OFF_SHORT = "Flat ₹150\nOFF"
    const val FOOD_IN_10_MINS = "Food In\n10 Mins"
    const val BOLT = "⚡ Bolt"
    const val MEALS_AT_99 = "Meals\nat ₹99"
    const val PRICE_99 = "₹99"
    const val HDFC_BANK = "🏦 HDFC"
    const val FLAT_75_OFF = "💳 Flat ₹75 OFF*"
    const val HDFC_CREDIT_CARD = "using HDFC Bank Credit card"
    const val LIVE_IT_UP = "Live \nit up!"
    const val CRAFTED_WITH_LOVE = "Crafted with ❤️ in Baner, India"

    // Rating and time format strings
    const val RATING_FORMAT = "%.1f"
    const val RATING_WITH_TIME_FORMAT = "%.1f • %s"
    const val RATING_WITH_REVIEWS_FORMAT = "%.1f (%s) • %s"
    const val CURRENCY_FORMAT = "₹%d"
    const val RESEND_TIMER_FORMAT = "Resend SMS in %d"
}

/**
 * Numeric Constants - Common numeric values
 */
object Numbers {
    const val ZERO = 0
    const val ONE = 1
    const val TWO = 2
    const val THREE = 3
    const val FOUR = 4
    const val FIVE = 5
    const val SIX = 6
    const val SEVEN = 7
    const val EIGHT = 8
    const val NINE = 9
    const val TEN = 10
    const val FIFTEEN = 15
    const val TWENTY = 20
    const val FIFTY = 50
    const val HUNDRED = 100

    // Phone number
    const val PHONE_NUMBER_LENGTH = 10
    const val OTP_LENGTH = 6
    const val COUNTRY_CODE = "+91"

    // Rating and reviews
    const val MAX_RATING = 5.0f
    const val MIN_RATING = 1.0f
    const val DEFAULT_RATING = 4.0f

    // Pagination
    const val DEFAULT_PAGE_SIZE = 20
    const val MAX_PAGE_SIZE = 100

    // Limits
    const val MAX_SEARCH_RESULTS = 50
    const val MAX_RECENT_SEARCHES = 10
    const val MAX_CART_ITEMS = 99

    // Animation specific values
    const val ROTATION_ANGLE_SMALL = 5f
    const val ROTATION_ANGLE_MEDIUM = 30.921f
    const val ROTATION_ANGLE_LARGE = 180f
    const val ROTATION_ANGLE_REVERSE = -20.921f
    const val ROTATION_ANGLE_FULL = 360f

    // Scale values
    const val SCALE_NONE = 0f
    const val SCALE_TINY = 0.8f
    const val SCALE_SMALL = 0.95f
    const val SCALE_DEFAULT = 1f
    const val SCALE_MEDIUM = 1.05f
    const val SCALE_LARGE = 1.2f
    const val SCALE_HUGE = 1.3f

    // Other specific values
    const val OTP_BOX_COUNT = 6
    const val SOCIAL_LOGIN_SPACING = 47
    const val FOOTER_LINK_MULTIPLIER = 5
}

/**
 * Alpha Values - Transparency values
 */
object Alpha {
    const val FULLY_TRANSPARENT = 0.0f
    const val SLIGHTLY_TRANSPARENT = 0.1f
    const val SEMI_TRANSPARENT = 0.2f
    const val LIGHT_TRANSPARENT = 0.3f
    const val MEDIUM_TRANSPARENT = 0.4f
    const val HALF_TRANSPARENT = 0.5f
    const val MOSTLY_TRANSPARENT = 0.6f
    const val MOSTLY_OPAQUE = 0.7f
    const val SLIGHTLY_OPAQUE = 0.8f
    const val NEAR_OPAQUE = 0.85f
    const val ALMOST_OPAQUE = 0.9f
    const val FULLY_OPAQUE = 1.0f

    // Common alpha values
    const val DISABLED = 0.32f
    const val SECONDARY_TEXT = 0.56f
    const val TERTIARY_TEXT = 0.51f
    const val HINT_TEXT = 0.4f
    const val SUBTITLE_TEXT = 0.66f
    const val PRESSED_STATE = 0.12f
    const val HOVER_STATE = 0.08f
    const val FOCUS_STATE = 0.24f

    // Specific alpha values found in the codebase
    const val ALPHA_32 = 0.32f
    const val ALPHA_40 = 0.4f
    const val ALPHA_51 = 0.51f
    const val ALPHA_56 = 0.56f
    const val ALPHA_66 = 0.66f
    const val ALPHA_70 = 0.7f
    const val ALPHA_85 = 0.85f
}

/**
 * Layout Constants - Layout specific values
 */
object Layout {
    const val WELCOME_TOP_PADDING = 151
    const val OTP_TOP_PADDING = 55
    const val OTP_HEADER_SPACING = 17
    const val OTP_MESSAGE_SPACING = 59
    const val OTP_PHONE_SPACING = 32
    const val OTP_INPUT_SPACING = 49
    const val OTP_BUTTON_SPACING = 30
    const val OTP_FOOTER_SPACING = 59
    const val BANNER_CONTENT_PADDING = 20
    const val BANNER_SPACING = 12
    const val BANNER_SECTION_SPACING = 16
    const val CATEGORY_SPACING = 12
    const val RESTAURANT_SPACING = 12
    const val STORE_ITEM_SPACING = 16
    const val SOCIAL_LOGIN_SPACING = 47
    val FOOD_IMAGES_HEIGHT = 367.dp
    val PHONE_INPUT_HEIGHT = 40.dp
    val COUNTRY_CODE_WIDTH = 64.dp
    val COUNTRY_CODE_HORIZONTAL_PADDING = 8.dp
    val FLAG_WIDTH = 24.dp
    val FLAG_HEIGHT = 16.dp
    val DROPDOWN_ICON_SIZE = 14.dp
    val PHONE_INPUT_HORIZONTAL_PADDING = 10.dp
    val PHONE_INPUT_SPACER = 6.dp
    val SOCIAL_BUTTON_SIZE = 40.dp
    val SOCIAL_ICON_SIZE = 21.dp
    val DOT_SIZE = 2.5.dp
    val DOT_SPACING = 2.5.dp
    val FOOTER_LINK_SPACING = 10.dp
    val FOOTER_UNDERLINE_HEIGHT = 1.dp
    val FOOTER_LINK_BOTTOM_SPACING = 2.dp
}
