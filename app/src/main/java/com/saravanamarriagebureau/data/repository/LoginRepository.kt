package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.LoginRequest
import com.saravanamarriagebureau.data.model.LoginResponse
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun login(
        mobile: String,
        password: String
    ): ApiResult<LoginResponse> {
        return safeApiCall {
            api.login(
                LoginRequest(
                    mobile = mobile,
                    password = password
                )
            )
        }
    }
}