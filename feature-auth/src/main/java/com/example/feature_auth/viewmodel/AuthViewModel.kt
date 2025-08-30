package com.example.feature_auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_auth.state.AuthEffect
import com.example.feature_auth.state.AuthEvent
import com.example.feature_auth.state.AuthScreen
import com.example.feature_auth.state.AuthState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for authentication flow using MVI pattern.
 */
class AuthViewModel : ViewModel() {
    
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()
    
    private val _effect = MutableSharedFlow<AuthEffect>()
    val effect: SharedFlow<AuthEffect> = _effect.asSharedFlow()
    
    /**
     * Handles all user events in the auth flow.
     */
    fun handleEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.PhoneNumberChanged -> handlePhoneNumberChanged(event.phoneNumber)
            is AuthEvent.ContinueWithPhone -> handleContinueWithPhone(event.phoneNumber)
            is AuthEvent.SocialLoginClicked -> handleSocialLogin(event.provider)
            is AuthEvent.OtpChanged -> handleOtpChanged(event.otp)
            is AuthEvent.VerifyOtp -> handleVerifyOtp(event.otp)
            is AuthEvent.ResendOtp -> handleResendOtp()
            is AuthEvent.NavigateBack -> handleNavigateBack()
            is AuthEvent.TryOtherMethods -> handleTryOtherMethods()
            is AuthEvent.ClearError -> handleClearError()
        }
    }
    
    private fun handlePhoneNumberChanged(phoneNumber: String) {
        // Only allow digits and limit to 10 characters
        val filteredPhoneNumber = phoneNumber.filter { it.isDigit() }.take(10)
        _state.update { 
            it.copy(
                phoneNumber = filteredPhoneNumber,
                errorMessage = null
            ) 
        }
    }
    
    private fun handleContinueWithPhone(phoneNumber: String) {
        viewModelScope.launch {
            // Validate phone number
            if (phoneNumber.isEmpty()) {
                _state.update { it.copy(errorMessage = "Phone number cannot be empty") }
                return@launch
            }
            
            if (!phoneNumber.all { it.isDigit() }) {
                _state.update { it.copy(errorMessage = "Phone number must contain only digits") }
                return@launch
            }
            
            if (phoneNumber.length != 10) {
                _state.update { it.copy(errorMessage = "Phone number must be 10 digits") }
                return@launch
            }
            
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            
            try {
                // Simulate API call to send OTP
                delay(1000)
                
                _state.update { 
                    it.copy(
                        isLoading = false,
                        currentScreen = AuthScreen.OtpVerification,
                        resendTimer = 15,
                        otp = ""
                    ) 
                }
                
                _effect.emit(AuthEffect.NavigateToOtp(phoneNumber))
                _effect.emit(AuthEffect.ShowToast("OTP sent to +91-$phoneNumber"))
                
                // Start resend timer
                startResendTimer()
                
            } catch (e: Exception) {
                _state.update { 
                    it.copy(
                        isLoading = false,
                        errorMessage = "Failed to send OTP. Please try again."
                    ) 
                }
            }
        }
    }
    
    private fun handleSocialLogin(provider: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            try {
                // Simulate social login
                delay(1000)
                
                _state.update { it.copy(isLoading = false) }
                _effect.emit(AuthEffect.NavigateToHome)
                _effect.emit(AuthEffect.ShowToast("Login successful"))
                
            } catch (e: Exception) {
                _state.update { 
                    it.copy(
                        isLoading = false,
                        errorMessage = "Social login failed. Please try again."
                    ) 
                }
            }
        }
    }
    
    private fun handleOtpChanged(otp: String) {
        // Only allow digits and limit to 6 characters
        val filteredOtp = otp.filter { it.isDigit() }.take(6)
        _state.update { 
            it.copy(
                otp = filteredOtp,
                isOtpComplete = filteredOtp.length == 6,
                errorMessage = null
            ) 
        }
        
        // Auto-verify when OTP is complete
        if (filteredOtp.length == 6) {
            handleVerifyOtp(filteredOtp)
        }
    }
    
    private fun handleVerifyOtp(otp: String) {
        viewModelScope.launch {
            if (otp.length != 6) {
                _state.update { it.copy(errorMessage = "Please enter complete 6-digit OTP") }
                return@launch
            }
            
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            
            try {
                // Simulate OTP verification
                delay(1500)
                
                // For demo purposes, accept any 6-digit OTP
                _state.update { it.copy(isLoading = false) }
                _effect.emit(AuthEffect.NavigateToHome)
                _effect.emit(AuthEffect.ShowToast("OTP verified successfully"))
                
            } catch (e: Exception) {
                _state.update { 
                    it.copy(
                        isLoading = false,
                        errorMessage = "Invalid OTP. Please try again."
                    ) 
                }
            }
        }
    }
    
    private fun handleResendOtp() {
        viewModelScope.launch {
            if (_state.value.resendTimer > 0) {
                return@launch
            }
            
            _state.update { it.copy(isLoading = true) }
            
            try {
                // Simulate resend OTP API call
                delay(1000)
                
                _state.update { 
                    it.copy(
                        isLoading = false,
                        resendTimer = 15,
                        otp = ""
                    ) 
                }
                
                _effect.emit(AuthEffect.ShowToast("OTP resent successfully"))
                startResendTimer()
                
            } catch (e: Exception) {
                _state.update { 
                    it.copy(
                        isLoading = false,
                        errorMessage = "Failed to resend OTP. Please try again."
                    ) 
                }
            }
        }
    }
    
    private fun handleNavigateBack() {
        viewModelScope.launch {
            when (_state.value.currentScreen) {
                AuthScreen.OtpVerification -> {
                    _state.update { 
                        it.copy(
                            currentScreen = AuthScreen.Welcome,
                            otp = "",
                            resendTimer = 0,
                            errorMessage = null
                        ) 
                    }
                    _effect.emit(AuthEffect.NavigateToWelcome)
                }
                AuthScreen.Welcome -> {
                    // Handle back press on welcome screen if needed
                }
            }
        }
    }
    
    private fun handleTryOtherMethods() {
        viewModelScope.launch {
            _state.update { 
                it.copy(
                    currentScreen = AuthScreen.Welcome,
                    otp = "",
                    resendTimer = 0,
                    errorMessage = null
                ) 
            }
            _effect.emit(AuthEffect.NavigateToWelcome)
        }
    }
    
    private fun handleClearError() {
        _state.update { it.copy(errorMessage = null) }
    }
    
    private fun startResendTimer() {
        viewModelScope.launch {
            while (_state.value.resendTimer > 0) {
                delay(1000)
                _state.update { it.copy(resendTimer = it.resendTimer - 1) }
            }
        }
    }
}
