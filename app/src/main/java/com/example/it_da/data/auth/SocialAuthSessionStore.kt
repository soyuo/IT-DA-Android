package com.example.it_da.data.auth

import com.example.it_da.domain.model.SocialAuthAccount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SocialAuthSessionStore {
    private val _currentAccount = MutableStateFlow<SocialAuthAccount?>(null)
    val currentAccount = _currentAccount.asStateFlow()

    // Keeps the latest social authentication result in memory for the sign-up flow.
    fun save(account: SocialAuthAccount) {
        _currentAccount.value = account
    }

    // Clears the in-memory social authentication result when a new auth flow should start.
    fun clear() {
        _currentAccount.value = null
    }

    companion object {
        val default = SocialAuthSessionStore()
    }
}
