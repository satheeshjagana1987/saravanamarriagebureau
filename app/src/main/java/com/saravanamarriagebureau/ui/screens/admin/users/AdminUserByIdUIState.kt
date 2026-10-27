package com.saravanamarriagebureau.ui.screens.admin.users

import com.saravanamarriagebureau.data.model.AdminUserDetailsResponse
import com.saravanamarriagebureau.data.model.ProfileData

sealed class AdminUserByIdUIState {

    data object Idle : AdminUserByIdUIState()

    data object Loading : AdminUserByIdUIState()

    data class Success(
        val data: AdminUserDetailsResponse,
        val message: String? = null
    ) : AdminUserByIdUIState()

    data class Error(
        val message: String
    ) : AdminUserByIdUIState()
}