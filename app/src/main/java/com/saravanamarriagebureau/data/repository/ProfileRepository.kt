package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.enums.Gender
import com.saravanamarriagebureau.data.model.Profile
import com.saravanamarriagebureau.data.model.ProfileResponse
import com.saravanamarriagebureau.data.model.RegisterRequest
import com.saravanamarriagebureau.data.model.RegisterResponse
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun getProfile(): ApiResult<ProfileResponse> {
        return safeApiCall {
            api.getProfile()
        }
    }

    suspend fun updateProfile(
         name: String,
         age: String,
         height: String,
         education: String,
         profession: String,
         location: String,
         imageUrl: String? = null,
         mobileNumber: String,
         religion: String,
         gender: String,
         email: String
    ): ApiResult<ProfileResponse> {
        return safeApiCall {
            api.updateProfile(
                Profile(
                    name = name,
                    age = age,
                    height = height,
                    education = education,
                    profession = profession,
                    location = location,
                    imageUrl = imageUrl,
                    mobileNumber = mobileNumber,
                    email = email,
                    gender = gender,
                    religion = religion
                )
            )
        }
    }
}