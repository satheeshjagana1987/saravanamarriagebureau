package com.saravanamarriagebureau.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.LoginRepository
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.AppPreferences
import com.saravanamarriagebureau.util.Constants.KEY_USER_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val appPreferences: AppPreferences
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<LoginUIState>(LoginUIState.Idle)

    val uiState = _uiState.asStateFlow()

    private val _uiStateRefreshToken =
        MutableStateFlow<RefreshTokenUIState>(RefreshTokenUIState.Idle)

    val uiStateRefreshToken = _uiStateRefreshToken.asStateFlow()

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
                    appPreferences.saveAccessToken(
                        result.data.accessToken
                    )

                    appPreferences.saveRefreshToken(
                        result.data.refreshToken
                    )

                    appPreferences.saveLoginStatus(
                        isLoggedIn = true
                    )

                    appPreferences.saveUserType(
                        result.data.userType ?: KEY_USER_TYPE
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

    fun getRefreshToken(
        refreshToken: String,
    ) {

        viewModelScope.launch {

            _uiStateRefreshToken.value = RefreshTokenUIState.Loading

            when (
                val result = loginRepository.refreshToken(
                    refreshToken = refreshToken,
                )
            ) {

                is ApiResult.Success -> {
                    _uiStateRefreshToken.value =
                        RefreshTokenUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                    appPreferences.saveAccessToken(
                        result.data.accessToken
                    )

                    appPreferences.saveRefreshToken(
                        result.data.refreshToken
                    )

                    appPreferences.saveLoginStatus(
                        isLoggedIn = true
                    )

                    appPreferences.saveUserType(
                        result.data.userType ?: KEY_USER_TYPE
                    )

                }

                is ApiResult.Error -> {
                    _uiStateRefreshToken.value =
                        RefreshTokenUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }
}
