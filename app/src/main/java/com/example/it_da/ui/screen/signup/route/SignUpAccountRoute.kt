package com.example.it_da.ui.screen.signup.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.it_da.ui.screen.signup.screen.SignUpAccountScreen
import com.example.it_da.ui.screen.signup.viewmodel.SignUpAccountViewModel

// Connects the account sign-up ViewModel state to the account sign-up screen.
@Composable
fun SignUpAccountRoute(
    onNextClick: () -> Unit,
    viewModel: SignUpAccountViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    SignUpAccountScreen(
        uiState = uiState,
        onIdChange = viewModel::onIdChange,
        onPasswordChange = viewModel::onPasswordChange,
        onPasswordConfirmChange = viewModel::onPasswordConfirmChange,
        onNextClick = onNextClick
    )
}
