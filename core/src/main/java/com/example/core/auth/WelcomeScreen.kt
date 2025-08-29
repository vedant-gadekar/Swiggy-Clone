package com.example.core.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.swiggyy.ui.theme.SwiggyFontFamily
import com.example.core.R

@Composable
fun WelcomeScreen(
    onContinue: (String) -> Unit,
    onSocialLogin: (String) -> Unit = {}
) {
    var phoneNumber by remember { mutableStateOf("") }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Orange background section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(367.dp)
                .background(Color(0xFFFF5722))
        )
        
        // Content
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Food images
            FoodImagesSection()
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Main content section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title and subtitle
                Text(
                    text = "India's #1 Food Delivery\nand Dining App",
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    lineHeight = 30.sp,
                    modifier = Modifier.padding(horizontal = 25.dp)
                )
                
                Spacer(modifier = Modifier.height(27.dp))
                
                // Log in or sign up text
                Text(
                    text = "Log in or sign up",
                    color = Color.Black.copy(alpha = 0.66f),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
                
                Spacer(modifier = Modifier.height(6.dp))
                
                // Divider lines
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Black.copy(alpha = 0.2f))
                )
                
                Spacer(modifier = Modifier.height(21.dp))
                
                // Phone input section
                PhoneInputSection(
                    phoneNumber = phoneNumber,
                    onPhoneNumberChange = { phoneNumber = it }
                )
                
                Spacer(modifier = Modifier.height(15.dp))

                var errorMessage by remember { mutableStateOf<String?>(null) }
                // Continue button
                Button(
                    onClick = {
                        errorMessage = when {
                            phoneNumber.isEmpty() -> "Phone number cannot be empty"
                            !phoneNumber.all { it.isDigit() } -> "Phone number must contain only digits"
                            phoneNumber.length != 10 -> "Phone number must be 10 digits"
                            else -> null
                        }

                        if (errorMessage == null) {
                            onContinue(phoneNumber)
                        }


                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF5722)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Continue",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                errorMessage?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = it,
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))
                
                // "or" text
                Text(
                    text = "or",
                    color = Color.Black.copy(alpha = 0.66f),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
                
                Spacer(modifier = Modifier.height(6.dp))
                
                // Divider lines
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Black.copy(alpha = 0.2f))
                )
                
                Spacer(modifier = Modifier.height(14.dp))
                
                // Social login buttons
                SocialLoginSection(onSocialLogin = onSocialLogin)
                
                Spacer(modifier = Modifier.height(15.dp))
                
                // Footer
                FooterSection()
                
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
        
        // SWIGGY branding overlay
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 151.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SWIGGY",
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun FoodImagesSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(367.dp)
    ) {
        // Burger image (rotated)
        Image(
            painter = painterResource(R.drawable.burger),
            contentDescription = "Burger",
            modifier = Modifier
                .size(150.dp)
                .offset(x = (-10).dp, y = 200.dp)
                .rotate(30.921f),
            contentScale = ContentScale.Crop
        )
        
        // Float image
        Image(
            painter = painterResource(R.drawable.shake),
            contentDescription = "Float",
            modifier = Modifier
                .size(180.dp)
                .offset(x = 290.dp, y = 177.dp)
                .rotate(-20.921f),
            contentScale = ContentScale.Crop
        )
        
        // Cake image
        Image(
            painter = painterResource(R.drawable.cake),
            contentDescription = "Cake",
            modifier = Modifier
                .size(181.dp, 157.dp)
                .offset(x = (-35).dp, y = 5.dp),
            contentScale = ContentScale.Crop
        )
        
        // Pizza image
        Image(
            painter = painterResource(R.drawable.pizza),
            contentDescription = "Pizza",
            modifier = Modifier
                .size(223.dp, 212.dp)
                .offset(x = 250.dp, y = (-41).dp),
            contentScale = ContentScale.Crop
        )
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
            .height(40.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Country code section
        Card(
            modifier = Modifier
                .width(64.dp)
                .fillMaxHeight(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(R.drawable.india_flag),
                    contentDescription = "India flag",
                    modifier = Modifier.size(width = 24.dp, height = 16.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.dropdown),
                    contentDescription = "Dropdown",
                    modifier = Modifier.size(14.dp),
                    tint = Color(0xFFB0B0B0)
                )
            }
        }

        // Phone number input
        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "+91",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.width(6.dp))

                BasicTextField(
                    value = phoneNumber,
                    onValueChange = onPhoneNumberChange,
                    modifier = Modifier.weight(1f),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        if (phoneNumber.isEmpty()) {
                            Text(
                                text = "Enter Phone Number",
                                color = Color.Black.copy(alpha = 0.4f),
                                fontSize = 15.sp
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
private fun SocialLoginSection(onSocialLogin: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        // Google login button
        Card(
            modifier = Modifier
                .size(40.dp)
                .clickable { onSocialLogin("google") },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            shape = CircleShape
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.google),
                    contentDescription = "Google",
                    modifier = Modifier.size(21.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.width(47.dp))
        
        // More options button
        Card(
            modifier = Modifier
                .size(40.dp)
                .clickable { onSocialLogin("more") },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            shape = CircleShape
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.5.dp)
                ) {
                    repeat(3) {
                        Box(
                            modifier = Modifier
                                .size(2.5.dp)
                                .background(Color.Black, CircleShape)
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
            text = "By continuing, you agree to our",
            color = Color.Black.copy(alpha = 0.66f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(14.dp))
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            FooterLink("Terms of Services")
            FooterLink("Privacy Policy")
            FooterLink("Content Policy")
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
            color = Color.Black.copy(alpha = 0.66f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable { }
        )
        
        Spacer(modifier = Modifier.height(2.dp))
        
        Box(
            modifier = Modifier
                .width(text.length * 5.dp)
                .height(1.dp)
                .background(Color.Black.copy(alpha = 0.2f))
        )
    }
}
