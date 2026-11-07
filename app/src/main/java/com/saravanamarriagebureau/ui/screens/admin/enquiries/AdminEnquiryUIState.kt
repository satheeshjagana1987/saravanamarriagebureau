package com.saravanamarriagebureau.ui.screens.admin.enquiries

import com.saravanamarriagebureau.data.model.EnquiryItem

sealed class AdminEnquiryUIState {

    data object Idle : AdminEnquiryUIState()

    data object Loading : AdminEnquiryUIState()

    data class Success(
        val data: EnquiryItem,
        val message: String? = null
    ) : AdminEnquiryUIState()

    data class Error(
        val message: String
    ) : AdminEnquiryUIState()
}