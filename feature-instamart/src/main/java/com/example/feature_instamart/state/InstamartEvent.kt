package com.example.feature_instamart.state


sealed class InstamartEvent {
    object LoadData : InstamartEvent()
    data class AddToCart(val productId: String) : InstamartEvent()
    data class RemoveFromCart(val productId: String) : InstamartEvent()
    data class UpdateQuantity(val productId: String, val quantity: Int) : InstamartEvent()
    data class CategoryClicked(val categoryId: String) : InstamartEvent()
    data class ProductClicked(val productId: String) : InstamartEvent()
    data class SeeAllClicked(val section: String) : InstamartEvent()
    data class PromotionalButtonClicked(val sliderId: String) : InstamartEvent()
    data class SearchQueryChanged(val query: String) : InstamartEvent()
    object ClearError : InstamartEvent()
}

