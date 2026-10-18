package com.saravanamarriagebureau.ui.screens.changepassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.ChangePasswordRepository
import com.saravanamarriagebureau.ui.screens.forgetpassword.ForgetPasswordUIState
import com.saravanamarriagebureau.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val changePasswordRepository: ChangePasswordRepository
): ViewModel(){

    private val _uiState =
        MutableStateFlow<ChangePasswordUIState>(ChangePasswordUIState.Idle)

    val uiState = _uiState.asStateFlow()

    fun changePassword(
        oldPassword: String,
        newPassword: String,
        confirmPassword: String,
    ) {

        viewModelScope.launch {

            _uiState.value = ChangePasswordUIState.Loading

            when (
                val result = changePasswordRepository.changePassword(
                    currentPassword = oldPassword,
                    newPassword = newPassword,
                    confirmNewPassword = confirmPassword
                )
            ) {

                is ApiResult.Success -> {
                    _uiState.value =
                        ChangePasswordUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiState.value =
                        ChangePasswordUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }
}