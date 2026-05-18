package com.example.it_da.ui.screen.login.viewmodel

import android.content.Context
import android.content.ContextWrapper
import com.example.it_da.data.auth.SocialAuthSessionStore
import com.example.it_da.data.repository.SocialAuthRepository
import com.example.it_da.domain.model.SocialAuthAccount
import com.example.it_da.domain.model.SocialAuthProvider
import com.example.it_da.testing.MainDispatcherRule
import com.example.it_da.ui.screen.login.state.LoginNavigationEffect
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val context: Context = ContextWrapper(null)

    @Test
    fun googleSignUpShowsLoadingThenNavigatesAndStoresAccountOnSuccess() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val account = SocialAuthAccount(
            provider = SocialAuthProvider.GOOGLE,
            email = "google@example.com",
            displayName = "Google User",
            idToken = "google-id-token"
        )
        val repository = DeferredSocialAuthRepository()
        val sessionStore = SocialAuthSessionStore()
        val viewModel = LoginViewModel(repository, sessionStore)
        val navigationEffect = async {
            viewModel.navigationEffect.first()
        }

        runCurrent()
        viewModel.onGoogleSignUpClick(context)
        runCurrent()

        assertTrue(viewModel.uiState.value.isSocialAuthLoading)
        assertNull(viewModel.uiState.value.socialAuthErrorMessage)
        assertEquals(SocialAuthProvider.GOOGLE, repository.requestedProvider)

        repository.complete(Result.success(account))
        advanceUntilIdle()

        assertFalse(viewModel.uiState.value.isSocialAuthLoading)
        assertEquals(account, sessionStore.currentAccount.value)
        assertEquals(
            LoginNavigationEffect.NavigateToSignUpAdditionalInfo,
            navigationEffect.await()
        )
    }

    @Test
    fun kakaoSignUpRequestsKakaoProviderAndNavigatesOnSuccess() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val account = SocialAuthAccount(
            provider = SocialAuthProvider.KAKAO,
            email = "kakao@example.com",
            displayName = "Kakao User",
            accessToken = "kakao-access-token"
        )
        val repository = DeferredSocialAuthRepository()
        val sessionStore = SocialAuthSessionStore()
        val viewModel = LoginViewModel(repository, sessionStore)
        val navigationEffect = async {
            viewModel.navigationEffect.first()
        }

        runCurrent()
        viewModel.onKakaoSignUpClick(context)
        runCurrent()
        repository.complete(Result.success(account))
        advanceUntilIdle()

        assertEquals(SocialAuthProvider.KAKAO, repository.requestedProvider)
        assertEquals(account, sessionStore.currentAccount.value)
        assertEquals(
            LoginNavigationEffect.NavigateToSignUpAdditionalInfo,
            navigationEffect.await()
        )
    }

    @Test
    fun socialSignUpFailureStopsLoadingAndShowsErrorMessage() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val repository = DeferredSocialAuthRepository()
        val sessionStore = SocialAuthSessionStore()
        val viewModel = LoginViewModel(repository, sessionStore)

        viewModel.onGoogleSignUpClick(context)
        runCurrent()
        repository.complete(Result.failure(IllegalStateException("Auth failed")))
        advanceUntilIdle()

        assertFalse(viewModel.uiState.value.isSocialAuthLoading)
        assertEquals("Auth failed", viewModel.uiState.value.socialAuthErrorMessage)
        assertNull(sessionStore.currentAccount.value)
    }

    @Test
    fun appleSignUpShowsReadyLaterMessageWithoutCallingRepository() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val repository = DeferredSocialAuthRepository()
        val viewModel = LoginViewModel(repository, SocialAuthSessionStore())

        viewModel.onAppleSignUpClick()

        assertFalse(viewModel.uiState.value.isSocialAuthLoading)
        assertEquals("Apple 회원가입은 준비 중입니다.", viewModel.uiState.value.socialAuthErrorMessage)
        assertNull(repository.requestedProvider)
    }

    @Test
    fun normalLoginNavigatesToHomeWhenRequiredInputsAreFilled() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val repository = DeferredSocialAuthRepository()
        val viewModel = LoginViewModel(repository, SocialAuthSessionStore())
        val navigationEffect = async {
            viewModel.navigationEffect.first()
        }

        viewModel.onIdChange("itda-user")
        viewModel.onPasswordChange("password")
        viewModel.onLoginClick()
        advanceUntilIdle()

        assertEquals(
            LoginNavigationEffect.NavigateToHome,
            navigationEffect.await()
        )
        assertNull(repository.requestedProvider)
    }

    private class DeferredSocialAuthRepository : SocialAuthRepository {
        private val result = CompletableDeferred<Result<SocialAuthAccount>>()
        var requestedProvider: SocialAuthProvider? = null
            private set

        // Records the requested provider and waits until the test completes the auth result.
        override suspend fun authenticate(
            context: Context,
            provider: SocialAuthProvider
        ): Result<SocialAuthAccount> {
            requestedProvider = provider
            return result.await()
        }

        // Completes the suspended fake authentication request with a test-controlled result.
        fun complete(authResult: Result<SocialAuthAccount>) {
            result.complete(authResult)
        }
    }
}
