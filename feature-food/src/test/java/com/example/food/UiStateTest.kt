package com.example.food

import com.example.swiggyy.bottomNavBar.food.state.UiState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class UiStateTest {

    @Test
    fun getOrNull_returnsDataOnlyOnSuccess() {
        val success = UiState.Success(listOf(1, 2, 3))
        val loading: UiState<List<Int>> = UiState.Loading
        val error: UiState<List<Int>> = UiState.Error("x")
        val empty: UiState<List<Int>> = UiState.Empty

        assertEquals(listOf(1, 2, 3), success.getOrNull())
        assertEquals(null, loading.getOrNull())
        assertEquals(null, error.getOrNull())
        assertEquals(null, empty.getOrNull())
    }

    @Test
    fun any_appliesPredicateOnlyOnSuccess() {
        val success = UiState.Success(listOf(1, 2, 3))
        val loading: UiState<List<Int>> = UiState.Loading

        assertTrue(success.any { it.contains(2) })
        assertFalse(success.any { it.contains(4) })
        assertFalse(loading.any { it.isNotEmpty() })
    }
}


