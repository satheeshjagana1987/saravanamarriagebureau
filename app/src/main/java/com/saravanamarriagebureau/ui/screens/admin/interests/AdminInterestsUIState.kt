package com.saravanamarriagebureau.ui.screens.admin.interests

import com.saravanamarriagebureau.data.model.AdminInterestsData
import com.saravanamarriagebureau.data.model.PendingApprovalUser
import com.saravanamarriagebureau.ui.screens.admin.users.AdminUsersUIState

sealed class AdminInterestsUIState {

    data object Idle : AdminInterestsUIState()

    data object Loading : AdminInterestsUIState()

    data class Success(
        val data: List<AdminInterestsData>,
        val message: String? = null
    ) : AdminInterestsUIState()

    data class Error(
        val message: String
    ) : AdminInterestsUIState()
}