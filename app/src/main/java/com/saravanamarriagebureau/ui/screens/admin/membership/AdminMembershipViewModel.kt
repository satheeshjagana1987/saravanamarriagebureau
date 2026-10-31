package com.saravanamarriagebureau.ui.screens.admin.membership

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.AdminMembershipRepository
import com.saravanamarriagebureau.ui.screens.interests.SendInterestsUIState
import com.saravanamarriagebureau.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminMembershipViewModel @Inject  constructor(
    private val adminMembershipRepository: AdminMembershipRepository
): ViewModel(){

    private val _uiStateAdminMembershipPlans =
        MutableStateFlow<AdminMembershipPlansUIState>(AdminMembershipPlansUIState.Idle)

    val uiStateAdminMembershipPlans = _uiStateAdminMembershipPlans.asStateFlow()


    private val _uiStateAddAdminMembershipPlan =
        MutableStateFlow<AddAdminMembershipPlanUIState>(AddAdminMembershipPlanUIState.Idle)

    val uiStateAddAdminMembershipPlan = _uiStateAddAdminMembershipPlan.asStateFlow()


    private val _uiStateAdminMembershipPlanById =
        MutableStateFlow<AddAdminMembershipPlanUIState>(AddAdminMembershipPlanUIState.Idle)

    val uiStateAdminMembershipPlanById = _uiStateAdminMembershipPlanById.asStateFlow()

    private val _uiStateUpdateAdminMembershipPlan =
        MutableStateFlow<AddAdminMembershipPlanUIState>(AddAdminMembershipPlanUIState.Idle)

    val uiStateUpdateAdminMembershipPlan = _uiStateUpdateAdminMembershipPlan.asStateFlow()

    private val _uiStateDeleteAdminMembershipPlan =
        MutableStateFlow<DeleteAdminMembershipPlanUIState>(DeleteAdminMembershipPlanUIState.Idle)

    val uiStateDeleteAdminMembershipPlan = _uiStateDeleteAdminMembershipPlan.asStateFlow()

    fun getAdminMemberShipPlans(){
        viewModelScope.launch {

            _uiStateAdminMembershipPlans.value = AdminMembershipPlansUIState.Loading

            when (
                val result = adminMembershipRepository.getAdminMemberShipPlans()
            ) {

                is ApiResult.Success -> {
                    _uiStateAdminMembershipPlans.value =
                        AdminMembershipPlansUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateAdminMembershipPlans.value =
                        AdminMembershipPlansUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun addAdminMemberShipPlan(
        code: String,
        name: String,
        description: String,
        durationDays: Int,
        priceMinorUnits: Int,
    ) {

        viewModelScope.launch {

            _uiStateAddAdminMembershipPlan.value = AddAdminMembershipPlanUIState.Loading

            when (
                val result = adminMembershipRepository.addAdminMemberShipPlan(
                    name = name,
                    code = code,
                    description = description,
                    durationDays = durationDays,
                    priceMinorUnits = priceMinorUnits
                )
            ) {

                is ApiResult.Success -> {
                    _uiStateAddAdminMembershipPlan.value =
                        AddAdminMembershipPlanUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateAddAdminMembershipPlan.value =
                        AddAdminMembershipPlanUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun getAdminMemberShipPlanDetailsById(
        planId: String
    ){
        viewModelScope.launch {

            _uiStateAdminMembershipPlanById.value = AddAdminMembershipPlanUIState.Loading

            when (
                val result = adminMembershipRepository.getAdminMemberShipPlanDetailsById(
                    planId = planId
                )
            ) {

                is ApiResult.Success -> {
                    _uiStateAdminMembershipPlanById.value =
                        AddAdminMembershipPlanUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateAdminMembershipPlanById.value =
                        AddAdminMembershipPlanUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun updateAdminMemberShipPlan(
        code: String,
        name: String,
        description: String,
        durationDays: Int,
        priceMinorUnits: Int,
        planId: String
    ) {

        viewModelScope.launch {

            _uiStateUpdateAdminMembershipPlan.value = AddAdminMembershipPlanUIState.Loading

            when (
                val result = adminMembershipRepository.updateAdminMemberShipPlan(
                    name = name,
                    code = code,
                    description = description,
                    durationDays = durationDays,
                    priceMinorUnits = priceMinorUnits,
                    planId = planId
                )
            ) {

                is ApiResult.Success -> {
                    _uiStateUpdateAdminMembershipPlan.value =
                        AddAdminMembershipPlanUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateUpdateAdminMembershipPlan.value =
                        AddAdminMembershipPlanUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun deleteAdminMemberShipPlanDetails(
        planId: String
    ){
        viewModelScope.launch {

            _uiStateDeleteAdminMembershipPlan.value = DeleteAdminMembershipPlanUIState.Loading

            when (
                val result = adminMembershipRepository.deleteAdminMemberShipPlanDetails(
                    planId = planId
                )
            ) {

                is ApiResult.Success -> {
                    _uiStateDeleteAdminMembershipPlan.value =
                        DeleteAdminMembershipPlanUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateDeleteAdminMembershipPlan.value =
                        DeleteAdminMembershipPlanUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun resetDeleteInterestState() {
        _uiStateDeleteAdminMembershipPlan.value = DeleteAdminMembershipPlanUIState.Idle
    }
}