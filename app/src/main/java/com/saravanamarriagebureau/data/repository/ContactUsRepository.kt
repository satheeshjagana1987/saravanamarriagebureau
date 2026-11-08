package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.EnquiryItem
import com.saravanamarriagebureau.data.model.SubmitEnquiryRequest
import com.saravanamarriagebureau.data.model.SubmitEnquiryResponseData
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class ContactUsRepository @Inject constructor(
    private val api: SmbApi
) {

    suspend fun getEnquiries(
    ): ApiResult<List<EnquiryItem>> {
        return safeApiCall {
            api.getEnquiries()
        }
    }

    suspend fun getEnquiryById(
        enquiryId: String
    ): ApiResult<EnquiryItem> {
        return safeApiCall {
            api.getEnquiryById(
                enquiryId = enquiryId
            )
        }
    }

    suspend fun submitEnquiry(
        fullName: String,
        mobileNumber: String,
        email: String,
        subject: String,
        message: String,
    ): ApiResult<SubmitEnquiryResponseData> {
        return safeApiCall {
            api.submitEnquiry(
                request = SubmitEnquiryRequest(
                    fullName = fullName,
                    mobileNumber = mobileNumber,
                    emailAddress = email,
                    subject = subject,
                    message = message
                )
            )
        }
    }
}