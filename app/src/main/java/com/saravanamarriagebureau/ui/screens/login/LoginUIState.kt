package com.saravanamarriagebureau.ui.screens.login

import com.saravanamarriagebureau.data.model.LoginResponse

sealed class LoginUIState {

    data object Idle : LoginUIState()

    data object Loading : LoginUIState()

    data class Success(
        val data: LoginResponse,
        val message: String? = null
    ) : LoginUIState()

    data class Error(
        val message: String
    ) : LoginUIState()
}