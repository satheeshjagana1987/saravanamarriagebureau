package com.saravanamarriagebureau.ui.screens.login

import com.saravanamarriagebureau.data.model.LoginData

sealed class LoginUIState {

    data object Idle : LoginUIState()

    data object Loading : LoginUIState()

    data class Success(
        val data: LoginData,
        val message: String? = null
    ) : LoginUIState()

    data class Error(
        val message: String
    ) : LoginUIState()
}