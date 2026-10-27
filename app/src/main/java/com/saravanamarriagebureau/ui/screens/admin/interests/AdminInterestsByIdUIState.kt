package com.saravanamarriagebureau.ui.screens.admin.interests

import com.saravanamarriagebureau.data.model.AdminInterestsData

sealed class AdminInterestsByIdUIState {

    data object Idle : AdminInterestsByIdUIState()

    data object Loading : AdminInterestsByIdUIState()

    data class Success(
        val data: AdminInterestsData,
        val message: String? = null
    ) : AdminInterestsByIdUIState()

    data class Error(
        val message: String
    ) : AdminInterestsByIdUIState()
}