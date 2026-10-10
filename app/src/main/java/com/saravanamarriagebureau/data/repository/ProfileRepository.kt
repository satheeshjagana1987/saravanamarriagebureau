package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.enums.Gender
import com.saravanamarriagebureau.data.model.Profile
import com.saravanamarriagebureau.data.model.ProfileRequest
import com.saravanamarriagebureau.data.model.ProfileResponse
import com.saravanamarriagebureau.data.model.RegisterRequest
import com.saravanamarriagebureau.data.model.RegisterResponse
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun getProfile(
    ): ApiResult<ProfileResponse> {
        return safeApiCall {
            api.getProfile()
        }
    }

    suspend fun updateProfile(
         height: String,
         education: String,
         profession: String,
         religion: String,
         email: String,
         fullAddress: String,
    ): ApiResult<ProfileResponse> {
        return safeApiCall {
            api.updateProfile(
                ProfileRequest(
                    height = height,
                    education = education,
                    profession = profession,
                    religion = religion,
                    fullAddress = fullAddress,
                    email = email,
                )
            )
        }
    }
}