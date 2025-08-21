
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swiggyy.R

data class MenuItem(
    val id: String,
    val title: String,
    val icon: ImageVector,
    val showDivider: Boolean = true
)

@Composable
fun ProfileScreen() {
    val menuItems = remember {
        listOf(
            MenuItem("1", "Orders", Icons.Default.ShoppingBag),
            MenuItem("2", "My Details", Icons.Default.Person),
            MenuItem("3", "Delivery Address", Icons.Default.LocationOn),
            MenuItem("4", "Payment Methods", Icons.Default.CreditCard),
            MenuItem("5", "Promo Card", Icons.Default.LocalOffer),
            MenuItem("6", "Notifications", Icons.Default.Notifications),
            MenuItem("7", "Help", Icons.Default.Help),
            MenuItem("8", "About", Icons.Default.Info, showDivider = false)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFCFCFC))
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        
        // Profile Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // User Info
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Afsar Hossen",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF181725)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Edit Profile",
                        tint = Color(0xFF53B175),
                        modifier = Modifier
                            .size(18.dp)
                            .clickable { }
                    )
                }
                
                Text(
                    text = "Imshuvo97@gmail.com",
                    fontSize = 16.sp,
                    color = Color(0xFF7C7C7C)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Menu Items
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            items(menuItems) { menuItem ->
                MenuItemRow(
                    menuItem = menuItem,
                    onClick = { }
                )
            }
            
            item {
                Spacer(modifier = Modifier.height(32.dp))
                
                // Log Out Button
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF2F3F2)
                    ),
                    shape = RoundedCornerShape(19.dp)
                ) {
                    Icon(
                        Icons.Default.ExitToApp,
                        contentDescription = "Log Out",
                        tint = Color(0xFF53B175),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Log Out",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF53B175)
                    )
                }
                
                Spacer(modifier = Modifier.height(100.dp)) // Space for bottom navigation
            }
        }
    }
}

@Composable
fun MenuItemRow(
    menuItem: MenuItem,
    onClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = menuItem.icon,
                contentDescription = menuItem.title,
                tint = Color(0xFF181725),
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(20.dp))
            
            Text(
                text = menuItem.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF181725),
                modifier = Modifier.weight(1f)
            )
            
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = "Go to ${menuItem.title}",
                tint = Color(0xFF181725),
                modifier = Modifier.size(24.dp)
            )
        }
        
        if (menuItem.showDivider) {
            Divider(
                color = Color(0xFFE2E2E2),
                thickness = 1.dp,
                modifier = Modifier.padding(start = 44.dp)
            )
        }
    }
}
