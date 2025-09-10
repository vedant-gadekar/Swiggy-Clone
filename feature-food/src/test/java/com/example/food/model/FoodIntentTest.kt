package com.example.food.model

import com.example.swiggyy.bottomNavBar.food.state.FoodIntent
import com.example.swiggyy.feature_food.model.Category
import com.example.swiggyy.feature_food.model.Restaurant
import com.example.swiggyy.feature_food.model.StoreItem
import com.example.swiggyy.feature_food.model.SwiggyOption
import org.junit.Assert.*
import org.junit.Test

class FoodIntentTest {

    private val sampleCategory = Category("1", "Pizza", 0)
    private val sampleRestaurant = Restaurant(
        id = "1",
        name = "Test Restaurant",
        imageRes = 0,
        rating = 4.5f,
        reviewCount = 100,
        deliveryTime = "30 mins",
        cuisines = listOf("Italian")
    )
    private val sampleStoreItem = StoreItem(
        id = "1",
        name = "Test Item",
        imageRes = 0,
        originalPrice = 100,
        discountedPrice = 80,
        rating = 4.0f,
        reviewCount = 50,
        description = "Test description"
    )
    private val sampleSwiggyOption = SwiggyOption("1", "Test Option", 0)

    @Test
    fun `LoadData intent should be object type`() {
        val intent = FoodIntent.LoadData
        
        assertTrue(intent is FoodIntent.LoadData)
        assertEquals(FoodIntent.LoadData, intent)
    }

    @Test
    fun `SearchQueryChanged intent should contain query`() {
        val query = "pizza restaurant"
        val intent = FoodIntent.SearchQueryChanged(query)
        
        assertTrue(intent is FoodIntent.SearchQueryChanged)
        assertEquals(query, intent.query)
    }

    @Test
    fun `SearchQueryChanged intent should handle empty query`() {
        val intent = FoodIntent.SearchQueryChanged("")
        
        assertEquals("", intent.query)
    }

    @Test
    fun `SearchQueryChanged intent should handle whitespace query`() {
        val intent = FoodIntent.SearchQueryChanged("   ")
        
        assertEquals("   ", intent.query)
    }

    @Test
    fun `CategoryClicked intent should contain category`() {
        val intent = FoodIntent.CategoryClicked(sampleCategory)
        
        assertTrue(intent is FoodIntent.CategoryClicked)
        assertEquals(sampleCategory, intent.category)
        assertEquals("1", intent.category.id)
        assertEquals("Pizza", intent.category.name)
    }

    @Test
    fun `RestaurantClicked intent should contain restaurant`() {
        val intent = FoodIntent.RestaurantClicked(sampleRestaurant)
        
        assertTrue(intent is FoodIntent.RestaurantClicked)
        assertEquals(sampleRestaurant, intent.restaurant)
        assertEquals("1", intent.restaurant.id)
        assertEquals("Test Restaurant", intent.restaurant.name)
    }

    @Test
    fun `StoreItemClicked intent should contain store item`() {
        val intent = FoodIntent.StoreItemClicked(sampleStoreItem)
        
        assertTrue(intent is FoodIntent.StoreItemClicked)
        assertEquals(sampleStoreItem, intent.item)
        assertEquals("1", intent.item.id)
        assertEquals("Test Item", intent.item.name)
    }

    @Test
    fun `SwiggyOptionClicked intent should contain swiggy option`() {
        val intent = FoodIntent.SwiggyOptionClicked(sampleSwiggyOption)
        
        assertTrue(intent is FoodIntent.SwiggyOptionClicked)
        assertEquals(sampleSwiggyOption, intent.option)
        assertEquals("1", intent.option.id)
        assertEquals("Test Option", intent.option.title)
    }

    @Test
    fun `FilterSelected intent should contain filter`() {
        val filter = "Vegetarian"
        val intent = FoodIntent.FilterSelected(filter)
        
        assertTrue(intent is FoodIntent.FilterSelected)
        assertEquals(filter, intent.filter)
    }

    @Test
    fun `FilterSelected intent should handle empty filter`() {
        val intent = FoodIntent.FilterSelected("")
        
        assertEquals("", intent.filter)
    }

    @Test
    fun `SortSelected intent should contain sort`() {
        val sort = "Rating: High to Low"
        val intent = FoodIntent.SortSelected(sort)
        
        assertTrue(intent is FoodIntent.SortSelected)
        assertEquals(sort, intent.sort)
    }

    @Test
    fun `SortSelected intent should handle empty sort`() {
        val intent = FoodIntent.SortSelected("")
        
        assertEquals("", intent.sort)
    }

