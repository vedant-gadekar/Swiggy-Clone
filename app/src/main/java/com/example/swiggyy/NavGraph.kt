package com.example.swiggyy

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.swiggy.feature_home.HomeScreen
import com.example.swiggy.feature_home.HomeViewModel
import com.example.swiggyy.feature_dineout.Dineout
import com.example.swiggyy.feature_food.Food
import com.example.swiggyy.feature_instamart.InstaMart

@Composable
fun NavigationGraph(navController: NavHostController,
                    homeViewModel: HomeViewModel
) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen(homeViewModel)
        }
        composable(BottomNavItem.InstaMart.screen_route) {
            InstaMart()
        }
        composable(BottomNavItem.Dineout.screen_route) {
            Dineout()
        }
        composable(BottomNavItem.Food.screen_route) {
            Food()
        }
    }
}