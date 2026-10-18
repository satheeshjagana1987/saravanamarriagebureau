package com.saravanamarriagebureau.di

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * App-wide signal that the session has expired and refresh was not possible.
 *
 * The TokenAuthenticator calls [onSessionExpired] from a background thread when a
 * token refresh fails. Observe [events] at the app's top level (e.g. in the root
 * composable / navigation host) to force the user back to the login screen.
 */
@Singleton
class SessionExpiredHandler @Inject constructor() {

    private val _events = MutableSharedFlow<Unit>(
        replay = 0,
        extraBufferCapacity = 1
    )
    val events: SharedFlow<Unit> = _events.asSharedFlow()

    fun onSessionExpired() {
        // tryEmit is safe to call from any thread and never suspends.
        _events.tryEmit(Unit)
    }
}
