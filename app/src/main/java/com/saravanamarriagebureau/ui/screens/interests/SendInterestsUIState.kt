package com.saravanamarriagebureau.ui.screens.interests

import com.saravanamarriagebureau.data.model.UserInterestsData
import com.saravanamarriagebureau.data.model.UserInterestsResponse

sealed class SendInterestsUIState {

    data object Idle : SendInterestsUIState()

    data object Loading : SendInterestsUIState()

    data class Success(
        val data: UserInterestsResponse,
        val message: String? = null
    ) : SendInterestsUIState()

    data class Error(
        val message: String
    ) : SendInterestsUIState()
}