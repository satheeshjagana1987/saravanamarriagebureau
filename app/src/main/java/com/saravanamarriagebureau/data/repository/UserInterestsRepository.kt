package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.UserInterestsData
import com.saravanamarriagebureau.data.model.UserInterestsResponse
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class UserInterestsRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun getSentInterests(
    ): ApiResult<List<UserInterestsData>> {
        return safeApiCall {
            api.getSentInterests()
        }
    }

    suspend fun getReceivedInterests(
    ): ApiResult<List<UserInterestsData>> {
        return safeApiCall {
            api.getReceivedInterests()
        }
    }

    suspend fun acceptReceivedInterest(
        interestId: String
    ): ApiResult<List<UserInterestsData>> {
        return safeApiCall {
            api.acceptReceivedInterest(
                interestId = interestId
            )
        }
    }

    suspend fun rejectReceivedInterest(
        interestId: String
    ): ApiResult<List<UserInterestsData>> {
        return safeApiCall {
            api.rejectReceivedInterest(
                interestId = interestId
            )
        }
    }
}