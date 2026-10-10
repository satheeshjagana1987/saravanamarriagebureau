package com.saravanamarriagebureau.ui.screens.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.RegisterRepository
import com.saravanamarriagebureau.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepository: RegisterRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<RegisterUIState>(RegisterUIState.Idle)

    val uiState = _uiState.asStateFlow()

    fun register(
        name: String,
        mobile: String,
        email: String,
        dob: String,
        password: String,
        confirmPassword: String,
        gender: String,
    ) {

        viewModelScope.launch {

            _uiState.value = RegisterUIState.Loading

            when (
                val result = registerRepository.register(
                    name = name,
                    mobile = mobile,
                    email = email,
                    dob = dob,
                    password = password,
                    confirmPassword = confirmPassword,
                    gender = gender
                )
            ) {

                is ApiResult.Success -> {

                    _uiState.value =
                        RegisterUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {

                    _uiState.value =
                        RegisterUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }
}