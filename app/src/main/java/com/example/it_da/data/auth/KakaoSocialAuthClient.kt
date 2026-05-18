package com.example.it_da.data.auth

import android.content.Context
import com.example.it_da.domain.model.SocialAuthAccount
import com.example.it_da.domain.model.SocialAuthProvider
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class KakaoSocialAuthClient(
    private val nativeAppKey: String
) : SocialAuthClient {
    override val provider: SocialAuthProvider = SocialAuthProvider.KAKAO

    // Starts KakaoTalk login when available and falls back to Kakao Account login otherwise.
    override suspend fun authenticate(context: Context): Result<SocialAuthAccount> {
        if (nativeAppKey.isBlank()) {
            return Result.failure(
                IllegalStateException("Kakao Native App Key가 설정되지 않았습니다.")
            )
        }

        return try {
            val token = requestKakaoToken(context)
            Result.success(requestKakaoAccount(token))
        } catch (exception: CancellationException) {
            throw exception
        } catch (exception: Throwable) {
            Result.failure(IllegalStateException("Kakao 인증 정보를 가져오지 못했습니다.", exception))
        }
    }

    // Requests a Kakao OAuth token using KakaoTalk or Kakao Account.
    private suspend fun requestKakaoToken(context: Context): OAuthToken {
        return suspendCoroutine { continuation ->
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                    continuation.resumeTokenResult(token, error)
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                    continuation.resumeTokenResult(token, error)
                }
            }
        }
    }

    // Loads optional Kakao profile values and combines them with the OAuth token.
    private suspend fun requestKakaoAccount(token: OAuthToken): SocialAuthAccount {
        return suspendCoroutine { continuation ->
            UserApiClient.instance.me { user, _ ->
                continuation.resume(
                    SocialAuthAccount(
                        provider = provider,
                        email = user?.kakaoAccount?.email,
                        displayName = user?.kakaoAccount?.profile?.nickname,
                        idToken = token.idToken,
                        accessToken = token.accessToken
                    )
                )
            }
        }
    }

    // Converts the Kakao callback pair into a coroutine result.
    private fun kotlin.coroutines.Continuation<OAuthToken>.resumeTokenResult(
        token: OAuthToken?,
        error: Throwable?
    ) {
        when {
            token != null -> resume(token)
            error != null -> resumeWith(Result.failure(error))
            else -> resumeWith(Result.failure(IllegalStateException("Kakao 인증 토큰이 비어 있습니다.")))
        }
    }
}
