package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.AdminApproveOrRejectMatchRequest
import com.saravanamarriagebureau.data.model.AdminInterestsData
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class AdminInterestsRepository @Inject constructor(
    private val api: SmbApi
) {

    suspend fun getAdminPendingInterests(): ApiResult<List<AdminInterestsData>> {
        return safeApiCall {
            api.getAdminPendingInterests()
        }
    }

    suspend fun getAdminAcceptedInterests(): ApiResult<List<AdminInterestsData>> {
        return safeApiCall {
            api.getAdminAcceptedInterests()
        }
    }

    suspend fun getAdminRejectedInterests(): ApiResult<List<AdminInterestsData>> {
        return safeApiCall {
            api.getAdminRejectedInterests()
        }
    }

    suspend fun getAdminInterestById(
        interestId: String
    ): ApiResult<AdminInterestsData> {
        return safeApiCall {
            api.getAdminInterestById(
                interestId = interestId
            )
        }
    }

    suspend fun adminApproveOrRejectMatch(
        interestId:String,
        isMatchFixed: Boolean,
        matchRejectionReason:String
    ): ApiResult<AdminInterestsData> {
        return safeApiCall {
            api.adminApproveOrRejectMatch(
                interestId = interestId,
                request = AdminApproveOrRejectMatchRequest(
                    isMatchFixed = isMatchFixed,
                    matchRejectRemarks = matchRejectionReason
                )
            )
        }
    }
}