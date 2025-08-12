package com.example.swiggyy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController
import com.example.swiggy.feature_home.HomeViewModel
import com.example.swiggyy.feature_bottomNavBar.BottomNavViewModel
import com.example.swiggyy.feature_bottomNavBar.BottomNavigationBar
import com.example.swiggyy.feature_bottomNavBar.NavigationGraph
import com.example.swiggyy.ui.theme.SwiggyyTheme
import kotlin.getValue


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val BottomNavViewModel: BottomNavViewModel by viewModels()

            val navController=rememberNavController()
            val homeViewModel: HomeViewModel by viewModels()
            SwiggyyTheme {
                Scaffold(modifier = Modifier.statusBarsPadding(),
                    bottomBar = {
                        BottomNavigationBar(navController = navController,BottomNavViewModel)
                    }) {
                    NavigationGraph(navController = navController, homeViewModel)
                }
            }
        }
    }
}

