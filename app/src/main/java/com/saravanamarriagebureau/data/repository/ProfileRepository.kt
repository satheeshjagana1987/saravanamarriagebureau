package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.enums.Gender
import com.saravanamarriagebureau.data.model.ProfileData
import com.saravanamarriagebureau.data.model.ProfileRequest
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun getProfile(
    ): ApiResult<ProfileData> {
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
         city: String,
         district: String,
         maritalStatus: String
    ): ApiResult<ProfileData> {
        return safeApiCall {
            api.updateProfile(
                ProfileRequest(
                    height = height,
                    education = education,
                    profession = profession,
                    religion = religion,
                    fullAddress = "",
                    email = email,
                    city = city,
                    district = district,
                    maritalStatus = maritalStatus
                )
            )
        }
    }
}