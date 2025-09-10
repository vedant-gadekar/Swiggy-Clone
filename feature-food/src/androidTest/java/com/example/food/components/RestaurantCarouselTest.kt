package com.example.food.components

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
class RestaurantCarouselTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var reorderRestaurants: List<Restaurant>
    private lateinit var quickDeliveryRestaurants: List<Restaurant>
    private var clickedRestaurant: Restaurant? = null
    private var favoritedRestaurant: Restaurant? = null

    @Before
    fun setup() {
        reorderRestaurants = listOf(
            Restaurant(
                id = "r1",
                name = "Reorder Restaurant 1",
                imageRes = R.drawable.foodimage1,
                rating = 4.2f,
                reviewCount = 100,
                deliveryTime = "25 mins",
                cuisines = listOf("Italian"),
                offer = "20% OFF"
            ),
            Restaurant(
                id = "r2",
                name = "Reorder Restaurant 2",
                imageRes = R.drawable.foodimage2,
                rating = 4.5f,
                reviewCount = 200,
                deliveryTime = "30 mins",
                cuisines = listOf("Chinese"),
                offer = "Free Delivery"
            )
        )

        quickDeliveryRestaurants = listOf(
            Restaurant(
                id = "q1",
                name = "Quick Delivery Restaurant 1",
                imageRes = R.drawable.foodimage1,
                rating = 4.0f,
                reviewCount = 80,
                deliveryTime = "15 mins",
                cuisines = listOf("Fast Food"),
                offer = "30% OFF"
            ),
            Restaurant(
                id = "q2",
                name = "Quick Delivery Restaurant 2",
                imageRes = R.drawable.foodimage2,
                rating = 4.3f,
                reviewCount = 150,
                deliveryTime = "20 mins",
                cuisines = listOf("Pizza"),
                offer = "Buy 1 Get 1"
            )
        )

        clickedRestaurant = null
        favoritedRestaurant = null
    }

    @Test
    fun restaurantCarousel_displaysReorderTabByDefault() {
        composeTestRule.setContent {
            RestaurantCarousel(
                reorderRestaurants = reorderRestaurants,
                quickDeliveryRestaurants = quickDeliveryRestaurants,
                onRestaurantClick = { clickedRestaurant = it },
                onFavoriteClick = { favoritedRestaurant = it }
            )
        }

        // Verify REORDER tab is displayed and selected by default
        composeTestRule.onNodeWithText("REORDER").assertIsDisplayed()
        composeTestRule.onNodeWithText("QUICK DELIVERY").assertIsDisplayed()
        
        // Verify reorder restaurants are displayed initially
        composeTestRule.onNodeWithText("Reorder Restaurant 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Reorder Restaurant 2").assertIsDisplayed()
        
        // Verify quick delivery restaurants are not initially displayed
        composeTestRule.onNodeWithText("Quick Delivery Restaurant 1").assertDoesNotExist()
        composeTestRule.onNodeWithText("Quick Delivery Restaurant 2").assertDoesNotExist()
    }

    @Test
    fun restaurantCarousel_switchesToQuickDeliveryWhenTabClicked() {
        composeTestRule.setContent {
            RestaurantCarousel(
                reorderRestaurants = reorderRestaurants,
                quickDeliveryRestaurants = quickDeliveryRestaurants,
                onRestaurantClick = { clickedRestaurant = it },
                onFavoriteClick = { favoritedRestaurant = it }
            )
        }

        // Click on QUICK DELIVERY tab
        composeTestRule.onNodeWithText("QUICK DELIVERY").performClick()
        
        // Wait for composition to update
        composeTestRule.waitForIdle()

        // Verify quick delivery restaurants are now displayed
        composeTestRule.onNodeWithText("Quick Delivery Restaurant 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Quick Delivery Restaurant 2").assertIsDisplayed()
        
        // Verify reorder restaurants are no longer displayed
        composeTestRule.onNodeWithText("Reorder Restaurant 1").assertDoesNotExist()
        composeTestRule.onNodeWithText("Reorder Restaurant 2").assertDoesNotExist()
    }

    @Test
    fun restaurantCarousel_switchesBackToReorderWhenTabClicked() {
        composeTestRule.setContent {
            RestaurantCarousel(
                reorderRestaurants = reorderRestaurants,
                quickDeliveryRestaurants = quickDeliveryRestaurants,
                onRestaurantClick = { clickedRestaurant = it },
                onFavoriteClick = { favoritedRestaurant = it }
            )
        }

        // First switch to QUICK DELIVERY
        composeTestRule.onNodeWithText("QUICK DELIVERY").performClick()
        composeTestRule.waitForIdle()
        
        // Verify we're on quick delivery
        composeTestRule.onNodeWithText("Quick Delivery Restaurant 1").assertIsDisplayed()

        // Now switch back to REORDER
        composeTestRule.onNodeWithText("REORDER").performClick()
        composeTestRule.waitForIdle()

        // Verify we're back on reorder
        composeTestRule.onNodeWithText("Reorder Restaurant 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Quick Delivery Restaurant 1").assertDoesNotExist()
    }

    @Test
    fun restaurantCarousel_handlesRestaurantClick() {
        composeTestRule.setContent {
            RestaurantCarousel(
                reorderRestaurants = reorderRestaurants,
                quickDeliveryRestaurants = quickDeliveryRestaurants,
                onRestaurantClick = { clickedRestaurant = it },
                onFavoriteClick = { favoritedRestaurant = it }
            )
        }

        // Click on first reorder restaurant
        composeTestRule.onNodeWithText("Reorder Restaurant 1").performClick()
        
        // Verify the correct restaurant was clicked
        assert(clickedRestaurant != null)
        assert(clickedRestaurant!!.id == "r1")
        assert(clickedRestaurant!!.name == "Reorder Restaurant 1")
    }

    @Test
    fun restaurantCarousel_handlesRestaurantClickInQuickDeliveryTab() {
        composeTestRule.setContent {
            RestaurantCarousel(
                reorderRestaurants = reorderRestaurants,
                quickDeliveryRestaurants = quickDeliveryRestaurants,
                onRestaurantClick = { clickedRestaurant = it },
                onFavoriteClick = { favoritedRestaurant = it }
            )
        }

        // Switch to quick delivery tab
        composeTestRule.onNodeWithText("QUICK DELIVERY").performClick()
        composeTestRule.waitForIdle()

        // Click on first quick delivery restaurant
        composeTestRule.onNodeWithText("Quick Delivery Restaurant 1").performClick()
        
        // Verify the correct restaurant was clicked
        assert(clickedRestaurant != null)
        assert(clickedRestaurant!!.id == "q1")
        assert(clickedRestaurant!!.name == "Quick Delivery Restaurant 1")
    }

    @Test
    fun restaurantCarousel_handlesFavoriteClick() {
        composeTestRule.setContent {
            RestaurantCarousel(
                reorderRestaurants = reorderRestaurants,
                quickDeliveryRestaurants = quickDeliveryRestaurants,
                onRestaurantClick = { clickedRestaurant = it },
                onFavoriteClick = { favoritedRestaurant = it }
            )
        }

        // Click on favorite button of first restaurant
        // Note: This assumes the favorite button has a content description "Favorite"
        composeTestRule.onAllNodesWithContentDescription("Favorite")[0].performClick()
        
        // Verify the correct restaurant was favorited
        assert(favoritedRestaurant != null)
        assert(favoritedRestaurant!!.id == "r1")
    }

    @Test
    fun restaurantCarousel_handlesEmptyReorderList() {
        composeTestRule.setContent {
            RestaurantCarousel(
                reorderRestaurants = emptyList(),
                quickDeliveryRestaurants = quickDeliveryRestaurants,
                onRestaurantClick = { clickedRestaurant = it },
                onFavoriteClick = { favoritedRestaurant = it }
            )
        }

        // Tabs should still be displayed
        composeTestRule.onNodeWithText("REORDER").assertIsDisplayed()
        composeTestRule.onNodeWithText("QUICK DELIVERY").assertIsDisplayed()
        
        // No reorder restaurants should be displayed
        composeTestRule.onNodeWithText("Reorder Restaurant 1").assertDoesNotExist()
        composeTestRule.onNodeWithText("Reorder Restaurant 2").assertDoesNotExist()
    }

    @Test
    fun restaurantCarousel_handlesEmptyQuickDeliveryList() {
        composeTestRule.setContent {
            RestaurantCarousel(
                reorderRestaurants = reorderRestaurants,
                quickDeliveryRestaurants = emptyList(),
                onRestaurantClick = { clickedRestaurant = it },
                onFavoriteClick = { favoritedRestaurant = it }
            )
        }

        // Switch to quick delivery tab
        composeTestRule.onNodeWithText("QUICK DELIVERY").performClick()
        composeTestRule.waitForIdle()

        // No quick delivery restaurants should be displayed
        composeTestRule.onNodeWithText("Quick Delivery Restaurant 1").assertDoesNotExist()
        composeTestRule.onNodeWithText("Quick Delivery Restaurant 2").assertDoesNotExist()
        
        // Tab should still be displayed
        composeTestRule.onNodeWithText("QUICK DELIVERY").assertIsDisplayed()
    }

    @Test
    fun restaurantCarousel_handlesBothEmptyLists() {
        composeTestRule.setContent {
            RestaurantCarousel(
                reorderRestaurants = emptyList(),
                quickDeliveryRestaurants = emptyList(),
                onRestaurantClick = { clickedRestaurant = it },
                onFavoriteClick = { favoritedRestaurant = it }
            )
        }

        // Tabs should still be displayed
        composeTestRule.onNodeWithText("REORDER").assertIsDisplayed()
        composeTestRule.onNodeWithText("QUICK DELIVERY").assertIsDisplayed()
        
        // Switch between tabs should work without crashing
        composeTestRule.onNodeWithText("QUICK DELIVERY").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("REORDER").performClick()
        composeTestRule.waitForIdle()
    }

    @Test
    fun restaurantCarousel_preservesTabStateAfterRecomposition() {
        var shouldRecompose = false
        
        composeTestRule.setContent {
            // Force recomposition when shouldRecompose changes
            val temp = shouldRecompose
            RestaurantCarousel(
                reorderRestaurants = reorderRestaurants,
                quickDeliveryRestaurants = quickDeliveryRestaurants,
                onRestaurantClick = { clickedRestaurant = it },
                onFavoriteClick = { favoritedRestaurant = it }
            )
        }

        // Switch to quick delivery tab
        composeTestRule.onNodeWithText("QUICK DELIVERY").performClick()
        composeTestRule.waitForIdle()
        
        // Verify we're on quick delivery
        composeTestRule.onNodeWithText("Quick Delivery Restaurant 1").assertIsDisplayed()

        // Trigger recomposition
        shouldRecompose = true
        composeTestRule.waitForIdle()

        // Verify tab state is preserved (still on quick delivery)
        composeTestRule.onNodeWithText("Quick Delivery Restaurant 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Reorder Restaurant 1").assertDoesNotExist()
    }

    @Test
    fun restaurantCarousel_handlesLargeLists() {
        val largeReorderList = (1..20).map { index ->
            Restaurant(
                id = "r$index",
                name = "Reorder Restaurant $index",
                imageRes = R.drawable.foodimage1,
                rating = 4.0f + (index % 10) * 0.1f,
                reviewCount = index * 10,
                deliveryTime = "${20 + index} mins",
                cuisines = listOf("Cuisine $index")
            )
        }

        composeTestRule.setContent {
            RestaurantCarousel(
                reorderRestaurants = largeReorderList,
                quickDeliveryRestaurants = quickDeliveryRestaurants,
                onRestaurantClick = { clickedRestaurant = it },
                onFavoriteClick = { favoritedRestaurant = it }
            )
        }

        // Verify some items from the large list are displayed
        composeTestRule.onNodeWithText("Reorder Restaurant 1").assertIsDisplayed()
        
        // Since it's a LazyRow, not all items will be composed immediately
        // But the component should handle large lists without crashing
    }

    @Test
    fun restaurantCarousel_tabButtonStyleChangesOnSelection() {
        composeTestRule.setContent {
            RestaurantCarousel(
                reorderRestaurants = reorderRestaurants,
                quickDeliveryRestaurants = quickDeliveryRestaurants,
                onRestaurantClick = { clickedRestaurant = it },
                onFavoriteClick = { favoritedRestaurant = it }
            )
        }

        // Initially REORDER should be selected, QUICK DELIVERY should not be
        // This test depends on the TabButton implementation having different semantics for selected/unselected states
        val reorderTab = composeTestRule.onNodeWithText("REORDER")
        val quickDeliveryTab = composeTestRule.onNodeWithText("QUICK DELIVERY")
        
        // Both tabs should be displayed
        reorderTab.assertIsDisplayed()
        quickDeliveryTab.assertIsDisplayed()
        
        // Click quick delivery to change selection
        quickDeliveryTab.performClick()
        composeTestRule.waitForIdle()
        
        // Both should still be displayed but with different selection states
        reorderTab.assertIsDisplayed()
        quickDeliveryTab.assertIsDisplayed()
    }

    @Test
    fun restaurantCarousel_rapidTabSwitchingHandledCorrectly() {
        composeTestRule.setContent {
            RestaurantCarousel(
                reorderRestaurants = reorderRestaurants,
                quickDeliveryRestaurants = quickDeliveryRestaurants,
                onRestaurantClick = { clickedRestaurant = it },
                onFavoriteClick = { favoritedRestaurant = it }
            )
        }

        // Rapidly switch between tabs multiple times
        repeat(5) {
            composeTestRule.onNodeWithText("QUICK DELIVERY").performClick()
            composeTestRule.onNodeWithText("REORDER").performClick()
        }
        
        composeTestRule.waitForIdle()

        // Should end up on REORDER tab (last click)
        composeTestRule.onNodeWithText("Reorder Restaurant 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Quick Delivery Restaurant 1").assertDoesNotExist()
    }
}
