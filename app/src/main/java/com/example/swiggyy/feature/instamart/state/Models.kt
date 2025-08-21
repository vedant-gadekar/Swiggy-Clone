
import androidx.compose.ui.graphics.Color

// User model
data class User(
    val id: String,
    val name: String,
    val email: String,
    val profileImageRes: Int
)

// Category model (extended from ProductCategory)
data class Category(
    val id: String,
    val name: String,
    val imageRes: Int,
    val backgroundColor: Color,
    val isSelected: Boolean = false
)

// Product model (extended from Product)
data class ProductItem(
    val id: String,
    val name: String,
    val price: Double,
    val originalPrice: Double? = null,
    val weight: String,
    val description: String = "",
    val imageRes: Int,
    val categoryId: String,
    val isInCart: Boolean = false,
    val isFavorite: Boolean = false,
    val discount: Int? = null,
    val rating: Float = 0f,
    val reviewCount: Int = 0
)

// Cart item model
data class CartItem(
    val id: String,
    val productId: String,
    val product: ProductItem,
    val quantity: Int,
    val addedAt: Long = System.currentTimeMillis()
)

// Address model
data class Address(
    val id: String,
    val title: String,
    val fullAddress: String,
    val isDefault: Boolean = false
)

// Payment method model
data class PaymentMethod(
    val id: String,
    val type: PaymentType,
    val cardNumber: String? = null,
    val expiryDate: String? = null,
    val holderName: String? = null,
    val isDefault: Boolean = false
)

enum class PaymentType {
    CREDIT_CARD,
    DEBIT_CARD,
    PAYPAL,
    CASH_ON_DELIVERY
}

// Order model
data class Order(
    val id: String,
    val items: List<CartItem>,
    val total: Double,
    val status: OrderStatus,
    val createdAt: Long,
    val deliveryAddress: Address,
    val paymentMethod: PaymentMethod
)

enum class OrderStatus {
    PENDING,
    CONFIRMED,
    PREPARING,
    ON_THE_WAY,
    DELIVERED,
    CANCELLED
}

// Notification model
data class AppNotification(
    val id: String,
    val title: String,
    val message: String,
    val type: NotificationType,
    val isRead: Boolean = false,
    val createdAt: Long
)

enum class NotificationType {
    ORDER_UPDATE,
    PROMOTION,
    SYSTEM,
    DELIVERY
}

// Promo code model
data class PromoCode(
    val id: String,
    val code: String,
    val title: String,
    val description: String,
    val discountPercentage: Int,
    val minimumAmount: Double,
    val expiryDate: Long,
    val isUsed: Boolean = false
)
