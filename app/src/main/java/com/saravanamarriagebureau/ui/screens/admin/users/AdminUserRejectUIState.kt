package com.saravanamarriagebureau.ui.screens.admin.users

import com.saravanamarriagebureau.data.model.RegistrationRejectionData

sealed class AdminUserRejectUIState {


    data object Loading : AdminUserRejectUIState()

    data class Success(
        val data: RegistrationRejectionData,
        val message: String? = null
    ) : AdminUserRejectUIState()

    data class Error(
        val message: String
    ) : AdminUserRejectUIState()

    data object Idle : AdminUserRejectUIState()
}