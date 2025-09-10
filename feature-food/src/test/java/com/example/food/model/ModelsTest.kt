package com.example.food.model

import com.example.swiggyy.feature_food.model.Category
import com.example.swiggyy.feature_food.model.Restaurant
import com.example.swiggyy.feature_food.model.StoreItem
import com.example.swiggyy.feature_food.model.SwiggyOption
import org.junit.Assert.*
import org.junit.Test

class ModelsTest {

    @Test
    fun `Restaurant data class should have correct default values`() {
        val restaurant = Restaurant(
            id = "1",
            name = "Test Restaurant",
            imageRes = 123,
            rating = 4.5f,
            reviewCount = 100,
            deliveryTime = "30 mins",
            cuisines = listOf("Italian", "Pizza")
        )

        assertEquals("1", restaurant.id)
        assertEquals("Test Restaurant", restaurant.name)
        assertEquals(123, restaurant.imageRes)
        assertEquals(4.5f, restaurant.rating, 0.01f)
        assertEquals(100, restaurant.reviewCount)
        assertEquals("30 mins", restaurant.deliveryTime)
        assertEquals(listOf("Italian", "Pizza"), restaurant.cuisines)
        assertEquals("", restaurant.offer) // Default value
        assertFalse(restaurant.isAd) // Default value
        assertFalse(restaurant.hasFreeDelivery) // Default value
        assertFalse(restaurant.hasOneBenefits) // Default value
        assertFalse(restaurant.isFavorite) // Default value
    }

    @Test
    fun `Restaurant data class should handle all parameters correctly`() {
        val restaurant = Restaurant(
            id = "2",
            name = "Premium Restaurant",
            imageRes = 456,
            rating = 4.8f,
            reviewCount = 250,
            deliveryTime = "15-20 mins",
            cuisines = listOf("Indian", "Chinese", "Continental"),
            offer = "50% OFF up to ₹100",
            isAd = true,
            hasFreeDelivery = true,
            hasOneBenefits = true,
            isFavorite = true
        )

        assertEquals("2", restaurant.id)
        assertEquals("Premium Restaurant", restaurant.name)
        assertEquals(456, restaurant.imageRes)
        assertEquals(4.8f, restaurant.rating, 0.01f)
        assertEquals(250, restaurant.reviewCount)
        assertEquals("15-20 mins", restaurant.deliveryTime)
        assertEquals(listOf("Indian", "Chinese", "Continental"), restaurant.cuisines)
        assertEquals("50% OFF up to ₹100", restaurant.offer)
        assertTrue(restaurant.isAd)
        assertTrue(restaurant.hasFreeDelivery)
        assertTrue(restaurant.hasOneBenefits)
        assertTrue(restaurant.isFavorite)
    }

    @Test
    fun `Restaurant copy function should work correctly`() {
        val originalRestaurant = Restaurant(
            id = "1",
            name = "Original",
            imageRes = 123,
            rating = 4.0f,
            reviewCount = 50,
            deliveryTime = "45 mins",
            cuisines = listOf("Fast Food"),
            isFavorite = false
        )

        val copiedRestaurant = originalRestaurant.copy(
            name = "Updated Restaurant",
            isFavorite = true
        )

        // Changed values
        assertEquals("Updated Restaurant", copiedRestaurant.name)
        assertTrue(copiedRestaurant.isFavorite)

        // Unchanged values
        assertEquals("1", copiedRestaurant.id)
        assertEquals(123, copiedRestaurant.imageRes)
        assertEquals(4.0f, copiedRestaurant.rating, 0.01f)
        assertEquals(50, copiedRestaurant.reviewCount)
        assertEquals("45 mins", copiedRestaurant.deliveryTime)
        assertEquals(listOf("Fast Food"), copiedRestaurant.cuisines)
    }

    @Test
    fun `Restaurant equality should work correctly`() {
        val restaurant1 = Restaurant(
            id = "1",
            name = "Test",
            imageRes = 123,
            rating = 4.5f,
            reviewCount = 100,
            deliveryTime = "30 mins",
            cuisines = listOf("Italian")
        )

        val restaurant2 = Restaurant(
            id = "1",
            name = "Test",
            imageRes = 123,
            rating = 4.5f,
            reviewCount = 100,
            deliveryTime = "30 mins",
            cuisines = listOf("Italian")
        )

        val restaurant3 = Restaurant(
            id = "2",
            name = "Test",
            imageRes = 123,
            rating = 4.5f,
            reviewCount = 100,
            deliveryTime = "30 mins",
            cuisines = listOf("Italian")
        )

        assertEquals(restaurant1, restaurant2)
        assertNotEquals(restaurant1, restaurant3)
    }

