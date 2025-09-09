package com.example.core

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.core.components.SearchScreen
import com.example.core.state.SearchState
import com.example.core.viewmodel.SearchViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun SearchScreen_initialState_rendersHeaderAndSearchBar() {
        composeRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "search") {
                composable("search") { SearchScreen(viewModel = SearchViewModel()) }
            }
        }
        composeRule.onNodeWithText(Strings.SEARCH_HEADER_PROMPT).assertIsDisplayed()
        composeRule.onNode(hasSetTextAction()).assertIsDisplayed()
    }

    @Test
    fun SearchScreen_typingQuery_updatesViewModelState() {
        val vm = SearchViewModel()
        composeRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "search") {
                composable("search") { SearchScreen(viewModel = vm) }
            }
        }
        composeRule.onNode(hasSetTextAction()).performTextInput("pasta & pizza !@#")
        assert(vm.state.value.searchQuery == "pasta & pizza !@#")
    }

    @Test
    fun TrendingSearchesSection_nonEmpty_showsChips_andClickTriggersBackEffect() {
        val vm = SearchViewModel()
        var navigatedBack = false
        composeRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "search") {
                composable("search") {
                    SearchScreen(
                        viewModel = vm,
                        onNavigateBack = { navigatedBack = true }
                    )
                }
            }
        }
        // Default state has trending items like "Irani Cafe". Click one by text to trigger event.
        composeRule.onNodeWithText("Irani Cafe").performClick()
        assert(navigatedBack)
    }

    @Test
    fun TrendingSearchesSection_empty_rendersGracefully_noChips() {
        val vm = SearchViewModel()
        // Reflectively set empty trending
        val field = SearchViewModel::class.java.getDeclaredField("_state").apply { isAccessible = true }
        @Suppress("UNCHECKED_CAST")
        val state = field.get(vm) as MutableStateFlow<SearchState>
        state.value = state.value.copy(trendingSearches = emptyList())

        composeRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "search") {
                composable("search") { SearchScreen(viewModel = vm) }
            }
        }
        // Assert one of the default chip texts does not exist
        composeRule.onNodeWithText("Irani Cafe").assertDoesNotExist()
    }

    @Test
    fun PopularInstamartSection_rendersItems_andEmptyGracefully() {
        val vm = SearchViewModel()
        // Ensure default popular items render
        composeRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "search") {
                composable("search") { SearchScreen(viewModel = vm) }
            }
        }
        composeRule.onNodeWithText("Vegetables").assertIsDisplayed()

        // Now set empty list and recompose
        val field = SearchViewModel::class.java.getDeclaredField("_state").apply { isAccessible = true }
        @Suppress("UNCHECKED_CAST")
        val state = field.get(vm) as MutableStateFlow<SearchState>
        state.value = state.value.copy(popularItems = emptyList())
        composeRule.waitForIdle()
        composeRule.onNodeWithText("Vegetables").assertDoesNotExist()
    }

    @Test
    fun PopularCuisinesSection_rendersCuisines_andEmptyGracefully() {
        val vm = SearchViewModel()
        composeRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "search") {
                composable("search") { SearchScreen(viewModel = vm) }
            }
        }
        composeRule.onNodeWithText("Biryani").assertIsDisplayed()

        // Empty cuisines
        val field = SearchViewModel::class.java.getDeclaredField("_state").apply { isAccessible = true }
        @Suppress("UNCHECKED_CAST")
        val state = field.get(vm) as MutableStateFlow<SearchState>
        state.value = state.value.copy(popularCuisines = emptyList())
        composeRule.waitForIdle()
        composeRule.onNodeWithText("Biryani").assertDoesNotExist()
    }
}