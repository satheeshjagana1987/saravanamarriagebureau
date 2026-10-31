package com.saravanamarriagebureau.ui.screens.admin.membership

import com.saravanamarriagebureau.data.model.AddMembershipPlanData

sealed class AddAdminMembershipPlanUIState {

    data object Idle : AddAdminMembershipPlanUIState()

    data object Loading : AddAdminMembershipPlanUIState()

    data class Success(
        val data: AddMembershipPlanData,
        val message: String? = null
    ) : AddAdminMembershipPlanUIState()

    data class Error(
        val message: String
    ) : AddAdminMembershipPlanUIState()
}