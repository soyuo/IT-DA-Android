package com.example.it_da.ui.screen.login.state

// Represents one-time navigation events emitted by the login ViewModel.
sealed interface LoginNavigationEffect {
    data object NavigateToHome : LoginNavigationEffect
    data object NavigateToSignUpAdditionalInfo : LoginNavigationEffect
}
