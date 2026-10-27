package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.AdminUserDetailsResponse
import com.saravanamarriagebureau.data.model.PendingApprovalUser
import com.saravanamarriagebureau.data.model.ProfileData
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class AdminUsersRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun getAdminUsers(): ApiResult<List<PendingApprovalUser>> {
        return safeApiCall {
            api.getAdminUsers()
        }
    }

    suspend fun getAdminUserById(
        profileId: String
    ): ApiResult<AdminUserDetailsResponse> {
        return safeApiCall {
            api.getAdminUserById(
                userId = profileId
            )
        }
    }
}