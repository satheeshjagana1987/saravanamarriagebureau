package com.saravanamarriagebureau.data.model

data class ApiError(
    val success: Boolean? = false,
    val message: String? = null,
    val errorCode: String? = null
)