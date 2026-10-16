package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.LoginData
import com.saravanamarriagebureau.data.model.LoginRequest
import com.saravanamarriagebureau.data.model.RefreshTokenRequest
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun login(
        mobile: String,
        password: String
    ): ApiResult<LoginData> {
        return safeApiCall {
            api.login(
                LoginRequest(
                    identifier = mobile,
                    password = password
                )
            )
        }
    }

    suspend fun refreshToken(
        refreshToken: String
    ): ApiResult<LoginData> {
        return safeApiCall {
            api.refreshToken(
                RefreshTokenRequest(
                    refreshToken = refreshToken,
                )
            )
        }
    }
}