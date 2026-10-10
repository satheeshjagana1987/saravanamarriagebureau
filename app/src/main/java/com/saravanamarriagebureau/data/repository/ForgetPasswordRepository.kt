package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.ForgetPasswordRequest
import com.saravanamarriagebureau.data.model.ForgetPasswordResponse
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class ForgetPasswordRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun forgetPassword(
        mobile: String,
    ): ApiResult<ForgetPasswordResponse> {
        return safeApiCall {
            api.forgetPassword(
                ForgetPasswordRequest(
                    mobile = mobile,
                )
            )
        }
    }
}