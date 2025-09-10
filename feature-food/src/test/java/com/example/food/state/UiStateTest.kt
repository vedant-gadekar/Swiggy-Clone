package com.example.food.state

import com.example.swiggyy.bottomNavBar.food.state.UiState
import com.example.swiggyy.feature_food.model.Restaurant
import org.junit.Assert.*
import org.junit.Test

class UiStateTest {

    private val sampleRestaurant = Restaurant(
        id = "1",
        name = "Test Restaurant",
        imageRes = 0,
        rating = 4.5f,
        reviewCount = 100,
        deliveryTime = "30 mins",
        cuisines = listOf("Italian")
    )

    private val sampleRestaurantList = listOf(
        sampleRestaurant,
        Restaurant(
            id = "2",
            name = "Another Restaurant",
            imageRes = 0,
            rating = 3.8f,
            reviewCount = 50,
            deliveryTime = "45 mins",
            cuisines = listOf("Chinese")
        )
    )

    @Test
    fun `Loading state should be instance of Loading`() {
        val loadingState: UiState<List<Restaurant>> = UiState.Loading
        
        assertTrue(loadingState is UiState.Loading)
        assertFalse(loadingState is UiState.Success)
        assertFalse(loadingState is UiState.Error)
        assertFalse(loadingState is UiState.Empty)
    }

    @Test
    fun `Success state should contain data`() {
        val successState = UiState.Success(sampleRestaurantList)
        
        assertTrue(successState is UiState.Success)
        assertFalse(successState is UiState.Loading)
        assertFalse(successState is UiState.Error)
        assertFalse(successState is UiState.Empty)
        
        assertEquals(sampleRestaurantList, successState.data)
        assertEquals(2, successState.data.size)
    }

    @Test
    fun `Error state should contain message and optional throwable`() {
        val errorMessage = "Network error occurred"
        val throwable = RuntimeException("Connection failed")
        
        val errorStateWithThrowable = UiState.Error(errorMessage, throwable)
        val errorStateWithoutThrowable = UiState.Error(errorMessage)
        
        assertTrue(errorStateWithThrowable is UiState.Error)
        assertEquals(errorMessage, errorStateWithThrowable.message)
        assertEquals(throwable, errorStateWithThrowable.throwable)
        
        assertTrue(errorStateWithoutThrowable is UiState.Error)
        assertEquals(errorMessage, errorStateWithoutThrowable.message)
        assertNull(errorStateWithoutThrowable.throwable)
    }

    @Test
    fun `Empty state should be instance of Empty`() {
        val emptyState: UiState<List<Restaurant>> = UiState.Empty
        
        assertTrue(emptyState is UiState.Empty)
        assertFalse(emptyState is UiState.Loading)
        assertFalse(emptyState is UiState.Success)
        assertFalse(emptyState is UiState.Error)
    }

    @Test
    fun `getOrNull should return data for Success state`() {
        val successState = UiState.Success(sampleRestaurantList)
        
        val result = successState.getOrNull()
        
        assertNotNull(result)
        assertEquals(sampleRestaurantList, result)
        assertEquals(2, result!!.size)
    }

    @Test
    fun `getOrNull should return null for Loading state`() {
        val loadingState: UiState<List<Restaurant>> = UiState.Loading
        
        val result = loadingState.getOrNull()
        
        assertNull(result)
    }

    @Test
    fun `getOrNull should return null for Error state`() {
        val errorState: UiState<List<Restaurant>> = UiState.Error("Error message")
        
        val result = errorState.getOrNull()
        
        assertNull(result)
    }

    @Test
    fun `getOrNull should return null for Empty state`() {
        val emptyState: UiState<List<Restaurant>> = UiState.Empty
        
        val result = emptyState.getOrNull()
        
        assertNull(result)
    }

    @Test
    fun `getOrNull should work with different data types`() {
        val stringSuccessState = UiState.Success("test string")
        val intSuccessState = UiState.Success(42)
        val booleanSuccessState = UiState.Success(true)
        
        assertEquals("test string", stringSuccessState.getOrNull())
        assertEquals(42, intSuccessState.getOrNull())
        assertEquals(true, booleanSuccessState.getOrNull())
    }

    @Test
    fun `any should return true when Success state data matches predicate`() {
        val successState = UiState.Success(sampleRestaurantList)
        
        val hasHighRatedRestaurant = successState.any { restaurants ->
            restaurants.any { it.rating >= 4.0f }
        }
        
        assertTrue(hasHighRatedRestaurant)
    }

    @Test
    fun `any should return false when Success state data doesn't match predicate`() {
        val successState = UiState.Success(sampleRestaurantList)
        
        val hasVeryHighRatedRestaurant = successState.any { restaurants ->
            restaurants.any { it.rating >= 5.0f }
        }
        
        assertFalse(hasVeryHighRatedRestaurant)
    }

    @Test
    fun `any should return false for Loading state`() {
        val loadingState: UiState<List<Restaurant>> = UiState.Loading
        
        val result = loadingState.any { restaurants ->
            restaurants.any { it.rating >= 4.0f }
        }
        
        assertFalse(result)
    }

