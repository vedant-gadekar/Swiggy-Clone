package com.example.feature_auth.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Strings {
    const val BACK = "Back"
    const val OTP_VERIFICATION = "OTP Verification"
    const val VERIFICATION_MESSAGE = "We have sent a verification code to"
    const val RESEND_SMS = "Resend SMS"
    const val RESEND_SMS_IN = "Resend SMS in"

    // Welcome screen texts
    const val WELCOME_TITLE = "Welcome to Swiggy"
    const val LOG_IN_OR_SIGN_UP = "Log in or sign up"
    const val ENTER_PHONE_NUMBER = "Enter phone number"
    const val CONTINUE = "Continue"
    const val OR = "OR"
    const val BY_CONTINUING_TEXT = "By continuing, you agree to our"
    const val TERMS_OF_SERVICE = "Terms of Service"
    const val PRIVACY_POLICY = "Privacy Policy"
    const val CONTENT_POLICY = "Content Policy"

    // Content descriptions
    const val CONTENT_DESC_BURGER = "Burger image"
    const val CONTENT_DESC_FLOAT = "Float image"
    const val CONTENT_DESC_CAKE = "Cake image"
    const val CONTENT_DESC_PIZZA = "Pizza image"
    const val CONTENT_DESC_INDIA_FLAG = "India flag"
    const val CONTENT_DESC_DROPDOWN = "Dropdown icon"
    const val CONTENT_DESC_GOOGLE = "Google login"

    // Actions
    const val TRY_OTHER_LOGIN_METHODS = "Try other login methods"

    // Errors
    const val ERROR_PHONE_EMPTY = "Phone number cannot be empty"
    const val ERROR_PHONE_INVALID = "Phone number must contain only digits"
    const val ERROR_PHONE_LENGTH = "Phone number must be 10 digits"
    const val ERROR_OTP_INCOMPLETE = "Please enter complete 6-digit OTP"
    const val ERROR_OTP_INVALID = "Invalid OTP. Please try again."
    const val ERROR_RESEND_FAILED = "Failed to resend OTP. Please try again."
    const val ERROR_SEND_OTP_FAILED = "Failed to send OTP. Please try again."
    const val ERROR_SOCIAL_LOGIN_FAILED = "Social login failed. Please try again."

    // Success
    const val SUCCESS_OTP_SENT_TO_PHONE = "OTP sent to +91-%s"
    const val SUCCESS_OTP_RESENT = "OTP resent successfully"
    const val SUCCESS_OTP_VERIFIED = "OTP verified successfully"
    const val SUCCESS_LOGIN = "Login successful"
}

object Dimensions {
    val PADDING_HORIZONTAL = 16.dp
    val ICON_SIZE = 24.dp
    val SPACER_WIDTH = 17.dp
    val HEADER_FONT_SIZE = 18.sp
    val SPACER_HEIGHT_TOP = 55.dp
    val SPACER_HEIGHT_HEADER = 59.dp
    val MESSAGE_FONT_SIZE = 15.sp
    val SPACER_HEIGHT_MESSAGE = 32.dp
    val SPACER_HEIGHT_PHONE = 49.dp
    val OTP_BOX_SPACING = 12.dp
    val SPACER_HEIGHT_OTP = 30.dp
    val ERROR_FONT_SIZE = 12.sp
    val CARD_WIDTH = 133.dp
    val CARD_HEIGHT = 40.dp
    val CARD_CORNER_RADIUS = 7.dp
    val BORDER_WIDTH = 1.dp
    val PROGRESS_SIZE = 16.dp
    val PROGRESS_STROKE = 2.dp
    val SPACER_HEIGHT_ERROR = 16.dp
    val SPACER_HEIGHT_16 = 16.dp
    val SPACER_HEIGHT_15 = 15.dp
    val SPACER_HEIGHT_24 = 24.dp
    val DIVIDER_HEIGHT = 1.dp
    val BUTTON_FONT_SIZE = 14.sp
    val SPACER_HEIGHT_BOTTOM = 59.dp
    val TRY_OTHER_METHODS_FONT_SIZE = 14.sp
    val TRY_OTHER_METHODS_PADDING = 8.dp
}

object Colors {
    val WHITE = Color.White
    val BLACK = Color.Black
    val RED = Color.Red
    val BLACK_ALPHA_32 = Color.Black.copy(alpha = 0.32f)
    val BLACK_ALPHA_56 = Color.Black.copy(alpha = 0.56f)
}

object Numbers {
    const val ZERO = 0
    const val PHONE_NUMBER_LENGTH = 10
    const val OTP_LENGTH = 6
    const val COUNTRY_CODE = "+91"
}

object Animation {
    const val NETWORK_DELAY_SHORT = 400
    const val NETWORK_DELAY_MEDIUM = 800
}

object Timer {
    const val OTP_RESEND_TIMER = 60
    const val TIMER_TICK_INTERVAL = 1000L
}

object Booleans {
    const val ENABLED = true
    const val DISABLED = false
    const val DEFAULT_LOADING = false
}

object Chars {
    const val ZERO = '0'
    const val SPACE = ' '
}
