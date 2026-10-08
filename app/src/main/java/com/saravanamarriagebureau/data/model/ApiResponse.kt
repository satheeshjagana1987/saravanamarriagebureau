package com.saravanamarriagebureau.data.model

data class ApiResponse<T> (
    val success: Boolean,
    val message: String?,
    val data: T?,
    val errorCode: String?
)