    @Test
    fun `any should return false for Error state`() {
        val errorState: UiState<List<Restaurant>> = UiState.Error("Error message")
        
        val result = errorState.any { restaurants ->
            restaurants.any { it.rating >= 4.0f }
        }
        
        assertFalse(result)
    }

    @Test
    fun `any should return false for Empty state`() {
        val emptyState: UiState<List<Restaurant>> = UiState.Empty
        
        val result = emptyState.any { restaurants ->
            restaurants.any { it.rating >= 4.0f }
        }
        
        assertFalse(result)
    }

    @Test
    fun `any should work with empty list in Success state`() {
        val emptyListSuccessState = UiState.Success(emptyList<Restaurant>())
        
        val result = emptyListSuccessState.any { restaurants ->
            restaurants.isNotEmpty()
        }
        
        assertFalse(result)
    }

    @Test
    fun `any should work with complex predicates`() {
        val successState = UiState.Success(sampleRestaurantList)
        
        // Test multiple conditions
        val hasItalianRestaurantWithGoodRating = successState.any { restaurants ->
            restaurants.any { restaurant ->
                restaurant.cuisines.contains("Italian") && restaurant.rating >= 4.0f
            }
        }
        
        val hasFastDelivery = successState.any { restaurants ->
            restaurants.any { restaurant ->
                val deliveryTime = restaurant.deliveryTime.replace(" mins", "").toIntOrNull() ?: 0
                deliveryTime <= 30
            }
        }
        
        assertTrue(hasItalianRestaurantWithGoodRating)
        assertTrue(hasFastDelivery)
    }

    @Test
    fun `any should work with single item lists`() {
        val singleItemState = UiState.Success(listOf(sampleRestaurant))
        
        val hasTestRestaurant = singleItemState.any { restaurants ->
            restaurants.any { it.name == "Test Restaurant" }
        }
        
        val hasWrongRestaurant = singleItemState.any { restaurants ->
            restaurants.any { it.name == "Wrong Restaurant" }
        }
        
        assertTrue(hasTestRestaurant)
        assertFalse(hasWrongRestaurant)
    }

    @Test
    fun `any should work with non-collection data types`() {
        val stringState = UiState.Success("hello world")
        val intState = UiState.Success(42)
        
        val stringContainsHello = stringState.any { it.contains("hello") }
        val stringContainsGoodbye = stringState.any { it.contains("goodbye") }
        val intIsEven = intState.any { it % 2 == 0 }
        val intIsNegative = intState.any { it < 0 }
        
        assertTrue(stringContainsHello)
        assertFalse(stringContainsGoodbye)
        assertTrue(intIsEven)
        assertFalse(intIsNegative)
    }

    @Test
    fun `Success state with null data should handle operations correctly`() {
        val nullDataState = UiState.Success<String?>(null)
        
        val result = nullDataState.getOrNull()
        val anyResult = nullDataState.any { it?.isNotEmpty() == true }
        
        assertNull(result)
        assertFalse(anyResult)
    }

    @Test
    fun `Error state with empty message should still be valid`() {
        val errorWithEmptyMessage = UiState.Error("")
        
        assertTrue(errorWithEmptyMessage is UiState.Error)
        assertEquals("", errorWithEmptyMessage.message)
        assertNull(errorWithEmptyMessage.throwable)
        assertNull(errorWithEmptyMessage.getOrNull())
    }

    @Test
    fun `Success state equality should work correctly`() {
        val state1 = UiState.Success(sampleRestaurantList)
        val state2 = UiState.Success(sampleRestaurantList)
        val state3 = UiState.Success(emptyList<Restaurant>())
        
        assertEquals(state1, state2)
        assertNotEquals(state1, state3)
    }

    @Test
    fun `Error state equality should work correctly`() {
        val throwable = RuntimeException("test")
        val error1 = UiState.Error("message", throwable)
        val error2 = UiState.Error("message", throwable)
        val error3 = UiState.Error("different message", throwable)
        val error4 = UiState.Error("message", RuntimeException("different"))
        val error5 = UiState.Error("message")
        
        assertEquals(error1, error2)
        assertNotEquals(error1, error3)
        assertNotEquals(error1, error4)
        assertNotEquals(error1, error5)
    }

    @Test
    fun `singleton states should be equal`() {
        val loading1: UiState<String> = UiState.Loading
        val loading2: UiState<Int> = UiState.Loading
        val empty1: UiState<String> = UiState.Empty
        val empty2: UiState<Int> = UiState.Empty
        
        assertEquals(loading1, loading2)
        assertEquals(empty1, empty2)
        assertNotEquals(loading1, empty1)
    }

    @Test
    fun `type safety should be maintained across different generic types`() {
        val stringState: UiState<String> = UiState.Success("test")
        val intState: UiState<Int> = UiState.Success(42)
        val listState: UiState<List<Restaurant>> = UiState.Success(sampleRestaurantList)
        
        // These should compile and work correctly with type safety
        val stringValue: String? = stringState.getOrNull()
        val intValue: Int? = intState.getOrNull()
        val listValue: List<Restaurant>? = listState.getOrNull()
        
        assertEquals("test", stringValue)
        assertEquals(42, intValue)
        assertEquals(sampleRestaurantList, listValue)
    }
}
