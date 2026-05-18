package com.example.it_da.ui.screen.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.it_da.data.repository.FakeHomeRepository

class HomeViewModelFactory : ViewModelProvider.Factory {
    // Creates HomeViewModel with a fake repository until the server-backed repository is ready.
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(FakeHomeRepository()) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
