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
import com.example.core.components.SearchScreen


@Composable
fun NavigationGraph(navController: NavHostController,
                    homeViewModel: HomeViewModel
) {
    NavHost(navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen(homeViewModel, navController)
        }
        composable(BottomNavItem.InstaMart.screenRoute) {
            InstaMart(navController = navController)
        }
        composable(BottomNavItem.Dineout.screenRoute) {
            Dineout()
        }
        composable(BottomNavItem.Food.screenRoute) {
            Food(navController = navController)
        }
        composable("search") {
            SearchScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToFood = { cuisineFilter ->
                    navController.navigate(BottomNavItem.Food.screenRoute)
                },
                onNavigateToInstamart = { itemFilter ->
                    navController.navigate(BottomNavItem.InstaMart.screenRoute)
                }
            )
        }
    }
}
