package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.RegisterRequest
import com.saravanamarriagebureau.data.model.RegisterResponse
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun register(
        name: String,
        mobile: String,
        email: String,
        dob: String,
        password: String,
        confirmPassword: String,
        gender: String,
    ): ApiResult<RegisterResponse> {
        return safeApiCall {
            api.register(
                RegisterRequest(
                    fullName = name,
                    mobile = mobile,
                    email = email,
                    dateOfBirth = dob,
                    password = password,
                    confirmPassword = confirmPassword,
                    gender = gender
                )
            )
        }
    }
}