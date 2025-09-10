package com.example.food

import com.example.swiggyy.bottomNavBar.food.state.FoodState
import com.example.swiggyy.bottomNavBar.food.state.UiState
import com.example.swiggyy.feature_food.model.Category
import com.example.swiggyy.feature_food.viewmodel.FoodViewModel
import com.example.swiggyy.bottomNavBar.food.state.FoodIntent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FoodViewModelTest {

    @Test
    fun init_loadsInitialData_success() = runTest {
        val vm = FoodViewModel()
        // After init, sample data should be loaded
        val state = vm.state.value
        assertTrue(state.categories is UiState.Success)
        assertTrue(state.reorderRestaurants is UiState.Success)
        assertTrue(state.quickDeliveryRestaurants is UiState.Success)
        assertTrue(state.featuredRestaurants is UiState.Success)
        assertTrue(state.ninetyNineStoreItems is UiState.Success)
        assertTrue(state.swiggyOptions is UiState.Success)
        assertEquals(false, state.isLoading)
    }

    @Test
    fun searchQueryChanged_updatesState() = runTest {
        val vm = FoodViewModel()
        vm.handleIntent(FoodIntent.SearchQueryChanged("pizza"))
        assertEquals("pizza", vm.state.value.searchQuery)
    }

    @Test
    fun categoryClicked_setsQueryToCategoryName() = runTest {
        val vm = FoodViewModel()
        val category = Category(id = "1", name = "Burgers", imageRes = 0)
        vm.handleIntent(FoodIntent.CategoryClicked(category))
        assertEquals("Burgers", vm.state.value.searchQuery)
    }

    @Test
    fun filterAndSort_updateSelections() = runTest {
        val vm = FoodViewModel()
        vm.handleIntent(FoodIntent.FilterSelected("Veg Only"))
        vm.handleIntent(FoodIntent.SortSelected("Rating"))
        assertEquals("Veg Only", vm.state.value.selectedFilter)
        assertEquals("Rating", vm.state.value.selectedSort)
    }

    @Test
    fun toggleFavorite_updatesFeaturedRestaurants() = runTest {
        val vm = FoodViewModel()
        val initial = vm.state.value
        val restaurants = initial.featuredRestaurants.getOrNull().orEmpty()
        // Use first restaurant id if present; otherwise short-circuit
        assertTrue(restaurants.isNotEmpty())
        val target = restaurants.first()
        val targetId = target.id

        vm.handleIntent(FoodIntent.ToggleFavorite(targetId, isFavorite = !target.isFavorite))

        val updated = vm.state.value.featuredRestaurants.getOrNull().orEmpty()
        val updatedTarget = updated.first { it.id == targetId }
        assertEquals(!target.isFavorite, updatedTarget.isFavorite)
    }

    @Test
    fun retry_reloadsData() = runTest {
        val vm = FoodViewModel()
        // Simulate retry; it should reload sample data without errors
        vm.handleIntent(FoodIntent.Retry)
        val state = vm.state.value
        assertTrue(state.categories is UiState.Success)
        assertEquals(null, state.error)
    }

    @Test
    fun foodState_helpers_work() {
        val base = FoodState()
        // Loading flag
        val loading = base.copy(isLoading = true)
        assertTrue(loading.isLoadingAny)

        // Error detection
        val withError = base.copy(categories = UiState.Error("boom"))
        assertTrue(withError.hasError)
        assertEquals("boom", withError.getFirstErrorMessage())
    }
}


