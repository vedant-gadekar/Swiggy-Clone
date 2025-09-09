package com.example.feature_auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import com.example.core.R
import com.example.core.Colors as CoreColors
import com.example.core.Dimensions as CoreDimensions
import com.example.core.FontSizes as CoreFontSizes
import com.example.core.Spacing as CoreSpacing
import com.example.core.Layout as CoreLayout
import com.example.feature_auth.utils.Numbers as AuthNumbers
import com.example.core.Alpha as CoreAlpha
import com.example.feature_auth.utils.Strings as AuthStrings

@Composable
fun PhoneInputSection(
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(CoreLayout.PHONE_INPUT_HEIGHT),
        horizontalArrangement = Arrangement.spacedBy(CoreSpacing.NORMAL)
    ) {
        // Country code section
        Card(
            modifier = Modifier
                .width(CoreLayout.COUNTRY_CODE_WIDTH)
                .fillMaxHeight(),
            colors = CardDefaults.cardColors(containerColor = CoreColors.WHITE),
            elevation = CardDefaults.cardElevation(defaultElevation = CoreDimensions.CARD_ELEVATION_TINY),
            shape = RoundedCornerShape(CoreDimensions.CORNER_RADIUS_NORMAL)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = CoreLayout.COUNTRY_CODE_HORIZONTAL_PADDING),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = androidx.compose.ui.res.painterResource(R.drawable.india_flag),
                    contentDescription = AuthStrings.CONTENT_DESC_INDIA_FLAG,
                    modifier = Modifier.size(
                        width = CoreLayout.FLAG_WIDTH,
                        height = CoreLayout.FLAG_HEIGHT
                    )
                )
                Icon(
                    painter = androidx.compose.ui.res.painterResource(R.drawable.dropdown),
                    contentDescription = AuthStrings.CONTENT_DESC_DROPDOWN,
                    modifier = Modifier.size(CoreLayout.DROPDOWN_ICON_SIZE),
                    tint = CoreColors.GRAY_DROPDOWN
                )
            }
        }

        // Phone number input
        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            colors = CardDefaults.cardColors(containerColor = CoreColors.WHITE),
            elevation = CardDefaults.cardElevation(defaultElevation = CoreDimensions.CARD_ELEVATION_TINY),
            shape = RoundedCornerShape(CoreDimensions.CORNER_RADIUS_NORMAL)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = CoreLayout.PHONE_INPUT_HORIZONTAL_PADDING),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = AuthNumbers.COUNTRY_CODE,
                    color = CoreColors.BLACK,
                    fontSize = CoreFontSizes.PHONE_TEXT,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.width(CoreLayout.PHONE_INPUT_SPACER))

                BasicTextField(
                    value = phoneNumber,
                    onValueChange = onPhoneNumberChange,
                    modifier = Modifier.weight(1f),
                    textStyle = TextStyle(
                        color = CoreColors.BLACK,
                        fontSize = CoreFontSizes.PHONE_TEXT,
                        fontWeight = FontWeight.Normal
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        if (phoneNumber.isEmpty()) {
                            Text(
                                text = AuthStrings.ENTER_PHONE_NUMBER,
                                color = CoreColors.BLACK.copy(alpha = CoreAlpha.HINT_TEXT),
                                fontSize = CoreFontSizes.PHONE_HINT
                            )
                        }
                        innerTextField()
                    }
                )
            }
        }
    }
}


