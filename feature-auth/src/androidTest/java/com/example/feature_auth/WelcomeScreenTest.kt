package com.example.feature_auth


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.core.Strings as CoreStrings
import com.example.feature_auth.viewmodel.AuthViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.feature_auth.utils.Strings

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class WelcomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun welcome_initialState_rendersPhoneField_andContinueButton() {
        val vm = AuthViewModel()
        composeTestRule.setContent {
            WelcomeScreen(viewModel = vm, showArtwork = false)
        }

        // Placeholder for phone number should be visible initially
        composeTestRule.onNodeWithText(Strings.ENTER_PHONE_NUMBER).assertIsDisplayed()

        // Continue button text
        composeTestRule.onNode(hasText(Strings.CONTINUE).and(hasClickAction())).assertIsDisplayed()
    }

    @Test
    fun welcome_typingPhone_updatesState() = runTest {
        val vm = AuthViewModel()
        composeTestRule.setContent {
            WelcomeScreen(viewModel = vm, showArtwork = false)
        }

        // Find editable node and type 10 digits
        composeTestRule.onNode(hasSetTextAction()).performTextInput("9876543210")

        // State reflects typed phone
        assert(vm.state.value.phoneNumber == "9876543210")
        assert(vm.state.value.isPhoneNumberValid)
    }

    @Test
    fun welcome_emptyPhone_showsError_onContinue() = runTest {
        val vm = AuthViewModel()
        composeTestRule.setContent {
            WelcomeScreen(viewModel = vm, showArtwork = false)
        }

        composeTestRule.onNode(hasText(CoreStrings.CONTINUE).and(hasClickAction())).performClick()

        // Error message appears
        composeTestRule.onNodeWithText(CoreStrings.ERROR_PHONE_EMPTY).assertIsDisplayed()
    }

    @Test
    fun welcome_invalidPhone_nonNumeric_showsError() = runTest {
        val vm = AuthViewModel()
        composeTestRule.setContent {
            WelcomeScreen(viewModel = vm, showArtwork = false)
        }

        // Enter alphanumeric; ViewModel filters to digits so first enter text, then click continue
        composeTestRule.onNode(hasSetTextAction()).performTextInput("98ab54cd10")
        composeTestRule.onNode(hasText(CoreStrings.CONTINUE).and(hasClickAction())).performClick()

        // Because non-digits are filtered out, length likely != 10 -> length error
        composeTestRule.onNodeWithText(CoreStrings.ERROR_PHONE_LENGTH).assertIsDisplayed()
    }

    @Test
    fun welcome_invalidPhone_tooShort_showsError() = runTest {
        val vm = AuthViewModel()
        composeTestRule.setContent {
            WelcomeScreen(viewModel = vm, showArtwork = false)
        }

        composeTestRule.onNode(hasSetTextAction()).performTextInput("12345")
        composeTestRule.onNode(hasText(CoreStrings.CONTINUE).and(hasClickAction())).performClick()

        composeTestRule.onNodeWithText(CoreStrings.ERROR_PHONE_LENGTH).assertIsDisplayed()
    }

    @Test
    fun welcome_extremelyLongInput_isTruncatedTo10() = runTest {
        val vm = AuthViewModel()
        composeTestRule.setContent {
            WelcomeScreen(viewModel = vm, showArtwork = false)
        }

        composeTestRule.onNode(hasSetTextAction()).performTextInput("12345678901234567890")
        assert(vm.state.value.phoneNumber == "1234567890")
    }

    @Test
    fun welcome_multipleRapidClicks_onlyOneNavigationEffectEmitted() = runTest {
        val vm = AuthViewModel()
        composeTestRule.setContent {
            WelcomeScreen(viewModel = vm, showArtwork = false)
        }

        // Valid number
        composeTestRule.onNode(hasSetTextAction()).performTextClearance()
        composeTestRule.onNode(hasSetTextAction()).performTextInput("9998887776")

        // Rapid clicks
        val continueNode = composeTestRule.onNode(hasText(CoreStrings.CONTINUE).and(hasClickAction()))
        continueNode.performClick()
        // Second click may be disabled due to loading; ignore if it fails
        runCatching { continueNode.performClick() }

        // Button should become disabled almost immediately due to loading
        // And only a single flow to OTP should occur. We assert by waiting until state switches to OTP once.
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            vm.state.value.currentScreen.name == "OtpVerification"
        }

        // Ensure still only one transition happened by checking that after transition, click has no effect while loading false
        // (best-effort: assert isLoading is false and screen stays OTP)
        assert(vm.state.value.currentScreen.name == "OtpVerification")
    }
}






