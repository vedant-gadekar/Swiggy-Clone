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
import com.example.core.auth.WelcomeScreen
import com.example.core.auth.OTPVerificationScreen


@Composable
fun NavigationGraph(navController: NavHostController,
                    homeViewModel: HomeViewModel,
                    isAuthenticated: Boolean = false
) {
    val startDestination = if (isAuthenticated) BottomNavItem.Home.screenRoute else "welcome"

    NavHost(navController, startDestination = startDestination) {
        // Authentication screens
        composable("welcome") {
            WelcomeScreen(
                onContinue = { phoneNumber ->
                    navController.navigate("otp_verification/$phoneNumber")
                },
                onSocialLogin = { provider ->
                    // Handle social login
                    navController.navigate(BottomNavItem.Home.screenRoute) {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }
        composable("otp_verification/{phoneNumber}") { backStackEntry ->
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            OTPVerificationScreen(
                phoneNumber = "+91-$phoneNumber",
                onBack = {
                    navController.popBackStack()
                },
                onVerified = {
                    navController.navigate(BottomNavItem.Home.screenRoute) {
                        popUpTo("welcome") { inclusive = true }
                    }
                },
                onTryOtherMethods = {
                    navController.popBackStack("welcome", inclusive = false)
                },
                onResendOTP = {
                    // Handle resend OTP logic
                }
            )
        }

        // Main app screens
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
