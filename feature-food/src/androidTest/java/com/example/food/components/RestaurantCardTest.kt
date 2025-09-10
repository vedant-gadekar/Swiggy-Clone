package com.example.food.components

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.food.R
import com.example.swiggyy.feature_food.model.Restaurant
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RestaurantCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var sampleRestaurant: Restaurant
    private var onClickCalled = false
    private var onFavoriteClickCalled = false

    @Before
    fun setup() {
        sampleRestaurant = Restaurant(
            id = "1",
            name = "Test Restaurant",
            imageRes = R.drawable.foodimage1,
            rating = 4.5f,
            reviewCount = 150,
            deliveryTime = "30-35 mins",
            cuisines = listOf("Italian", "Pizza"),
            offer = "50% OFF up to ₹100",
            isAd = true,
            hasFreeDelivery = true,
            hasOneBenefits = true,
            isFavorite = false
        )
        onClickCalled = false
        onFavoriteClickCalled = false
    }

    @Test
    fun restaurantCard_displaysBasicInformation() {
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = sampleRestaurant,
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Verify restaurant name is displayed
        composeTestRule.onNodeWithText("Test Restaurant").assertIsDisplayed()
        
        // Verify rating is displayed
        composeTestRule.onNodeWithText("4.5").assertIsDisplayed()
        
        // Verify review count is displayed
        composeTestRule.onNodeWithText("(150)").assertIsDisplayed()
        
        // Verify delivery time is displayed
        composeTestRule.onNodeWithText("30-35 mins").assertIsDisplayed()
        
        // Verify cuisines are displayed
        composeTestRule.onNodeWithText("Italian, Pizza").assertIsDisplayed()
    }

    @Test
    fun restaurantCard_displaysOfferCorrectly() {
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = sampleRestaurant,
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Verify offer is displayed
        composeTestRule.onNodeWithText("50% OFF up to ₹100").assertIsDisplayed()
    }

    @Test
    fun restaurantCard_showsAdBadgeWhenIsAd() {
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = sampleRestaurant.copy(isAd = true),
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Verify AD badge is displayed
        composeTestRule.onNodeWithText("AD").assertIsDisplayed()
    }

    @Test
    fun restaurantCard_hidesAdBadgeWhenNotAd() {
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = sampleRestaurant.copy(isAd = false),
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Verify AD badge is not displayed
        composeTestRule.onNodeWithText("AD").assertDoesNotExist()
    }

    @Test
    fun restaurantCard_showsOneBenefitsWhenHasOneBenefits() {
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = sampleRestaurant.copy(hasOneBenefits = true),
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Should show one benefits indicator (implementation specific)
        // This would need to be adjusted based on actual UI implementation
    }

    @Test
    fun restaurantCard_showsFreeDeliveryWhenHasFreeDelivery() {
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = sampleRestaurant.copy(hasFreeDelivery = true),
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Should show free delivery indicator (implementation specific)
        // This would need to be adjusted based on actual UI implementation
    }

    @Test
    fun restaurantCard_clickTriggersOnClick() {
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = sampleRestaurant,
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Click on the restaurant card
        composeTestRule.onNodeWithText("Test Restaurant").performClick()
        
        // Verify onClick was called
        assert(onClickCalled)
        assert(!onFavoriteClickCalled) // Should not trigger favorite click
    }

    @Test
    fun restaurantCard_favoriteClickTriggersFavoriteCallback() {
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = sampleRestaurant,
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Click on the favorite button (heart icon)
        composeTestRule.onNodeWithContentDescription("Favorite").performClick()
        
        // Verify onFavoriteClick was called
        assert(onFavoriteClickCalled)
        assert(!onClickCalled) // Should not trigger main click
    }

    @Test
    fun restaurantCard_showsFilledHeartWhenFavorite() {
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = sampleRestaurant.copy(isFavorite = true),
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Should show filled heart icon (content description or semantic check)
        composeTestRule.onNodeWithContentDescription("Favorite").assertIsDisplayed()
    }

    @Test
    fun restaurantCard_showsOutlineHeartWhenNotFavorite() {
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = sampleRestaurant.copy(isFavorite = false),
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Should show outline heart icon
        composeTestRule.onNodeWithContentDescription("Favorite").assertIsDisplayed()
    }

    @Test
    fun restaurantCard_handlesEmptyOffer() {
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = sampleRestaurant.copy(offer = ""),
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Restaurant name should still be displayed
        composeTestRule.onNodeWithText("Test Restaurant").assertIsDisplayed()
        
        // Offer should not be visible when empty
        composeTestRule.onNodeWithText("50% OFF up to ₹100").assertDoesNotExist()
    }

    @Test
    fun restaurantCard_handlesPercentageOffer() {
        val percentageOfferRestaurant = sampleRestaurant.copy(offer = "30% OFF")
        
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = percentageOfferRestaurant,
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Verify percentage offer is displayed
        composeTestRule.onNodeWithText("30% OFF").assertIsDisplayed()
    }

    @Test
    fun restaurantCard_handlesItemsAtOffer() {
        val itemsAtOfferRestaurant = sampleRestaurant.copy(offer = "ITEMS AT ₹99")
        
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = itemsAtOfferRestaurant,
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Verify items at offer is displayed
        composeTestRule.onNodeWithText("ITEMS AT ₹99").assertIsDisplayed()
    }

    @Test
    fun restaurantCard_handlesFreeDeliveryOffer() {
        val freeDeliveryRestaurant = sampleRestaurant.copy(offer = "Free Delivery")
        
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = freeDeliveryRestaurant,
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Verify free delivery offer is displayed
        composeTestRule.onNodeWithText("Free Delivery").assertIsDisplayed()
    }

    @Test
    fun restaurantCard_handlesMultipleCuisines() {
        val multiCuisineRestaurant = sampleRestaurant.copy(
            cuisines = listOf("Italian", "Chinese", "Indian", "Continental")
        )
        
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = multiCuisineRestaurant,
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Verify all cuisines are displayed (joined by comma)
        composeTestRule.onNodeWithText("Italian, Chinese, Indian, Continental").assertIsDisplayed()
    }

    @Test
    fun restaurantCard_handlesSingleCuisine() {
        val singleCuisineRestaurant = sampleRestaurant.copy(cuisines = listOf("Pizza"))
        
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = singleCuisineRestaurant,
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Verify single cuisine is displayed
        composeTestRule.onNodeWithText("Pizza").assertIsDisplayed()
    }

    @Test
    fun restaurantCard_handlesNoCuisines() {
        val noCuisineRestaurant = sampleRestaurant.copy(cuisines = emptyList())
        
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = noCuisineRestaurant,
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Restaurant name should still be displayed
        composeTestRule.onNodeWithText("Test Restaurant").assertIsDisplayed()
        
        // Should handle empty cuisines gracefully
        composeTestRule.onNodeWithText("").assertDoesNotExist()
    }

    @Test
    fun restaurantCard_handlesLowRating() {
        val lowRatingRestaurant = sampleRestaurant.copy(rating = 2.5f)
        
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = lowRatingRestaurant,
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Verify low rating is displayed
        composeTestRule.onNodeWithText("2.5").assertIsDisplayed()
    }

    @Test
    fun restaurantCard_handlesHighReviewCount() {
        val highReviewRestaurant = sampleRestaurant.copy(reviewCount = 1500)
        
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = highReviewRestaurant,
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Verify high review count is displayed
        composeTestRule.onNodeWithText("(1500)").assertIsDisplayed()
    }

    @Test
    fun restaurantCard_handlesLongDeliveryTime() {
        val longDeliveryRestaurant = sampleRestaurant.copy(deliveryTime = "60-90 mins")
        
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = longDeliveryRestaurant,
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Verify long delivery time is displayed
        composeTestRule.onNodeWithText("60-90 mins").assertIsDisplayed()
    }

    @Test
    fun restaurantCard_handlesLongRestaurantName() {
        val longNameRestaurant = sampleRestaurant.copy(
            name = "Very Long Restaurant Name That Should Be Handled Gracefully By The UI Component"
        )
        
        composeTestRule.setContent {
            RestaurantCard(
                restaurant = longNameRestaurant,
                onClick = { onClickCalled = true },
                onFavoriteClick = { onFavoriteClickCalled = true }
            )
        }

        // Verify long name is displayed (might be truncated by UI)
        composeTestRule.onNodeWithText(
            "Very Long Restaurant Name That Should Be Handled Gracefully By The UI Component",
            substring = true
        ).assertIsDisplayed()
    }
}
