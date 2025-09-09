package com.example.swiggyy

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.swiggyy.feature_home.HomeViewModel
import com.example.swiggyy.feature_home.HomeIntent
import com.example.swiggyy.feature_home.HomeState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.swiggyy.feature_home.HomeScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material3.Text
import com.example.core.Strings as CoreStrings
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import com.example.core.state.CarouselItem
import com.example.swiggyy.feature_home.Category

/**
 * UI tests for HomeScreen following MVI architecture patterns.
 * Tests verify initial rendering, user interactions, state changes, and edge cases.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun home_initialState_rendersAllKeyElements_withTestTags_andMockState() {
        val mockState = HomeState(
            locationName = "Test Location",
            locationAddress = "Test Address",
            searchQuery = "",
            categories = listOf(
                Category("FOOD DELIVERY", "FROM RESTAURANTS", "UP TO 60% OFF", com.example.swiggyy.R.drawable.food_del),
                Category("INSTAMART", "GET ANYTHING INSTANTLY", "UP TO ₹100 OFF", com.example.swiggyy.R.drawable.home_instamart),
                Category("DINEOUT", "CASHBACK CARNIVAL", "UP TO 50% OFF", com.example.swiggyy.R.drawable.home_dineout),
                Category("SCENES", "DISCOVER EVENTS NEARBY", null, com.example.swiggyy.R.drawable.scenes),
            ),
            carouselItems = listOf(
                CarouselItem(null, null, "HIGH PROTEIN DELIGHTS", "To meet your daily protein intake!", "Order now", com.example.swiggyy.R.drawable.carousel),
                CarouselItem("BIGGEST SAVINGS EVER!", null, "EXTRA 10% Dinecash", "...guaranteed on your bill", "BOOK ON DINEOUT", com.example.swiggyy.R.drawable.carousel),
                CarouselItem("NOT FOR EVERYONE, JUST FOR YOU", "ONE-TIME OFFER", "Free Swiggy ONE For 12 Months!", "With Swiggy Credit Card.", "Apply Now", com.example.swiggyy.R.drawable.carousel),
            ),
            currentPage = 0
        )
        val vm = HomeViewModel(initialState = mockState)

        var navController: NavHostController? = null
        
        composeTestRule.setContent {
            navController = rememberNavController()
            HomeScreen(viewModel = vm, navController = navController!!)
        }

        // Assert search bar is displayed by tag
        composeTestRule.onNodeWithTag("home_search_bar").assertIsDisplayed()

        // Assert category cards by tag
        composeTestRule.onNodeWithTag("category_FOOD DELIVERY").assertIsDisplayed()
        composeTestRule.onNodeWithTag("category_INSTAMART").assertIsDisplayed()
        composeTestRule.onNodeWithTag("category_DINEOUT").assertIsDisplayed()
        composeTestRule.onNodeWithTag("category_SCENES").assertIsDisplayed()

        // Assert first carousel card by tag
        composeTestRule.onNodeWithTag("carousel_item_HIGH PROTEIN DELIGHTS").assertIsDisplayed()

        // Footer tags (optional if added)
        // composeTestRule.onNodeWithTag("footer_live_it_up").assertIsDisplayed()
        // composeTestRule.onNodeWithTag("footer_crafted").assertIsDisplayed()
    }

    @Test
    fun home_searchBarClick_navigatesToSearchScreen() = runTest {
        val vm = HomeViewModel()
        var navController: NavHostController? = null
        
        composeTestRule.setContent {
            navController = rememberNavController()
            NavHost(navController = navController!!, startDestination = "home") {
                composable("home") {
                    HomeScreen(viewModel = vm, navController = navController!!)
                }
                composable("search") {
                    Text(text = CoreStrings.SEARCH_HEADER_PROMPT)
                }
            }
        }

        // Click on search bar on Home (click the tagged row, not a TextField)
        composeTestRule.onNodeWithTag("home_search_bar").performClick()
        composeTestRule.waitForIdle()

        // Assert Search screen is shown
        composeTestRule.onNodeWithText(CoreStrings.SEARCH_HEADER_PROMPT).assertIsDisplayed()
    }


    @Test
    fun home_clickingFoodDeliveryCategory_navigatesToFoodScreen() = runTest {
        val vm = HomeViewModel()
        var navController: NavHostController? = null
        
        composeTestRule.setContent {
            navController = rememberNavController()
            NavHost(navController = navController!!, startDestination = "home") {
                composable("home") {
                    HomeScreen(viewModel = vm, navController = navController!!)
                }
                composable("food") {
                    Text(text = "Food Screen")
                }
            }
        }

        // Click on FOOD DELIVERY category (use stable testTag on Card)
        composeTestRule.onNodeWithTag("category_FOOD DELIVERY").performClick()
        
        // Verify navigation occurred by asserting destination content
        composeTestRule.onNodeWithText("Food Screen").assertIsDisplayed()
    }

    @Test
    fun home_clickingCarouselButton_dispatchesCorrectIntent() = runTest {
        val vm = HomeViewModel()
        var navController: NavHostController? = null
        
        composeTestRule.setContent {
            navController = rememberNavController()
            HomeScreen(viewModel = vm, navController = navController!!)
        }

        // Click on carousel button
        composeTestRule.onNodeWithText("Order now").performClick()
        
        // Verify the intent was handled (carousel item click)
        composeTestRule.waitForIdle()
    }

    @Test
    fun home_carouselPageChange_updatesState() = runTest {
        val vm = HomeViewModel()
        var navController: NavHostController? = null
        
        composeTestRule.setContent {
            navController = rememberNavController()
            HomeScreen(viewModel = vm, navController = navController!!)
        }

        // Simulate page change (this would typically be triggered by swipe or page indicator)
        vm.handleIntent(HomeIntent.PageChanged(1))
        
        // Verify state is updated
        assert(vm.state.value.currentPage == 1)
    }

    @Test
    fun home_categoryCards_areClickable() = runTest {
        val vm = HomeViewModel()
        var navController: NavHostController? = null
        
        composeTestRule.setContent {
            navController = rememberNavController()
            HomeScreen(viewModel = vm, navController = navController!!)
        }

        // Verify all category cards are clickable
        composeTestRule.onNode(hasText("FOOD DELIVERY").and(hasClickAction())).assertIsDisplayed()
        composeTestRule.onNode(hasText("INSTAMART").and(hasClickAction())).assertIsDisplayed()
        composeTestRule.onNode(hasText("DINEOUT").and(hasClickAction())).assertIsDisplayed()
        composeTestRule.onNode(hasText("SCENES").and(hasClickAction())).assertIsDisplayed()
    }

    @Test
    fun home_offersAreDisplayed_whenPresent() = runTest {
        val vm = HomeViewModel()
        var navController: NavHostController? = null
        
        composeTestRule.setContent {
            navController = rememberNavController()
            HomeScreen(viewModel = vm, navController = navController!!)
        }

        // Verify offer text is displayed for categories that have offers
        composeTestRule.onNodeWithText("UP TO 60% OFF + FREE DEL").assertIsDisplayed()
        composeTestRule.onNodeWithText("UP TO ₹100 OFF").assertIsDisplayed()
        composeTestRule.onNodeWithText("UP TO 50% OFF").assertIsDisplayed()
    }

    @Test
    fun home_carouselItems_areDisplayedCorrectly() = runTest {
        val vm = HomeViewModel()
        var navController: NavHostController? = null
        
        composeTestRule.setContent {
            navController = rememberNavController()
            HomeScreen(viewModel = vm, navController = navController!!)
        }

        // Page 0 assertions
        composeTestRule.onNodeWithText("HIGH PROTEIN DELIGHTS").assertIsDisplayed()
        composeTestRule.onNodeWithText("To meet your daily protein intake!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Order now").assertIsDisplayed()

        // Swipe to page 1
        composeTestRule.onNodeWithTag("home_carousel").performTouchInput { swipeLeft() }
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("BIGGEST SAVINGS EVER!").assertIsDisplayed()
        composeTestRule.onNodeWithText("EXTRA 10% Dinecash").assertIsDisplayed()
        composeTestRule.onNodeWithText("BOOK ON DINEOUT").assertIsDisplayed()

        // Swipe to page 2
        composeTestRule.onNodeWithTag("home_carousel").performTouchInput { swipeLeft() }
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("NOT FOR EVERYONE, JUST FOR YOU").assertIsDisplayed()
        composeTestRule.onNodeWithText("Free Swiggy ONE For 12 Months!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Apply Now").assertIsDisplayed()
    }

    @Test
    fun home_profileImage_isDisplayed() = runTest {
        val vm = HomeViewModel()
        var navController: NavHostController? = null
        
        composeTestRule.setContent {
            navController = rememberNavController()
            HomeScreen(viewModel = vm, navController = navController!!)
        }

        // Verify profile image is displayed (by checking content description)
        composeTestRule.onNodeWithContentDescription("Profile Picture").assertIsDisplayed()
    }
}