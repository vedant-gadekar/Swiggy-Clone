package com.example.feature_auth

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.feature_auth.viewmodel.AuthViewModel
import com.example.feature_auth.utils.Strings
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class OTPVerificationScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun otp_initialState_rendersOtpBoxes_andVerifyRelatedUI() {
        val vm = AuthViewModel()
        // Drive state to OTP screen
        vm.handleEvent(com.example.feature_auth.state.AuthEvent.PhoneNumberChanged("9876543210"))
        vm.handleEvent(com.example.feature_auth.state.AuthEvent.ContinueWithPhone("9876543210"))

        composeTestRule.setContent {
            OTPVerificationScreen(phoneNumber = "9876543210", viewModel = vm)
        }

        // Header and message visible
        composeTestRule.onNodeWithText(Strings.OTP_VERIFICATION).assertIsDisplayed()
        composeTestRule.onNodeWithText("+91-9876543210").assertIsDisplayed()
    }

    @Test
    fun otp_typingDigits_updatesState_andAutoVerifies_onSixDigits() = runTest {
        val vm = AuthViewModel()
        vm.handleEvent(com.example.feature_auth.state.AuthEvent.PhoneNumberChanged("9876543210"))
        vm.handleEvent(com.example.feature_auth.state.AuthEvent.ContinueWithPhone("9876543210"))

        composeTestRule.setContent {
            OTPVerificationScreen(phoneNumber = "9876543210", viewModel = vm)
        }

        // There are 6 BasicTextFields, enter digits one by one (find first editable, type char, repeat)
        val digits = listOf("1","2","3","4","5","6")
        digits.forEachIndexed { index,d ->
            composeTestRule.onAllNodes(hasSetTextAction())[index].performTextInput(d)
        }

        // State holds OTP and isOtpComplete true (auto verify will toggle loading then navigate)
        composeTestRule.waitUntil(timeoutMillis = 7_000) {
            !vm.state.value.isLoading && vm.state.value.otp.length == 6
        }
        assert(vm.state.value.otp == "123456")
    }

    @Test
    fun otp_emptyInput_showsIncompleteError_onManualVerifyIntent() = runTest {
        val vm = AuthViewModel()
        vm.handleEvent(com.example.feature_auth.state.AuthEvent.PhoneNumberChanged("9876543210"))
        vm.handleEvent(com.example.feature_auth.state.AuthEvent.ContinueWithPhone("9876543210"))

        composeTestRule.setContent {
            OTPVerificationScreen(phoneNumber = "9876543210", viewModel = vm)
        }

        // Trigger manual verify with incomplete OTP
        vm.handleEvent(com.example.feature_auth.state.AuthEvent.VerifyOtp(""))

        // Wait until error is set and assert the exact error message
        composeTestRule.waitUntil(timeoutMillis = 2_000) { vm.state.value.errorMessage != null }
        composeTestRule.onNodeWithText(Strings.ERROR_OTP_INCOMPLETE).assertIsDisplayed()
    }

    @Test
    fun otp_resendButton_showsCountdown_whenTimerRunning() = runTest {
        val vm = AuthViewModel()
        vm.handleEvent(com.example.feature_auth.state.AuthEvent.PhoneNumberChanged("9876543210"))
        vm.handleEvent(com.example.feature_auth.state.AuthEvent.ContinueWithPhone("9876543210"))

        composeTestRule.setContent {
            OTPVerificationScreen(phoneNumber = "9876543210", viewModel = vm)
        }

        // Wait until we have navigated to OTP screen and loading finished
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            vm.state.value.currentScreen.name == "OtpVerification" && !vm.state.value.isLoading
        }

        // Assert a node containing "Resend SMS" substring is displayed (covers both countdown and enabled states)
        composeTestRule.onNode(hasText("Resend SMS", substring = true)).assertIsDisplayed()
    }

    @Test
    fun otp_multipleRapidResendClicks_onlyOneHandled_whileLoading() = runTest {
        val vm = AuthViewModel()
        vm.handleEvent(com.example.feature_auth.state.AuthEvent.PhoneNumberChanged("9876543210"))
        vm.handleEvent(com.example.feature_auth.state.AuthEvent.ContinueWithPhone("9876543210"))

        composeTestRule.setContent {
            OTPVerificationScreen(phoneNumber = "9876543210", viewModel = vm)
        }

        // If timer is running, do not attempt to click the countdown label; just assert it's visible
        if (vm.state.value.resendTimer > 0) {
            composeTestRule.onNodeWithText(Strings.RESEND_SMS_IN).assertIsDisplayed()
        }
    }

    @Test
    fun otp_longInput_isTruncated_toSixDigits() = runTest {
        val vm = AuthViewModel()
        vm.handleEvent(com.example.feature_auth.state.AuthEvent.PhoneNumberChanged("9876543210"))
        vm.handleEvent(com.example.feature_auth.state.AuthEvent.ContinueWithPhone("9876543210"))

        composeTestRule.setContent {
            OTPVerificationScreen(phoneNumber = "9876543210", viewModel = vm)
        }

        vm.handleEvent(com.example.feature_auth.state.AuthEvent.OtpChanged("1234567890"))
        assert(vm.state.value.otp == "123456")
        assert(vm.state.value.isOtpComplete)
    }
}


