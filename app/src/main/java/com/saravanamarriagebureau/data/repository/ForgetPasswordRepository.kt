package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.ForgetPasswordRequest
import com.saravanamarriagebureau.data.model.PasswordResponse
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class ForgetPasswordRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun forgetPassword(
        otp: String,
        password: String,
        confirmPassword: String
    ): ApiResult<PasswordResponse> {
        return safeApiCall {
            api.forgetPassword(
                ForgetPasswordRequest(
                    otp = otp,
                    password = password,
                    confirmPassword = confirmPassword
                )
            )
        }
    }
}