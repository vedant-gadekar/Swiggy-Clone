package com.example.core

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.core.components.Carousel
import com.example.core.components.CarouselCard
import com.example.core.components.LocationBar
import com.example.core.components.SearchBar
import com.example.core.state.CarouselItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CommonComponentsTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun LocationBar_rendersNameAndAddress_displaysText() {
        composeRule.setContent {
            LocationBar(name = "Test Name", address = "Test Address")
        }
        composeRule.onNodeWithText("Test Name").assertIsDisplayed()
        composeRule.onNodeWithText("Test Address").assertIsDisplayed()
    }

    @Test
    fun SearchBar_clickableMode_onClick_invoked() {
        val clicked = mutableStateOf(false)
        composeRule.setContent {
            SearchBar(
                query = "",
                onQueryChange = {},
                modifier = Modifier.Companion.testTag("core_search_bar"),
                onClick = { clicked.value = true }
            )
        }
        composeRule.onNodeWithTag("core_search_bar").performClick()
        assert(clicked.value)
    }

    @Test
    fun Carousel_displaysFirstItem_rendersCardByTag() {
        val items = listOf(
            CarouselItem(
                overline = "OVERLINE",
                chip = "CHIP",
                title = "TITLE A",
                subtitle = "SUB A",
                buttonText = "ACTION A",
                imageRes = R.drawable.icon_swiggy
            )
        )

        composeRule.setContent {
            Carousel(
                items = items,
                currentPage = 0,
                onPageChanged = {},
                onButtonClick = {}
            )
        }

        composeRule.onNodeWithTag("home_carousel").assertIsDisplayed()
        composeRule.onNodeWithTag("carousel_item_TITLE A").assertIsDisplayed()
    }

    @Test
    fun CarouselCard_buttonClick_callsCallback() {
        val clickedTitle = mutableStateOf<String?>(null)
        val item = CarouselItem(
            overline = null,
            chip = null,
            title = "Title",
            subtitle = "Subtitle",
            buttonText = "Go",
            imageRes = R.drawable.icon_swiggy
        )
        composeRule.setContent {
            CarouselCard(item = item, onButtonClick = { clickedTitle.value = it.title })
        }
        composeRule.onNodeWithText("Go").performClick()
        assert(clickedTitle.value == "Title")
    }
}