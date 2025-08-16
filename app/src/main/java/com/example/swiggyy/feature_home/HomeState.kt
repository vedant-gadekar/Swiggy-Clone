package com.example.swiggyy.feature_home

import com.example.swiggyy.R

data class HomeState(
    val locationName: String = "Amar Business Zone",
    val locationAddress: String = "Baner - Mahalunge Road, Veerbhadra Nagar",
    val searchQuery: String = "",
    val bannerMessage: String = "Vedant, your one expires soon!",
    val bannerSubMessage: String = "You've saved ₹157 with current plan. Renew now & keep saving more.",


    val categories: List<Category> = listOf(
        Category(
            "FOOD DELIVERY",
            "FROM RESTAURANTS",
            "UP TO 60% OFF + FREE DEL",
            R.drawable.food_del
        ),
        Category(
            "INSTAMART",
            "GET ANYTHING INSTANTLY",
            "UP TO ₹100 OFF",
            R.drawable.home_instamart
        ),
        Category(
            "DINEOUT",
            "CASHBACK CARNIVAL",
            "UP TO 50% OFF",
            R.drawable.home_dineout
        ),
        Category(
            "SCENES",
            "DISCOVER EVENTS NEARBY",
            null,
            R.drawable.scenes
        )
    ),

    val carouselItems: List<CarouselItem> = listOf(
        CarouselItem(
            null,
            null,
            "HIGH PROTEIN DELIGHTS",
            "To meet your daily protein intake!",
            "Order now",
            R.drawable.carousel
            ),
        CarouselItem(
            "BIGGEST SAVINGS EVER!",
            null,
            "EXTRA 10% Dinecash",
            "...guaranteed on your bill,erveytime you dine out!",
            "BOOK ON DINEOUT",
            R.drawable.carousel
        ),
        CarouselItem(
            "NOT FOR EVERYONE, JUST FOR YOU",
            "ONE-TIME OFFER",
            "Free Swiggy ONE For 12 Months!",
            "With Swiggy Credit Card.",
            "Apply Now",
            R.drawable.carousel
        )
    ),
    val currentPage: Int = 0
)

data class Category(
    val title: String,
    val subtitle: String,
    val offer: String?=null,
    val imageRes:Int
)


data class CarouselItem(
    val overline:String?,
    val chip:String? ,
    val title: String,
    val subtitle: String,
    val buttonText: String,
    val imageRes: Int
)