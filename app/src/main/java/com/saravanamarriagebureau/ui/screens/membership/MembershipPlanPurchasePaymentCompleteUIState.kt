package com.saravanamarriagebureau.ui.screens.membership

import com.saravanamarriagebureau.data.model.MembershipPlanPurchasePaymentCompleteData

sealed class MembershipPlanPurchasePaymentCompleteUIState {

    data object Idle : MembershipPlanPurchasePaymentCompleteUIState()

    data object Loading : MembershipPlanPurchasePaymentCompleteUIState()

    data class Success(
        val data: MembershipPlanPurchasePaymentCompleteData,
        val message: String? = null
    ) : MembershipPlanPurchasePaymentCompleteUIState()

    data class Error(
        val message: String
    ) : MembershipPlanPurchasePaymentCompleteUIState()
}