package com.saravanamarriagebureau.ui.screens.interests

import com.saravanamarriagebureau.data.model.UserInterestsData

sealed class UserInterestsAcceptRejectUIState {

    data object Idle : UserInterestsAcceptRejectUIState()

    data object Loading : UserInterestsAcceptRejectUIState()

    data class Success(
        val data: UserInterestsData,
        val message: String? = null
    ) : UserInterestsAcceptRejectUIState()

    data class Error(
        val message: String
    ) : UserInterestsAcceptRejectUIState()
}