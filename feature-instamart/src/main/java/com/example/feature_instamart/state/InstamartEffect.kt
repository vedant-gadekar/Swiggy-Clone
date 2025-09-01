package com.example.feature_instamart.state

sealed class InstamartEffect {
    data class NavigateToCategory(val categoryId: String) : InstamartEffect()
    data class NavigateToProduct(val productId: String) : InstamartEffect()
    data class NavigateToSection(val section: String) : InstamartEffect()
    data class NavigateToPromotion(val sliderId: String) : InstamartEffect()
    data class ShowToast(val message: String) : InstamartEffect()
    object NavigateToCart : InstamartEffect()
}
