package com.example.instamart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.ui.graphics.Color
import com.example.instamart.state.*
import com.example.swiggyy.R
import com.example.swiggyy.feature.instamart.state.InstamartEffect
import com.example.swiggyy.feature.instamart.state.InstamartEvent
import com.example.swiggyy.feature.instamart.utils.InstamartConstants
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
            _effect.emit(InstamartEffect.ShowToast(InstamartConstants.Strings.AddedToCart))
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

    // Updated mock data loading functions using InstamartConstants
    private fun loadCategories(): List<CategoryData> {
        return listOf(
            CategoryData(
                InstamartConstants.CategoryIds.Fruits,
                InstamartConstants.Strings.CategoryFruits,
                InstamartConstants.ImageUrls.CategoryFruitsImage
            ),
            CategoryData(
                InstamartConstants.CategoryIds.MilkEgg,
                InstamartConstants.Strings.CategoryMilkEgg,
                InstamartConstants.ImageUrls.CategoryMilkEggImage
            ),
            CategoryData(
                InstamartConstants.CategoryIds.Beverages,
                InstamartConstants.Strings.CategoryBeverages,
                InstamartConstants.ImageUrls.CategoryBeveragesImage
            ),
            CategoryData(
                InstamartConstants.CategoryIds.Laundry,
                InstamartConstants.Strings.CategoryLaundry,
                InstamartConstants.ImageUrls.CategoryLaundryImage
            ),
            CategoryData(
                InstamartConstants.CategoryIds.Vegetables,
                InstamartConstants.Strings.CategoryVegetables,
                InstamartConstants.ImageUrls.CategoryVegetablesImage
            )
        )
    }

    private fun loadFruits(): List<ProductData> {
        return listOf(
            ProductData(
                InstamartConstants.ProductIds.Banana,
                InstamartConstants.Strings.ProductBanana,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price399,
                InstamartConstants.ImageUrls.BananaImage,
                InstamartConstants.Categories.FRUITS
            ),
            ProductData(
                InstamartConstants.ProductIds.Pepper,
                InstamartConstants.Strings.ProductPepper,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price299,
                InstamartConstants.ImageUrls.PepperImage,
                InstamartConstants.Categories.FRUITS
            ),
            ProductData(
                InstamartConstants.ProductIds.Orange,
                InstamartConstants.Strings.ProductOrange,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price399,
                InstamartConstants.ImageUrls.BananaImage,
                InstamartConstants.Categories.FRUITS
            ),
            ProductData(
                InstamartConstants.ProductIds.Strawberry,
                InstamartConstants.Strings.ProductStrawberry,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price199,
                InstamartConstants.ImageUrls.BananaImage,
                InstamartConstants.Categories.FRUITS
            ),
            ProductData(
                InstamartConstants.ProductIds.Lemon,
                InstamartConstants.Strings.ProductLemon,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price199,
                InstamartConstants.ImageUrls.BananaImage,
                InstamartConstants.Categories.FRUITS
            ),
            ProductData(
                InstamartConstants.ProductIds.WaterLemon,
                InstamartConstants.Strings.ProductWaterLemon,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price199,
                InstamartConstants.ImageUrls.WaterLemonImage,
                InstamartConstants.Categories.FRUITS
            ),
            ProductData(
                InstamartConstants.ProductIds.Apple,
                InstamartConstants.Strings.ProductApple,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price199,
                InstamartConstants.ImageUrls.BananaImage,
                InstamartConstants.Categories.FRUITS
            ),
            ProductData(
                InstamartConstants.ProductIds.Mango,
                InstamartConstants.Strings.ProductMango,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price499,
                InstamartConstants.ImageUrls.BananaImage,
                InstamartConstants.Categories.FRUITS
            ),
            ProductData(
                InstamartConstants.ProductIds.Grapes,
                InstamartConstants.Strings.ProductGrapes,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price345,
                InstamartConstants.ImageUrls.BananaImage,
                InstamartConstants.Categories.FRUITS
            )
        )
    }

    private fun loadDetergents(): List<ProductData> {
        return listOf(
            ProductData(
                InstamartConstants.ProductIds.Purex1,
                InstamartConstants.Strings.ProductPurex,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price529,
                InstamartConstants.ImageUrls.PurexImage,
                InstamartConstants.Categories.DETERGENT
            ),
            ProductData(
                InstamartConstants.ProductIds.Varnish,
                InstamartConstants.Strings.ProductVarnish,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price529,
                InstamartConstants.ImageUrls.VarnishImage,
                InstamartConstants.Categories.DETERGENT
            ),
            ProductData(
                InstamartConstants.ProductIds.Harpic1,
                InstamartConstants.Strings.ProductHarpic,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price529,
                InstamartConstants.ImageUrls.HarpicImage,
                InstamartConstants.Categories.DETERGENT
            ),
            ProductData(
                InstamartConstants.ProductIds.Harpic2,
                InstamartConstants.Strings.ProductHarpic,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price529,
                InstamartConstants.ImageUrls.HarpicImage,
                InstamartConstants.Categories.DETERGENT
            ),
            ProductData(
                InstamartConstants.ProductIds.Purex2,
                InstamartConstants.Strings.ProductPurex,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price345,
                InstamartConstants.ImageUrls.PurexImage,
                InstamartConstants.Categories.DETERGENT
            ),
            ProductData(
                InstamartConstants.ProductIds.Dettol,
                InstamartConstants.Strings.ProductDettol,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price229,
                InstamartConstants.ImageUrls.PurexImage,
                InstamartConstants.Categories.DETERGENT
            )
        )
    }

    private fun loadBiscuits(): List<ProductData> {
        return listOf(
            ProductData(
                InstamartConstants.ProductIds.Loacker1,
                InstamartConstants.Strings.ProductLoacker,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price225,
                InstamartConstants.ImageUrls.LoackerImage1,
                InstamartConstants.Categories.BISCUIT
            ),
            ProductData(
                InstamartConstants.ProductIds.Loacker2,
                InstamartConstants.Strings.ProductLoacker,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price225,
                InstamartConstants.ImageUrls.LoackerImage2,
                InstamartConstants.Categories.BISCUIT
            ),
            ProductData(
                InstamartConstants.ProductIds.Biscoff,
                InstamartConstants.Strings.ProductBiscoff,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price1025,
                InstamartConstants.ImageUrls.BiscoffImage,
                InstamartConstants.Categories.BISCUIT
            ),
            ProductData(
                InstamartConstants.ProductIds.TUC1,
                InstamartConstants.Strings.ProductTUC,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price299,
                InstamartConstants.ImageUrls.BiscoffImage,
                InstamartConstants.Categories.BISCUIT
            ),
            ProductData(
                InstamartConstants.ProductIds.TUC2,
                InstamartConstants.Strings.ProductTUC,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price399,
                InstamartConstants.ImageUrls.BiscoffImage,
                InstamartConstants.Categories.BISCUIT
            ),
            ProductData(
                InstamartConstants.ProductIds.Cherries,
                InstamartConstants.Strings.ProductCherries,
                InstamartConstants.Strings.DefaultRating,
                InstamartConstants.Prices.Price199,
                InstamartConstants.ImageUrls.BiscoffImage,
                InstamartConstants.Categories.BISCUIT
            )
        )
    }

    private fun loadPromotionalSliders(): List<SliderData> {
        return listOf(
            SliderData(
                id = InstamartConstants.SliderIds.Promo30,
                title = InstamartConstants.Strings.Offer30Title,
                subtitle = InstamartConstants.Strings.Offer30Subtitle,
                buttonText = InstamartConstants.Strings.ShopNow,
                backgroundColor = InstamartConstants.Colors.LightGreen,
                textColor = InstamartConstants.Colors.PrimaryGreen,
                buttonBackgroundColor = InstamartConstants.Colors.PrimaryGreen,
                buttonTextColor = InstamartConstants.Colors.White,
                imageUrl = InstamartConstants.ImageUrls.PromoLargeImage,
                isLarge = true
            ),
            SliderData(
                id = InstamartConstants.SliderIds.Promo25,
                title = InstamartConstants.Strings.Offer25Title,
                subtitle = InstamartConstants.Strings.Offer25Subtitle,
                buttonText = InstamartConstants.Strings.ShopNow,
                backgroundColor = InstamartConstants.Colors.PrimaryGreen,
                textColor = InstamartConstants.Colors.White,
                buttonBackgroundColor = InstamartConstants.Colors.White,
                buttonTextColor = InstamartConstants.Colors.DarkGray,
                imageUrl = InstamartConstants.ImageUrls.PromoSmallImage,
                isLarge = false
            )
        )
    }
}
