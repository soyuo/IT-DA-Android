package com.example.it_da.ui.screen.signup.state

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SignUpAdditionalInfoUiStateTest {
    @Test
    fun nextButtonDisabledWhenAnyFieldIsEmpty() {
        val state = SignUpAdditionalInfoUiState(
            name = "홍길동",
            interestField = "Back-end",
            techStack = "Kotlin",
            cohort = "10기",
            department = ""
        )

        assertFalse(state.isNextEnabled)
    }

    @Test
    fun nextButtonEnabledWhenAllAdditionalInfoFieldsAreFilled() {
        val state = SignUpAdditionalInfoUiState(
            name = "홍길동",
            interestField = "Back-end",
            techStack = "Kotlin",
            cohort = "10기",
            department = "SW과"
        )

        assertTrue(state.isNextEnabled)
    }
}
