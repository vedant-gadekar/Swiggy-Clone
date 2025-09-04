package com.example.swiggyy.bottomNavBar.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.instamart.components.InstaMart
import com.example.swiggyy.bottomNavBar.state.BottomNavItem
import com.example.swiggyy.feature_food.Food
import com.example.swiggyy.feature_home.HomeScreen
import com.example.swiggyy.feature_home.HomeViewModel
import com.example.core.components.SearchScreen
import com.example.feature_auth.WelcomeScreen
import com.example.feature_auth.OTPVerificationScreen
import com.example.feature_auth.viewmodel.AuthViewModel
import com.example.feature_auth.state.AuthEffect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NavigationGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    isAuthenticated: Boolean = false
) {

    val authViewModel: AuthViewModel = viewModel()
    val startDestination = if (isAuthenticated) BottomNavItem.Home.screenRoute else "welcome"

    // Handle auth effects
    LaunchedEffect(authViewModel.effect) {
        authViewModel.effect.collectLatest { effect ->
            when (effect) {
                is AuthEffect.NavigateToOtp -> {
                    navController.navigate("otp_verification/${effect.phoneNumber}")
                }
                is AuthEffect.NavigateToWelcome -> {
                    navController.popBackStack("welcome", inclusive = false)
                }
                is AuthEffect.NavigateToHome -> {
                    navController.navigate(BottomNavItem.Home.screenRoute) {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
                is AuthEffect.ShowToast -> {
                    // Handle toast - you can implement a toast system here
                    // For now, we'll just ignore it as the UI shows loading states
                }
                is AuthEffect.ShowError -> {
                    // Handle error - the error is already shown in the UI state
                }
            }
        }
    }

    NavHost(navController, startDestination = startDestination) {
        // Authentication screens
        composable("welcome") {
            WelcomeScreen(authViewModel)
        }
        
        composable("otp_verification/{phoneNumber}") { backStackEntry ->
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            OTPVerificationScreen(
                phoneNumber = phoneNumber,
                viewModel = authViewModel
            )
        }

        // Main app screens
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen(homeViewModel, navController)
        }
        composable(BottomNavItem.InstaMart.screenRoute) {
            InstaMart(navController = navController)
        }
//        composable(BottomNavItem.Dineout.screenRoute) {
//            Dineout()
//        }
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
