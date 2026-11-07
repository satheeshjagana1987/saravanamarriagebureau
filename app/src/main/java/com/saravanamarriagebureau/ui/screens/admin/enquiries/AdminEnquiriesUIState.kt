package com.saravanamarriagebureau.ui.screens.admin.enquiries

import com.saravanamarriagebureau.data.model.EnquiryItem

sealed class AdminEnquiriesUIState {

    data object Idle : AdminEnquiriesUIState()

    data object Loading : AdminEnquiriesUIState()

    data class Success(
        val data: List<EnquiryItem>,
        val message: String? = null
    ) : AdminEnquiriesUIState()

    data class Error(
        val message: String
    ) : AdminEnquiriesUIState()
}