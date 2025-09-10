package com.example.food.state

import com.example.swiggyy.bottomNavBar.food.state.FoodState
import com.example.swiggyy.bottomNavBar.food.state.UiState
import com.example.swiggyy.feature_food.model.Category
import com.example.swiggyy.feature_food.model.Restaurant
import com.example.swiggyy.feature_food.model.StoreItem
import com.example.swiggyy.feature_food.model.SwiggyOption
import org.junit.Assert.*
import org.junit.Test

class FoodStateTest {

    private val sampleCategory = Category("1", "Pizza", 0)
    private val sampleRestaurant = Restaurant(
        id = "1",
        name = "Test Restaurant",
        imageRes = 0,
        rating = 4.5f,
        reviewCount = 100,
        deliveryTime = "30 mins",
        cuisines = listOf("Italian")
    )
    private val sampleStoreItem = StoreItem(
        id = "1",
        name = "Test Item",
        imageRes = 0,
        originalPrice = 100,
        discountedPrice = 80,
        rating = 4.0f,
        reviewCount = 50,
        description = "Test description"
    )
    private val sampleSwiggyOption = SwiggyOption("1", "Test Option", 0)

    @Test
    fun `isLoadingAny should return false when no data is loading`() {
        val state = FoodState(
            isLoading = false,
            categories = UiState.Success(listOf(sampleCategory)),
            reorderRestaurants = UiState.Success(listOf(sampleRestaurant)),
            quickDeliveryRestaurants = UiState.Success(listOf(sampleRestaurant)),
            featuredRestaurants = UiState.Success(listOf(sampleRestaurant)),
            ninetyNineStoreItems = UiState.Success(listOf(sampleStoreItem)),
            swiggyOptions = UiState.Success(listOf(sampleSwiggyOption))
        )

        assertFalse(state.isLoadingAny)
    }

