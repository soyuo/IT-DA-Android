package com.example.it_da.ui.screen.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it_da.data.repository.HomeRepository
import com.example.it_da.ui.screen.home.toHomeUiState
import com.example.it_da.ui.screen.home.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadHomeDashboard()
    }

    // Loads home dashboard values from the repository and exposes them as UI state.
    private fun loadHomeDashboard() {
        viewModelScope.launch {
            homeRepository.getHomeDashboard()
                .onSuccess { homeDashboard ->
                    _uiState.value = homeDashboard.toHomeUiState()
                }
        }
    }
}
