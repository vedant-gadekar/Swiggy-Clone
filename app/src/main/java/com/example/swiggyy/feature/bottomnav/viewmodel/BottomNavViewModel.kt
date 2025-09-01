package com.example.swiggyy.feature_bottomNavBar

import androidx.lifecycle.ViewModel
import com.example.swiggyy.feature.bottomnav.state.BottomNavItem
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

data class BottomNavState (
    val selectedRoute: String = BottomNavItem.Home.screenRoute,
    val items: List<BottomNavItem> = listOf(
        BottomNavItem.Home,
        BottomNavItem.Food,
        BottomNavItem.InstaMart,
//        BottomNavItem.Dineout,
    )
)


sealed class BottomNavIntent {
    data class OnBottomNavItemClick(val route: String) : BottomNavIntent()
}