    @Test
    fun `Restaurant with empty cuisines list should work correctly`() {
        val restaurant = Restaurant(
            id = "1",
            name = "Test",
            imageRes = 123,
            rating = 4.0f,
            reviewCount = 50,
            deliveryTime = "30 mins",
            cuisines = emptyList()
        )

        assertTrue(restaurant.cuisines.isEmpty())
        assertEquals(0, restaurant.cuisines.size)
    }

    @Test
    fun `StoreItem data class should work correctly`() {
        val storeItem = StoreItem(
            id = "item1",
            name = "Organic Bananas",
            imageRes = 789,
            originalPrice = 100,
            discountedPrice = 80,
            rating = 4.2f,
            reviewCount = 150,
            description = "Fresh organic bananas from local farms"
        )

        assertEquals("item1", storeItem.id)
        assertEquals("Organic Bananas", storeItem.name)
        assertEquals(789, storeItem.imageRes)
        assertEquals(100, storeItem.originalPrice)
        assertEquals(80, storeItem.discountedPrice)
        assertEquals(4.2f, storeItem.rating, 0.01f)
        assertEquals(150, storeItem.reviewCount)
        assertEquals("Fresh organic bananas from local farms", storeItem.description)
    }

    @Test
    fun `StoreItem should handle discount scenarios correctly`() {
        // No discount scenario
        val fullPriceItem = StoreItem(
            id = "1",
            name = "Premium Item",
            imageRes = 123,
            originalPrice = 100,
            discountedPrice = 100,
            rating = 4.5f,
            reviewCount = 50,
            description = "Premium quality item"
        )

        assertEquals(fullPriceItem.originalPrice, fullPriceItem.discountedPrice)

        // Heavy discount scenario
        val heavyDiscountItem = StoreItem(
            id = "2",
            name = "Sale Item",
            imageRes = 456,
            originalPrice = 200,
            discountedPrice = 50,
            rating = 4.0f,
            reviewCount = 100,
            description = "Flash sale item"
        )

        assertTrue(heavyDiscountItem.discountedPrice < heavyDiscountItem.originalPrice)
        assertEquals(150, heavyDiscountItem.originalPrice - heavyDiscountItem.discountedPrice)
    }

    @Test
    fun `StoreItem copy function should work correctly`() {
        val originalItem = StoreItem(
            id = "1",
            name = "Original Item",
            imageRes = 123,
            originalPrice = 100,
            discountedPrice = 80,
            rating = 4.0f,
            reviewCount = 50,
            description = "Original description"
        )

        val copiedItem = originalItem.copy(
            discountedPrice = 70,
            rating = 4.5f
        )

        // Changed values
        assertEquals(70, copiedItem.discountedPrice)
        assertEquals(4.5f, copiedItem.rating, 0.01f)

        // Unchanged values
        assertEquals("1", copiedItem.id)
        assertEquals("Original Item", copiedItem.name)
        assertEquals(100, copiedItem.originalPrice)
        assertEquals(50, copiedItem.reviewCount)
        assertEquals("Original description", copiedItem.description)
    }

    @Test
    fun `StoreItem equality should work correctly`() {
        val item1 = StoreItem(
            id = "1",
            name = "Test Item",
            imageRes = 123,
            originalPrice = 100,
            discountedPrice = 80,
            rating = 4.0f,
            reviewCount = 50,
            description = "Test description"
        )

        val item2 = StoreItem(
            id = "1",
            name = "Test Item",
            imageRes = 123,
            originalPrice = 100,
            discountedPrice = 80,
            rating = 4.0f,
            reviewCount = 50,
            description = "Test description"
        )

        val item3 = item1.copy(discountedPrice = 90)

        assertEquals(item1, item2)
        assertNotEquals(item1, item3)
    }

    @Test
    fun `SwiggyOption data class should work correctly`() {
        val option = SwiggyOption(
            id = "opt1",
            title = "Swiggy One",
            imageRes = 999,
            description = "Free delivery on all orders"
        )

        assertEquals("opt1", option.id)
        assertEquals("Swiggy One", option.title)
        assertEquals(999, option.imageRes)
        assertEquals("Free delivery on all orders", option.description)
    }

    @Test
    fun `SwiggyOption with default description should work correctly`() {
        val option = SwiggyOption(
            id = "opt2",
            title = "Dineout",
            imageRes = 888
        )

        assertEquals("opt2", option.id)
        assertEquals("Dineout", option.title)
        assertEquals(888, option.imageRes)
        assertEquals("", option.description) // Default empty string
    }

    @Test
    fun `SwiggyOption copy function should work correctly`() {
        val originalOption = SwiggyOption(
            id = "1",
            title = "Original Title",
            imageRes = 123,
            description = "Original description"
        )

        val copiedOption = originalOption.copy(
            title = "Updated Title",
            description = "Updated description"
        )

        // Changed values
        assertEquals("Updated Title", copiedOption.title)
        assertEquals("Updated description", copiedOption.description)

        // Unchanged values
        assertEquals("1", copiedOption.id)
        assertEquals(123, copiedOption.imageRes)
    }

