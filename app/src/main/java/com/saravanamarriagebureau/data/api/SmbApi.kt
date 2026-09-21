package com.saravanamarriagebureau.data.api

import com.saravanamarriagebureau.data.model.Invoice
import com.saravanamarriagebureau.data.model.LoginRequest
import com.saravanamarriagebureau.data.model.LoginResponse
import com.saravanamarriagebureau.data.model.Match
import com.saravanamarriagebureau.data.model.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SmbApi {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): LoginResponse

    @GET("matches")
    suspend fun getMatches(): List<Match>

    @POST("matches/{id}/interest")
    suspend fun sendInterest(@Path("id") id: String)

    @POST("matches/{id}/fix")
    suspend fun fixMatch(@Path("id") id: String): Invoice

    @GET("invoices/{id}")
    suspend fun getInvoice(@Path("id") id: String): Invoice
}
