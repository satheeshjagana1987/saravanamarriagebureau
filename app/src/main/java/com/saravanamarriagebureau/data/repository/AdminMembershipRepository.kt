package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.AddMembershipPlanData
import com.saravanamarriagebureau.data.model.AddMembershipPlanRequest
import com.saravanamarriagebureau.data.model.DeleteMembershipPlanData
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class AdminMembershipRepository @Inject constructor(
    private val api: SmbApi
) {
    suspend fun getAdminMemberShipPlans(): ApiResult<List<AddMembershipPlanData>> {
        return safeApiCall {
            api.getAdminMemberShipPlans()
        }
    }

    suspend fun addAdminMemberShipPlan(
        code: String,
        name: String,
        description: String,
        durationDays: Int,
        priceMinorUnits: Int,
    ): ApiResult<AddMembershipPlanData> {
        return safeApiCall {
            api.addAdminMemberShipPlan(
                request = AddMembershipPlanRequest(
                    code = code,
                    name = name,
                    description = description,
                    durationDays = durationDays,
                    priceMinorUnits = priceMinorUnits,
                    currency = "INR",
                    isActive = true
                )
            )

        }
    }

    suspend fun updateAdminMemberShipPlan(
        code: String,
        name: String,
        description: String,
        durationDays: Int,
        priceMinorUnits: Int,
        planId: String
    ): ApiResult<AddMembershipPlanData> {
        return safeApiCall {
            api.updateAdminMemberShipPlan(
                planId = planId,
                request = AddMembershipPlanRequest(
                    code = code,
                    name = name,
                    description = description,
                    durationDays = durationDays,
                    priceMinorUnits = priceMinorUnits,
                    currency = "INR",
                    isActive = true
                )
            )

        }
    }

    suspend fun getAdminMemberShipPlanDetailsById(
        planId: String
    ): ApiResult<AddMembershipPlanData> {
        return safeApiCall {
            api.getAdminMemberShipPlanDetailsById(
                planId = planId
            )

        }
    }

    suspend fun deleteAdminMemberShipPlanDetails(
        planId: String
    ): ApiResult<DeleteMembershipPlanData> {
        return safeApiCall {
            api.deleteAdminMemberShipPlanDetails(
                planId = planId
            )
        }
    }
}