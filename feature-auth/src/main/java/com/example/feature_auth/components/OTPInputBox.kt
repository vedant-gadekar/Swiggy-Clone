package com.example.feature_auth.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.example.feature_auth.utils.Colors
import com.example.core.Dimensions as CoreDimensions
import com.example.core.FontSizes as CoreFontSizes

@Composable
fun OTPInputBox(
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
                .border(CoreDimensions.BORDER_WIDTH, Colors.BLACK, RoundedCornerShape(CoreDimensions.CORNER_RADIUS_MEDIUM)),
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




