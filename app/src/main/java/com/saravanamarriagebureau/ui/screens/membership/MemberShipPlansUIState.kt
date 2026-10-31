package com.saravanamarriagebureau.ui.screens.membership

import com.saravanamarriagebureau.data.model.PlanData

sealed class MemberShipPlansUIState {


    data object Idle : MemberShipPlansUIState()

    data object Loading : MemberShipPlansUIState()

    data class Success(
        val data: List<PlanData>,
        val message: String? = null
    ) : MemberShipPlansUIState()

    data class Error(
        val message: String
    ) : MemberShipPlansUIState()
}