package com.saravanamarriagebureau.util

sealed class ApiResult<out T> {
    data object Loading : ApiResult<Nothing>()

    data class Success<T>(
        val data: T,
        val message: String? = null
    ) : ApiResult<T>()

    data class Error(
        val message: String,
        val code: Int? = null
    ) : ApiResult<Nothing>()
}