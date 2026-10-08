package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.Interest
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class InterestsRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun getInterests(): ApiResult<List<Interest>> {
        return safeApiCall {
            api.getInterests()
        }
    }
}