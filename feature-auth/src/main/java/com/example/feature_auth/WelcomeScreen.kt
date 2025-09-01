package com.example.feature_auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.R
import com.example.core.Colors as CoreColors
import com.example.core.BrandColors
import com.example.core.Dimensions as CoreDimensions
import com.example.core.FontSizes as CoreFontSizes
import com.example.core.Spacing as CoreSpacing
import com.example.core.Numbers as CoreNumbers
import com.example.core.Alpha as CoreAlpha
import com.example.core.Strings as CoreStrings
import com.example.core.Layout as CoreLayout
import com.example.feature_auth.state.AuthEvent
import com.example.feature_auth.viewmodel.AuthViewModel

@Composable
fun WelcomeScreen(
    viewModel: AuthViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CoreColors.WHITE)
    ) {
        // Orange background section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(CoreDimensions.BANNER_HEIGHT)
                .background(BrandColors.SWIGGY_ORANGE)
        )
        
        // Content
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Food images section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(CoreLayout.FOOD_IMAGES_HEIGHT)
            ) {
                // Burger image
                Image(
                    painter = painterResource(R.drawable.burger),
                    contentDescription = CoreStrings.CONTENT_DESC_BURGER,
                    modifier = Modifier
                        .size(CoreDimensions.IMAGE_BANNER)
                        .offset(
                            x = CoreDimensions.OFFSET_SMALL,
                            y = CoreDimensions.OFFSET_Y_GIANT
                        )
                        .rotate(CoreNumbers.ROTATION_ANGLE_MEDIUM),
                    contentScale = ContentScale.Crop
                )

                // Float image
                Image(
                    painter = painterResource(R.drawable.shake),
                    contentDescription = CoreStrings.CONTENT_DESC_FLOAT,
                    modifier = Modifier
                        .size(CoreDimensions.IMAGE_MASSIVE)
                        .offset(
                            x = CoreDimensions.OFFSET_Y_EXTREME,
                            y = CoreDimensions.OFFSET_Y_MASSIVE
                        )
                        .rotate(CoreNumbers.ROTATION_ANGLE_REVERSE),
                    contentScale = ContentScale.Crop
                )

                // Cake image
                Image(
                    painter = painterResource(R.drawable.cake),
                    contentDescription = CoreStrings.CONTENT_DESC_CAKE,
                    modifier = Modifier
                        .size(
                            CoreDimensions.IMAGE_GIANT,
                            CoreDimensions.IMAGE_HUGE
                        )
                        .offset(
                            x = CoreDimensions.OFFSET_LARGE,
                            y = CoreDimensions.OFFSET_Y_SMALL
                        ),
                    contentScale = ContentScale.Crop
                )

                // Pizza image
                Image(
                    painter = painterResource(R.drawable.pizza),
                    contentDescription = CoreStrings.CONTENT_DESC_PIZZA,
                    modifier = Modifier
                        .size(
                            CoreDimensions.PIZZA_WIDTH,
                            CoreDimensions.PIZZA_HEIGHT
                        )
                        .offset(
                            x = CoreDimensions.PIZZA_OFFSET_X,
                            y = CoreDimensions.PIZZA_OFFSET_Y
                        ),
                    contentScale = ContentScale.Crop
                )
            }

            // Main content section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = CoreDimensions.INPUT_PADDING_HORIZONTAL),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    text = CoreStrings.WELCOME_TITLE,
                    color = CoreColors.BLACK,
                    fontSize = CoreFontSizes.HEADING,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    lineHeight = CoreFontSizes.LINE_HEIGHT,
                    modifier = Modifier.padding(horizontal = CoreSpacing.MASSIVE_SMALL)
                )
                
                Spacer(modifier = Modifier.height(CoreSpacing.MASSIVE))

                Text(
                    text = CoreStrings.LOG_IN_OR_SIGN_UP,
                    color = CoreColors.BLACK_ALPHA_66,
                    fontSize = CoreFontSizes.BODY,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(CoreSpacing.SMALL))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(CoreDimensions.BORDER_WIDTH)
                        .background(CoreColors.BLACK_ALPHA_20)
                )

                Spacer(modifier = Modifier.height(CoreSpacing.HUGE))

                // Phone input
                PhoneInputSection(
                    phoneNumber = state.phoneNumber,
                    onPhoneNumberChange = { phoneNumber ->
                        viewModel.handleEvent(AuthEvent.PhoneNumberChanged(phoneNumber))
                    }
                )
                
                Spacer(modifier = Modifier.height(CoreSpacing.MEDIUM_LARGE))

                // Continue button
                Button(
                    onClick = {
                        viewModel.handleEvent(AuthEvent.ContinueWithPhone(state.phoneNumber))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(CoreDimensions.BUTTON_HEIGHT),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandColors.SWIGGY_ORANGE
                    ),
                    shape = RoundedCornerShape(CoreDimensions.CORNER_RADIUS_NORMAL),
                    enabled = !state.isLoading
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(CoreDimensions.ICON_MEDIUM),
                            color = CoreColors.WHITE,
                            strokeWidth = CoreDimensions.BORDER_WIDTH_MEDIUM
                        )
                    } else {
                        Text(
                            text = CoreStrings.CONTINUE,
                            color = CoreColors.WHITE,
                            fontSize = CoreFontSizes.EXTRA_LARGE,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
                
                // Error message
                state.errorMessage?.let { errorMessage ->
                    Spacer(modifier = Modifier.height(CoreSpacing.MEDIUM_SMALL))
                    Text(
                        text = errorMessage,
                        color = CoreColors.ERROR_RED,
                        fontSize = CoreFontSizes.MEDIUM,
                        fontWeight = FontWeight.Medium
                    )
                }
                
                Spacer(modifier = Modifier.height(CoreSpacing.MEDIUM_LARGE))

                Text(
                    text = CoreStrings.OR,
                    color = CoreColors.BLACK_ALPHA_66,
                    fontSize = CoreFontSizes.BODY,
                    fontWeight = FontWeight.Medium
                )
                
                Spacer(modifier = Modifier.height(CoreSpacing.SMALL))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(CoreDimensions.BORDER_WIDTH)
                        .background(CoreColors.BLACK_ALPHA_20)
                )

                Spacer(modifier = Modifier.height(CoreSpacing.MEDIUM_LARGE))

                // Social login buttons
                SocialLoginButtons(
                    onSocialLogin = { provider ->
                        viewModel.handleEvent(AuthEvent.SocialLoginClicked(provider))
                    },
                    isLoading = state.isLoading
                )
                
                Spacer(modifier = Modifier.height(CoreSpacing.MEDIUM_LARGE))

                // Footer
                FooterSection()
            }
        }
        
        // SWIGGY branding overlay
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = CoreLayout.WELCOME_TOP_PADDING.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = CoreStrings.SWIGGY_BRAND,
                color = CoreColors.WHITE,
                fontSize = CoreFontSizes.BRAND,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun PhoneInputSection(
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
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
                    painter = painterResource(R.drawable.india_flag),
                    contentDescription = CoreStrings.CONTENT_DESC_INDIA_FLAG,
                    modifier = Modifier.size(
                        width = CoreLayout.FLAG_WIDTH,
                        height = CoreLayout.FLAG_HEIGHT
                    )
                )
                Icon(
                    painter = painterResource(R.drawable.dropdown),
                    contentDescription = CoreStrings.CONTENT_DESC_DROPDOWN,
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
                    text = CoreNumbers.COUNTRY_CODE,
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
                                text = CoreStrings.ENTER_PHONE_NUMBER,
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

@Composable
private fun SocialLoginButtons(
    onSocialLogin: (String) -> Unit,
    isLoading: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        // Google login button
        Card(
            modifier = Modifier
                .size(CoreLayout.SOCIAL_BUTTON_SIZE)
                .clickable(enabled = !isLoading) { onSocialLogin("google") },
            colors = CardDefaults.cardColors(containerColor = CoreColors.WHITE),
            elevation = CardDefaults.cardElevation(defaultElevation = CoreDimensions.CARD_ELEVATION_TINY),
            shape = CircleShape
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.google),
                    contentDescription = CoreStrings.CONTENT_DESC_GOOGLE,
                    modifier = Modifier.size(CoreLayout.SOCIAL_ICON_SIZE)
                )
            }
        }
        
        Spacer(modifier = Modifier.width(CoreNumbers.SOCIAL_LOGIN_SPACING.dp))

        // More options button
        Card(
            modifier = Modifier
                .size(CoreLayout.SOCIAL_BUTTON_SIZE)
                .clickable(enabled = !isLoading) { onSocialLogin("more") },
            colors = CardDefaults.cardColors(containerColor = CoreColors.WHITE),
            elevation = CardDefaults.cardElevation(defaultElevation = CoreDimensions.CARD_ELEVATION_TINY),
            shape = CircleShape
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(CoreLayout.DOT_SPACING)
                ) {
                    repeat(3) {
                        Box(
                            modifier = Modifier
                                .size(CoreLayout.DOT_SIZE)
                                .background(CoreColors.BLACK, CircleShape)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FooterSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = CoreStrings.BY_CONTINUING_TEXT,
            color = CoreColors.BLACK.copy(alpha = CoreAlpha.SUBTITLE_TEXT),
            fontSize = CoreFontSizes.FOOTER_TEXT,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(CoreSpacing.MEDIUM_LARGE))

        Row(
            horizontalArrangement = Arrangement.spacedBy(CoreLayout.FOOTER_LINK_SPACING)
        ) {
            FooterLink(CoreStrings.TERMS_OF_SERVICE)
            FooterLink(CoreStrings.PRIVACY_POLICY)
            FooterLink(CoreStrings.CONTENT_POLICY)
        }
    }
}

@Composable
private fun FooterLink(text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            color = CoreColors.BLACK.copy(alpha = CoreAlpha.SUBTITLE_TEXT),
            fontSize = CoreFontSizes.FOOTER_TEXT,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable { }
        )
        
        Spacer(modifier = Modifier.height(CoreLayout.FOOTER_LINK_BOTTOM_SPACING))

        Box(
            modifier = Modifier
                .width(text.length * CoreNumbers.FOOTER_LINK_MULTIPLIER.dp)
                .height(CoreLayout.FOOTER_UNDERLINE_HEIGHT)
                .background(CoreColors.BLACK.copy(alpha = CoreAlpha.SEMI_TRANSPARENT))
        )
    }
}
