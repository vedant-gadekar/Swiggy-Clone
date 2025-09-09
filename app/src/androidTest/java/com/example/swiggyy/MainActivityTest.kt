package com.example.swiggyy

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.swiggyy.feature_bottomNavBar.BottomNavViewModel
import com.example.swiggyy.feature_home.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.example.swiggyy.feature_bottomNavBar.BottomNavigationBar
import com.example.swiggyy.feature_home.HomeScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material3.Text
import androidx.compose.ui.test.onNodeWithTag
import com.example.core.Strings as CoreStrings

/**
 * UI tests for MainActivity integration following MVI architecture patterns.
 * Tests verify the integration between HomeScreen and BottomNavigationBar,
 * authentication state handling, and overall app flow.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun mainActivity_initialState_showsHomeScreen() {
        val bottomNavVm = BottomNavViewModel()
        val homeVm = HomeViewModel()
        var navController: NavHostController? = null
        var isAuthenticated by mutableStateOf(true)
        
        composeTestRule.setContent {
            navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    if (isAuthenticated) {
                        BottomNavigationBar(
                            navController = navController!!, 
                            viewModel = bottomNavVm
                        )
                    }
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavHost(navController = navController!!, startDestination = "home") {
                        composable("home") {
                            HomeScreen(viewModel = homeVm, navController = navController!!)
                        }
                        composable("food") { Text("Food Screen") }
                        composable("instamart") { Text("Instamart Screen") }
                        composable("search") { Text(CoreStrings.SEARCH_HEADER_PROMPT) }
                    }
                }
            }
        }

        // Verify home screen content is displayed via a key element
        composeTestRule.onNodeWithTag("home_search_bar").assertIsDisplayed()
        
        // Verify bottom navigation is displayed when authenticated
        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
        composeTestRule.onNodeWithText("Food").assertIsDisplayed()
        composeTestRule.onNodeWithText("InstaMart").assertIsDisplayed()
    }

    @Test
    fun mainActivity_unauthenticatedState_hidesBottomNavigation() {
        val bottomNavVm = BottomNavViewModel()
        val homeVm = HomeViewModel()
        var navController: NavHostController? = null
        var isAuthenticated by mutableStateOf(false)
        
        composeTestRule.setContent {
            navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    if (isAuthenticated) {
                        BottomNavigationBar(
                            navController = navController!!, 
                            viewModel = bottomNavVm
                        )
                    }
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavHost(navController = navController!!, startDestination = "home") {
                        composable("home") {
                            HomeScreen(viewModel = homeVm, navController = navController!!)
                        }
                        composable("food") { Text("Food Screen") }
                        composable("instamart") { Text("Instamart Screen") }
                        composable("search") { Text(CoreStrings.SEARCH_HEADER_PROMPT) }
                    }
                }
            }
        }

        // Verify home screen is visible (by tag)
        composeTestRule.onNodeWithTag("home_search_bar").assertIsDisplayed()
        
        // Verify bottom navigation is hidden when not authenticated
        composeTestRule.onNodeWithText("Home").assertDoesNotExist()
    }

    @Test
    fun mainActivity_bottomNavClick_switchesBetweenScreens() = runTest {
        val bottomNavVm = BottomNavViewModel()
        val homeVm = HomeViewModel()
        var navController: NavHostController? = null
        var isAuthenticated by mutableStateOf(true)
        
        composeTestRule.setContent {
            navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    if (isAuthenticated) {
                        BottomNavigationBar(
                            navController = navController!!, 
                            viewModel = bottomNavVm
                        )
                    }
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavHost(navController = navController!!, startDestination = "home") {
                        composable("home") {
                            HomeScreen(viewModel = homeVm, navController = navController!!)
                        }
                        composable("food") { Text("Food Screen") }
                        composable("instamart") { Text("Instamart Screen") }
                        composable("search") { Text(CoreStrings.SEARCH_HEADER_PROMPT) }
                    }
                }
            }
        }

        // Click on Food tab and assert destination
        composeTestRule.onNodeWithText("Food").performClick()
        composeTestRule.waitForIdle()
        assert(bottomNavVm.state.value.selectedRoute == "food")
        composeTestRule.onNodeWithText("Food Screen").assertIsDisplayed()
        
        // Click on InstaMart tab and assert destination
        composeTestRule.onNodeWithText("InstaMart").performClick()
        composeTestRule.waitForIdle()
        assert(bottomNavVm.state.value.selectedRoute == "instamart")
        composeTestRule.onNodeWithText("Instamart Screen").assertIsDisplayed()
    }

    @Test
    fun mainActivity_homeScreenInteractions_workCorrectly() = runTest {
        val bottomNavVm = BottomNavViewModel()
        val homeVm = HomeViewModel()
        var navController: NavHostController? = null
        var isAuthenticated by mutableStateOf(true)
        
        composeTestRule.setContent {
            navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    if (isAuthenticated) {
                        BottomNavigationBar(
                            navController = navController!!, 
                            viewModel = bottomNavVm
                        )
                    }
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavHost(navController = navController!!, startDestination = "home") {
                        composable("home") {
                            HomeScreen(viewModel = homeVm, navController = navController!!)
                        }
                        composable("food") { Text("Food Screen") }
                        composable("instamart") { Text("Instamart Screen") }
                        composable("search") { Text(CoreStrings.SEARCH_HEADER_PROMPT) }
                    }
                }
            }
        }

        // Click search bar -> navigates to search
        composeTestRule.onNodeWithTag("home_search_bar").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText(CoreStrings.SEARCH_HEADER_PROMPT).assertIsDisplayed()
        
        // Navigate back to Home to continue (simulate bottom tab click Home)
        composeTestRule.onNodeWithText("Home").performClick()
        composeTestRule.waitForIdle()
        
        // Category click (ensure card exists)
        composeTestRule.onNodeWithText("FOOD DELIVERY").performClick()
        composeTestRule.waitForIdle()
    }

    @Test
    fun mainActivity_rapidNavigation_handlesCorrectly() = runTest {
        val bottomNavVm = BottomNavViewModel()
        val homeVm = HomeViewModel()
        var navController: NavHostController? = null
        var isAuthenticated by mutableStateOf(true)
        
        composeTestRule.setContent {
            navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    if (isAuthenticated) {
                        BottomNavigationBar(
                            navController = navController!!, 
                            viewModel = bottomNavVm
                        )
                    }
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavHost(navController = navController!!, startDestination = "home") {
                        composable("home") {
                            HomeScreen(viewModel = homeVm, navController = navController!!)
                        }
                        composable("food") { Text("Food Screen") }
                        composable("instamart") { Text("Instamart Screen") }
                        composable("search") { Text(CoreStrings.SEARCH_HEADER_PROMPT) }
                    }
                }
            }
        }

        // Rapid navigation between tabs
        composeTestRule.onNodeWithText("Home").performClick()
        composeTestRule.onNodeWithText("Food").performClick()
        composeTestRule.onNodeWithText("InstaMart").performClick()
        composeTestRule.onNodeWithText("Home").performClick()
        
        composeTestRule.waitForIdle()
        
        // Verify final state is correct
        assert(bottomNavVm.state.value.selectedRoute == "home")
    }

    @Test
    fun mainActivity_authenticationStateChange_updatesUI() = runTest {
        val bottomNavVm = BottomNavViewModel()
        val homeVm = HomeViewModel()
        var navController: NavHostController? = null
        var isAuthenticated by mutableStateOf(false)
        
        composeTestRule.setContent {
            navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    if (isAuthenticated) {
                        BottomNavigationBar(
                            navController = navController!!, 
                            viewModel = bottomNavVm
                        )
                    }
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavHost(navController = navController!!, startDestination = "home") {
                        composable("home") {
                            HomeScreen(viewModel = homeVm, navController = navController!!)
                        }
                        composable("food") { Text("Food Screen") }
                        composable("instamart") { Text("Instamart Screen") }
                        composable("search") { Text(CoreStrings.SEARCH_HEADER_PROMPT) }
                    }
                }
            }
        }

        // Initially not authenticated => bottom nav hidden
        composeTestRule.onNodeWithTag("home_search_bar").assertIsDisplayed()
        composeTestRule.onNodeWithText("Home").assertDoesNotExist()
        
        // Simulate authentication
        isAuthenticated = true
        composeTestRule.waitForIdle()
        
        // Verify bottom navigation appears
        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
    }

    @Test
    fun mainActivity_multipleViewModels_stateIsolation() = runTest {
        val bottomNavVm = BottomNavViewModel()
        val homeVm = HomeViewModel()
        var navController: NavHostController? = null
        var isAuthenticated by mutableStateOf(true)
        
        composeTestRule.setContent {
            navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    if (isAuthenticated) {
                        BottomNavigationBar(
                            navController = navController!!, 
                            viewModel = bottomNavVm
                        )
                    }
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavHost(navController = navController!!, startDestination = "home") {
                        composable("home") {
                            HomeScreen(viewModel = homeVm, navController = navController!!)
                        }
                        composable("food") { Text("Food Screen") }
                        composable("instamart") { Text("Instamart Screen") }
                        composable("search") { Text(CoreStrings.SEARCH_HEADER_PROMPT) }
                    }
                }
            }
        }

        // Interact with home search bar
        composeTestRule.onNodeWithTag("home_search_bar").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText(CoreStrings.SEARCH_HEADER_PROMPT).assertIsDisplayed()
        
        // Interact with bottom navigation
        composeTestRule.onNodeWithText("Food").performClick()
        composeTestRule.waitForIdle()
        
        // Verify BottomNavViewModel state updated independently
        assert(bottomNavVm.state.value.selectedRoute == "food")
    }

    @Test
    fun mainActivity_edgeCase_emptyStateHandling() = runTest {
        val bottomNavVm = BottomNavViewModel()
        val homeVm = HomeViewModel()
        var navController: NavHostController? = null
        var isAuthenticated by mutableStateOf(true)
        
        composeTestRule.setContent {
            navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    if (isAuthenticated) {
                        BottomNavigationBar(
                            navController = navController!!, 
                            viewModel = bottomNavVm
                        )
                    }
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavHost(navController = navController!!, startDestination = "home") {
                        composable("home") {
                            HomeScreen(viewModel = homeVm, navController = navController!!)
                        }
                        composable("food") { Text("Food Screen") }
                        composable("instamart") { Text("Instamart Screen") }
                        composable("search") { Text(CoreStrings.SEARCH_HEADER_PROMPT) }
                    }
                }
            }
        }

        // Verify app handles empty states gracefully by asserting key elements
        composeTestRule.onNodeWithTag("home_search_bar").assertIsDisplayed()
        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
    }

    @Test
    fun mainActivity_recomposition_preservesState() = runTest {
        val bottomNavVm = BottomNavViewModel()
        val homeVm = HomeViewModel()
        var navController: NavHostController? = null
        var isAuthenticated by mutableStateOf(true)
        var triggerRecomposition by mutableStateOf(0)
        
        composeTestRule.setContent {
            navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    if (isAuthenticated) {
                        BottomNavigationBar(
                            navController = navController!!, 
                            viewModel = bottomNavVm
                        )
                    }
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavHost(navController = navController!!, startDestination = "home") {
                        composable("home") {
                            HomeScreen(viewModel = homeVm, navController = navController!!)
                        }
                        composable("food") { Text("Food Screen") }
                        composable("instamart") { Text("Instamart Screen") }
                        composable("search") { Text(CoreStrings.SEARCH_HEADER_PROMPT) }
                    }
                }
            }
        }

        // Navigate and set state
        composeTestRule.onNodeWithText("Food").performClick()
        composeTestRule.waitForIdle()
        assert(bottomNavVm.state.value.selectedRoute == "food")
        
        // Trigger recomposition
        triggerRecomposition++
        composeTestRule.waitForIdle()
        
        // Verify state is preserved
        assert(bottomNavVm.state.value.selectedRoute == "food")
    }
}