package com.saravanamarriagebureau.ui.screens.membership

import com.saravanamarriagebureau.data.model.MembershipPlanPurchaseData

sealed class MemberShipPlanPurchaseUiState {

    data object Idle : MemberShipPlanPurchaseUiState()

    data object Loading : MemberShipPlanPurchaseUiState()

    data class Success(
        val data: MembershipPlanPurchaseData,
        val message: String? = null
    ) : MemberShipPlanPurchaseUiState()

    data class Error(
        val message: String
    ) : MemberShipPlanPurchaseUiState()
}