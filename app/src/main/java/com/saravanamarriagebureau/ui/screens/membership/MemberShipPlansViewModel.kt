package com.saravanamarriagebureau.ui.screens.membership

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.MemberShipPlansRepository
import com.saravanamarriagebureau.ui.screens.interests.SendInterestsUIState
import com.saravanamarriagebureau.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemberShipPlansViewModel @Inject constructor(
    private val memberShipPlansRepository: MemberShipPlansRepository
) : ViewModel() {

    private val _uiStateMemberShipPlans =
        MutableStateFlow<MemberShipPlansUIState>(MemberShipPlansUIState.Idle)

    val uiStateMemberShipPlans = _uiStateMemberShipPlans.asStateFlow()

    private val _uiStateMemberShipDetails =
        MutableStateFlow<MemberShipDetailsUIState>(MemberShipDetailsUIState.Idle)

    val uiStateMemberShipDetails = _uiStateMemberShipDetails.asStateFlow()

    private val _uiStateMemberShipPlanPurchase =
        MutableStateFlow<MemberShipPlanPurchaseUiState>(MemberShipPlanPurchaseUiState.Idle)

    val uiStateMemberShipPlanPurchase = _uiStateMemberShipPlanPurchase.asStateFlow()

    private val _uiStateMemberShipPlanPurchasePaymentComplete =
        MutableStateFlow<MembershipPlanPurchasePaymentCompleteUIState>(MembershipPlanPurchasePaymentCompleteUIState.Idle)

    val uiStateMemberShipPlanPurchasePaymentComplete = _uiStateMemberShipPlanPurchasePaymentComplete.asStateFlow()

    fun getMemberShipPlans() {
        viewModelScope.launch {
            _uiStateMemberShipPlans.value = MemberShipPlansUIState.Loading

            when (val result = memberShipPlansRepository.getMemberShipPlans()) {
                is ApiResult.Success -> {
                    _uiStateMemberShipPlans.value =
                        MemberShipPlansUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateMemberShipPlans.value =
                        MemberShipPlansUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun membershipPlanPurchase(
        planId: String
    ) {
        viewModelScope.launch {
            _uiStateMemberShipPlanPurchase.value = MemberShipPlanPurchaseUiState.Loading

            when (val result = memberShipPlansRepository.membershipPlanPurchase(
                planId = planId
            )) {
                is ApiResult.Success -> {
                    _uiStateMemberShipPlanPurchase.value =
                        MemberShipPlanPurchaseUiState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateMemberShipPlanPurchase.value =
                        MemberShipPlanPurchaseUiState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun membershipPlanPurchasePaymentComplete(
        invoiceId: String,
        providerPaymentId: String,
        providerSignature: String,
        providerOrderId: String,
    ) {
        viewModelScope.launch {
            _uiStateMemberShipPlanPurchasePaymentComplete.value = MembershipPlanPurchasePaymentCompleteUIState.Loading

            when (val result = memberShipPlansRepository.membershipPlanPurchasePaymentComplete(
                invoiceId = invoiceId,
                providerPaymentId = providerPaymentId,
                providerOrderId = providerOrderId,
                providerSignature = providerSignature
            )) {
                is ApiResult.Success -> {
                    _uiStateMemberShipPlanPurchasePaymentComplete.value =
                        MembershipPlanPurchasePaymentCompleteUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateMemberShipPlanPurchasePaymentComplete.value =
                        MembershipPlanPurchasePaymentCompleteUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun getMemberShipDetails() {
        viewModelScope.launch {
            _uiStateMemberShipDetails.value = MemberShipDetailsUIState.Loading

            when (val result = memberShipPlansRepository.getMemberShipDetails()) {
                is ApiResult.Success -> {
                    _uiStateMemberShipDetails.value =
                        MemberShipDetailsUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateMemberShipDetails.value =
                        MemberShipDetailsUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }
}