package com.saravanamarriagebureau.data.api

import com.saravanamarriagebureau.data.model.ApiResponse
import com.saravanamarriagebureau.data.model.ChangePasswordRequest
import com.saravanamarriagebureau.data.model.ForgetPasswordRequest
import com.saravanamarriagebureau.data.model.ForgetPasswordResponse
import com.saravanamarriagebureau.data.model.Interest
import com.saravanamarriagebureau.data.model.Invoice
import com.saravanamarriagebureau.data.model.LoginData
import com.saravanamarriagebureau.data.model.LoginRequest
import com.saravanamarriagebureau.data.model.Match
import com.saravanamarriagebureau.data.model.MediaUploadCompleteResponse
import com.saravanamarriagebureau.data.model.MediaUploadInitiatedRequest
import com.saravanamarriagebureau.data.model.MediaUploadInitiatedResponse
import com.saravanamarriagebureau.data.model.ProfileData
import com.saravanamarriagebureau.data.model.ProfileRequest
import com.saravanamarriagebureau.data.model.RegisterRequest
import com.saravanamarriagebureau.data.model.RegisterResponse
import com.saravanamarriagebureau.data.model.ResetPasswordRequest
import com.saravanamarriagebureau.data.model.ResetPasswordResponse
import com.saravanamarriagebureau.data.model.UploadResponse
import com.saravanamarriagebureau.data.model.UserInterestsData
import com.saravanamarriagebureau.data.model.VerifyResetOTP
import com.saravanamarriagebureau.data.model.VerifyResetOTPRequest
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface SmbApi {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<ApiResponse<LoginData>>

    @POST("registration/submit")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<ApiResponse<RegisterResponse>>

    @GET("profile/me")
    suspend fun getProfile(): Response<ApiResponse<ProfileData>>

    @PATCH("profile/me")
    suspend fun updateProfile(
        @Body request: ProfileRequest
    ): Response<ApiResponse<ProfileData>>

    @POST("auth/forgot-password")
    suspend fun forgetPassword(@Body request: ForgetPasswordRequest): Response<ApiResponse<ForgetPasswordResponse>>

    @POST("auth/verify-reset-otp")
    suspend fun verifyResetOTP(@Body request: VerifyResetOTPRequest): Response<ApiResponse<VerifyResetOTP>>

    @POST("auth/reset-password")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): Response<ApiResponse<ResetPasswordResponse>>

    @POST("auth/changePassword")
    suspend fun changePassword(@Body request: ChangePasswordRequest): Response<ApiResponse<ForgetPasswordResponse>>

    @POST("profile/media/upload")
    suspend fun mediaUploadInitiated(@Body request: MediaUploadInitiatedRequest): Response<ApiResponse<MediaUploadInitiatedResponse>>

    @Multipart
    @PUT("")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ): Response<UploadResponse>

    @POST("profile/media/{mediaId}/complete")
    suspend fun mediaUploadCompleted(
        @Path("mediaId") mediaId: String
    ): Response<ApiResponse<MediaUploadCompleteResponse>>

    @GET("matches")
    suspend fun getMatches(): Response<ApiResponse<List<Match>>>

    @GET("interests/sent")
    suspend fun getSentInterests(): Response<ApiResponse<List<UserInterestsData>>>

    @GET("interests/received")
    suspend fun getReceivedInterests(): Response<ApiResponse<List<UserInterestsData>>>

    @GET("matches/{id}")
    suspend fun getMatch(@Path("id") id: String)

    @GET("interests/{id}")
    suspend fun getInterest(@Path("id") id: String)

    @GET("invoices/{id}")
    suspend fun getInvoice(@Path("id") id: String): Invoice

}
