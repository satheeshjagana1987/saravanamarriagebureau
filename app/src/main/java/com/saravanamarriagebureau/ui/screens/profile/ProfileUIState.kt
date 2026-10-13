package com.saravanamarriagebureau.ui.screens.profile

import com.saravanamarriagebureau.data.model.ProfileData

sealed class ProfileUIState {

    data object Idle : ProfileUIState()

    data object Loading : ProfileUIState()

    data class Success(
        val data: ProfileData,
        val message: String? = null
    ) : ProfileUIState()

    data class Error(
        val message: String
    ) : ProfileUIState()
}

/**
 * Separate state for the "update/submit profile" action so a successful
 * profile *load* is never mistaken for a successful *submit* (which navigates away).
 */
sealed class ProfileUpdateUIState {

    data object Idle : ProfileUpdateUIState()

    data object Loading : ProfileUpdateUIState()

    data class Success(
        val message: String? = null
    ) : ProfileUpdateUIState()

    data class Error(
        val message: String
    ) : ProfileUpdateUIState()
}
