package com.example.it_da.ui.screen.login.route

import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.it_da.ui.screen.login.screen.LoginScreen
import com.example.it_da.ui.screen.login.state.LoginNavigationEffect
import com.example.it_da.ui.screen.login.viewmodel.LoginViewModel
import com.example.it_da.ui.screen.login.viewmodel.LoginViewModelFactory

// Connects login ViewModel state, social auth events, and navigation callbacks to the screen.
@Composable
fun LoginRoute(
    onSignUpClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    onSocialSignUpSuccess: () -> Unit,
    viewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory())
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val socialAuthErrorMessage = uiState.socialAuthErrorMessage

    LaunchedEffect(viewModel) {
        viewModel.navigationEffect.collect { effect ->
            when (effect) {
                LoginNavigationEffect.NavigateToHome -> onLoginSuccess()
                LoginNavigationEffect.NavigateToSignUpAdditionalInfo -> onSocialSignUpSuccess()
            }
        }
    }

    LaunchedEffect(socialAuthErrorMessage) {
        if (socialAuthErrorMessage != null) {
            Toast.makeText(context, socialAuthErrorMessage, Toast.LENGTH_SHORT).show()
            viewModel.clearSocialAuthError()
        }
    }

    LoginScreen(
        uiState = uiState,
        onIdChange = viewModel::onIdChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = viewModel::onLoginClick,
        onSignUpClick = onSignUpClick,
        onAppleLoginClick = viewModel::onAppleSignUpClick,
        onGoogleLoginClick = {
            viewModel.onGoogleSignUpClick(context)
        },
        onKakaoLoginClick = {
            viewModel.onKakaoSignUpClick(context)
        }
    )
}
