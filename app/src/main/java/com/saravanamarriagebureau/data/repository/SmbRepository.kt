package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.ApiResponse
import com.saravanamarriagebureau.data.model.LoginRequest
import com.saravanamarriagebureau.data.model.RegisterRequest
import com.saravanamarriagebureau.util.ApiResult
import retrofit2.Response
import java.io.IOException
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

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<ApiResponse<T>>
    ): ApiResult<T> {

        return try {

            val response = apiCall()

            if (response.isSuccessful) {

                val body = response.body()

                if (body?.success == true && body.data != null) {

                    ApiResult.Success(
                        data = body.data,
                        message = body.message
                    )

                } else {

                    ApiResult.Error(
                        message = body?.message
                            ?: "Something went wrong"
                    )
                }

            } else {

                ApiResult.Error(
                    message = "HTTP ${response.code()} error",
                    code = response.code()
                )
            }

        } catch (e: IOException) {

            ApiResult.Error(
                message = "No internet connection"
            )

        } catch (e: Exception) {

            ApiResult.Error(
                message = e.message
                    ?: "Unexpected error"
            )
        }
    }
}
