package com.example.it_da.data.auth

import android.content.Context
import com.example.it_da.domain.model.SocialAuthAccount
import com.example.it_da.domain.model.SocialAuthProvider

interface SocialAuthClient {
    val provider: SocialAuthProvider

    // Starts the provider SDK authentication flow and returns the authenticated account data.
    suspend fun authenticate(context: Context): Result<SocialAuthAccount>
}
