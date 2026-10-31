package com.saravanamarriagebureau.ui.screens.admin.membership

import com.saravanamarriagebureau.data.model.DeleteMembershipPlanData

sealed class DeleteAdminMembershipPlanUIState {

    data object Idle : DeleteAdminMembershipPlanUIState()

    data object Loading : DeleteAdminMembershipPlanUIState()

    data class Success(
        val data: DeleteMembershipPlanData,
        val message: String? = null
    ) : DeleteAdminMembershipPlanUIState()

    data class Error(
        val message: String
    ) : DeleteAdminMembershipPlanUIState()
}