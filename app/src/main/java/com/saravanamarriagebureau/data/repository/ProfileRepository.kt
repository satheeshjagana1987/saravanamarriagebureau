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

    suspend fun getProfileById(
        profileId: String
    ): ApiResult<ProfileData> {
        return safeApiCall {
            api.getProfileById(
                profileId = profileId
            )
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
         maritalStatus: String,
         placeOfBirth: String,
         birthTime: String,
         birthStar: String,
         rasi: String,
         workLocation: String,
         nativePlace: String,
         familyDetails: String,
         income: String
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
                    maritalStatus = maritalStatus,
                    placeOfBirth = placeOfBirth,
                    birthTime = birthTime,
                    birthStar = birthStar,
                    rasi = rasi,
                    workLocation = workLocation,
                    nativePlace = nativePlace,
                    familyDetails = familyDetails,
                    income = income
                )
            )
        }
    }
}