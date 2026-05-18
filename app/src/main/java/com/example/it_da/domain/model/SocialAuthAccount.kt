package com.example.it_da.domain.model

// Represents an authenticated social account kept in memory until server sign-up is connected.
data class SocialAuthAccount(
    val provider: SocialAuthProvider,
    val email: String? = null,
    val displayName: String? = null,
    val idToken: String? = null,
    val accessToken: String? = null
)
