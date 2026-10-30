package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.AcceptRequest
import com.saravanamarriagebureau.data.model.AdminUserDetails
import com.saravanamarriagebureau.data.model.AdminUserDetailsResponse
import com.saravanamarriagebureau.data.model.PendingApprovalUser
import com.saravanamarriagebureau.data.model.ProfileData
import com.saravanamarriagebureau.data.model.RegistrationApprovalData
import com.saravanamarriagebureau.data.model.RegistrationRejectionData
import com.saravanamarriagebureau.data.model.RejectRequest
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class AdminUsersRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun getAdminPendingUsers(): ApiResult<List<PendingApprovalUser>> {
        return safeApiCall {
            api.getAdminPendingUsers()
        }
    }

    suspend fun getAdminApprovedUsers(): ApiResult<List<PendingApprovalUser>> {
        return safeApiCall {
            api.getAdminApprovedUsers()
        }
    }

    suspend fun getAdminRejectedUsers(): ApiResult<List<PendingApprovalUser>> {
        return safeApiCall {
            api.getAdminRejectedUsers()
        }
    }

    suspend fun getAdminUserById(
        profileId: String
    ): ApiResult<AdminUserDetails> {
        return safeApiCall {
            api.getAdminUserById(
                userId = profileId
            )
        }
    }

    suspend fun approveUser(
        userId: String,
        request: AcceptRequest
    ): ApiResult<RegistrationApprovalData> {
        return safeApiCall {
         api.approveAdminUser(
             userId = userId,
             request = request
         )
        }
    }

    suspend fun rejectUser(
        userId: String,
        request: RejectRequest
    ): ApiResult<RegistrationRejectionData> {
        return safeApiCall {
            api.rejectAdminUser(
                userId = userId,
                request = request
            )
        }
    }
}