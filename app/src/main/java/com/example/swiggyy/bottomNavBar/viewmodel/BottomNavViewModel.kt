package com.example.swiggyy.feature_bottomNavBar

import androidx.lifecycle.ViewModel
import com.example.swiggyy.bottomNavBar.state.BottomNavItem
import com.example.swiggyy.bottomNavBar.state.BottomNavState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BottomNavViewModel: ViewModel (){
    private val _state = MutableStateFlow(BottomNavState())
    val state: StateFlow<BottomNavState> = _state.asStateFlow()

    fun handleIntent(intent: BottomNavIntent){
        when (intent){
            is BottomNavIntent.OnBottomNavItemClick -> {
                _state.value = _state.value.copy(selectedRoute = intent.route)
            }
        }
    }
}



sealed class BottomNavIntent {
    data class OnBottomNavItemClick(val route: String) : BottomNavIntent()
}