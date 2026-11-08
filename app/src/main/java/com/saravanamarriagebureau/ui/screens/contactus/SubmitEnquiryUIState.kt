package com.saravanamarriagebureau.ui.screens.contactus

import com.saravanamarriagebureau.data.model.SubmitEnquiryResponseData

sealed class SubmitEnquiryUIState {

    data object Idle : SubmitEnquiryUIState()

    data object Loading : SubmitEnquiryUIState()

    data class Success(
        val data: SubmitEnquiryResponseData,
        val message: String? = null
    ) : SubmitEnquiryUIState()

    data class Error(
        val message: String
    ) : SubmitEnquiryUIState()
}