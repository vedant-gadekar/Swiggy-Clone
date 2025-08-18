package com.example.swiggyy.shared.pagination

/**
 * Represents the state of pagination for a list of items.
 *
 * @param T The type of items being paginated
 * @property items The current list of items
 * @property currentPage The current page number (1-based)
 * @property pageSize The number of items per page
 * @property hasReachedEnd Whether we've reached the end of the list
 * @property isLoading Whether a page is currently being loaded
 */
data class PaginationState<T>(
    val items: List<T> = emptyList(),
    val currentPage: Int = 0,
    val pageSize: Int = 10,
    val hasReachedEnd: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
) {
    /**
     * Returns true if this is the first page and there are no items.
     */
    val isFirstPageEmpty: Boolean
        get() = currentPage == 0 && items.isEmpty()

    /**
     * Returns the next page number to load.
     */
    val nextPage: Int
        get() = currentPage + 1

    /**
     * Returns a new [PaginationState] with the given items appended to the current list.
     */
    fun appendItems(newItems: List<T>): PaginationState<T> {
        return copy(
            items = items + newItems,
            currentPage = nextPage,
            hasReachedEnd = newItems.size < pageSize,
            isLoading = false,
            error = null
        )
    }

    /**
     * Returns a new [PaginationState] with the loading state set to true.
     */
    fun toLoadingState(): PaginationState<T> {
        return copy(isLoading = true, error = null)
    }

    /**
     * Returns a new [PaginationState] with the error state set.
     */
    fun toErrorState(error: Throwable): PaginationState<T> {
        return copy(
            isLoading = false,
            error = error.message ?: "An error occurred"
        )
    }

    companion object {
        /**
         * Creates a new [PaginationState] with default values.
         */
        fun <T> initial(
            pageSize: Int = 10
        ): PaginationState<T> {
            return PaginationState(
                pageSize = pageSize
            )
        }
    }
}

/**
 * Interface for paginated data sources.
 */
interface PaginatedDataLoader<T> {
    /**
     * Loads a page of data.
     *
     * @param page The page number to load (1-based)
     * @param pageSize The number of items to load per page
     * @return The list of items for the requested page
     */
    suspend fun loadPage(page: Int, pageSize: Int): List<T>
}

/**
 * Handles pagination for a list of items.
 *
 * @param T The type of items being paginated
 * @property initialPageSize The initial number of items to load per page
 * @property dataLoader The data loader to use for loading pages
 */
class PaginationManager<T>(
    private val initialPageSize: Int = 10,
    private val dataLoader: PaginatedDataLoader<T>
) {
    private var state: PaginationState<T> = PaginationState.initial(initialPageSize)
    private var isLoading = false

    /**
     * The current pagination state.
     */
    val currentState: PaginationState<T>
        get() = state

    /**
     * Loads the next page of items.
     *
     * @param onSuccess Callback called when the page is loaded successfully
     * @param onError Callback called when an error occurs
     */
    suspend fun loadNextPage(
        onSuccess: (PaginationState<T>) -> Unit = {},
        onError: (Throwable) -> Unit = {}
    ) {
        if (isLoading || state.hasReachedEnd) return

        isLoading = true
        state = state.toLoadingState()
        
        try {
            val items = dataLoader.loadPage(state.nextPage, state.pageSize)
            state = state.appendItems(items)
            onSuccess(state)
        } catch (e: Exception) {
            state = state.toErrorState(e)
            onError(e)
        } finally {
            isLoading = false
        }
    }

    /**
     * Refreshes the data, resetting to the first page.
     *
     * @param onSuccess Callback called when the refresh is complete
     * @param onError Callback called when an error occurs
     */
    suspend fun refresh(
        onSuccess: (PaginationState<T>) -> Unit = {},
        onError: (Throwable) -> Unit = {}
    ) {
        state = PaginationState.initial(initialPageSize)
        loadNextPage(onSuccess, onError)
    }
}
