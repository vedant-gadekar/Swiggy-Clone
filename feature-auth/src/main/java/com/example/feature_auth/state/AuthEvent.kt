package com.example.feature_auth.state

/**
 * Sealed class representing all possible user events in the Auth feature.
 */
sealed class AuthEvent {
    /**
     * Triggered when user enters or changes phone number.
     * @property phoneNumber The phone number entered by user
     */
    data class PhoneNumberChanged(val phoneNumber: String) : AuthEvent()
    
    /**
     * Triggered when user clicks continue button to proceed with phone verification.
     * @property phoneNumber The phone number to verify
     */
    data class ContinueWithPhone(val phoneNumber: String) : AuthEvent()
    
    /**
     * Triggered when user clicks social login buttons.
     * @property provider The social login provider (google, more, etc.)
     */
    data class SocialLoginClicked(val provider: String) : AuthEvent()
    
    /**
     * Triggered when user changes OTP input.
     * @property otp The OTP entered by user
     */
    data class OtpChanged(val otp: String) : AuthEvent()
    
    /**
     * Triggered when user submits OTP for verification.
     * @property otp The complete OTP to verify
     */
    data class VerifyOtp(val otp: String) : AuthEvent()
    
    /**
     * Triggered when user clicks resend OTP button.
     */
    object ResendOtp : AuthEvent()
    
    /**
     * Triggered when user clicks back button on OTP screen.
     */
    object NavigateBack : AuthEvent()
    
    /**
     * Triggered when user clicks "Try other login methods".
     */
    object TryOtherMethods : AuthEvent()
    
    /**
     * Triggered to clear any error state.
     */
    object ClearError : AuthEvent()
}
