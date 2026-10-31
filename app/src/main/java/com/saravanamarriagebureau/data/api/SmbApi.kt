package com.saravanamarriagebureau.data.api

import com.saravanamarriagebureau.data.model.AcceptRequest
import com.saravanamarriagebureau.data.model.AdminInterestsData
import com.saravanamarriagebureau.data.model.AdminInterestsResponse
import com.saravanamarriagebureau.data.model.AdminUserDetails
import com.saravanamarriagebureau.data.model.AdminUserDetailsResponse
import com.saravanamarriagebureau.data.model.ApiResponse
import com.saravanamarriagebureau.data.model.ChangePasswordRequest
import com.saravanamarriagebureau.data.model.DeleteMediaResponse
import com.saravanamarriagebureau.data.model.ForgetPasswordRequest
import com.saravanamarriagebureau.data.model.ForgetPasswordResponse
import com.saravanamarriagebureau.data.model.Invoice
import com.saravanamarriagebureau.data.model.LoginData
import com.saravanamarriagebureau.data.model.LoginRequest
import com.saravanamarriagebureau.data.model.LoginResponse
import com.saravanamarriagebureau.data.model.MatchesData
import com.saravanamarriagebureau.data.model.MediaUploadCompleteResponse
import com.saravanamarriagebureau.data.model.MediaUploadInitiatedRequest
import com.saravanamarriagebureau.data.model.MediaUploadInitiatedResponse
import com.saravanamarriagebureau.data.model.MembershipDetails
import com.saravanamarriagebureau.data.model.MembershipPlanPurchaseData
import com.saravanamarriagebureau.data.model.MembershipPlanPurchasePaymentCompleteData
import com.saravanamarriagebureau.data.model.MembershipPlanPurchasePaymentCompleteRequest
import com.saravanamarriagebureau.data.model.MembershipPlanPurchaseRequest
import com.saravanamarriagebureau.data.model.PendingApprovalUser
import com.saravanamarriagebureau.data.model.PlanData
import com.saravanamarriagebureau.data.model.ProfileData
import com.saravanamarriagebureau.data.model.ProfileRequest
import com.saravanamarriagebureau.data.model.RefreshTokenRequest
import com.saravanamarriagebureau.data.model.RegisterRequest
import com.saravanamarriagebureau.data.model.RegisterResponse
import com.saravanamarriagebureau.data.model.RegistrationApprovalData
import com.saravanamarriagebureau.data.model.RegistrationRejectionData
import com.saravanamarriagebureau.data.model.RejectRequest
import com.saravanamarriagebureau.data.model.ResetPasswordRequest
import com.saravanamarriagebureau.data.model.ResetPasswordResponse
import com.saravanamarriagebureau.data.model.UploadResponse
import com.saravanamarriagebureau.data.model.UserInterestsData
import com.saravanamarriagebureau.data.model.UserInterestsResponse
import com.saravanamarriagebureau.data.model.VerifyResetOTP
import com.saravanamarriagebureau.data.model.VerifyResetOTPRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface SmbApi {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<ApiResponse<LoginData>>

    @POST("auth/refresh")
    suspend fun refreshToken(
        @Body request: RefreshTokenRequest
    ): Response<ApiResponse<LoginData>>

    @POST("registration/submit")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<ApiResponse<RegisterResponse>>

    @GET("profile/me")
    suspend fun getProfile(): Response<ApiResponse<ProfileData>>

    @GET("profile/{profileId}")
    suspend fun getProfileById(@Path("profileId") profileId: String): Response<ApiResponse<ProfileData>>

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

    @POST("auth/reset-password")
    suspend fun changePassword(@Body request: ChangePasswordRequest): Response<ApiResponse<ResetPasswordResponse>>

    @POST("profile/media/upload")
    suspend fun mediaUploadInitiated(@Body request: MediaUploadInitiatedRequest): Response<ApiResponse<MediaUploadInitiatedResponse>>

    @Multipart
    @POST("profile/media/upload-file")
    suspend fun uploadImage(
        @Part media: MultipartBody.Part,
        @Part("mediaType") mediaType: RequestBody,
    ): Response<UploadResponse>

    @DELETE("profile/media/{mediaId}")
    suspend fun deleteImage(
        @Path("mediaId") mediaId: String
    ): Response<ApiResponse<DeleteMediaResponse>>

    @POST("profile/media/{mediaId}/complete")
    suspend fun mediaUploadCompleted(
        @Path("mediaId") mediaId: String
    ): Response<ApiResponse<MediaUploadCompleteResponse>>

    @GET("matches/today")
    suspend fun getTodayMatches(): Response<ApiResponse<List<MatchesData>>>

    @GET("matches/past")
    suspend fun getPastMatches(): Response<ApiResponse<List<MatchesData>>>

    @GET("matches/recommended")
    suspend fun getRecommendedMatches(): Response<ApiResponse<List<MatchesData>>>

    @GET("interests/sent")
    suspend fun getSentInterests(): Response<ApiResponse<List<UserInterestsData>>>

    @GET("interests/received")
    suspend fun getReceivedInterests(): Response<ApiResponse<List<UserInterestsData>>>

    @PATCH("interests/{interestId}/accept")
    suspend fun acceptReceivedInterest(
        @Path("interestId") interestId: String
    ): Response<ApiResponse<UserInterestsData>>

    @PATCH("interests/{interestId}/reject")
    suspend fun rejectReceivedInterest(
        @Path("interestId") interestId: String
    ): Response<ApiResponse<UserInterestsData>>

    @POST("interests/{profileId}")
    suspend fun sendInterest(
        @Path("profileId") profileId: String
    ): Response<ApiResponse<UserInterestsResponse>>

    @DELETE("interests/{interestId}")
    suspend fun deleteInterest(
        @Path("interestId") interestId: String
    ): Response<ApiResponse<UserInterestsResponse>>

    @GET("invoices/{id}")
    suspend fun getInvoice(@Path("id") id: String): Invoice

    //Admin

    @GET("registration/pending?limit=20&offset=0&status=PENDING_APPROVAL")
    suspend fun getAdminPendingUsers(): Response<ApiResponse<List<PendingApprovalUser>>>

    @GET("registration/pending?limit=20&offset=0&status=APPROVED")
    suspend fun getAdminApprovedUsers(): Response<ApiResponse<List<PendingApprovalUser>>>

    @GET("registration/pending?limit=20&offset=0&status=REJECTED")
    suspend fun getAdminRejectedUsers(): Response<ApiResponse<List<PendingApprovalUser>>>

    @GET("registration/{userId}")
    suspend fun getAdminUserById(
        @Path("userId") userId: String
    ): Response<ApiResponse<AdminUserDetails>>

    @POST("registration/{userId}/approve")
    suspend fun approveAdminUser(
        @Path("userId") userId: String,
        @Body request: AcceptRequest
    ): Response<ApiResponse<RegistrationApprovalData>>

    @POST("registration/{userId}/reject")
    suspend fun rejectAdminUser(
        @Path("userId") userId: String,
        @Body request: RejectRequest
    ): Response<ApiResponse<RegistrationRejectionData>>

    @GET("admin/interests?status=PENDING")
    suspend fun getAdminPendingInterests(): Response<ApiResponse<List<AdminInterestsData>>>

    @GET("admin/interests?status=ACCEPTED")
    suspend fun getAdminAcceptedInterests(): Response<ApiResponse<List<AdminInterestsData>>>


    @GET("admin/interests/{interestId}")
    suspend fun getAdminInterestById(
        @Path("interestId") interestId: String
    ): Response<ApiResponse<AdminInterestsData>>

    @GET("membership/me")
    suspend fun getMemberShipDetails(
    ): Response<ApiResponse<MembershipDetails>>

    @GET("membership/plans")
    suspend fun getMemberShipPlans(
    ): Response<ApiResponse<List<PlanData>>>

    @POST("membership/purchase")
    suspend fun membershipPlanPurchase(
        @Body request: MembershipPlanPurchaseRequest
    ): Response<ApiResponse<MembershipPlanPurchaseData>>

    @POST("payments/{invoiceId}/complete")
    suspend fun membershipPlanPurchasePaymentComplete(
        @Path("invoiceId") invoiceId: String,
        @Body request: MembershipPlanPurchasePaymentCompleteRequest
    ): Response<ApiResponse<MembershipPlanPurchasePaymentCompleteData>>

}
