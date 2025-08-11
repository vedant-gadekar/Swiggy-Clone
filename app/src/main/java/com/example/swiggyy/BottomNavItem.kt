package com.example.swiggyy

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", R.drawable.icon_swiggy,"home")
    object Food: BottomNavItem("Food",R.drawable.icon_food,"food")
    object InstaMart: BottomNavItem("InstaMart",R.drawable.icon_instamart,"instamart")
    object Dineout: BottomNavItem("Dineout",R.drawable.icon_dineout,"dineout")
}