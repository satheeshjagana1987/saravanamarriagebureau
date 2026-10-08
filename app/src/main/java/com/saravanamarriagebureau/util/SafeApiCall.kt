package com.saravanamarriagebureau.util

import com.saravanamarriagebureau.data.model.ApiResponse
import retrofit2.Response
import java.io.IOException

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