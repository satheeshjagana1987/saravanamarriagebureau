package com.saravanamarriagebureau.ui.screens.admin.membership

import com.saravanamarriagebureau.data.model.AddMembershipPlanData

sealed class AdminMembershipPlansUIState {

    data object Idle : AdminMembershipPlansUIState()

    data object Loading : AdminMembershipPlansUIState()

    data class Success(
        val data: List<AddMembershipPlanData>,
        val message: String? = null
    ) : AdminMembershipPlansUIState()

    data class Error(
        val message: String
    ) : AdminMembershipPlansUIState()
}
