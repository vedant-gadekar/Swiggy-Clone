package com.example.feature_auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.Numbers
import com.example.core.Strings
import com.example.core.Animation
import com.example.core.Timer
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
        val filteredPhoneNumber = phoneNumber.filter { it.isDigit() }.take(Numbers.PHONE_NUMBER_LENGTH)
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
                _state.update { it.copy(errorMessage = Strings.ERROR_PHONE_EMPTY) }
                return@launch
            }

            if (!phoneNumber.all { it.isDigit() }) {
                _state.update { it.copy(errorMessage = Strings.ERROR_PHONE_INVALID) }
                return@launch
            }

            if (phoneNumber.length != Numbers.PHONE_NUMBER_LENGTH) {
                _state.update { it.copy(errorMessage = Strings.ERROR_PHONE_LENGTH) }
                return@launch
            }
            
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            
            try {
                // Simulate API call to send OTP
                delay(Animation.NETWORK_DELAY_SHORT.toLong())

                _state.update {
                    it.copy(
                        isLoading = false,
                        currentScreen = AuthScreen.OtpVerification,
                        resendTimer = Timer.OTP_RESEND_TIMER,
                        otp = ""
                    )
                }
                
                _effect.emit(AuthEffect.NavigateToOtp(phoneNumber))
                _effect.emit(AuthEffect.ShowToast(Strings.SUCCESS_OTP_SENT_TO_PHONE.format(phoneNumber)))

                // Start resend timer
                startResendTimer()
                
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = Strings.ERROR_SEND_OTP_FAILED
                    )
                }
            }
        }
    }
    
    private fun handleSocialLogin(provider: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                // Simulate social login
                delay(Animation.NETWORK_DELAY_SHORT.toLong())

                _state.update { it.copy(isLoading = false) }
                _effect.emit(AuthEffect.NavigateToHome)
                _effect.emit(AuthEffect.ShowToast(Strings.SUCCESS_LOGIN))

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = Strings.ERROR_SOCIAL_LOGIN_FAILED
                    )
                }
            }
        }
    }
    
    private fun handleOtpChanged(otp: String) {
        // Only allow digits and limit to 6 characters
        val filteredOtp = otp.filter { it.isDigit() }.take(Numbers.OTP_LENGTH)
        _state.update {
            it.copy(
                otp = filteredOtp,
                isOtpComplete = filteredOtp.length == Numbers.OTP_LENGTH,
                errorMessage = null
            )
        }

        // Auto-verify when OTP is complete
        if (filteredOtp.length == Numbers.OTP_LENGTH) {
            handleVerifyOtp(filteredOtp)
        }
    }
    
    private fun handleVerifyOtp(otp: String) {
        viewModelScope.launch {
            if (otp.length != Numbers.OTP_LENGTH) {
                _state.update { it.copy(errorMessage = Strings.ERROR_OTP_INCOMPLETE) }
                return@launch
            }

            _state.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                // Simulate OTP verification
                delay(Animation.NETWORK_DELAY_MEDIUM.toLong())

                // For demo purposes, accept any 6-digit OTP
                _state.update { it.copy(isLoading = false) }
                _effect.emit(AuthEffect.NavigateToHome)
                _effect.emit(AuthEffect.ShowToast(Strings.SUCCESS_OTP_VERIFIED))

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = Strings.ERROR_OTP_INVALID
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
                delay(Animation.NETWORK_DELAY_SHORT.toLong())

                _state.update {
                    it.copy(
                        isLoading = false,
                        resendTimer = Timer.OTP_RESEND_TIMER,
                        otp = ""
                    )
                }

                _effect.emit(AuthEffect.ShowToast(Strings.SUCCESS_OTP_RESENT))
                startResendTimer()

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = Strings.ERROR_RESEND_FAILED
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
                delay(Timer.TIMER_TICK_INTERVAL)
                _state.update { it.copy(resendTimer = it.resendTimer - 1) }
            }
        }
    }
}
