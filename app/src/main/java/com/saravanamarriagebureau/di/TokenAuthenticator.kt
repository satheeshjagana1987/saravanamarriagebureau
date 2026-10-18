package com.saravanamarriagebureau.di

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.RefreshTokenRequest
import com.saravanamarriagebureau.util.AppPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

/**
 * Called by OkHttp automatically whenever a response comes back with HTTP 401.
 *
 * It tries to obtain a fresh access token using the stored refresh token and, on
 * success, returns the original request rebuilt with the new token so OkHttp can
 * retry it once. If the refresh fails (or there is no refresh token), it returns
 * null which tells OkHttp to give up, and the stored session is cleared so the app
 * can route the user back to login.
 *
 * Notes:
 * - [refreshApi] is backed by a SEPARATE OkHttpClient that has neither this
 *   authenticator nor the auth interceptor attached, so a 401 on the refresh
 *   endpoint cannot recurse back into here.
 * - A Provider is used to break the dependency cycle (this authenticator is part
 *   of the main OkHttpClient, so it cannot eagerly depend on anything built from
 *   that same client).
 * - [onSessionExpired] is invoked when refresh is impossible; hook your global
 *   "force logout / go to login" behaviour there.
 */
class TokenAuthenticator @Inject constructor(
    private val appPreferences: AppPreferences,
    @RefreshApi private val refreshApi: Provider<SmbApi>,
    private val onSessionExpired: SessionExpiredHandler
) : Authenticator {

    private val lock = Any()

    override fun authenticate(route: Route?, response: Response): Request? {

        // If we have already retried this request once with a token, stop.
        if (responseCount(response) >= 2) {
            Timber.e("TokenAuthenticator: already retried once, giving up")
            return null
        }

        val refreshToken = appPreferences.getRefreshToken()
        if (refreshToken.isNullOrBlank()) {
            Timber.e("TokenAuthenticator: no refresh token, session expired")
            handleSessionExpired()
            return null
        }

        synchronized(lock) {
            // The token on the request that just failed.
            val failedToken = response.request.header("Authorization")
                ?.removePrefix("Bearer ")
                ?.trim()

            val currentToken = appPreferences.getAccessToken()

            // Another request may have already refreshed the token while this one
            // was waiting on the lock. If so, just retry with the current token.
            if (!currentToken.isNullOrBlank() && currentToken != failedToken) {
                Timber.i("TokenAuthenticator: token already refreshed by another call")
                return response.request.newBuilder()
                    .header("Authorization", "Bearer $currentToken")
                    .build()
            }

            // Perform the refresh. The authenticator runs on OkHttp's background
            // dispatcher thread, so a blocking network call here is acceptable.
            val newAccessToken = runCatching {
                runBlocking {
                    refreshApi.get().refreshToken(
                        RefreshTokenRequest(refreshToken = refreshToken)
                    )
                }
            }.getOrNull()

            val body = newAccessToken?.body()
            if (newAccessToken?.isSuccessful == true && body?.data != null) {
                val data = body.data
                appPreferences.saveAccessToken(data.accessToken)
                appPreferences.saveRefreshToken(data.refreshToken)
                Timber.i("TokenAuthenticator: token refreshed, retrying request")

                return response.request.newBuilder()
                    .header("Authorization", "Bearer ${data.accessToken}")
                    .build()
            }

            Timber.e("TokenAuthenticator: refresh failed, session expired")
            handleSessionExpired()
            return null
        }
    }

    private fun handleSessionExpired() {
        appPreferences.clearPreferences()
        onSessionExpired.onSessionExpired()
    }

    private fun responseCount(response: Response): Int {
        var result = 1
        var prior = response.priorResponse
        while (prior != null) {
            result++
            prior = prior.priorResponse
        }
        return result
    }
}
