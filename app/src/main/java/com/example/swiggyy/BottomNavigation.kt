package com.example.swiggyy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Food,
        BottomNavItem.InstaMart,
        BottomNavItem.Dineout,
    )

    NavigationBar(containerColor = Color(0xFFFFFFFF)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(

                icon = {
                    Box(
                        modifier = Modifier
                            .size(35.dp), // or whatever size fits your icon
                        contentAlignment = Alignment.Center
                    )
                    {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = item.title,
                            Modifier.align(Alignment.Center)
                        )
                    }
                },
                label = { Text(text = item.title, fontSize = 11.sp) },
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    unselectedIconColor = Color.Black.copy(alpha = 0.4f),
                    selectedTextColor = Color.Black,
                    unselectedTextColor = Color.Black.copy(alpha = 0.4f)
                )
            )
        }
    }
}
