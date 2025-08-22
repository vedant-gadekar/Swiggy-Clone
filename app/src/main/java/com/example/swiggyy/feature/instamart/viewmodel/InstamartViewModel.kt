package com.example.instamart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.ui.graphics.Color
import com.example.instamart.state.*
import com.example.swiggyy.R
import com.example.swiggyy.feature.instamart.state.InstamartEffect
import com.example.swiggyy.feature.instamart.state.InstamartEvent
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class InstamartViewModel : ViewModel() {

    private val _state = MutableStateFlow(InstamartState())
    val state: StateFlow<InstamartState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<InstamartEffect>()
    val effect: SharedFlow<InstamartEffect> = _effect.asSharedFlow()

    init {
        handleEvent(InstamartEvent.LoadData)
    }

    fun handleEvent(event: InstamartEvent) {
        when (event) {
            is InstamartEvent.LoadData -> loadData()
            is InstamartEvent.AddToCart -> addToCart(event.productId)
            is InstamartEvent.RemoveFromCart -> removeFromCart(event.productId)
            is InstamartEvent.UpdateQuantity -> updateQuantity(event.productId, event.quantity)
            is InstamartEvent.CategoryClicked -> onCategoryClicked(event.categoryId)
            is InstamartEvent.ProductClicked -> onProductClicked(event.productId)
            is InstamartEvent.SeeAllClicked -> onSeeAllClicked(event.section)
            is InstamartEvent.PromotionalButtonClicked -> onPromotionalButtonClicked(event.sliderId)
            is InstamartEvent.ClearError -> clearError()
            is InstamartEvent.SearchQueryChanged -> updateSearchQuery(event.query)
        }
    }
    private fun updateSearchQuery(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                val categories = loadCategories()
                val fruits = loadFruits()
                val detergents = loadDetergents()
                val biscuits = loadBiscuits()
                val sliders = loadPromotionalSliders()

                _state.value = _state.value.copy(
                    isLoading = false,
                    categories = categories,
                    fruits = fruits,
                    detergents = detergents,
                    biscuits = biscuits,
                    promotionalSliders = sliders
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    private fun addToCart(productId: String) {
        val currentCart = _state.value.cartItems.toMutableMap()
        val currentQuantity = currentCart[productId] ?: 0
        currentCart[productId] = currentQuantity + 1

        _state.value = _state.value.copy(cartItems = currentCart)

        viewModelScope.launch {
            _effect.emit(InstamartEffect.ShowToast("Added to cart"))
        }
    }

    private fun removeFromCart(productId: String) {
        val currentCart = _state.value.cartItems.toMutableMap()
        val currentQuantity = currentCart[productId] ?: 0

        if (currentQuantity > 1) {
            currentCart[productId] = currentQuantity - 1
        } else {
            currentCart.remove(productId)
        }

        _state.value = _state.value.copy(cartItems = currentCart)
    }

    private fun updateQuantity(productId: String, quantity: Int) {
        val currentCart = _state.value.cartItems.toMutableMap()

        if (quantity > 0) {
            currentCart[productId] = quantity
        } else {
            currentCart.remove(productId)
        }

        _state.value = _state.value.copy(cartItems = currentCart)
    }

    private fun onCategoryClicked(categoryId: String) {
        viewModelScope.launch {
            _effect.emit(InstamartEffect.NavigateToCategory(categoryId))
        }
    }

    private fun onProductClicked(productId: String) {
        viewModelScope.launch {
            _effect.emit(InstamartEffect.NavigateToProduct(productId))
        }
    }

    private fun onSeeAllClicked(section: String) {
        viewModelScope.launch {
            _effect.emit(InstamartEffect.NavigateToSection(section))
        }
    }

    private fun onPromotionalButtonClicked(sliderId: String) {
        viewModelScope.launch {
            _effect.emit(InstamartEffect.NavigateToPromotion(sliderId))
        }
    }

    private fun clearError() {
        _state.value = _state.value.copy(error = null)
    }

    // Updated mock data loading functions
    private fun loadCategories(): List<CategoryData> {
        return listOf(
            CategoryData("1", "Fruits", R.drawable.bananas),
            CategoryData("2", "Milk & egg", R.drawable.dairy_eggs),
            CategoryData("3", "Beverages", R.drawable.beverages),
            CategoryData("4", "Laundry", R.drawable.icon_swiggy),
            CategoryData("5", "Vegetables", R.drawable.icon_swiggy)
        )
    }

    private fun loadFruits(): List<ProductData> {
        return listOf(
            ProductData("f1", "Banana", "4.8 (287)", "$3.99", R.drawable.bananas, "fruits"),
            ProductData("f2", "Pepper", "4.8 (287)", "$2.99", R.drawable.bell_pepper, "fruits"),
            ProductData("f3", "Orange", "4.8 (287)", "$3.99", R.drawable.icon_swiggy, "fruits"),
            ProductData("f4", "Strawberry", "4.8 (287)", "$1.99", R.drawable.icon_swiggy, "fruits"),
            ProductData("f5", "Lemon", "4.8 (287)", "$1.99", R.drawable.icon_swiggy, "fruits"),
            ProductData("f6", "Water lemon", "4.8 (287)", "$1.99", R.drawable.icon_swiggy, "fruits"),
            ProductData("f7", "Apple", "4.8 (287)", "$1.99", R.drawable.icon_swiggy, "fruits"),
            ProductData("f8", "Mango", "4.8 (287)", "$4.99", R.drawable.icon_swiggy, "fruits"),
            ProductData("f9", "Grapes", "4.8 (287)", "$3.45", R.drawable.icon_swiggy, "fruits"),
            ProductData("f10", "Pineapple", "4.8 (287)", "$2.89", R.drawable.icon_swiggy, "fruits")
        )
    }

    private fun loadDetergents(): List<ProductData> {
        return listOf(
            ProductData("d1", "Purex", "4.8 (287)", "$5.29", R.drawable.icon_swiggy, "detergent"),
            ProductData("d2", "Varnish", "4.8 (287)", "$5.29", R.drawable.icon_swiggy, "detergent"),
            ProductData("d3", "Harpic", "4.8 (287)", "$5.29", R.drawable.icon_swiggy, "detergent"),
            ProductData("d4", "Harpic", "4.8 (287)", "$5.29", R.drawable.icon_swiggy, "detergent"),
            ProductData("d5", "Purex", "4.8 (287)", "$3.45", R.drawable.icon_swiggy, "detergent"),
            ProductData("d6", "Dettol", "4.8 (287)", "$2.29", R.drawable.icon_swiggy, "detergent")
        )
    }

    private fun loadBiscuits(): List<ProductData> {
        return listOf(
            ProductData("b1", "Parle", "4.8 (287)", "$2.25", R.drawable.icon_swiggy, "biscuit"),
            ProductData("b2", "Marie", "4.8 (287)", "$2.25", R.drawable.icon_swiggy, "biscuit"),
            ProductData("b3", "Biscoff", "4.8 (287)", "$10.25", R.drawable.icon_swiggy, "biscuit"),
            ProductData("b4", "Hide n Seek", "4.8 (287)", "$2.99", R.drawable.icon_swiggy, "biscuit"),
            ProductData("b5", "TUC", "4.8 (287)", "$3.99", R.drawable.icon_swiggy, "biscuit"),
            ProductData("b6", "Cherries", "4.8 (287)", "$1.99", R.drawable.icon_swiggy, "biscuit")
        )
    }

    private fun loadPromotionalSliders(): List<SliderData> {
        return listOf(
            SliderData(
                id = "s1",
                title = "Up to 30% offer",
                subtitle = "Enjoy our big offer",
                buttonText = "Shop Now",
                backgroundColor = Color(0xFFD7FFD4),
                textColor = Color(0xFF0CA201),
                buttonBackgroundColor = Color(0xFF0CA201),
                buttonTextColor = Color.White,
                imageUrl = R.drawable.icon_swiggy,
                isLarge = true
            ),
            SliderData(
                id = "s2",
                title = "Up to 25% offer",
                subtitle = "On first buyers",
                buttonText = "Shop Now",
                backgroundColor = Color(0xFF0CA201),
                textColor = Color.White,
                buttonBackgroundColor = Color.White,
                buttonTextColor = Color(0xFF0A0B0A),
                imageUrl = R.drawable.icon_swiggy,
                isLarge = false
            )
        )
    }
}
