package com.example.swiggyy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core.Strings as CoreStrings
import com.example.swiggyy.feature_bottomNavBar.BottomNavViewModel
import com.example.swiggyy.feature_bottomNavBar.BottomNavigationBar
import com.example.swiggyy.feature_home.HomeScreen
import com.example.swiggyy.feature_home.HomeViewModel
import org.junit.Rule
import org.junit.Test

class BottomNavigationBarTest {

    @get:Rule
    val composeRule = createComposeRule()

    private fun setContent(
        isAuthenticated: Boolean = true,
        bottomNavViewModel: BottomNavViewModel,
        homeViewModel: HomeViewModel,
        onNavReady: (NavHostController) -> Unit
    ) {
        composeRule.setContent {
            val navController = rememberNavController()
            onNavReady(navController)

            Scaffold(
                bottomBar = {
                    if (isAuthenticated) {
                        BottomNavigationBar(
                            navController = navController,
                            viewModel = bottomNavViewModel
                        )
                    }
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(viewModel = homeViewModel, navController = navController)
                        }
                        composable("food") { Text("Food Screen") }
                        composable("instamart") { Text("Instamart Screen") }
                        composable("search") { Text(CoreStrings.SEARCH_HEADER_PROMPT) }
                    }
                }
            }
        }
    }

    @Test
    fun renders_items_whenAuthenticated() {
        val bottomVm = BottomNavViewModel()
        val homeVm = HomeViewModel()

        setContent(
            isAuthenticated = true,
            bottomNavViewModel = bottomVm,
            homeViewModel = homeVm,
            onNavReady = { }
        )

        composeRule.onNodeWithText("Home").assertIsDisplayed()
        composeRule.onNodeWithText("Food").assertIsDisplayed()
        composeRule.onNodeWithText("InstaMart").assertIsDisplayed()
        composeRule.onNodeWithText("Food Screen").assertDoesNotExist()
        composeRule.onNodeWithText("Instamart Screen").assertDoesNotExist()
    }

    @Test
    fun clicking_eachItem_updatesViewModel_andNavigates() {
        val bottomVm = BottomNavViewModel()
        val homeVm = HomeViewModel()

        setContent(true, bottomVm, homeVm) { }

        composeRule.onNodeWithText("Food").performClick()
        composeRule.waitForIdle()
        assert(bottomVm.state.value.selectedRoute == "food")
        composeRule.onNodeWithText("Food Screen").assertIsDisplayed()

        composeRule.onNodeWithText("InstaMart").performClick()
        composeRule.waitForIdle()
        assert(bottomVm.state.value.selectedRoute == "instamart")
        composeRule.onNodeWithText("Instamart Screen").assertIsDisplayed()

        composeRule.onNodeWithText("Home").performClick()
        composeRule.waitForIdle()
        assert(bottomVm.state.value.selectedRoute == "home")
        // Home route content is large; sanity check via HomeScreen element
        composeRule.onNodeWithText("Food Screen").assertDoesNotExist()
        composeRule.onNodeWithText("Instamart Screen").assertDoesNotExist()
    }

    @Test
    fun clicking_selectedItem_keepsState_consistent() {
        val bottomVm = BottomNavViewModel()
        val homeVm = HomeViewModel()

        setContent(true, bottomVm, homeVm) { }

        composeRule.onNodeWithText("Food").performClick()
        composeRule.waitForIdle()
        val selectedAfterFirstClick = bottomVm.state.value.selectedRoute

        // Click the same item again; should remain selected and not crash
        composeRule.onNodeWithText("Food").performClick()
        composeRule.waitForIdle()
        assert(bottomVm.state.value.selectedRoute == selectedAfterFirstClick)
        composeRule.onNodeWithText("Food Screen").assertIsDisplayed()
    }

    @Test
    fun fromSearch_clickingBottomItem_navigatesAndSyncsState() {
        val bottomVm = BottomNavViewModel()
        val homeVm = HomeViewModel()
        lateinit var nav: NavHostController

        setContent(true, bottomVm, homeVm) { nav = it }

        // Navigate to search via Home UI surrogate by directly navigating
        composeRule.runOnUiThread { nav.navigate("search") }
        composeRule.waitForIdle()
        composeRule.onNodeWithText(CoreStrings.SEARCH_HEADER_PROMPT).assertIsDisplayed()

        // Click a bottom item; code has special handling for search -> clear to start
        composeRule.onNodeWithText("InstaMart").performClick()
        composeRule.waitForIdle()
        assert(bottomVm.state.value.selectedRoute == "instamart")
        composeRule.onNodeWithText("Instamart Screen").assertIsDisplayed()
    }

    @Test
    fun rapid_reclicks_doNotBreakSelection() {
        val bottomVm = BottomNavViewModel()
        val homeVm = HomeViewModel()

        setContent(true, bottomVm, homeVm) { }

        composeRule.onNodeWithText("Home").performClick()
        composeRule.onNodeWithText("Home").performClick()
        composeRule.onNodeWithText("Food").performClick()
        composeRule.onNodeWithText("Food").performClick()
        composeRule.onNodeWithText("InstaMart").performClick()
        composeRule.onNodeWithText("InstaMart").performClick()
        composeRule.waitForIdle()

        assert(bottomVm.state.value.selectedRoute == "instamart")
        composeRule.onNodeWithText("Instamart Screen").assertIsDisplayed()
    }
}


