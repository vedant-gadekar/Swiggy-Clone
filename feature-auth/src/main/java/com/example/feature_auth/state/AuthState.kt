package com.example.feature_auth.state

/**
 * Represents the state of the Auth feature UI.
 *
 * @property currentScreen The current screen being displayed
 * @property phoneNumber The phone number entered by user
 * @property otp The OTP entered by user
 * @property isLoading Indicates if any operation is in progress
 * @property errorMessage Error message to display, null if no error
 * @property resendTimer Timer for OTP resend in seconds
 * @property isOtpComplete Indicates if OTP input is complete (6 digits)
 */
data class AuthState(
    val currentScreen: AuthScreen = AuthScreen.Welcome,
    val phoneNumber: String = "",
    val otp: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val resendTimer: Int = 0,
    val isOtpComplete: Boolean = false
) {
    /**
     * Returns true if phone number is valid (10 digits).
     */
    val isPhoneNumberValid: Boolean
        get() = phoneNumber.length == 10 && phoneNumber.all { it.isDigit() }
    
    /**
     * Returns true if resend button should be enabled.
     */
    val canResendOtp: Boolean
        get() = resendTimer == 0
    
    /**
     * Returns formatted phone number for display.
     */
    val formattedPhoneNumber: String
        get() = if (phoneNumber.isNotEmpty()) "+91-$phoneNumber" else ""
}

/**
 * Enum representing different screens in auth flow.
 */
enum class AuthScreen {
    Welcome,
    OtpVerification
}
