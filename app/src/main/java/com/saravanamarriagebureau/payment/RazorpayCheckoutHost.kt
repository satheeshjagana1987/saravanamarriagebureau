package com.saravanamarriagebureau.payment

interface RazorpayCheckoutHost {

    fun launchCheckout(
        keyId: String,
        orderId: String,
        amount: Int,
        currency: String,
        onSuccess: (
            paymentId: String,
            signature: String
        ) -> Unit,
        onFailure: (String) -> Unit
    )
}