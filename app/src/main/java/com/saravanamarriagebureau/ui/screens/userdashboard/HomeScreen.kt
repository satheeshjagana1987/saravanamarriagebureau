package com.saravanamarriagebureau.ui.screens.userdashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.data.model.MembershipDetails
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.formatMembershipDate
import com.saravanamarriagebureau.ui.components.isExpired
import com.saravanamarriagebureau.ui.screens.membership.MemberShipDetailsUIState
import com.saravanamarriagebureau.ui.screens.membership.MemberShipPlansViewModel
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.Red
import com.saravanamarriagebureau.ui.theme.Success
import com.saravanamarriagebureau.ui.theme.White
import com.saravanamarriagebureau.util.ToastUtils

@Composable
fun HomeScreen(
    onViewPlans: () -> Unit,
    viewModel: MemberShipPlansViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    var isLoading by remember {
        mutableStateOf(false)
    }

    var membershipDetails by remember {
        mutableStateOf<MembershipDetails?>(null)
    }

    val uiStateMemberShipDetails by viewModel.uiStateMemberShipDetails.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getMemberShipDetails()
    }

    LoadingDialog(isLoading = isLoading)

    LaunchedEffect(uiStateMemberShipDetails) {

        when (val state = uiStateMemberShipDetails) {

            is MemberShipDetailsUIState.Loading -> {
                isLoading = true
            }

            is MemberShipDetailsUIState.Success -> {
                isLoading = false
                membershipDetails = state.data
            }

            is MemberShipDetailsUIState.Error -> {
                isLoading = false
                ToastUtils.error(context, state.message)
            }

            else -> {
                isLoading = false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Cream)
            .padding(16.dp)
    ) {
        OfferScreen()
        Spacer(Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            membershipDetails?.let { details ->
                MembershipDetailsCard(
                    onClick = onViewPlans,
                    membershipDetails = details
                )
            } ?: run {
                MembershipDetailsCard(
                    onClick = onViewPlans,
                    membershipDetails = membershipDetails
                )
            }
        }
    }
}


@Composable
private fun MembershipDetailsCard(
    onClick: () -> Unit,
    membershipDetails: MembershipDetails?
) {
    Column(
        Modifier
            .padding(20.dp)
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (membershipDetails != null) {
            val startDate: String = formatMembershipDate(membershipDetails.startsAt)
            val endDate: String = formatMembershipDate(membershipDetails.expiresAt)

            val expired = isExpired(expiryDate = membershipDetails.expiresAt)

            LabelName(
                labelName = membershipDetails.planName ?: "NA",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Maroon
            )
            Spacer(Modifier.height(15.dp))
            LabelName(
                labelName = if (expired) "Expired" else membershipDetails.status,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (expired) Red else Success,
                textAlign = TextAlign.End
            )
            Spacer(Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                LabelName(
                    labelName = "Start Date",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Black,
                    modifier = Modifier.weight(1f)
                )

                LabelName(
                    labelName = startDate,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )

            }

            Spacer(Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                LabelName(
                    labelName = "End Date",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Black,
                    modifier = Modifier.weight(1f)
                )

                LabelName(
                    labelName = endDate,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(20.dp))
            if (expired) {
                PrimaryButton(text = stringResource(id = R.string.renew_plan), onClick = {
                    onClick()
                })
            }
        } else {
            LabelName(
                labelName = "Basic Plan(Current plan)",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Maroon
            )
            Spacer(Modifier.height(15.dp))
            LabelName(
                labelName = "Upgrade your plan to get more benefits ",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Success
            )
            Spacer(Modifier.height(15.dp))
            PrimaryButton(text = stringResource(id = R.string.upgrade_plan), onClick = {
                onClick()
            })
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(onViewPlans = {})
}
