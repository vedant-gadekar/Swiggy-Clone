package com.example.swiggyy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.swiggy.feature_home.HomeScreen
import com.example.swiggy.feature_home.HomeViewModel
import com.example.swiggyy.ui.theme.SwiggyyTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel: HomeViewModel by viewModels()


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwiggyyTheme {
                Scaffold(modifier = Modifier.statusBarsPadding()) { innerPadding ->
                    HomeScreen(viewModel)
                }
            }
        }
    }
}

