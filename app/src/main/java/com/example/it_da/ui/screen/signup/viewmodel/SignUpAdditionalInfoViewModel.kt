package com.example.it_da.ui.screen.signup.viewmodel

import androidx.lifecycle.ViewModel
import com.example.it_da.ui.screen.signup.state.SignUpAdditionalInfoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpAdditionalInfoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpAdditionalInfoUiState())
    val uiState = _uiState.asStateFlow()

    // Updates the user's display name for additional sign-up information.
    fun onNameChange(name: String) {
        _uiState.update { currentState ->
            currentState.copy(name = name)
        }
    }

    // Updates the selected or typed interest field value.
    fun onInterestFieldChange(interestField: String) {
        _uiState.update { currentState ->
            currentState.copy(interestField = interestField)
        }
    }

    // Updates the user's tech stack text for matching information.
    fun onTechStackChange(techStack: String) {
        _uiState.update { currentState ->
            currentState.copy(techStack = techStack)
        }
    }

    // Updates the cohort value shown in the additional information form.
    fun onCohortChange(cohort: String) {
        _uiState.update { currentState ->
            currentState.copy(cohort = cohort)
        }
    }

    // Updates the department value shown in the additional information form.
    fun onDepartmentChange(department: String) {
        _uiState.update { currentState ->
            currentState.copy(department = department)
        }
    }
}
