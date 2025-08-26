package com.example.swiggyy.feature.bottomnav.viewmodel

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dineout.Dineout
import com.example.instamart.components.InstaMart
import com.example.swiggyy.feature.bottomnav.state.BottomNavItem
import com.example.swiggyy.feature_food.Food
import com.example.swiggyy.feature_home.HomeScreen
import com.example.swiggyy.feature_home.HomeViewModel

@Composable
fun NavigationGraph(navController: NavHostController,
                    homeViewModel: HomeViewModel
) {
    NavHost(navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen(homeViewModel, navController)
        }
        composable(BottomNavItem.InstaMart.screenRoute) {
            InstaMart()
        }
        composable(BottomNavItem.Dineout.screenRoute) {
            Dineout()
        }
        composable(BottomNavItem.Food.screenRoute) {
            Food()
        }
    }
}