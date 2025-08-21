package com.example.swiggyy.feature_instamart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.swiggyy.feature.bottomnav.state.BottomNavItem
import com.example.swiggyy.feature.instamart.HomeScreen.InstaMartHomeScreen
import kotlin.collections.contains


@Composable
fun InstaMart() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .background(color = Color.Red)
    ) {
        Text(
            text = "Instamart",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun InstaMart() {
//    val navController = rememberNavController()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//
//    val bottomNavRoutes = listOf(
//        "home",
//        "categories",
//        "cart",
//        "favorites",
//        "profile"
//    )
//
//    val showBottomNav = currentRoute in bottomNavRoutes
//
//
//}



@Composable
fun FavoritesScreen() {
    // Implementation would go here based on the favorites screen from Figma
    EmptyStateScreen(
        title = "No Favorites Yet",
        description = "Add items to favorites to see them here"
    )
}

@Composable
fun EmptyStateScreen(
    title: String,
    description: String
) {
    androidx.compose.foundation.layout.Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        androidx.compose.material3.Text(
            text = title,
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall
        )
        androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(8.dp))
        androidx.compose.material3.Text(
            text = description,
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
        )
    }
}










































































































































































































































