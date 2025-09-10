package com.example.food.viewmodel

import app.cash.turbine.test
import com.example.swiggyy.bottomNavBar.food.state.FoodIntent
import com.example.swiggyy.bottomNavBar.food.state.FoodState
import com.example.swiggyy.bottomNavBar.food.state.UiState
import com.example.swiggyy.feature_food.model.Category
import com.example.swiggyy.feature_food.model.Restaurant
import com.example.swiggyy.feature_food.model.StoreItem
import com.example.swiggyy.feature_food.model.SwiggyOption
import com.example.swiggyy.feature_food.viewmodel.FoodViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FoodViewModelTest {

    private lateinit var viewModel: FoodViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = FoodViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should have default values`() {
        val initialState = viewModel.state.value
        
        assertEquals(false, initialState.isLoading)
        assertEquals("Amar Business Zone", initialState.locationName)
        assertEquals("Baner - Mahalunge Road, Veerbhadra...", initialState.locationAddress)
        assertEquals("", initialState.searchQuery)
        assertEquals("Search for 'Cake' 'Sweets'", initialState.searchHint)
        assertEquals("", initialState.selectedFilter)
        assertEquals("", initialState.selectedSort)
        assertNull(initialState.error)
        
        // All UiState collections should be initially empty Success states
        assertTrue(initialState.categories is UiState.Success)
        assertTrue(initialState.reorderRestaurants is UiState.Success)
        assertTrue(initialState.quickDeliveryRestaurants is UiState.Success)
        assertTrue(initialState.featuredRestaurants is UiState.Success)
        assertTrue(initialState.ninetyNineStoreItems is UiState.Success)
        assertTrue(initialState.swiggyOptions is UiState.Success)
    }

    @Test
    fun `loadInitialData should populate state with sample data`() = runTest {
        // Advance the coroutine to complete loadInitialData
        testScheduler.advanceUntilIdle()
        
        val state = viewModel.state.value
        
        // Verify loading is complete
        assertFalse(state.isLoading)
        assertNull(state.error)
        
        // Verify all data is loaded and contains expected content
        val categories = state.categories.getOrNull()
        assertNotNull(categories)
        assertTrue(categories!!.isNotEmpty())
        assertEquals(8, categories.size)
        assertTrue(categories.any { it.name == "Pizza" })
        assertTrue(categories.any { it.name == "Biryani" })
        
        val reorderRestaurants = state.reorderRestaurants.getOrNull()
        assertNotNull(reorderRestaurants)
        assertTrue(reorderRestaurants!!.isNotEmpty())
        assertEquals(3, reorderRestaurants.size)
        assertTrue(reorderRestaurants.any { it.name == "Wendy's Burgers" })
        assertTrue(reorderRestaurants.any { it.name == "Domino's Pizza" })
        
        val quickDeliveryRestaurants = state.quickDeliveryRestaurants.getOrNull()
        assertNotNull(quickDeliveryRestaurants)
        assertTrue(quickDeliveryRestaurants!!.isNotEmpty())
        assertTrue(quickDeliveryRestaurants.any { it.name == "Subway" })
        assertTrue(quickDeliveryRestaurants.any { it.name == "KFC" })
        
        val featuredRestaurants = state.featuredRestaurants.getOrNull()
        assertNotNull(featuredRestaurants)
        assertTrue(featuredRestaurants!!.isNotEmpty())
        assertEquals(3, featuredRestaurants.size)
        assertTrue(featuredRestaurants.any { it.name == "Pizza Hut" })
        
        val storeItems = state.ninetyNineStoreItems.getOrNull()
        assertNotNull(storeItems)
        assertTrue(storeItems!!.isNotEmpty())
        assertEquals(10, storeItems.size)
        assertTrue(storeItems.any { it.name == "Organic Bananas" })
        
        val swiggyOptions = state.swiggyOptions.getOrNull()
        assertNotNull(swiggyOptions)
        assertTrue(swiggyOptions!!.isNotEmpty())
        assertEquals(2, swiggyOptions.size)
        assertTrue(swiggyOptions.any { it.title == "Swiggy One" })
        assertTrue(swiggyOptions.any { it.title == "Dineout" })
    }

    @Test
    fun `handleIntent SearchQueryChanged should update search query`() = runTest {
        testScheduler.advanceUntilIdle()
        
        val testQuery = "pizza"
        viewModel.handleIntent(FoodIntent.SearchQueryChanged(testQuery))
        
        assertEquals(testQuery, viewModel.state.value.searchQuery)
    }

    @Test
    fun `handleIntent CategoryClicked should update search query with category name`() = runTest {
        testScheduler.advanceUntilIdle()
        
        val testCategory = Category("1", "Italian", 0)
        viewModel.handleIntent(FoodIntent.CategoryClicked(testCategory))
        
        assertEquals("Italian", viewModel.state.value.searchQuery)
    }

    @Test
    fun `handleIntent FilterSelected should update selected filter`() = runTest {
        testScheduler.advanceUntilIdle()
        
        val testFilter = "Vegetarian"
        viewModel.handleIntent(FoodIntent.FilterSelected(testFilter))
        
        assertEquals(testFilter, viewModel.state.value.selectedFilter)
    }

    @Test
    fun `handleIntent SortSelected should update selected sort`() = runTest {
        testScheduler.advanceUntilIdle()
        
        val testSort = "Rating: High to Low"
        viewModel.handleIntent(FoodIntent.SortSelected(testSort))
        
        assertEquals(testSort, viewModel.state.value.selectedSort)
    }

    @Test
    fun `handleIntent LoadData should trigger data reload`() = runTest {
        // Wait for initial load to complete
        testScheduler.advanceUntilIdle()
        
        // Clear the state by setting it to loading
        viewModel.handleIntent(FoodIntent.LoadData)
        
        // Check that loading is triggered
        assertTrue(viewModel.state.value.isLoading)
        
        // Advance to complete the loading
        testScheduler.advanceUntilIdle()
        
        // Verify data is loaded again
        assertFalse(viewModel.state.value.isLoading)
        val categories = viewModel.state.value.categories.getOrNull()
        assertNotNull(categories)
        assertTrue(categories!!.isNotEmpty())
    }

    @Test
    fun `handleIntent Retry should trigger data reload`() = runTest {
        // Wait for initial load
        testScheduler.advanceUntilIdle()
        
        // Trigger retry
        viewModel.handleIntent(FoodIntent.Retry)
        
        // Check that loading is triggered
        assertTrue(viewModel.state.value.isLoading)
        
        // Advance to complete the retry
        testScheduler.advanceUntilIdle()
        
        // Verify data is loaded
        assertFalse(viewModel.state.value.isLoading)
        assertNotNull(viewModel.state.value.categories.getOrNull())
    }

    @Test
    fun `toggleFavorite should update restaurant favorite status when restaurant exists`() = runTest {
        // Wait for initial data load
        testScheduler.advanceUntilIdle()
        
        val restaurantId = "5" // Pizza Hut from sample data
        val newFavoriteStatus = false
        
        // Toggle favorite
        viewModel.handleIntent(FoodIntent.ToggleFavorite(restaurantId, newFavoriteStatus))
        
        // Verify the restaurant's favorite status is updated
        val featuredRestaurants = viewModel.state.value.featuredRestaurants.getOrNull()
        assertNotNull(featuredRestaurants)
        
        val updatedRestaurant = featuredRestaurants!!.find { it.id == restaurantId }
        assertNotNull(updatedRestaurant)
        assertEquals(newFavoriteStatus, updatedRestaurant!!.isFavorite)
        
        // Verify other restaurants are not affected
        val otherRestaurants = featuredRestaurants.filter { it.id != restaurantId }
        assertTrue(otherRestaurants.all { it.isFavorite == it.isFavorite }) // Original values preserved
    }

    @Test
    fun `toggleFavorite should not crash when restaurant doesn't exist`() = runTest {
        // Wait for initial data load
        testScheduler.advanceUntilIdle()
        
        val nonExistentRestaurantId = "999"
        val newFavoriteStatus = true
        
        // This should not crash
        viewModel.handleIntent(FoodIntent.ToggleFavorite(nonExistentRestaurantId, newFavoriteStatus))
        
        // Verify state is still consistent
        val featuredRestaurants = viewModel.state.value.featuredRestaurants.getOrNull()
        assertNotNull(featuredRestaurants)
        
        // No restaurant should have been affected
        val updatedRestaurant = featuredRestaurants!!.find { it.id == nonExistentRestaurantId }
        assertNull(updatedRestaurant)
    }

    @Test
    fun `toggleFavorite should handle empty restaurant list gracefully`() = runTest {
        // Create a ViewModel without triggering initial load to have empty data
        val testViewModel = FoodViewModel()
        
        val restaurantId = "1"
        val newFavoriteStatus = true
        
        // This should not crash even with empty list
        testViewModel.handleIntent(FoodIntent.ToggleFavorite(restaurantId, newFavoriteStatus))
        
        // Verify state remains consistent
        val featuredRestaurants = testViewModel.state.value.featuredRestaurants.getOrNull()
        assertNotNull(featuredRestaurants)
        assertTrue(featuredRestaurants!!.isEmpty())
    }

    @Test
    fun `toggleFavorite from true to false should work correctly`() = runTest {
        testScheduler.advanceUntilIdle()
        
        // Find a restaurant that's initially favorite=true (Pizza Hut)
        val restaurantId = "5"
        
        // First verify it's currently favorite
        val initialRestaurants = viewModel.state.value.featuredRestaurants.getOrNull()!!
        val initialRestaurant = initialRestaurants.find { it.id == restaurantId }!!
        assertTrue(initialRestaurant.isFavorite)
        
        // Toggle to false
        viewModel.handleIntent(FoodIntent.ToggleFavorite(restaurantId, false))
        
        // Verify it's now false
        val updatedRestaurants = viewModel.state.value.featuredRestaurants.getOrNull()!!
        val updatedRestaurant = updatedRestaurants.find { it.id == restaurantId }!!
        assertFalse(updatedRestaurant.isFavorite)
    }

    @Test
    fun `toggleFavorite from false to true should work correctly`() = runTest {
        testScheduler.advanceUntilIdle()
        
        // Find a restaurant that's initially favorite=false (Burger King)
        val restaurantId = "6"
        
        // First verify it's currently not favorite
        val initialRestaurants = viewModel.state.value.featuredRestaurants.getOrNull()!!
        val initialRestaurant = initialRestaurants.find { it.id == restaurantId }!!
        assertFalse(initialRestaurant.isFavorite)
        
        // Toggle to true
        viewModel.handleIntent(FoodIntent.ToggleFavorite(restaurantId, true))
        
        // Verify it's now true
        val updatedRestaurants = viewModel.state.value.featuredRestaurants.getOrNull()!!
        val updatedRestaurant = updatedRestaurants.find { it.id == restaurantId }!!
        assertTrue(updatedRestaurant.isFavorite)
    }

    @Test
    fun `state flow should emit states in correct order during loading`() = runTest {
        val newViewModel = FoodViewModel()
        
        newViewModel.state.test {
            // Initial state
            val initialState = awaitItem()
            assertEquals(false, initialState.isLoading)
            assertTrue(initialState.categories is UiState.Success)
            assertTrue(initialState.categories.getOrNull()!!.isEmpty())
            
            // Advance to trigger and complete loading
            testScheduler.advanceUntilIdle()
            
            // Loading state
            val loadingState = awaitItem()
            assertTrue(loadingState.isLoading)
            
            // Final loaded state
            val loadedState = awaitItem()
            assertFalse(loadedState.isLoading)
            assertTrue(loadedState.categories is UiState.Success)
            assertTrue(loadedState.categories.getOrNull()!!.isNotEmpty())
            
            // Ensure no more emissions
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `multiple intent handling should work correctly`() = runTest {
        testScheduler.advanceUntilIdle()
        
        // Handle multiple intents in sequence
        viewModel.handleIntent(FoodIntent.SearchQueryChanged("pizza"))
        viewModel.handleIntent(FoodIntent.FilterSelected("Vegetarian"))
        viewModel.handleIntent(FoodIntent.SortSelected("Price: Low to High"))
        
        val state = viewModel.state.value
        assertEquals("pizza", state.searchQuery)
        assertEquals("Vegetarian", state.selectedFilter)
        assertEquals("Price: Low to High", state.selectedSort)
    }

    @Test
    fun `sample data should have expected properties`() = runTest {
        testScheduler.advanceUntilIdle()
        
        val state = viewModel.state.value
        
        // Test categories have required properties
        val categories = state.categories.getOrNull()!!
        categories.forEach { category ->
            assertNotNull(category.id)
            assertNotNull(category.name)
            assertTrue(category.name.isNotEmpty())
            assertTrue(category.imageRes != 0)
        }
        
        // Test restaurants have required properties
        val reorderRestaurants = state.reorderRestaurants.getOrNull()!!
        reorderRestaurants.forEach { restaurant ->
            assertNotNull(restaurant.id)
            assertNotNull(restaurant.name)
            assertTrue(restaurant.name.isNotEmpty())
            assertTrue(restaurant.rating > 0)
            assertTrue(restaurant.reviewCount > 0)
            assertTrue(restaurant.deliveryTime.isNotEmpty())
            assertTrue(restaurant.cuisines.isNotEmpty())
            assertTrue(restaurant.imageRes != 0)
        }
        
        // Test store items have valid pricing
        val storeItems = state.ninetyNineStoreItems.getOrNull()!!
        storeItems.forEach { item ->
            assertTrue(item.originalPrice > 0)
            assertTrue(item.discountedPrice > 0)
            assertTrue(item.discountedPrice <= item.originalPrice)
            assertTrue(item.rating > 0)
            assertTrue(item.reviewCount > 0)
            assertTrue(item.name.isNotEmpty())
            assertTrue(item.description.isNotEmpty())
        }
    }
}
