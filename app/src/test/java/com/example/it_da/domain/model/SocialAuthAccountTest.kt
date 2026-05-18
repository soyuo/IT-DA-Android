package com.example.it_da.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class SocialAuthAccountTest {
    @Test
    fun providerNamesMatchServerFriendlyValues() {
        val providerNames = SocialAuthProvider.entries.map { provider ->
            provider.name
        }

        assertEquals(listOf("APPLE", "GOOGLE", "KAKAO"), providerNames)
    }

    @Test
    fun socialAuthAccountKeepsProviderAndTokenValues() {
        val account = SocialAuthAccount(
            provider = SocialAuthProvider.GOOGLE,
            email = "user@example.com",
            displayName = "User",
            idToken = "id-token",
            accessToken = "access-token"
        )

        assertEquals(SocialAuthProvider.GOOGLE, account.provider)
        assertEquals("user@example.com", account.email)
        assertEquals("User", account.displayName)
        assertEquals("id-token", account.idToken)
        assertEquals("access-token", account.accessToken)
    }
}
