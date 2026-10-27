package com.saravanamarriagebureau.ui.screens.admin.users

import com.saravanamarriagebureau.data.model.PendingApprovalUser

sealed class AdminUsersUIState {


    data object Idle : AdminUsersUIState()

    data object Loading : AdminUsersUIState()

    data class Success(
        val data: List<PendingApprovalUser>,
        val message: String? = null
    ) : AdminUsersUIState()

    data class Error(
        val message: String
    ) : AdminUsersUIState()

}