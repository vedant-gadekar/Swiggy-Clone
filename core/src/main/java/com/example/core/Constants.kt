package com.example.core

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Centralized constants for the entire application.
 * All hardcoded values should be moved here to ensure consistency and maintainability.
 */
object AppConstants {
    
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
        
        // Gray variants
        val GRAY_LIGHT = Color(0xFFF5F5F5)
        val GRAY_MEDIUM = Color(0xFFE0E0E0)
        val GRAY_DARK = Color(0xFF757575)
        val GRAY_TEXT = Color(0xFF424444)
        val GRAY_SECONDARY = Color(0xFF7C7C7C)
        val GRAY_BORDER = Color(0xFFE5E5E5)
        
        // Status colors
        val SUCCESS_GREEN = Color(0xFF4CAF50)
        val ERROR_RED = Color(0xFFE74C3C)
        val WARNING_ORANGE = Color(0xFFFF9800)
        val INFO_BLUE = Color(0xFF2196F3)
        
        // Background colors
        val BACKGROUND_PRIMARY = Color(0xFFFCFCFC)
        val BACKGROUND_SECONDARY = Color(0xFFF0F0F0)
        val SURFACE_PRIMARY = Color(0xFFFFFFFF)
        
        // Category colors
        val CATEGORY_GREEN = Color(0xFFE8F5E8)
        val CATEGORY_ORANGE = Color(0xFFFFF3E0)
        val CATEGORY_RED = Color(0xFFFFEBEE)
        val CATEGORY_PURPLE = Color(0xFFF3E5F5)
        val CATEGORY_YELLOW = Color(0xFFFFF8E1)
        val CATEGORY_BLUE = Color(0xFFE3F2FD)
        
