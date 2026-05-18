package com.example.it_da.ui.screen.signup.state

private const val MinIdLength = 6
private const val MaxIdLength = 15
private const val MinPasswordLength = 8
private const val MaxPasswordLength = 20

// Represents the account information that the first sign-up screen displays and validates.
data class SignUpAccountUiState(
    val id: String = "",
    val password: String = "",
    val passwordConfirm: String = ""
) {
    val isNextEnabled: Boolean
        get() = isIdValid &&
            isPasswordValid &&
            isPasswordConfirmValid &&
            password == passwordConfirm

    private val isIdValid: Boolean
        get() = id.length in MinIdLength..MaxIdLength

    private val isPasswordValid: Boolean
        get() = password.length in MinPasswordLength..MaxPasswordLength

    private val isPasswordConfirmValid: Boolean
        get() = passwordConfirm.length in MinPasswordLength..MaxPasswordLength
}
