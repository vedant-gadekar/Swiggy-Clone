
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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

data class ProductCategory(
    val id: String,
    val name: String,
    val imageRes: Int,
    val backgroundColor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCategoriesScreen() {
    val categories = remember {
        listOf(
            ProductCategory("1", "Fresh Fruits\n& Vegetable", R.drawable.icon_swiggy, Color(0xFFE8F5E8)),
            ProductCategory("2", "Cooking Oil\n& Ghee", R.drawable.cooking_oil, Color(0xFFFFF3E0)),
            ProductCategory("3", "Meat & Fish", R.drawable.meat_fish, Color(0xFFFFEBEE)),
            ProductCategory("4", "Bakery & Snacks", R.drawable.bakery_snacks, Color(0xFFF3E5F5)),
            ProductCategory("5", "Dairy & Eggs", R.drawable.dairy_eggs, Color(0xFFFFF8E1)),
            ProductCategory("6", "Beverages", R.drawable.beverages, Color(0xFFE3F2FD))
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
                Text(
                    text = "Find Products",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF181725),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
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
            
            // Categories Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 100.dp) // Space for bottom navigation
            ) {
                items(categories) { category ->
                    CategoryCard(category = category)
                }
            }
        }
    }
}

@Composable
fun CategoryCard(category: ProductCategory) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(189.dp)
            .clickable { },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = category.backgroundColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(category.imageRes),
                contentDescription = category.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = category.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF181725),
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )
        }
    }
}
