package com.saravanamarriagebureau.ui.screens.admin.users

import com.saravanamarriagebureau.data.model.RegistrationApprovalData

sealed class AdminUserApproveUIState {

    data object Loading : AdminUserApproveUIState()

    data class Success(
        val data: RegistrationApprovalData,
        val message: String? = null
    ) : AdminUserApproveUIState()

    data class Error(
        val message: String
    ) : AdminUserApproveUIState()

    data object Idle : AdminUserApproveUIState()

}