package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.VerifyResetOTP
import com.saravanamarriagebureau.data.model.VerifyResetOTPRequest
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class VerifyResetOTPRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun verifyResetOTPPassword(
        mobile: String,
        otp: String,
    ): ApiResult<VerifyResetOTP> {
        return safeApiCall {
            api.verifyResetOTP(
                VerifyResetOTPRequest(
                    mobile = mobile,
                    otp = otp
                )
            )
        }
    }
}