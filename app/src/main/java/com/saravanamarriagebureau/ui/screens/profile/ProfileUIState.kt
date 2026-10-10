package com.saravanamarriagebureau.ui.screens.profile

import com.saravanamarriagebureau.data.model.ProfileData
import com.saravanamarriagebureau.data.model.ProfileResponse

sealed class ProfileUIState {

    data object Idle : ProfileUIState()

    data object Loading : ProfileUIState()

    data class Success(
        val data: ProfileResponse,
        val message: String? = null
    ) : ProfileUIState()

    data class Error(
        val message: String
    ) : ProfileUIState()
}