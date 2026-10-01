package com.saravanamarriagebureau.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saravanamarriagebureau.ui.components.DividerLine
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.components.SecondaryButton
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.Red
import com.saravanamarriagebureau.ui.theme.Success
import com.saravanamarriagebureau.ui.theme.SuccessSoft
import com.saravanamarriagebureau.ui.theme.TextSecondary
import com.saravanamarriagebureau.ui.theme.Warning
import com.saravanamarriagebureau.ui.theme.White

@Composable
fun InvoiceScreen(onPay: () -> Unit, onBack: () -> Unit) {
    Column(Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(Cream)) {
        ScreenHeader("Success Fee Invoice", onBack)
        Spacer(Modifier.height(50.dp))
        Card(colors = CardDefaults.cardColors(containerColor = SuccessSoft)) {
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp).fillMaxWidth()) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Verified",
                    tint = Success,
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    "Invoice Generated",
                    Modifier.padding(start = 10.dp),
                    color = Success,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Spacer(Modifier.height(30.dp))
        Card(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            // Set your custom background color here
            colors = CardDefaults.cardColors(
                containerColor = White
            ),
            border = CardDefaults.outlinedCardBorder(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Invoice ID",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = TextSecondary,
                            fontWeight = FontWeight.Normal
                        ),
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "#AF12345",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Black,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )
                }
                DividerLine()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Match ID",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = TextSecondary,
                            fontWeight = FontWeight.Normal
                        ),
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "#AF12345",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Black,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )
                }
                DividerLine()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Branch",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = TextSecondary,
                            fontWeight = FontWeight.Normal
                        ),
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "Guntur",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Black,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )
                }
                DividerLine()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Amount",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = TextSecondary,
                            fontWeight = FontWeight.Normal
                        ),
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = '₹' + "50,000",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Black,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )
                }
                DividerLine()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Date",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = TextSecondary,
                            fontWeight = FontWeight.Normal
                        ),
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "28 September 2026",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Black,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )
                }
                DividerLine()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Payment Status",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = TextSecondary,
                            fontWeight = FontWeight.Normal
                        ),
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "Pending",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Warning,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )
                }
                DividerLine()
            }
        }
        Spacer(Modifier.height(20.dp))
        Text(
            text = "Contacts will be revealed after successful payment.",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp, color = Red,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.weight(1f))
        PrimaryButton(
            text = "Pay Now (₹50,000)", onClick = onPay,
            modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 20.dp)
        )
        Spacer(Modifier.height(5.dp))
        SecondaryButton(
            text = "Go Back",
            onClick = {onBack()},
            modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 50.dp)
        )
    }
}

@Preview
@Composable
fun InvoiceScreenPreview() {
    InvoiceScreen(onPay = {}, onBack = {})
}