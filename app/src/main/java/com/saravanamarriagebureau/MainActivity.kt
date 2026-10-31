package com.saravanamarriagebureau

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import com.saravanamarriagebureau.payment.RazorpayCheckoutHost
import com.saravanamarriagebureau.ui.SmbNavigationApp
import com.saravanamarriagebureau.ui.theme.SaravanaMarriageBureauTheme
import com.saravanamarriagebureau.util.AppPreferences
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(), RazorpayCheckoutHost,
    PaymentResultWithDataListener {

    @Inject
    lateinit var appPreferences: AppPreferences

    private var paymentSuccessCallback:
            ((String, String) -> Unit)? = null

    private var paymentFailureCallback:
            ((String) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Checkout.preload(applicationContext)
        enableEdgeToEdge()
        setContent {
            SaravanaMarriageBureauTheme {
                SmbNavigationApp(
                    appPreferences = appPreferences
                )
            }
        }
    }

    override fun launchCheckout(
        keyId: String,
        orderId: String,
        amount: Int,
        currency: String,
        onSuccess: (paymentId: String, signature: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        try {

            paymentSuccessCallback = onSuccess
            paymentFailureCallback = onFailure

            val checkout = Checkout()

            checkout.setKeyID(keyId)

            val options = JSONObject().apply {

                put("name", "Saravana Marriage Bureau")

                put(
                    "description",
                    "Membership Subscription"
                )

                put("order_id", orderId)
                put("amount", amount)
                put("currency", currency)

                put(
                    "theme",
                    JSONObject().apply {
                        put("color", "#800000")
                    }
                )
            }

            checkout.open(this, options)
        } catch (e: Exception) {

            paymentFailureCallback?.invoke(
                e.message ?: "Unable to start payment"
            )

            clearPaymentCallbacks()
        }
    }

    override fun onPaymentSuccess(
        razorpayPaymentId: String?,
        paymentData: PaymentData?
    ) {
        val paymentId = razorpayPaymentId
        val signature = paymentData?.signature

        if (paymentId.isNullOrBlank() ||
            signature.isNullOrBlank()
        ) {

            paymentFailureCallback?.invoke(
                "Invalid Razorpay payment response"
            )

            clearPaymentCallbacks()
            return
        }
        paymentSuccessCallback?.invoke(
            paymentId,
            signature
        )

        clearPaymentCallbacks()
    }

    override fun onPaymentError(
        code: Int,
        response: String?,
        p2: PaymentData?
    ) {
        paymentFailureCallback?.invoke(
            response ?: "Payment cancelled or failed"
        )

        clearPaymentCallbacks()
    }

    private fun clearPaymentCallbacks() {

        paymentSuccessCallback = null
        paymentFailureCallback = null
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SaravanaMarriageBureauTheme {
        Greeting("Android")
    }
}