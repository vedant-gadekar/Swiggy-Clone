package com.example.swiggy.feature_home

import com.example.swiggyy.R

data class HomeState(
    val locationName: String = "Amar Business Zone",
    val locationAddress: String = "Baner - Mahalunge Road, Veerbhadra Nagar",
    val searchQuery: String = "",
    val bannerMessage: String = "Vedant, your one expires soon!",
    val bannerSubMessage: String = "You've saved ₹157 with current plan. Renew now & keep saving more.",


    val categories: List<Category> = listOf(
        Category("FOOD DELIVERY", "FROM RESTAURANTS", "UP TO 60% OFF + FREE DEL", R.drawable.food_del),
        Category("INSTAMART", "GET ANYTHING INSTANTLY", "UP TO ₹100 OFF", R.drawable.home_instamart),
        Category("DINEOUT", "CASHBACK CARNIVAL", "UP TO 50% OFF",R.drawable.home_dineout),
        Category("SCENES", "DISCOVER EVENTS NEARBY",null, R.drawable.scenes)
    ),

    val creditCardOffer: String = "Lifetime free, just for you"
)

data class Category(
    val title: String,
    val subtitle: String,
    val offer: String?=null,
    val imageRes:Int
)
