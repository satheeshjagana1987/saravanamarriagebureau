package com.saravanamarriagebureau.ui.screens.membership

import com.saravanamarriagebureau.data.model.MembershipDetails

sealed class MemberShipDetailsUIState {

    data object Idle : MemberShipDetailsUIState()

    data object Loading : MemberShipDetailsUIState()

    data class Success(
        val data: MembershipDetails,
        val message: String? = null
    ) : MemberShipDetailsUIState()

    data class Error(
        val message: String
    ) : MemberShipDetailsUIState()
}