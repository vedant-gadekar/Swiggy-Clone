package com.example.swiggyy.feature.instamart.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swiggyy.R

data class Product(
    val id: String,
    val name: String,
    val price: String,
    val weight: String,
    val imageRes: Int,
    val isInCart: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstaMartHomeScreen() {
    val products = remember {
        listOf(
            Product("1", "Organic Bananas", "$6.99", "7pcs, Priceg",R.drawable.bananas ),
            Product("2", "Red Apple", "$6.99", "1kg, Priceg", R.drawable.red_apple)
        )
    }
    
    val bestSellingProducts = remember {
        listOf(
            Product("3", "Bell Pepper Red", "$4.99", "1kg, Priceg", R.drawable.bell_pepper),
            Product("4", "Ginger", "$2.99", "250gm, Priceg", R.drawable.ginger)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFCFCFC))
    ) {
        // Top Bar
        TopAppBar(
            title = {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = "Location",
                            tint = Color(0xFF181725),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Dhaka, Banasre",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF181725)
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            )
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                
                // Search Bar
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    placeholder = {
                        Text(
                            "Search Store",
                            color = Color(0xFF7C7C7C),
                            fontSize = 14.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color(0xFF181725)
                        )
                    },
                    shape = RoundedCornerShape(27.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFF2F3F2),
                        focusedBorderColor = Color(0xFF53B175),
                        unfocusedContainerColor = Color(0xFFF2F3F2),
                        focusedContainerColor = Color(0xFFF2F3F2)
                    )
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Fresh Vegetables Banner
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF2F3F2)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Fresh Vegetables",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF181725)
                            )
                            Text(
                                text = "Get Up To 40% Off",
                                fontSize = 12.sp,
                                color = Color(0xFF7C7C7C)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "See all",
                                fontSize = 12.sp,
                                color = Color(0xFF53B175),
                                modifier = Modifier.clickable { }
                            )
                        }
                        Image(
                            painter = painterResource(R.drawable.icon_swiggy),
                            contentDescription = "Fresh Vegetables",
                            modifier = Modifier.size(80.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Exclusive Offer Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Exclusive Offer",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF181725)
                    )
                    Text(
                        text = "See all",
                        fontSize = 14.sp,
                        color = Color(0xFF53B175),
                        modifier = Modifier.clickable { }
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(end = 20.dp)
                ) {
                    items(products) { product ->
                        ProductCard(product = product)
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Best Selling Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Best Selling",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF181725)
                    )
                    Text(
                        text = "See all",
                        fontSize = 14.sp,
                        color = Color(0xFF53B175),
                        modifier = Modifier.clickable { }
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(end = 20.dp)
                ) {
                    items(bestSellingProducts) { product ->
                        ProductCard(product = product)
                    }
                }
                
                Spacer(modifier = Modifier.height(100.dp)) // Space for bottom navigation
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .width(173.dp)
            .height(248.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = product.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF181725),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = product.weight,
                fontSize = 14.sp,
                color = Color(0xFF7C7C7C)
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.price,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF181725)
                )
                
                FloatingActionButton(
                    onClick = { },
                    modifier = Modifier.size(40.dp),
                    containerColor = Color(0xFF53B175),
                    contentColor = Color.White
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add to cart",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
