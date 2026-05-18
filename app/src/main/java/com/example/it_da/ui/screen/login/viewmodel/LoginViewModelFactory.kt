package com.example.it_da.ui.screen.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.it_da.BuildConfig
import com.example.it_da.data.auth.GoogleSocialAuthClient
import com.example.it_da.data.auth.KakaoSocialAuthClient
import com.example.it_da.data.repository.DefaultSocialAuthRepository

class LoginViewModelFactory : ViewModelProvider.Factory {
    // Creates LoginViewModel with provider-specific SDK clients hidden behind the repository.
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            val socialAuthRepository = DefaultSocialAuthRepository(
                socialAuthClients = listOf(
                    GoogleSocialAuthClient(BuildConfig.GOOGLE_WEB_CLIENT_ID),
                    KakaoSocialAuthClient(BuildConfig.KAKAO_NATIVE_APP_KEY)
                )
            )

            return LoginViewModel(socialAuthRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
