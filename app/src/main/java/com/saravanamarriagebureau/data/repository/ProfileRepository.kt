package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.Profile
import com.saravanamarriagebureau.data.model.ProfileResponse
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun getProfile(): ApiResult<ProfileResponse> {
        return safeApiCall {
            api.getProfile()
        }
    }
}