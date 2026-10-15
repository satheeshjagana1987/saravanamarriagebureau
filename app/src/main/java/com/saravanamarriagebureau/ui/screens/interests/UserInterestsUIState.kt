package com.saravanamarriagebureau.ui.screens.interests

import com.saravanamarriagebureau.data.model.UserInterestsData

sealed class UserInterestsUIState {

    data object Idle : UserInterestsUIState()

    data object Loading : UserInterestsUIState()

    data class Success(
        val data: List<UserInterestsData>,
        val message: String? = null
    ) : UserInterestsUIState()

    data class Error(
        val message: String
    ) : UserInterestsUIState()

}