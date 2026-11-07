package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.AdminEnquiryStatusUpdateRequest
import com.saravanamarriagebureau.data.model.EnquiryItem
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class AdminEnquiriesRepository @Inject constructor(
    private val api: SmbApi
) {

    suspend fun getAdminEnquiries(
    ): ApiResult<List<EnquiryItem>> {
        return safeApiCall {
            api.getAdminEnquiries()
        }
    }

    suspend fun getAdminEnquiryById(
        enquiryId: String
    ): ApiResult<EnquiryItem> {
        return safeApiCall {
            api.getAdminEnquiryById(
                enquiryId = enquiryId
            )
        }
    }

    suspend fun updateAdminEnquiryById(
        enquiryId: String,
        comment: String,
    ): ApiResult<EnquiryItem> {
        return safeApiCall {
            api.updateAdminEnquiryById(
                enquiryId = enquiryId,
                request = AdminEnquiryStatusUpdateRequest(
                    status = "RESOLVED",
                    comment = comment
                )
            )
        }
    }
}