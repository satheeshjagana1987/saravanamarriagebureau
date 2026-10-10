package com.saravanamarriagebureau.ui.screens.forgetpassword

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.ForgetPasswordRequest
import com.saravanamarriagebureau.data.model.ForgetPasswordResponse
import com.saravanamarriagebureau.data.model.ResetPasswordRequest
import com.saravanamarriagebureau.data.model.ResetPasswordResponse
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class ResetPasswordRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun resetPassword(
        resetToken: String,
        newPassword: String,
        confirmPassword: String,
    ): ApiResult<ResetPasswordResponse> {
        return safeApiCall {
            api.resetPassword(
                ResetPasswordRequest(
                    resetToken = resetToken,
                    newPassword = newPassword,
                    confirmPassword = confirmPassword
                )
            )
        }
    }
}