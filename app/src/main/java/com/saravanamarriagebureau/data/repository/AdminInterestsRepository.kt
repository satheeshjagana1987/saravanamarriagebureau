package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.AdminInterestsData
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class AdminInterestsRepository @Inject constructor(
    private val api: SmbApi
) {

    suspend fun getAdminInterests(): ApiResult<List<AdminInterestsData>> {
        return safeApiCall {
            api.getAdminInterests()
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
}