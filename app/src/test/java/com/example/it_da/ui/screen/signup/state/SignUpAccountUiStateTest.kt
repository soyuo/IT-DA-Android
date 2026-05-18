package com.example.it_da.ui.screen.signup.state

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SignUpAccountUiStateTest {
    @Test
    fun nextButtonDisabledWhenFieldsAreEmpty() {
        val state = SignUpAccountUiState()

        assertFalse(state.isNextEnabled)
    }

    @Test
    fun nextButtonDisabledWhenIdLengthIsInvalid() {
        val state = SignUpAccountUiState(
            id = "abcde",
            password = "password1",
            passwordConfirm = "password1"
        )

        assertFalse(state.isNextEnabled)
    }

    @Test
    fun nextButtonDisabledWhenPasswordLengthIsInvalid() {
        val state = SignUpAccountUiState(
            id = "abcdef",
            password = "pass",
            passwordConfirm = "pass"
        )

        assertFalse(state.isNextEnabled)
    }

    @Test
    fun nextButtonDisabledWhenPasswordsDoNotMatch() {
        val state = SignUpAccountUiState(
            id = "abcdef",
            password = "password1",
            passwordConfirm = "password2"
        )

        assertFalse(state.isNextEnabled)
    }

    @Test
    fun nextButtonEnabledWhenAccountInputsAreValid() {
        val state = SignUpAccountUiState(
            id = "abcdef",
            password = "password1",
            passwordConfirm = "password1"
        )

        assertTrue(state.isNextEnabled)
    }
}
