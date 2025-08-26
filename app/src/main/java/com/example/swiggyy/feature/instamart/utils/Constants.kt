package com.example.swiggyy.feature.instamart.utils
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.state.CarouselItem
import com.example.swiggyy.R

object InstamartConstants {
    object InstamartDefaults {

        const val DEFAULT_LOCATION_NAME = "Amar Business Zone"
        const val DEFAULT_LOCATION_ADDRESS = "Baner - Mahalunge Road, Veerbhadra..."
        const val DEFAULT_SEARCH_HINT = "Search for 'Cake' 'Sweets'"

        val defaultCarouselItems = listOf(
            CarouselItem(
                "FRESH DEALS TODAY",
                "LIMITED TIME",
                "Up to 50% OFF",
                "On fresh fruits & vegetables!",
                "Shop Now",
                R.drawable.vegetables
            ),
            CarouselItem(
                "DAIRY ESSENTIALS",
                "STARTING AT ₹49",
                "Milk, Butter & More",
                "Delivered in minutes to your doorstep!",
                "Order Now",
                R.drawable.dairy_eggs
            ),
            CarouselItem(
                "INSTANT SNACKS",
                "BUY 1 GET 1 FREE",
                "Your favorite chips & biscuits",
                "Hurry! Limited stock available.",
                "Grab Now",
                R.drawable.snacks
            ),
            CarouselItem(
                "HOUSEHOLD MUST-HAVES",
                "EVERYDAY LOW PRICES",
                "Cleaning & Kitchen Essentials",
                "Never run out of daily needs!",
                "Shop Essentials",

                R.drawable.household
            )
        )
    }


    // Colors
    object Colors {
        val White = Color(0xFFFFFFFF)
        val Black = Color(0xFF000000)
        val PrimaryGreen = Color(0xFF0CA201)
        val LightGreen = Color(0xFFD7FFD4)
        val DarkGray = Color(0xFF0A0B0A)
        val MediumGray = Color(0xFF5A5555)
        val LightGray = Color(0xFFF6F6F6)
        val BorderGray = Color(0xFFECECEC)
        val StarYellow = Color(0xFFFFD500)
        val ShadowBlack = Color.Black.copy(alpha = 0.05f)
    }

    // Dimensions
    object Dimensions {
        // Spacing
        val SpacingXSmall = 5.dp
        val SpacingSmall = 8.dp
        val SpacingMedium = 10.dp
        val SpacingNormal = 13.dp
        val SpacingLarge = 15.dp
        val SpacingXLarge2 = 17.dp
        val SpacingXLarge = 20.dp
        val SpacingXXLarge = 25.dp
        val SpacingXXXLarge = 36.dp

        // Card dimensions
        val ProductCardWidth = 170.dp
        val ProductCardHeight = 245.dp
        val ProductImageHeight = 147.dp
        val ProductImageSize = 146.dp
        val CategoryItemSize = 70.dp

        // Button dimensions
        val AddButtonSize = 38.dp
        val IconSizeSmall = 12.dp
        val IconSizeMedium = 16.dp
        val IconSizeLarge = 18.dp

        val TEXT_WEIGHT_S = 0.2f
        val TEXT_WEIGHT_M = 0.8f

        // Promotional slider dimensions
        val SliderLargeWidth = 383.dp
        val SliderSmallWidth = 277.dp
        val SliderLargeHeight = 102.dp
        val SliderSmallHeight = 76.dp
        val SliderImageLargeWidth = 100.dp
        val SliderImageSmallWidth = 29.dp

        // Section header
        val SectionHeaderMinHeight = 55.dp

        // Border and corner radius
        val CornerRadiusSmall = 5.dp
        val CornerRadiusMedium = 10.dp
        val CornerRadiusLarge = 15.dp
        val CornerRadiusXLarge = 40.dp
        val SliderCornerRadiusSmall = 7.223.dp
        val SliderButtonCornerRadiusSmall = 3.612.dp
        val BorderWidth = 1.dp
        val ShadowElevation = 7.dp

        val SECTION_HEIGHT=100.dp
    }

    // Typography
    object Typography {
        val FontSizeXSmall = 9.sp
        val FontSizeSmall = 10.sp
        val FontSizeMedium = 12.sp
        val FontSizeNormal = 13.sp
        val FontSizeLarge = 14.sp
        val FontSizeXLarge = 16.sp
        val FontSizeXXLarge = 20.sp
    }

    // Text Strings
    object Strings {
        const val ZERO=0;
        const val ONE=1;
        const val SeeAll = "See all"
        const val ShopNow = "Shop Now"
        const val AddToCart = "Add to cart"
        const val AddMore = "Add more"
        const val Remove = "Remove"
        const val AddedToCart = "Added to cart"

