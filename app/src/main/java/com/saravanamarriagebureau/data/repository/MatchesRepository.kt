package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.Match
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class MatchesRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun getMatches(): ApiResult<List<Match>> {
        return safeApiCall {
            api.getMatches()
        }
    }

}