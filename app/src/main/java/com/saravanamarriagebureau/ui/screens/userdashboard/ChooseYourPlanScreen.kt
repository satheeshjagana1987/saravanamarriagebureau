package com.saravanamarriagebureau.ui.screens.userdashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.CardBorder
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.Success

@Composable
fun ChooseYourPlanScreen(
    onBack: () -> Unit,
    onContinue: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Cream)
    ) {
        ScreenHeader(title = "Choose Your Plan", onBack)
        Spacer(Modifier.height(30.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Cream)
                .padding(16.dp)
                .selectableGroup() // Crucial for screen-reader accessibility
        ) {
            var selectedPlan by remember { mutableStateOf("299") }

            Column(Modifier.fillMaxSize()) {

                PlanCard(
                    title = "Dasara Offer",
                    subtitle = "Free Registration",
                    price = "₹0",
                    selected = selectedPlan == "0",
                    onClick = { selectedPlan = "0" }
                )

                PlanCard(
                    title = stringResource(R.string.yearly_membership),
                    subtitle = "For profile viewing only",
                    price = "₹299 / Year",
                    selected = selectedPlan == "299",
                    onClick = {
                        selectedPlan = "299"
                    }
                )

                Spacer(Modifier.weight(1f))

                PrimaryButton(
                    "Continue",
                    onClick = {
                        onContinue(selectedPlan)
                        // You can also use selectedPlan here
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 50.dp
                        )
                )
            }
        }
    }
}

@Composable
private fun PlanCard(
    title: String,
    subtitle: String,
    price: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) {
                Color(0xFFFFF5F5)
            } else {
                Color.White
            }
        ),
        border = if (selected) {
            BorderStroke(1.dp, Maroon)
        } else {
            BorderStroke(1.dp, CardBorder)
        },
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                LabelName(
                    labelName = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Black,
                    modifier = Modifier.padding(0.dp)
                )
                Spacer(Modifier.height(5.dp))
                LabelName(
                    labelName = price,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Maroon,
                    modifier = Modifier.padding(0.dp)
                )
                Spacer(Modifier.height(5.dp))

                LabelName(
                    labelName = subtitle,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Success,
                    modifier = Modifier.padding(0.dp)
                )
            }
            Icon(
                imageVector = if (selected) {
                    Icons.Default.CheckCircle
                } else {
                    Icons.Default.RadioButtonUnchecked
                },
                contentDescription = if (selected) {
                    "Selected plan"
                } else {
                    "Select plan"
                },
                tint = if (selected) Success else CardBorder
            )
        }
    }
}

@Preview
@Composable
fun ChooseYourPlanScreenPreview() {
    ChooseYourPlanScreen(onBack = {}, onContinue = {})
}