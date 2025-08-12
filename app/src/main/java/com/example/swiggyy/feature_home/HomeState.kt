package com.example.swiggy.feature_home

data class HomeState(
    val locationName: String = "Amar Business Zone",
    val locationAddress: String = "Baner - Mahalunge Road, Veerbhadra Nagar",
    val searchQuery: String = "",
    val bannerMessage: String = "Vedant, your one expires soon!",
    val bannerSubMessage: String = "You've saved ₹157 with current plan. Renew now & keep saving more.",


    val categories: List<Category> = listOf(
        Category("Food Delivery", "From restaurants", "UP TO 60% OFF & FREE DEL"),
        Category("Instamart", "Get anything instantly", "UP TO ₹100 OFF"),
        Category("Dineout", "Cashback carnival", "UP TO 50% OFF"),
        Category("Scenes", "Discover events nearby", "NEW")
    ),
    val creditCardOffer: String = "Lifetime free, just for you"
)

data class Category(
    val title: String,
    val subtitle: String,
    val offer: String
)
