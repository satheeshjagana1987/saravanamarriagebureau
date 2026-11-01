package com.saravanamarriagebureau.ui.screens.admin.interests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.AdminInterestsRepository
import com.saravanamarriagebureau.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminInterestsViewModel @Inject constructor(
    private val adminInterestsRepository: AdminInterestsRepository
) : ViewModel() {

    private val _uiStateAdminPendingInterests =
        MutableStateFlow<AdminInterestsUIState>(AdminInterestsUIState.Idle)

    val uiStateAdminPendingInterests = _uiStateAdminPendingInterests.asStateFlow()

    private val _uiStateAdminAcceptedInterests =
        MutableStateFlow<AdminInterestsUIState>(AdminInterestsUIState.Idle)

    val uiStateAdminAcceptedInterests = _uiStateAdminAcceptedInterests.asStateFlow()

    private val _uiStateAdminInterestsById =
        MutableStateFlow<AdminInterestsByIdUIState>(AdminInterestsByIdUIState.Idle)

    val uiStateAdminInterestsById = _uiStateAdminInterestsById.asStateFlow()

    private val _uiStateAdminInterestsApproveOrRejectMatch =
        MutableStateFlow<AdminInterestsByIdUIState>(AdminInterestsByIdUIState.Idle)

    val uiStateAdminInterestsApproveOrRejectMatch =
        _uiStateAdminInterestsApproveOrRejectMatch.asStateFlow()

    fun getAdminPendingInterests() {

        viewModelScope.launch {

            _uiStateAdminPendingInterests.value = AdminInterestsUIState.Loading

            when (
                val result = adminInterestsRepository.getAdminPendingInterests()
            ) {

                is ApiResult.Success -> {
                    _uiStateAdminPendingInterests.value =
                        AdminInterestsUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateAdminPendingInterests.value =
                        AdminInterestsUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun getAdminAcceptedInterests() {

        viewModelScope.launch {

            _uiStateAdminAcceptedInterests.value = AdminInterestsUIState.Loading

            when (
                val result = adminInterestsRepository.getAdminAcceptedInterests()
            ) {

                is ApiResult.Success -> {
                    _uiStateAdminAcceptedInterests.value =
                        AdminInterestsUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateAdminAcceptedInterests.value =
                        AdminInterestsUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun loadAdminInterestById(
        profileId: String
    ) {
        viewModelScope.launch {
            _uiStateAdminInterestsById.value = AdminInterestsByIdUIState.Loading

            when (val result = adminInterestsRepository.getAdminInterestById(
                interestId = profileId
            )) {
                is ApiResult.Success -> {
                    _uiStateAdminInterestsById.value =
                        AdminInterestsByIdUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateAdminInterestsById.value =
                        AdminInterestsByIdUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun adminApproveOrRejectMatch(
        interestId: String,
        isMatchFixed: Boolean,
        matchRejectionReason: String
    ) {
        viewModelScope.launch {
            _uiStateAdminInterestsApproveOrRejectMatch.value = AdminInterestsByIdUIState.Loading

            when (val result = adminInterestsRepository.adminApproveOrRejectMatch(
                interestId = interestId,
                isMatchFixed = isMatchFixed,
                matchRejectionReason = matchRejectionReason
            )) {
                is ApiResult.Success -> {
                    _uiStateAdminInterestsApproveOrRejectMatch.value =
                        AdminInterestsByIdUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateAdminInterestsApproveOrRejectMatch.value =
                        AdminInterestsByIdUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }
}