package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.Match
import com.saravanamarriagebureau.data.model.MatchesData
import com.saravanamarriagebureau.data.model.UserInterestsResponse
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class MatchesRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun getTodayMatches(): ApiResult<List<MatchesData>> {
        return safeApiCall {
            api.getTodayMatches()
        }
    }

    suspend fun getPastMatches(): ApiResult<List<MatchesData>> {
        return safeApiCall {
            api.getPastMatches()
        }
    }

    suspend fun sendInterest(
        profileId: String
    ): ApiResult<UserInterestsResponse> {
        return safeApiCall {
            api.sendInterest(profileId = profileId)
        }
    }

    suspend fun deleteInterest(
        interestId: String
    ): ApiResult<UserInterestsResponse> {
        return safeApiCall {
            api.deleteInterest(interestId = interestId)
        }
    }
}