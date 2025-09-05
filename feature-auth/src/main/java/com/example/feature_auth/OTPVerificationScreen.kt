package com.example.feature_auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.BrandColors
import com.example.feature_auth.state.AuthEvent
import com.example.feature_auth.viewmodel.AuthViewModel
import com.example.feature_auth.utils.Colors
import com.example.feature_auth.utils.Strings
import com.example.feature_auth.utils.Dimensions
import com.example.feature_auth.utils.Chars
import com.example.core.Dimensions as CoreDimensions
import com.example.core.FontSizes as CoreFontSizes
import com.example.feature_auth.components.OTPInputBox
import com.example.feature_auth.utils.Strings as AuthStrings

@Composable
fun OTPVerificationScreen(
    phoneNumber: String,
    viewModel: AuthViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusRequesters = remember { List(6) { FocusRequester() } }
    val keyboardController = LocalSoftwareKeyboardController.current
    
    // Split OTP into individual digits for the input boxes
    val otpDigits = remember(state.otp) {
        val digits = state.otp.padEnd(6, Chars.SPACE).take(6).toList()
        digits.map { if (it == Chars.SPACE) "" else it.toString() }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.WHITE)
            .padding(horizontal = Dimensions.PADDING_HORIZONTAL)
    ) {
        Spacer(modifier = Modifier.height(Dimensions.SPACER_HEIGHT_TOP))
        // Header with back button and title
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.handleEvent(AuthEvent.NavigateBack) },
                modifier = Modifier.size(Dimensions.ICON_SIZE)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = Strings.BACK,
                    tint = Colors.BLACK
                )
            }
            Spacer(modifier = Modifier.width(Dimensions.SPACER_WIDTH))
            Text(
                text = Strings.OTP_VERIFICATION,
                color = Colors.BLACK,
                fontSize = Dimensions.HEADER_FONT_SIZE,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.height(Dimensions.SPACER_HEIGHT_HEADER))
        // Verification message
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = Strings.VERIFICATION_MESSAGE,
                color = Colors.BLACK,
                fontSize = Dimensions.MESSAGE_FONT_SIZE,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(Dimensions.SPACER_HEIGHT_MESSAGE))
            Text(
                text = state.formattedPhoneNumber,
                color = Colors.BLACK,
                fontSize = Dimensions.MESSAGE_FONT_SIZE,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(Dimensions.SPACER_HEIGHT_PHONE))
        // OTP Input boxes
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.OTP_BOX_SPACING, Alignment.CenterHorizontally)
        ) {
            otpDigits.forEachIndexed { index, value ->
                OTPInputBox(
                    value = value,
                    onValueChange = { newValue ->
                        if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                            val currentOtp = state.otp.padEnd(6, Chars.ZERO).take(6).toMutableList()
                            
                            if (newValue.isEmpty()) {
                                // Handle backspace
                                if (index < currentOtp.size) {
                                    currentOtp[index] = Chars.ZERO
                                }
                            } else {
                                // Handle digit input
                                if (index < currentOtp.size) {
                                    currentOtp[index] = newValue[0]
                                }
                            }
                            
                            val newOtp = currentOtp.joinToString("").replace(Chars.ZERO.toString(), "").take(6)
                            viewModel.handleEvent(AuthEvent.OtpChanged(newOtp))
                            
                            // Move focus to next box if current is filled
                            if (newValue.isNotEmpty() && index < 5) {
                                focusRequesters[index + 1].requestFocus()
                            }
                        }
                    },
                    modifier = Modifier.focusRequester(focusRequesters[index]),
                    isLoading = state.isLoading
                )
            }
        }
        Spacer(modifier = Modifier.height(Dimensions.SPACER_HEIGHT_OTP))
        // Error message
        state.errorMessage?.let { errorMessage ->
            Text(
                text = errorMessage,
                color = Colors.RED,
                fontSize = Dimensions.ERROR_FONT_SIZE,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(Dimensions.SPACER_HEIGHT_ERROR))
        }

        // Verify button (submit)
        Button(
            onClick = {
                // Close keyboard and submit OTP for verification
                keyboardController?.hide()
                viewModel.handleEvent(AuthEvent.VerifyOtp(state.otp))
            },
            enabled = !state.isLoading && state.isOtpComplete,
            modifier = Modifier
                .fillMaxWidth()
                .height(CoreDimensions.BUTTON_HEIGHT),
            colors = ButtonDefaults.buttonColors(
                containerColor = BrandColors.SWIGGY_ORANGE
            ),
            shape = RoundedCornerShape(CoreDimensions.CORNER_RADIUS_NORMAL),
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(CoreDimensions.ICON_MEDIUM),
                    color = Colors.WHITE,
                    strokeWidth = Dimensions.PROGRESS_STROKE
                )
            } else {
                Text(
                    text = "Verify",
                    color = Colors.WHITE,
                    fontSize = Dimensions.BUTTON_FONT_SIZE,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(Dimensions.SPACER_HEIGHT_OTP))
        // Resend SMS button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .width(Dimensions.CARD_WIDTH)
                    .height(Dimensions.CARD_HEIGHT)
                    .clickable(enabled = state.canResendOtp && !state.isLoading) {
                        viewModel.handleEvent(AuthEvent.ResendOtp)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Colors.WHITE
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                shape = RoundedCornerShape(Dimensions.CARD_CORNER_RADIUS)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            Dimensions.BORDER_WIDTH,
                            Colors.BLACK_ALPHA_32,
                            RoundedCornerShape(Dimensions.CARD_CORNER_RADIUS)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(Dimensions.PROGRESS_SIZE),
                            color = Colors.BLACK_ALPHA_56,
                            strokeWidth = Dimensions.PROGRESS_STROKE
                        )
                    } else {
                        Text(
                            text = if (state.resendTimer > 0) 
                                "${Strings.RESEND_SMS_IN} ${state.resendTimer}"
                            else Strings.RESEND_SMS,
                            color = Colors.BLACK.copy(alpha = 0.56f),
                            fontSize = Dimensions.BUTTON_FONT_SIZE,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(Dimensions.SPACER_HEIGHT_BOTTOM))
        // Try other login methods
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = AuthStrings.TRY_OTHER_LOGIN_METHODS,
                color = BrandColors.SWIGGY_ORANGE,
                fontSize = Dimensions.TRY_OTHER_METHODS_FONT_SIZE,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable(enabled = !state.isLoading) { 
                        viewModel.handleEvent(AuthEvent.TryOtherMethods) 
                    }
                    .padding(vertical = Dimensions.TRY_OTHER_METHODS_PADDING)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun OTPInputBox(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    Card(
        modifier = modifier
            .size(CoreDimensions.OTP_INPUT_SIZE),
        colors = CardDefaults.cardColors(containerColor = Colors.WHITE),
        elevation = CardDefaults.cardElevation(defaultElevation = CoreDimensions.CARD_ELEVATION_NONE),
        shape = RoundedCornerShape(CoreDimensions.CORNER_RADIUS_MEDIUM)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(Dimensions.BORDER_WIDTH, Colors.BLACK, RoundedCornerShape(CoreDimensions.CORNER_RADIUS_MEDIUM)),
            contentAlignment = Alignment.Center
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxSize(),
                textStyle = TextStyle(
                    color = Colors.BLACK,
                    fontSize = CoreFontSizes.EXTRA_LARGE,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                enabled = !isLoading,
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        innerTextField()
                    }
                }
            )
        }
    }
}
