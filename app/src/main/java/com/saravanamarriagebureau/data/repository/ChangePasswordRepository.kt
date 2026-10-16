package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.ChangePasswordRequest
import com.saravanamarriagebureau.data.model.ForgetPasswordResponse
import com.saravanamarriagebureau.data.model.ResetPasswordResponse
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class ChangePasswordRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun changePassword(
        currentPassword: String,
        newPassword: String,
        confirmNewPassword: String
    ): ApiResult<ResetPasswordResponse> {
        return safeApiCall {
            api.changePassword(
                ChangePasswordRequest(
                    oldPassword = currentPassword,
                    newPassword = newPassword,
                    confirmPassword = confirmNewPassword
                )
            )
        }
    }
}