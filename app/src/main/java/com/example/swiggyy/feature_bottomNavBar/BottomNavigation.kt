package com.example.swiggyy.feature_bottomNavBar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
fun BottomNavigationBar(navController: NavController,
                        viewModel: BottomNavViewModel
) {
    val state = viewModel.state.collectAsState().value

    NavigationBar(containerColor = Color.White) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        state.items.forEach { item ->
            NavigationBarItem(

                icon = {
                    Box(
                        modifier = Modifier
                            .size(30.dp),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = item.title,
//                            Modifier.align(Alignment.Center),
                        )
                    }
                },
                label = { Text(text = item.title, fontSize = 11.sp) },
                selected = currentRoute == item.screenRoute,
                onClick = {
                    if (currentRoute != item.screenRoute) {
                        navController.navigate(item.screenRoute) {
                            popUpTo(
                                navController.graph.startDestinationRoute ?: return@navigate
                            ) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFFF6F00),
                    unselectedIconColor = Color.Black.copy(alpha = 0.4f),
                    selectedTextColor = Color(0xFFFF6F00),
                    unselectedTextColor = Color.Black.copy(alpha = 0.4f),
                    indicatorColor = Color.Transparent
                )
            )

        }
    }
}