        // Special colors
        val STAR_YELLOW = Color(0xFFFFD500)
        val VEG_GREEN = Color(0xFF4CAF50)
        val NON_VEG_RED = Color(0xFFE74C3C)
        val SHADOW_BLACK = Color.Black.copy(alpha = 0.05f)
    }
    
    /**
     * Typography - Font sizes used throughout the app
     */
    object FontSizes {
        val EXTRA_SMALL = 9.sp
        val SMALL = 11.sp
        val MEDIUM = 12.sp
        val NORMAL = 14.sp
        val LARGE = 16.sp
        val EXTRA_LARGE = 18.sp
        val TITLE = 20.sp
        val SUBTITLE = 22.sp
        val HEADING = 25.sp
        val HERO = 40.sp
        val BRAND = 48.sp
    }
    
    /**
     * Spacing - Consistent spacing values
     */
    object Spacing {
        val EXTRA_SMALL = 4.dp
        val SMALL = 8.dp
        val MEDIUM = 12.dp
        val NORMAL = 16.dp
        val LARGE = 20.dp
        val EXTRA_LARGE = 24.dp
        val HUGE = 32.dp
        val MASSIVE = 48.dp
    }
    
    /**
     * Dimensions - UI component dimensions
     */
    object Dimensions {
        // Icon sizes
        val ICON_SMALL = 16.dp
        val ICON_MEDIUM = 24.dp
        val ICON_LARGE = 32.dp
        val ICON_EXTRA_LARGE = 40.dp
        
        // Button dimensions
        val BUTTON_HEIGHT = 40.dp
        val BUTTON_HEIGHT_LARGE = 48.dp
        val BUTTON_WIDTH_SMALL = 64.dp
        val BUTTON_WIDTH_MEDIUM = 133.dp
        
        // Card dimensions
        val CARD_HEIGHT_SMALL = 40.dp
        val CARD_HEIGHT_MEDIUM = 42.dp
        val CARD_ELEVATION = 1.dp
        val CARD_ELEVATION_MEDIUM = 4.dp
        
        // Border and corner radius
        val BORDER_WIDTH = 1.dp
        val BORDER_WIDTH_MEDIUM = 2.dp
        val CORNER_RADIUS_SMALL = 7.dp
        val CORNER_RADIUS_MEDIUM = 8.dp
        val CORNER_RADIUS_LARGE = 15.dp
        val CORNER_RADIUS_EXTRA_LARGE = 30.dp
        
        // Screen sections
        val BANNER_HEIGHT = 367.dp
        val SECTION_HEADER_HEIGHT = 55.dp
        val BOTTOM_BAR_HEIGHT = 56.dp
        
        // Input dimensions
        val INPUT_HEIGHT = 40.dp
        val INPUT_PADDING_HORIZONTAL = 18.dp
        val OTP_INPUT_SIZE = 42.dp
        
        // Image sizes
        val IMAGE_SMALL = 48.dp
        val IMAGE_MEDIUM = 80.dp
        val IMAGE_LARGE = 120.dp
        val IMAGE_BANNER = 150.dp
        
        // Layout spacing
        val LAYOUT_PADDING = 16.dp
        val LAYOUT_MARGIN = 8.dp
        val SECTION_SPACING = 24.dp
    }
    
    /**
     * Animation - Animation durations and delays
     */
    object Animation {
        // Duration in milliseconds
        const val DURATION_SHORT = 100
        const val DURATION_MEDIUM = 300
        const val DURATION_LONG = 500
        const val DURATION_EXTRA_LONG = 1000
        
        // Delays in milliseconds
        const val DELAY_SHORT = 50
        const val DELAY_MEDIUM = 100
        const val DELAY_LONG = 400
        
        // Special animation durations
        const val SHIMMER_DURATION = 2000
        const val INFINITE_REPEAT_DURATION = 2500
        const val FADE_DURATION = 200
        const val SLIDE_DURATION = 300
        
        // Network simulation delays
        const val NETWORK_DELAY_SHORT = 1000
        const val NETWORK_DELAY_MEDIUM = 1500
        const val NETWORK_DELAY_LONG = 2000
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
        const val SHOW_MORE = "Show more"
        const val SHOW_LESS = "Show less"
        
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
        const val CONTENT_DESC_MENU = "Menu"
        const val CONTENT_DESC_PROFILE = "Profile"
        const val CONTENT_DESC_CART = "Cart"
        const val CONTENT_DESC_FAVORITE = "Favorite"
        const val CONTENT_DESC_FILTER = "Filter"
        const val CONTENT_DESC_SORT = "Sort"
        const val CONTENT_DESC_INDIA_FLAG = "India flag"
        const val CONTENT_DESC_DROPDOWN = "Dropdown"
        const val CONTENT_DESC_GOOGLE = "Google"
        const val CONTENT_DESC_MORE_OPTIONS = "More options"
        const val CONTENT_DESC_BURGER = "Burger"
        const val CONTENT_DESC_FLOAT = "Float"
        const val CONTENT_DESC_CAKE = "Cake"
        const val CONTENT_DESC_PIZZA = "Pizza"
        
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
        
        // Success messages
        const val SUCCESS_OTP_SENT = "OTP sent successfully"
        const val SUCCESS_OTP_VERIFIED = "OTP verified successfully"
        const val SUCCESS_LOGIN = "Login successful"
        const val SUCCESS_LOGOUT = "Logged out successfully"
        
        // Placeholder and hints
        const val SEARCH_HINT = "Search for dishes, restaurants"
        const val SEARCH_HINT_GROCERY = "Search for groceries"
        const val LOCATION_DEFAULT = "Current Location"
    }
    
    /**
     * Numeric Constants - Common numeric values
     */
    object Numbers {
        const val ZERO = 0
        const val ONE = 1
        const val TWO = 2
        const val THREE = 3
        const val FIVE = 5
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
    }
    
    /**
     * Alpha Values - Transparency values
     */
    object Alpha {
        const val FULLY_TRANSPARENT = 0.0f
        const val SLIGHTLY_TRANSPARENT = 0.1f
        const val SEMI_TRANSPARENT = 0.2f
        const val MEDIUM_TRANSPARENT = 0.4f
        const val MOSTLY_TRANSPARENT = 0.6f
        const val SLIGHTLY_OPAQUE = 0.8f
        const val FULLY_OPAQUE = 1.0f
        
        // Common alpha values
        const val DISABLED = 0.32f
        const val SECONDARY_TEXT = 0.56f
        const val HINT_TEXT = 0.4f
        const val PRESSED_STATE = 0.12f
        const val HOVER_STATE = 0.08f
        const val FOCUS_STATE = 0.24f
    }
}
