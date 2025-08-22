package com.example.swiggyy.feature.food.state

/**
 * Base sealed class representing the state of a UI component.
 */
sealed interface UiState<out T> {
    /**
     * Represents a loading state where data is being fetched.
     */
    object Loading : UiState<Nothing>

    /**
     * Represents a success state with the data.
     * @property data The data to be displayed
     */
    data class Success<T>(val data: T) : UiState<T>

    /**
     * Represents an error state with an error message.
     * @property message The error message to be displayed
     * @property throwable Optional throwable that caused the error
     */
    data class Error(val message: String, val throwable: Throwable? = null) : UiState<Nothing>

    /**
     * Represents an empty state when there's no data to display.
     */
    object Empty : UiState<Nothing>

    /**
     * Returns the data if the state is [Success], or null otherwise.
     */
    fun getOrNull(): T? = (this as? Success)?.data

    /**
     * Returns true if the state is [Success] and the data matches the predicate.
     */
    fun any(predicate: (T) -> Boolean): Boolean {
        return (this as? Success)?.let { predicate(it.data) } ?: false
    }
}