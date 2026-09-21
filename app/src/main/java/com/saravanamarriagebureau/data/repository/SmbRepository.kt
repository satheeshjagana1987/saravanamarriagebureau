package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.LoginRequest
import com.saravanamarriagebureau.data.model.RegisterRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmbRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun login(mobile: String, password: String) =
        api.login(LoginRequest(mobile, password))

    suspend fun register(request: RegisterRequest) =
        api.register(request)

    suspend fun matches() = api.getMatches()

    suspend fun sendInterest(id: String) = api.sendInterest(id)

    suspend fun fixMatch(id: String) = api.fixMatch(id)

    suspend fun invoice(id: String) = api.getInvoice(id)
}
