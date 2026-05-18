package com.example.it_da.data.repository

import android.content.Context
import com.example.it_da.domain.model.SocialAuthAccount
import com.example.it_da.domain.model.SocialAuthProvider

interface SocialAuthRepository {
    // Authenticates the selected provider and returns app-level social account data.
    suspend fun authenticate(
        context: Context,
        provider: SocialAuthProvider
    ): Result<SocialAuthAccount>
}
