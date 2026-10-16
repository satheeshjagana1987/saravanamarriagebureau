package com.saravanamarriagebureau.ui.screens.login

import com.saravanamarriagebureau.data.model.LoginData

sealed class RefreshTokenUIState {

    data object Idle : RefreshTokenUIState()

    data object Loading : RefreshTokenUIState()

    data class Success(
        val data: LoginData,
        val message: String? = null
    ) : RefreshTokenUIState()

    data class Error(
        val message: String
    ) : RefreshTokenUIState()
}