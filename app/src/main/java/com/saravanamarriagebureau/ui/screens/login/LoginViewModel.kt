package com.saravanamarriagebureau.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.LoginRepository
import com.saravanamarriagebureau.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<LoginUIState>(LoginUIState.Idle)

    val uiState = _uiState.asStateFlow()

    fun login(
        mobile: String,
        password: String
    ) {

        viewModelScope.launch {

            _uiState.value = LoginUIState.Loading

            when (
                val result = loginRepository.login(
                    mobile = mobile,
                    password = password
                )
            ) {

                is ApiResult.Success -> {

                    _uiState.value =
                        LoginUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {

                    _uiState.value =
                        LoginUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }
}
