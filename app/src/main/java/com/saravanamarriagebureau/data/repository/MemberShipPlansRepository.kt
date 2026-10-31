package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.MembershipDetails
import com.saravanamarriagebureau.data.model.MembershipPlanPurchaseData
import com.saravanamarriagebureau.data.model.MembershipPlanPurchasePaymentCompleteData
import com.saravanamarriagebureau.data.model.MembershipPlanPurchasePaymentCompleteRequest
import com.saravanamarriagebureau.data.model.MembershipPlanPurchaseRequest
import com.saravanamarriagebureau.data.model.PlanData
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class MemberShipPlansRepository @Inject constructor(
    private val api: SmbApi
) {

    suspend fun getMemberShipPlans(
    ): ApiResult<List<PlanData>> {
        return safeApiCall {
            api.getMemberShipPlans()

        }
    }

    suspend fun membershipPlanPurchase(
        planId: String
    ): ApiResult<MembershipPlanPurchaseData> {
        return safeApiCall {
            api.membershipPlanPurchase(
                request = MembershipPlanPurchaseRequest(
                    planId = planId
                )
            )

        }
    }

    suspend fun membershipPlanPurchasePaymentComplete(
        invoiceId: String,
        providerPaymentId: String,
        providerSignature: String,
        providerOrderId: String,

        ): ApiResult<MembershipPlanPurchasePaymentCompleteData> {
        return safeApiCall {
            api.membershipPlanPurchasePaymentComplete(
                invoiceId = invoiceId,
                request = MembershipPlanPurchasePaymentCompleteRequest(
                    providerPaymentId = providerPaymentId,
                    providerOrderId = providerOrderId,
                    providerSignature = providerSignature
                )
            )

        }
    }

    suspend fun getMemberShipDetails(
    ): ApiResult<MembershipDetails> {
        return safeApiCall {
            api.getMemberShipDetails()

        }
    }
}