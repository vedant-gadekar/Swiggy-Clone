package com.example.swiggyy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.swiggyy.feature.bottomnav.state.BottomNavItem
import com.example.swiggyy.feature.bottomnav.viewmodel.NavigationGraph
import com.example.swiggyy.feature_bottomNavBar.BottomNavViewModel
import com.example.swiggyy.feature_bottomNavBar.BottomNavigationBar
import com.example.swiggyy.feature_home.HomeViewModel
import com.example.swiggyy.ui.theme.SwiggyyTheme
import kotlin.getValue


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val BottomNavViewModel: BottomNavViewModel by viewModels()

            val navController = rememberNavController()
            val homeViewModel: HomeViewModel by viewModels()

            // For demo purposes, set to false to show welcome screen first
            // In a real app, this would be determined by checking auth state
            val isAuthenticated = remember { mutableStateOf(false) }

            // Listen for navigation to main screens to update auth state
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            LaunchedEffect(navBackStackEntry) {
                val currentRoute = navBackStackEntry?.destination?.route
                if (currentRoute == BottomNavItem.Home.screenRoute ||
                    currentRoute == BottomNavItem.Food.screenRoute ||
                    currentRoute == BottomNavItem.InstaMart.screenRoute ||
                    currentRoute == BottomNavItem.Dineout.screenRoute) {
                    isAuthenticated.value = true
                }
            }

            SwiggyyTheme {
                Scaffold(
                    modifier = Modifier.statusBarsPadding(),
                    bottomBar = {
                        // Only show bottom navigation when authenticated
                        if (isAuthenticated.value) {
                            BottomNavigationBar(navController = navController, BottomNavViewModel)
                        }
                    }
                ) {
                    NavigationGraph(
                        navController = navController,
                        homeViewModel = homeViewModel,
                        isAuthenticated = isAuthenticated.value
                    )
                }
            }
        }
    }
}