    @Test
    fun `isLoadingAny should return true when main isLoading is true`() {
        val state = FoodState(
            isLoading = true,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertTrue(state.isLoadingAny)
    }

    @Test
    fun `isLoadingAny should return true when categories is loading`() {
        val state = FoodState(
            isLoading = false,
            categories = UiState.Loading,
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertTrue(state.isLoadingAny)
    }

    @Test
    fun `isLoadingAny should return true when reorderRestaurants is loading`() {
        val state = FoodState(
            isLoading = false,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Loading,
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertTrue(state.isLoadingAny)
    }

    @Test
    fun `isLoadingAny should return true when quickDeliveryRestaurants is loading`() {
        val state = FoodState(
            isLoading = false,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Loading,
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertTrue(state.isLoadingAny)
    }

    @Test
    fun `isLoadingAny should return true when featuredRestaurants is loading`() {
        val state = FoodState(
            isLoading = false,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Loading,
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertTrue(state.isLoadingAny)
    }

    @Test
    fun `isLoadingAny should return true when ninetyNineStoreItems is loading`() {
        val state = FoodState(
            isLoading = false,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Loading,
            swiggyOptions = UiState.Success(emptyList())
        )

        assertTrue(state.isLoadingAny)
    }

    @Test
    fun `isLoadingAny should return true when swiggyOptions is loading`() {
        val state = FoodState(
            isLoading = false,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Loading
        )

        assertTrue(state.isLoadingAny)
    }

    @Test
    fun `isLoadingAny should return true when multiple items are loading`() {
        val state = FoodState(
            isLoading = true,
            categories = UiState.Loading,
            reorderRestaurants = UiState.Loading,
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertTrue(state.isLoadingAny)
    }

    @Test
    fun `hasError should return false when no errors exist`() {
        val state = FoodState(
            error = null,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertFalse(state.hasError)
    }

    @Test
    fun `hasError should return true when top-level error exists`() {
        val state = FoodState(
            error = "Network error",
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertTrue(state.hasError)
    }

    @Test
    fun `hasError should return true when categories has error`() {
        val state = FoodState(
            error = null,
            categories = UiState.Error("Categories error"),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertTrue(state.hasError)
    }

    @Test
    fun `hasError should return true when reorderRestaurants has error`() {
        val state = FoodState(
            error = null,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Error("Reorder error"),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertTrue(state.hasError)
    }

    @Test
    fun `hasError should return true when quickDeliveryRestaurants has error`() {
        val state = FoodState(
            error = null,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Error("Quick delivery error"),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertTrue(state.hasError)
    }

    @Test
    fun `hasError should return true when featuredRestaurants has error`() {
        val state = FoodState(
            error = null,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Error("Featured restaurants error"),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertTrue(state.hasError)
    }

    @Test
    fun `hasError should return true when ninetyNineStoreItems has error`() {
        val state = FoodState(
            error = null,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Error("Store items error"),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertTrue(state.hasError)
    }

    @Test
    fun `hasError should return true when swiggyOptions has error`() {
        val state = FoodState(
            error = null,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Error("Swiggy options error")
        )

        assertTrue(state.hasError)
    }

    @Test
    fun `hasError should return true when multiple errors exist`() {
        val state = FoodState(
            error = "Main error",
            categories = UiState.Error("Categories error"),
            reorderRestaurants = UiState.Error("Reorder error"),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertTrue(state.hasError)
    }

    @Test
    fun `getFirstErrorMessage should return null when no errors exist`() {
        val state = FoodState(
            error = null,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertNull(state.getFirstErrorMessage())
    }

    @Test
    fun `getFirstErrorMessage should return top-level error first`() {
        val topLevelError = "Network connection lost"
        val state = FoodState(
            error = topLevelError,
            categories = UiState.Error("Categories error"),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertEquals(topLevelError, state.getFirstErrorMessage())
    }

    @Test
    fun `getFirstErrorMessage should return categories error when no top-level error`() {
        val categoriesError = "Failed to load categories"
        val state = FoodState(
            error = null,
            categories = UiState.Error(categoriesError),
            reorderRestaurants = UiState.Error("Reorder error"),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertEquals(categoriesError, state.getFirstErrorMessage())
    }

    @Test
    fun `getFirstErrorMessage should return reorderRestaurants error when categories is success`() {
        val reorderError = "Failed to load reorder restaurants"
        val state = FoodState(
            error = null,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Error(reorderError),
            quickDeliveryRestaurants = UiState.Error("Quick delivery error"),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertEquals(reorderError, state.getFirstErrorMessage())
    }

    @Test
    fun `getFirstErrorMessage should return quickDeliveryRestaurants error when previous states are success`() {
        val quickDeliveryError = "Failed to load quick delivery restaurants"
        val state = FoodState(
            error = null,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Error(quickDeliveryError),
            featuredRestaurants = UiState.Error("Featured error"),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertEquals(quickDeliveryError, state.getFirstErrorMessage())
    }

    @Test
    fun `getFirstErrorMessage should return featuredRestaurants error when previous states are success`() {
        val featuredError = "Failed to load featured restaurants"
        val state = FoodState(
            error = null,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Error(featuredError),
            ninetyNineStoreItems = UiState.Error("Store items error"),
            swiggyOptions = UiState.Success(emptyList())
        )

        assertEquals(featuredError, state.getFirstErrorMessage())
    }

    @Test
    fun `getFirstErrorMessage should return ninetyNineStoreItems error when previous states are success`() {
        val storeItemsError = "Failed to load store items"
        val state = FoodState(
            error = null,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Error(storeItemsError),
            swiggyOptions = UiState.Error("Swiggy options error")
        )

        assertEquals(storeItemsError, state.getFirstErrorMessage())
    }

    @Test
    fun `getFirstErrorMessage should return swiggyOptions error when all previous states are success`() {
        val swiggyOptionsError = "Failed to load Swiggy options"
        val state = FoodState(
            error = null,
            categories = UiState.Success(emptyList()),
            reorderRestaurants = UiState.Success(emptyList()),
            quickDeliveryRestaurants = UiState.Success(emptyList()),
            featuredRestaurants = UiState.Success(emptyList()),
            ninetyNineStoreItems = UiState.Success(emptyList()),
            swiggyOptions = UiState.Error(swiggyOptionsError)
        )

        assertEquals(swiggyOptionsError, state.getFirstErrorMessage())
    }

    @Test
    fun `default state should have correct initial values`() {
        val state = FoodState()

        assertFalse(state.isLoading)
        assertEquals("Amar Business Zone", state.locationName)
        assertEquals("Baner - Mahalunge Road, Veerbhadra...", state.locationAddress)
        assertEquals("", state.searchQuery)
        assertEquals("Search for 'Cake' 'Sweets'", state.searchHint)
        assertEquals("", state.selectedFilter)
        assertEquals("", state.selectedSort)
        assertNull(state.error)
        
        assertTrue(state.categories is UiState.Success)
        assertTrue(state.reorderRestaurants is UiState.Success)
        assertTrue(state.quickDeliveryRestaurants is UiState.Success)
        assertTrue(state.featuredRestaurants is UiState.Success)
        assertTrue(state.ninetyNineStoreItems is UiState.Success)
        assertTrue(state.swiggyOptions is UiState.Success)
        
        assertFalse(state.isLoadingAny)
        assertFalse(state.hasError)
        assertNull(state.getFirstErrorMessage())
    }

    @Test
    fun `copy functionality should work correctly`() {
        val originalState = FoodState(
            isLoading = true,
            searchQuery = "pizza",
            selectedFilter = "Vegetarian",
            selectedSort = "Rating"
        )

        val copiedState = originalState.copy(
            isLoading = false,
            searchQuery = "burger"
        )

        assertFalse(copiedState.isLoading)
        assertEquals("burger", copiedState.searchQuery)
        assertEquals("Vegetarian", copiedState.selectedFilter) // Should remain unchanged
        assertEquals("Rating", copiedState.selectedSort) // Should remain unchanged
    }

    @Test
    fun `state with mixed loading and error states should handle properties correctly`() {
        val state = FoodState(
            isLoading = false,
            categories = UiState.Loading,
            reorderRestaurants = UiState.Error("Reorder error"),
            quickDeliveryRestaurants = UiState.Success(listOf(sampleRestaurant)),
            featuredRestaurants = UiState.Loading,
            ninetyNineStoreItems = UiState.Success(listOf(sampleStoreItem)),
            swiggyOptions = UiState.Error("Options error")
        )

        assertTrue(state.isLoadingAny) // Because categories and featuredRestaurants are loading
        assertTrue(state.hasError) // Because reorderRestaurants and swiggyOptions have errors
        assertEquals("Reorder error", state.getFirstErrorMessage()) // First error found
    }
}
