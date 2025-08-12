package com.example.swiggyy.feature_bottomNavBar

import com.example.swiggyy.R

sealed class BottomNavItem(var title:String, var icon:Int, var screenRoute:String){

    object Home : BottomNavItem("Home", R.drawable.icon_swiggy,"home")
    object Food: BottomNavItem("Food", R.drawable.food,"food")
    object InstaMart: BottomNavItem("InstaMart", R.drawable.instamart,"instamart")
    object Dineout: BottomNavItem("Dineout", R.drawable.dineout2,"dineout")
}