    @Test
    fun `LocationClicked intent should be object type`() {
        val intent = FoodIntent.LocationClicked
        
        assertTrue(intent is FoodIntent.LocationClicked)
        assertEquals(FoodIntent.LocationClicked, intent)
    }

    @Test
    fun `SeeAllNinetyNineStore intent should be object type`() {
        val intent = FoodIntent.SeeAllNinetyNineStore
        
        assertTrue(intent is FoodIntent.SeeAllNinetyNineStore)
        assertEquals(FoodIntent.SeeAllNinetyNineStore, intent)
    }

    @Test
    fun `ToggleFavorite intent should contain restaurant id and favorite status`() {
        val restaurantId = "restaurant123"
        val isFavorite = true
        val intent = FoodIntent.ToggleFavorite(restaurantId, isFavorite)
        
        assertTrue(intent is FoodIntent.ToggleFavorite)
        assertEquals(restaurantId, intent.restaurantId)
        assertEquals(isFavorite, intent.isFavorite)
    }

    @Test
    fun `ToggleFavorite intent should handle false favorite status`() {
        val intent = FoodIntent.ToggleFavorite("restaurant456", false)
        
        assertEquals("restaurant456", intent.restaurantId)
        assertFalse(intent.isFavorite)
    }

    @Test
    fun `ToggleFavorite intent should handle empty restaurant id`() {
        val intent = FoodIntent.ToggleFavorite("", true)
        
        assertEquals("", intent.restaurantId)
        assertTrue(intent.isFavorite)
    }

    @Test
    fun `Retry intent should be object type`() {
        val intent = FoodIntent.Retry
        
        assertTrue(intent is FoodIntent.Retry)
        assertEquals(FoodIntent.Retry, intent)
    }

    @Test
    fun `all object intents should be equal to themselves`() {
        assertEquals(FoodIntent.LoadData, FoodIntent.LoadData)
        assertEquals(FoodIntent.LocationClicked, FoodIntent.LocationClicked)
        assertEquals(FoodIntent.SeeAllNinetyNineStore, FoodIntent.SeeAllNinetyNineStore)
        assertEquals(FoodIntent.Retry, FoodIntent.Retry)
    }

    @Test
    fun `data class intents should be equal when properties are equal`() {
        val intent1 = FoodIntent.SearchQueryChanged("pizza")
        val intent2 = FoodIntent.SearchQueryChanged("pizza")
        val intent3 = FoodIntent.SearchQueryChanged("burger")
        
        assertEquals(intent1, intent2)
        assertNotEquals(intent1, intent3)
    }

    @Test
    fun `CategoryClicked intents should be equal when categories are equal`() {
        val category1 = Category("1", "Pizza", 123)
        val category2 = Category("1", "Pizza", 123)
        val category3 = Category("2", "Burger", 456)
        
        val intent1 = FoodIntent.CategoryClicked(category1)
        val intent2 = FoodIntent.CategoryClicked(category2)
        val intent3 = FoodIntent.CategoryClicked(category3)
        
        assertEquals(intent1, intent2)
        assertNotEquals(intent1, intent3)
    }

    @Test
    fun `RestaurantClicked intents should be equal when restaurants are equal`() {
        val restaurant1 = Restaurant("1", "Test", 0, 4.5f, 100, "30 min", listOf("Italian"))
        val restaurant2 = Restaurant("1", "Test", 0, 4.5f, 100, "30 min", listOf("Italian"))
        val restaurant3 = Restaurant("2", "Other", 0, 4.0f, 50, "20 min", listOf("Chinese"))
        
        val intent1 = FoodIntent.RestaurantClicked(restaurant1)
        val intent2 = FoodIntent.RestaurantClicked(restaurant2)
        val intent3 = FoodIntent.RestaurantClicked(restaurant3)
        
        assertEquals(intent1, intent2)
        assertNotEquals(intent1, intent3)
    }

    @Test
    fun `ToggleFavorite intents should be equal when properties are equal`() {
        val intent1 = FoodIntent.ToggleFavorite("rest1", true)
        val intent2 = FoodIntent.ToggleFavorite("rest1", true)
        val intent3 = FoodIntent.ToggleFavorite("rest1", false)
        val intent4 = FoodIntent.ToggleFavorite("rest2", true)
        
        assertEquals(intent1, intent2)
        assertNotEquals(intent1, intent3)
        assertNotEquals(intent1, intent4)
    }

    @Test
    fun `FilterSelected intents should be equal when filters are equal`() {
        val intent1 = FoodIntent.FilterSelected("Vegetarian")
        val intent2 = FoodIntent.FilterSelected("Vegetarian")
        val intent3 = FoodIntent.FilterSelected("Non-Vegetarian")
        
        assertEquals(intent1, intent2)
        assertNotEquals(intent1, intent3)
    }

