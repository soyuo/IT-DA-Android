package com.example.it_da.ui.screen.signup.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.it_da.ui.screen.signup.screen.SignUpAdditionalInfoScreen
import com.example.it_da.ui.screen.signup.viewmodel.SignUpAdditionalInfoViewModel

// Connects additional sign-up state and events to the second sign-up screen.
@Composable
fun SignUpAdditionalInfoRoute(
    onNextClick: () -> Unit,
    viewModel: SignUpAdditionalInfoViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    SignUpAdditionalInfoScreen(
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
        onInterestFieldChange = viewModel::onInterestFieldChange,
        onTechStackChange = viewModel::onTechStackChange,
        onCohortChange = viewModel::onCohortChange,
        onDepartmentChange = viewModel::onDepartmentChange,
        onDropdownArrowClick = {},
        onNextClick = onNextClick
    )
}
