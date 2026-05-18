package com.example.it_da.data.auth

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import com.example.it_da.domain.model.SocialAuthAccount
import com.example.it_da.domain.model.SocialAuthProvider
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlin.coroutines.cancellation.CancellationException

class GoogleSocialAuthClient(
    private val webClientId: String,
    private val credentialManagerFactory: (Context) -> CredentialManager = { context ->
        CredentialManager.create(context)
    }
) : SocialAuthClient {
    override val provider: SocialAuthProvider = SocialAuthProvider.GOOGLE

    // Opens Credential Manager and converts the selected Google credential into app auth data.
    override suspend fun authenticate(context: Context): Result<SocialAuthAccount> {
        if (webClientId.isBlank()) {
            return Result.failure(
                IllegalStateException("Google Web Client ID가 설정되지 않았습니다.")
            )
        }

        return try {
            val credential = requestGoogleCredential(context)

            Result.success(
                SocialAuthAccount(
                    provider = provider,
                    email = credential.id,
                    displayName = credential.displayName,
                    idToken = credential.idToken
                )
            )
        } catch (exception: CancellationException) {
            throw exception
        } catch (exception: GetCredentialCancellationException) {
            Result.failure(IllegalStateException("Google 회원가입이 취소되었습니다.", exception))
        } catch (exception: GetCredentialException) {
            Result.failure(IllegalStateException("Google 인증 정보를 가져오지 못했습니다.", exception))
        } catch (exception: GoogleIdTokenParsingException) {
            Result.failure(IllegalStateException("Google 인증 토큰을 해석하지 못했습니다.", exception))
        } catch (exception: IllegalStateException) {
            Result.failure(exception)
        }
    }

    // Requests a Google ID token through Android Credential Manager.
    private suspend fun requestGoogleCredential(context: Context): GoogleIdTokenCredential {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(webClientId)
            .setAutoSelectEnabled(false)
            .build()
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        val credentialResponse = credentialManagerFactory(context).getCredential(context, request)
        val credential = credentialResponse.credential

        if (
            credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) {
            return GoogleIdTokenCredential.createFrom(credential.data)
        }

        throw IllegalStateException("Google 인증 응답 형식이 올바르지 않습니다.")
    }
}
