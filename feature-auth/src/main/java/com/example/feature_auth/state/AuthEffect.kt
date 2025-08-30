package com.example.feature_auth.state

/**
 * Sealed class representing side effects in the Auth feature.
 * These are one-time events that affect the UI but are not part of the state.
 */
sealed class AuthEffect {
    /**
     * Navigate to OTP verification screen.
     * @property phoneNumber The phone number for which OTP was sent
     */
    data class NavigateToOtp(val phoneNumber: String) : AuthEffect()
    
    /**
     * Navigate back to welcome screen.
     */
    object NavigateToWelcome : AuthEffect()
    
    /**
     * Navigate to main app (home screen) after successful authentication.
     */
    object NavigateToHome : AuthEffect()
    
    /**
     * Show a toast message.
     * @property message The message to display
     */
    data class ShowToast(val message: String) : AuthEffect()
    
    /**
     * Show error message.
     * @property message The error message to display
     */
    data class ShowError(val message: String) : AuthEffect()
}
