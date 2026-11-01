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
        isMatchFixed: Boolean,
        matchRejectionReason:String
    ): ApiResult<AdminInterestsData> {
        return safeApiCall {
            api.adminApproveOrRejectMatch(
                request = AdminApproveOrRejectMatchRequest(
                    isMatchFixed = isMatchFixed,
                    matchRejectRemarks = matchRejectionReason
                )
            )
        }
    }
}