    @Test
    fun `SwiggyOption equality should work correctly`() {
        val option1 = SwiggyOption("1", "Test", 123, "Description")
        val option2 = SwiggyOption("1", "Test", 123, "Description")
        val option3 = SwiggyOption("2", "Test", 123, "Description")

        assertEquals(option1, option2)
        assertNotEquals(option1, option3)
    }

    @Test
    fun `Category data class should work correctly`() {
        val category = Category(
            id = "cat1",
            name = "Pizza",
            imageRes = 555
        )

        assertEquals("cat1", category.id)
        assertEquals("Pizza", category.name)
        assertEquals(555, category.imageRes)
    }

    @Test
    fun `Category copy function should work correctly`() {
        val originalCategory = Category(
            id = "1",
            name = "Original Category",
            imageRes = 123
        )

        val copiedCategory = originalCategory.copy(
            name = "Updated Category"
        )

        // Changed value
        assertEquals("Updated Category", copiedCategory.name)

        // Unchanged values
        assertEquals("1", copiedCategory.id)
        assertEquals(123, copiedCategory.imageRes)
    }

    @Test
    fun `Category equality should work correctly`() {
        val category1 = Category("1", "Pizza", 123)
        val category2 = Category("1", "Pizza", 123)
        val category3 = Category("1", "Burger", 123)

        assertEquals(category1, category2)
        assertNotEquals(category1, category3)
    }

    @Test
    fun `All models should handle empty strings correctly`() {
        val restaurant = Restaurant(
            id = "",
            name = "",
            imageRes = 0,
            rating = 0f,
            reviewCount = 0,
            deliveryTime = "",
            cuisines = emptyList(),
            offer = ""
        )

        val storeItem = StoreItem(
            id = "",
            name = "",
            imageRes = 0,
            originalPrice = 0,
            discountedPrice = 0,
            rating = 0f,
            reviewCount = 0,
            description = ""
        )

        val category = Category("", "", 0)
        val swiggyOption = SwiggyOption("", "", 0, "")

        // These should not crash and should maintain empty values
        assertEquals("", restaurant.id)
        assertEquals("", restaurant.name)
        assertEquals("", storeItem.id)
        assertEquals("", storeItem.name)
        assertEquals("", category.id)
        assertEquals("", category.name)
        assertEquals("", swiggyOption.id)
        assertEquals("", swiggyOption.title)
    }

    @Test
    fun `Models should handle boundary values correctly`() {
        // Test with maximum/minimum reasonable values
        val restaurant = Restaurant(
            id = "max_id_test_very_long_string_to_test_boundaries",
            name = "Restaurant with a very long name to test string boundaries and edge cases",
            imageRes = Int.MAX_VALUE,
            rating = 5.0f, // Maximum typical rating
            reviewCount = Int.MAX_VALUE,
            deliveryTime = "120+ mins", // Very long delivery time
            cuisines = (1..20).map { "Cuisine$it" } // Many cuisines
        )

        val storeItem = StoreItem(
            id = "store_max_test",
            name = "Store item with maximum values",
            imageRes = Int.MAX_VALUE,
            originalPrice = Int.MAX_VALUE,
            discountedPrice = Int.MAX_VALUE,
            rating = 5.0f,
            reviewCount = Int.MAX_VALUE,
            description = "A".repeat(1000) // Long description
        )

        // These should work without issues
        assertNotNull(restaurant.id)
        assertNotNull(restaurant.name)
        assertTrue(restaurant.cuisines.size == 20)
        assertTrue(storeItem.description.length == 1000)
    }

    @Test
    fun `Restaurant rating calculations should be handled properly`() {
        val restaurants = listOf(
            Restaurant("1", "A", 0, 4.5f, 100, "30", listOf("A")),
            Restaurant("2", "B", 0, 3.8f, 200, "25", listOf("B")),
            Restaurant("3", "C", 0, 4.9f, 50, "40", listOf("C"))
        )

        val averageRating = restaurants.map { it.rating }.average()
        val totalReviews = restaurants.sumOf { it.reviewCount }
        val highRatedRestaurants = restaurants.filter { it.rating >= 4.0f }

        assertEquals(4.4, averageRating, 0.1)
        assertEquals(350, totalReviews)
        assertEquals(2, highRatedRestaurants.size)
    }

    @Test
    fun `StoreItem discount percentage calculation should work`() {
        val item = StoreItem(
            id = "1",
            name = "Test",
            imageRes = 0,
            originalPrice = 100,
            discountedPrice = 75,
            rating = 4.0f,
            reviewCount = 100,
            description = "Test"
        )

        val discountAmount = item.originalPrice - item.discountedPrice
        val discountPercentage = (discountAmount.toDouble() / item.originalPrice) * 100

        assertEquals(25, discountAmount)
        assertEquals(25.0, discountPercentage, 0.1)
    }
}
