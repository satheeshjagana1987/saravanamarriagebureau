package com.saravanamarriagebureau.ui.screens.admin.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.model.AcceptRequest
import com.saravanamarriagebureau.data.model.RejectRequest
import com.saravanamarriagebureau.data.repository.AdminUsersRepository
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

    private val _uiStateAdminPendingUsers =
        MutableStateFlow<AdminUsersUIState>(AdminUsersUIState.Idle)

    val uiStateAdminPendingUsers = _uiStateAdminPendingUsers.asStateFlow()

    private val _uiStateAdminApprovedUsers =
        MutableStateFlow<AdminUsersUIState>(AdminUsersUIState.Idle)

    val uiStateAdminApprovedUsers = _uiStateAdminApprovedUsers.asStateFlow()

    private val _uiStateAdminRejectedUsers =
        MutableStateFlow<AdminUsersUIState>(AdminUsersUIState.Idle)

    val uiStateAdminRejectedUsers = _uiStateAdminRejectedUsers.asStateFlow()

    private val _uiStateById =
        MutableStateFlow<AdminUserByIdUIState>(AdminUserByIdUIState.Idle)

    val uiStateById = _uiStateById.asStateFlow()

    private val _uiStateByApproveUser =
        MutableStateFlow<AdminUserApproveUIState>(AdminUserApproveUIState.Idle)

    val uiStateByApproveUser = _uiStateByApproveUser.asStateFlow()

    private val _uiStateByRejectUser =
        MutableStateFlow<AdminUserRejectUIState>(AdminUserRejectUIState.Idle)

    val uiStateByRejectUser = _uiStateByRejectUser.asStateFlow()

    fun getAdminPendingUsers() {

        viewModelScope.launch {

            _uiStateAdminPendingUsers.value = AdminUsersUIState.Loading

            when (
                val result = adminUsersRepository.getAdminPendingUsers()
            ) {

                is ApiResult.Success -> {
                    _uiStateAdminPendingUsers.value =
                        AdminUsersUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateAdminPendingUsers.value =
                        AdminUsersUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun getAdminApprovedUsers() {

        viewModelScope.launch {

            _uiStateAdminApprovedUsers.value = AdminUsersUIState.Loading

            when (
                val result = adminUsersRepository.getAdminApprovedUsers()
            ) {

                is ApiResult.Success -> {
                    _uiStateAdminApprovedUsers.value =
                        AdminUsersUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateAdminApprovedUsers.value =
                        AdminUsersUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun getAdminRejectedUsers() {

        viewModelScope.launch {

            _uiStateAdminRejectedUsers.value = AdminUsersUIState.Loading

            when (
                val result = adminUsersRepository.getAdminRejectedUsers()
            ) {

                is ApiResult.Success -> {
                    _uiStateAdminRejectedUsers.value =
                        AdminUsersUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateAdminRejectedUsers.value =
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

    fun approveUser(
        userId: String
    ) {
        viewModelScope.launch {
            _uiStateByApproveUser.value = AdminUserApproveUIState.Loading

            val request = AcceptRequest(
                action = "APPROVE"
            )

            when (val result = adminUsersRepository.approveUser(
                userId = userId,
                request = request
            )) {
                is ApiResult.Success -> {
                    _uiStateByApproveUser.value =
                        AdminUserApproveUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateByApproveUser.value =
                        AdminUserApproveUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun rejectUser(
        userId: String,
        reason: String,
    ) {
        viewModelScope.launch {
            _uiStateByRejectUser.value = AdminUserRejectUIState.Loading

            val request = RejectRequest(
                action = "REJECT",
                reason = reason
            )

            when (val result = adminUsersRepository.rejectUser(
                userId = userId,
                request = request
            )) {
                is ApiResult.Success -> {
                    _uiStateByRejectUser.value =
                        AdminUserRejectUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateByRejectUser.value =
                        AdminUserRejectUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }
}