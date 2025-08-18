package com.example.swiggyy.shared.accessibility

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import com.example.swiggyy.R
import com.example.swiggyy.feature_food.model.Category
import com.example.swiggyy.feature_food.model.Restaurant
import com.example.swiggyy.feature_food.model.StoreItem
import com.example.swiggyy.feature_food.model.SwiggyOption

/**
 * Utility object for managing accessibility content descriptions.
 */
object AccessibilityUtils {
    
    // Common content descriptions
    const val BACK_BUTTON = "Back"
    const val CLOSE_BUTTON = "Close"
    const val SEARCH_ICON = "Search"
    const val FILTER_ICON = "Filter"
    const val SORT_ICON = "Sort"
    const val FAVORITE_ICON = "Add to favorites"
    const val FAVORITE_SELECTED_ICON = "Remove from favorites"
    
    // Restaurant related
    fun getRestaurantContentDescription(restaurant: Restaurant): String {
        return "${restaurant.name}. Rating: ${restaurant.rating} out of 5. " +
                "${restaurant.reviewCount} reviews. " +
                "Delivery time: ${restaurant.deliveryTime}. " +
                "Cuisines: ${restaurant.cuisines.joinToString(", ")}. " +
                if (restaurant.hasFreeDelivery) "Free delivery available. " else "" +
                if (restaurant.hasOneBenefits) "Swiggy One benefits available. " else "" +
                if (restaurant.isFavorite) "Added to favorites." else ""
    }
    
    // Category related
    fun getCategoryContentDescription(category: Category): String {
        return "${category.name} category"
    }
    
    // Store item related
    fun getStoreItemContentDescription(item: StoreItem): String {
        return "${item.name}. Original price: ₹${item.originalPrice}, " +
                "Discounted price: ₹${item.discountedPrice}. " +
                "Rating: ${item.rating} out of 5. ${item.reviewCount} reviews. " +
                item.description
    }
    
    // Swiggy option related
    fun getSwiggyOptionContentDescription(option: SwiggyOption): String {
        return option.title
    }
    
    // Search bar
    const val SEARCH_BAR_DESCRIPTION = "Search for restaurants or food"
    
    // Location bar
    fun getLocationBarDescription(locationName: String, address: String): String {
        return "Current location: $locationName, $address. Tap to change location"
    }
    
    // Error states
    const val ERROR_ICON = "Error"
    const val RETRY_BUTTON = "Retry"
    
    // Empty states
    const val EMPTY_STATE_ICON = "Empty state"
    
    // Loading states
    const val LOADING_INDICATOR = "Loading content"
}

/**
 * Adds standard button semantics to a composable.
 */
fun SemanticsPropertyReceiver.buttonSemantics(
    label: String,
    onClickLabel: String? = null,
    role: Role = Role.Button
) {
    this.contentDescription = label
    this.role = role
    if (onClickLabel != null) {
        // In a real app, we would use the onClickLabel property
        // when it's available in the SemanticsPropertyReceiver
    }
}

/**
 * Adds standard image semantics to a composable.
 */
fun SemanticsPropertyReceiver.imageSemantics(
    contentDescription: String,
    role: Role = Role.Image
) {
    this.contentDescription = contentDescription
    this.role = role
}

/**
 * Adds standard icon semantics to a composable.
 */
fun SemanticsPropertyReceiver.iconSemantics(
    contentDescription: String,
    role: Role = Role.Image
) = imageSemantics(contentDescription, role)
