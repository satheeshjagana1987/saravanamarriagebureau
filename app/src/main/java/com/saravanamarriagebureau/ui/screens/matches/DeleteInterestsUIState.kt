package com.saravanamarriagebureau.ui.screens.matches

import com.saravanamarriagebureau.data.model.UserInterestsResponse

sealed class DeleteInterestsUIState {

    data object Idle : DeleteInterestsUIState()

    data object Loading : DeleteInterestsUIState()

    data class Success(
        val data: UserInterestsResponse,
        val message: String? = null
    ) : DeleteInterestsUIState()

    data class Error(
        val message: String
    ) : DeleteInterestsUIState()
}