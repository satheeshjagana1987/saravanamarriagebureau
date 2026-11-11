package com.saravanamarriagebureau.ui.screens.admin.users

import com.saravanamarriagebureau.data.model.BlockedUserResponseData
import com.saravanamarriagebureau.data.model.RegistrationRejectionData

sealed class AdminUserBlockedUIState {

    data object Loading : AdminUserBlockedUIState()

    data class Success(
        val data: BlockedUserResponseData,
        val message: String? = null
    ) : AdminUserBlockedUIState()

    data class Error(
        val message: String
    ) : AdminUserBlockedUIState()

    data object Idle : AdminUserBlockedUIState()
}