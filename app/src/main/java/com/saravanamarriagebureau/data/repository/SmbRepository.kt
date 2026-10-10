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

}
