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
import com.example.feature_auth.state.AuthEvent
import com.example.feature_auth.viewmodel.AuthViewModel

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
        val digits = state.otp.padEnd(6, ' ').take(6).toList()
        digits.map { if (it == ' ') "" else it.toString() }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(55.dp))
        
        // Header with back button and title
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.handleEvent(AuthEvent.NavigateBack) },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            
            Spacer(modifier = Modifier.width(17.dp))
            
            Text(
                text = "OTP Verification",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
        
        Spacer(modifier = Modifier.height(59.dp))
        
        // Verification message
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "We have sent a verification code to",
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                text = state.formattedPhoneNumber,
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        
        Spacer(modifier = Modifier.height(49.dp))
        
        // OTP Input boxes
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.CenterHorizontally)
        ) {
            otpDigits.forEachIndexed { index, value ->
                OTPInputBox(
                    value = value,
                    onValueChange = { newValue ->
                        if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                            val currentOtp = state.otp.padEnd(6, '0').take(6).toMutableList()
                            
                            if (newValue.isEmpty()) {
                                // Handle backspace
                                if (index < currentOtp.size) {
                                    currentOtp[index] = '0'
                                }
                            } else {
                                // Handle digit input
                                if (index < currentOtp.size) {
                                    currentOtp[index] = newValue[0]
                                }
                            }
                            
                            val newOtp = currentOtp.joinToString("").replace("0", "").take(6)
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
        
        Spacer(modifier = Modifier.height(30.dp))
        
        // Error message
        state.errorMessage?.let { errorMessage ->
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        
        // Resend SMS button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .width(133.dp)
                    .height(40.dp)
                    .clickable(enabled = state.canResendOtp && !state.isLoading) { 
                        viewModel.handleEvent(AuthEvent.ResendOtp)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                shape = RoundedCornerShape(7.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            1.dp, 
                            Color.Black.copy(alpha = 0.32f), 
                            RoundedCornerShape(7.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(16.dp),
                            color = Color.Black.copy(alpha = 0.56f),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            text = if (state.resendTimer > 0) 
                                "Resend SMS in ${state.resendTimer}" 
                            else 
                                "Resend SMS",
                            color = Color.Black.copy(alpha = 0.56f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(59.dp))
        
        // Try other login methods
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Try other login methods",
                color = Color(0xFFFF5722),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable(enabled = !state.isLoading) { 
                        viewModel.handleEvent(AuthEvent.TryOtherMethods) 
                    }
                    .padding(vertical = 8.dp)
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
            .size(42.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(7.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(1.dp, Color.Black, RoundedCornerShape(7.dp)),
            contentAlignment = Alignment.Center
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxSize(),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
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
