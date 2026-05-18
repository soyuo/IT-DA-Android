package com.example.it_da.ui.screen.login.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it_da.data.auth.SocialAuthSessionStore
import com.example.it_da.data.repository.SocialAuthRepository
import com.example.it_da.domain.model.SocialAuthProvider
import com.example.it_da.ui.screen.login.state.LoginNavigationEffect
import com.example.it_da.ui.screen.login.state.LoginUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val socialAuthRepository: SocialAuthRepository,
    private val socialAuthSessionStore: SocialAuthSessionStore = SocialAuthSessionStore.default
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _navigationEffect = MutableSharedFlow<LoginNavigationEffect>()
    val navigationEffect = _navigationEffect.asSharedFlow()

    // Updates the ID input value used by the normal login form.
    fun onIdChange(id: String) {
        _uiState.update { currentState ->
            currentState.copy(id = id)
        }
    }

    // Updates the password input value used by the normal login form.
    fun onPasswordChange(password: String) {
        _uiState.update { currentState ->
            currentState.copy(password = password)
        }
    }

    // Emits the normal login navigation event until server login validation is connected.
    fun onLoginClick() {
        if (!_uiState.value.isLoginEnabled) {
            return
        }

        viewModelScope.launch {
            _navigationEffect.emit(LoginNavigationEffect.NavigateToHome)
        }
    }

    // Shows a clear message because Android Apple auth needs a server redirect endpoint.
    fun onAppleSignUpClick() {
        _uiState.update { currentState ->
            currentState.copy(
                isSocialAuthLoading = false,
                socialAuthErrorMessage = "Apple 회원가입은 준비 중입니다."
            )
        }
    }

    // Starts Google social sign-up and navigates to additional information after success.
    fun onGoogleSignUpClick(context: Context) {
        authenticateWithSocialProvider(
            context = context,
            provider = SocialAuthProvider.GOOGLE
        )
    }

    // Starts Kakao social sign-up and navigates to additional information after success.
    fun onKakaoSignUpClick(context: Context) {
        authenticateWithSocialProvider(
            context = context,
            provider = SocialAuthProvider.KAKAO
        )
    }

    // Clears the current social auth error when a new attempt should start.
    fun clearSocialAuthError() {
        _uiState.update { currentState ->
            currentState.copy(socialAuthErrorMessage = null)
        }
    }

    // Coordinates social auth loading state, in-memory account storage, and navigation effects.
    private fun authenticateWithSocialProvider(
        context: Context,
        provider: SocialAuthProvider
    ) {
        if (_uiState.value.isSocialAuthLoading) {
            return
        }

        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isSocialAuthLoading = true,
                    socialAuthErrorMessage = null
                )
            }

            socialAuthRepository.authenticate(context, provider)
                .onSuccess { account ->
                    socialAuthSessionStore.save(account)
                    _uiState.update { currentState ->
                        currentState.copy(isSocialAuthLoading = false)
                    }
                    _navigationEffect.emit(LoginNavigationEffect.NavigateToSignUpAdditionalInfo)
                }
                .onFailure { throwable ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            isSocialAuthLoading = false,
                            socialAuthErrorMessage = throwable.message ?: "소셜 회원가입에 실패했습니다."
                        )
                    }
                }
        }
    }
}
