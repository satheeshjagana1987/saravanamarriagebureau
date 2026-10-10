package com.saravanamarriagebureau.data.api

import com.saravanamarriagebureau.data.model.ApiResponse
import com.saravanamarriagebureau.data.model.ChangePasswordRequest
import com.saravanamarriagebureau.data.model.ForgetPasswordRequest
import com.saravanamarriagebureau.data.model.Interest
import com.saravanamarriagebureau.data.model.Invoice
import com.saravanamarriagebureau.data.model.LoginRequest
import com.saravanamarriagebureau.data.model.LoginResponse
import com.saravanamarriagebureau.data.model.Match
import com.saravanamarriagebureau.data.model.PasswordResponse
import com.saravanamarriagebureau.data.model.Profile
import com.saravanamarriagebureau.data.model.ProfileResponse
import com.saravanamarriagebureau.data.model.RegisterRequest
import com.saravanamarriagebureau.data.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SmbApi {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse<LoginResponse>>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<ApiResponse<RegisterResponse>>

    @GET("profile")
    suspend fun getProfile(): Response<ApiResponse<ProfileResponse>>

    @PUT("profile")
    suspend fun updateProfile(@Body request: Profile): Response<ApiResponse<ProfileResponse>>

    @POST("auth/forgetPassword")
    suspend fun forgetPassword(@Body request: ForgetPasswordRequest): Response<ApiResponse<PasswordResponse>>

    @POST("auth/changePassword")
    suspend fun changePassword(@Body request: ChangePasswordRequest): Response<ApiResponse<PasswordResponse>>

    @GET("matches")
    suspend fun getMatches(): Response<ApiResponse<List<Match>>>

    @GET("interests")
    suspend fun getInterests(): Response<ApiResponse<List<Interest>>>

    @GET("matches/{id}")
    suspend fun getMatch(@Path("id") id: String)

    @GET("interests/{id}")
    suspend fun getInterest(@Path("id") id: String)

    @GET("invoices/{id}")
    suspend fun getInvoice(@Path("id") id: String): Invoice

}