        // Section titles
        const val Fruits = "Fruits"
        const val Detergent = "Detergent"
        const val Biscuit = "Biscuit"

        // Category names
        const val CategoryFruits = "Fruits"
        const val CategoryMilkEgg = "Milk & egg"
        const val CategoryBeverages = "Beverages"
        const val CategoryLaundry = "Laundry"
        const val CategoryVegetables = "Vegetables"
        const val ProfilePicture="Profile Picture"
        // Promotional texts
        const val Offer30Title = "Up to 30% offer"
        const val Offer30Subtitle = "Enjoy our big offer"
        const val Offer25Title = "Up to 25% offer"
        const val Offer25Subtitle = "On first buyers"

        // Product names
        const val ProductBanana = "Banana"
        const val ProductPepper = "Pepper"
        const val ProductOrange = "Orange"
        const val ProductStrawberry = "Strawberry"
        const val ProductLemon = "Lemon"
        const val ProductWaterLemon = "Water lemon"
        const val ProductApple = "Apple"
        const val ProductMango = "Mango"
        const val ProductGrapes = "Grapes"
        const val ProductPurex = "Purex"
        const val ProductVarnish = "Varnish"
        const val ProductHarpic = "Harpic"
        const val ProductDettol = "Dettol"
        const val ProductLoacker = "Loacker"
        const val ProductBiscoff = "Biscoff"
        const val ProductTUC = "TUC"
        const val ProductCherries = "Cherries"

        // Common rating
        const val DefaultRating = "4.8 (287)"
    }

    // Image URLs
    object ImageUrls {
        // Category images
        val CategoryFruitsImage = R.drawable.icon_swiggy
        val CategoryMilkEggImage = R.drawable.dairy_eggs
        val CategoryBeveragesImage = R.drawable.beverages
        val CategoryLaundryImage = R.drawable.icon_swiggy
        val CategoryVegetablesImage = R.drawable.vegetables

        // Product images
        val BananaImage = R.drawable.bananas
        val PepperImage = R.drawable.bell_pepper
        val WaterLemonImage = R.drawable.icon_swiggy
        val PurexImage = R.drawable.icon_swiggy
        val VarnishImage = R.drawable.icon_swiggy
        val HarpicImage = R.drawable.icon_swiggy
        val LoackerImage1 = R.drawable.icon_swiggy
        val LoackerImage2 = R.drawable.icon_swiggy
        val BiscoffImage = R.drawable.icon_swiggy

        // Promotional slider images
        val PromoLargeImage = R.drawable.icon_swiggy
        val PromoSmallImage = R.drawable.icon_swiggy

        // Default/placeholder image
        val DefaultProductImage = R.drawable.icon_swiggy
    }

    // Prices
    object Prices {
        const val Price199 = "$1.99"
        const val Price225 = "$2.25"
        const val Price229 = "$2.29"
        const val Price299 = "$2.99"
        const val Price345 = "$3.45"
        const val Price399 = "$3.99"
        const val Price289 = "$2.89"
        const val Price499 = "$4.99"
        const val Price529 = "$5.29"
        const val Price1025 = "$10.25"
    }

    // Product Categories
    object Categories {
        const val FRUITS = "fruits"
        const val DETERGENT = "detergent"
        const val BISCUIT = "biscuit"
    }

    // Product IDs
    object ProductIds {
        const val Banana = "f1"
        const val Pepper = "f2"
        const val Orange = "f3"
        const val Strawberry = "f4"
        const val Lemon = "f5"
        const val WaterLemon = "f6"
        const val Apple = "f7"
        const val Mango = "f8"
        const val Grapes = "f9"
        const val PawPaw = "f10"
        const val Purex1 = "d1"
        const val Varnish = "d2"
        const val Harpic1 = "d3"
        const val Harpic2 = "d4"
        const val Purex2 = "d5"
        const val Dettol = "d6"
        const val Loacker1 = "b1"
        const val Loacker2 = "b2"
        const val Biscoff = "b3"
        const val TUC1 = "b4"
        const val TUC2 = "b5"
        const val Cherries = "b6"
    }

    // Category IDs
    object CategoryIds {
        const val Fruits = "1"
        const val MilkEgg = "2"
        const val Beverages = "3"
        const val Laundry = "4"
        const val Vegetables = "5"
    }

    // Slider IDs
    object SliderIds {
        const val Promo30 = "s1"
        const val Promo25 = "s2"
    }
}
