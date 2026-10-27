package com.saravanamarriagebureau.ui.screens.admin.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.AdminUsersRepository
import com.saravanamarriagebureau.ui.screens.matches.MatchesUiState
import com.saravanamarriagebureau.ui.screens.profile.ProfileUIState
import com.saravanamarriagebureau.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminUsersViewModel  @Inject constructor(
    private val adminUsersRepository: AdminUsersRepository
) : ViewModel() {

    private val _uiStateAdminUsers =
        MutableStateFlow<AdminUsersUIState>(AdminUsersUIState.Idle)

    val uiStateAdminUsers = _uiStateAdminUsers.asStateFlow()

    private val _uiStateById =
        MutableStateFlow<AdminUserByIdUIState>(AdminUserByIdUIState.Idle)

    val uiStateById = _uiStateById.asStateFlow()

    fun getAdminUsers() {

        viewModelScope.launch {

            _uiStateAdminUsers.value = AdminUsersUIState.Loading

            when (
                val result = adminUsersRepository.getAdminUsers()
            ) {

                is ApiResult.Success -> {
                    _uiStateAdminUsers.value =
                        AdminUsersUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateAdminUsers.value =
                        AdminUsersUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun loadProfileById(
        profileId: String
    ) {
        viewModelScope.launch {
            _uiStateById.value = AdminUserByIdUIState.Loading

            when (val result = adminUsersRepository.getAdminUserById(
                profileId = profileId
            )) {
                is ApiResult.Success -> {
                    _uiStateById.value =
                        AdminUserByIdUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateById.value =
                        AdminUserByIdUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun resetState() {
        _uiStateAdminUsers.value = AdminUsersUIState.Idle
    }
}