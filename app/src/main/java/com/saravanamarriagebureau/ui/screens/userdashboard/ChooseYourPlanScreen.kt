package com.saravanamarriagebureau.ui.screens.userdashboard

import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
    onContinue: () -> Unit,
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
            MembershipScreen(onContinue)
        }
    }
}

@Composable
fun MembershipScreen(onContinue: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        PlanCard("Dasara Offer", "Free Registration", "₹0", selected = true)
        PlanCard(stringResource(id = R.string.yearly_membership), "For profile viewing only", "₹299 / Year", selected = false)
        Spacer(Modifier.weight(1f))
        PrimaryButton(
            "Continue",
            onClick = {onContinue},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, end = 16.dp, bottom = 50.dp)
        )
    }
}

@Composable
private fun PlanCard(title: String, subtitle: String, price: String, selected: Boolean) {
    Card(
        Modifier.fillMaxWidth().padding(vertical = 8.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
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
                Icons.Default.CheckCircle,
                null,
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