    @Test
    fun `SortSelected intents should be equal when sorts are equal`() {
        val intent1 = FoodIntent.SortSelected("Rating: High to Low")
        val intent2 = FoodIntent.SortSelected("Rating: High to Low")
        val intent3 = FoodIntent.SortSelected("Price: Low to High")
        
        assertEquals(intent1, intent2)
        assertNotEquals(intent1, intent3)
    }

    @Test
    fun `different intent types should not be equal`() {
        val loadDataIntent = FoodIntent.LoadData
        val retryIntent = FoodIntent.Retry
        val searchIntent = FoodIntent.SearchQueryChanged("test")
        val categoryIntent = FoodIntent.CategoryClicked(sampleCategory)
        
        assertNotEquals(loadDataIntent, retryIntent)
        assertNotEquals(loadDataIntent, searchIntent)
        assertNotEquals(searchIntent, categoryIntent)
    }

    @Test
    fun `intent toString should work correctly`() {
        val searchIntent = FoodIntent.SearchQueryChanged("pizza")
        val favoriteIntent = FoodIntent.ToggleFavorite("rest1", true)
        
        // These should not crash and should return meaningful strings
        assertNotNull(searchIntent.toString())
        assertNotNull(favoriteIntent.toString())
        assertTrue(searchIntent.toString().contains("SearchQueryChanged"))
        assertTrue(favoriteIntent.toString().contains("ToggleFavorite"))
    }

    @Test
    fun `intent hashCode should be consistent`() {
        val intent1 = FoodIntent.SearchQueryChanged("pizza")
        val intent2 = FoodIntent.SearchQueryChanged("pizza")
        val intent3 = FoodIntent.SearchQueryChanged("burger")
        
        assertEquals(intent1.hashCode(), intent2.hashCode())
        assertNotEquals(intent1.hashCode(), intent3.hashCode())
    }

    @Test
    fun `complex intent scenarios`() {
        // Test with complex objects
        val complexRestaurant = Restaurant(
            id = "complex123",
            name = "Restaurant with very long name and special characters! @#$%",
            imageRes = Int.MAX_VALUE,
            rating = 4.999f,
            reviewCount = 99999,
            deliveryTime = "5-120 mins",
            cuisines = listOf("Italian", "Chinese", "Indian", "Continental", "Mexican"),
            offer = "50% OFF up to ₹500 + Free Delivery + Extra 10% Cashback",
            isAd = true,
            hasFreeDelivery = true,
            hasOneBenefits = true,
            isFavorite = true
        )
        
        val complexIntent = FoodIntent.RestaurantClicked(complexRestaurant)
        
        assertTrue(complexIntent is FoodIntent.RestaurantClicked)
        assertEquals(complexRestaurant, complexIntent.restaurant)
        assertEquals("Restaurant with very long name and special characters! @#$%", complexIntent.restaurant.name)
        assertEquals(5, complexIntent.restaurant.cuisines.size)
    }

    @Test
    fun `intent type checking with when expression`() {
        val intents = listOf(
            FoodIntent.LoadData,
            FoodIntent.SearchQueryChanged("test"),
            FoodIntent.CategoryClicked(sampleCategory),
            FoodIntent.RestaurantClicked(sampleRestaurant),
            FoodIntent.StoreItemClicked(sampleStoreItem),
            FoodIntent.SwiggyOptionClicked(sampleSwiggyOption),
            FoodIntent.FilterSelected("filter"),
            FoodIntent.SortSelected("sort"),
            FoodIntent.LocationClicked,
            FoodIntent.SeeAllNinetyNineStore,
            FoodIntent.ToggleFavorite("id", true),
            FoodIntent.Retry
        )
        
        intents.forEach { intent ->
            val result = when (intent) {
                is FoodIntent.LoadData -> "load"
                is FoodIntent.SearchQueryChanged -> "search"
                is FoodIntent.CategoryClicked -> "category"
                is FoodIntent.RestaurantClicked -> "restaurant"
                is FoodIntent.StoreItemClicked -> "store"
                is FoodIntent.SwiggyOptionClicked -> "option"
                is FoodIntent.FilterSelected -> "filter"
                is FoodIntent.SortSelected -> "sort"
                is FoodIntent.LocationClicked -> "location"
                is FoodIntent.SeeAllNinetyNineStore -> "seeall"
                is FoodIntent.ToggleFavorite -> "favorite"
                is FoodIntent.Retry -> "retry"
            }
            assertNotNull(result)
        }
    }
}
