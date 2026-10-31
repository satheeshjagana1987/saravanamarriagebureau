package com.saravanamarriagebureau.ui.screens.membership

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saravanamarriagebureau.data.model.PlanData
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.CardBorder
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.Success
import com.saravanamarriagebureau.util.ToastUtils

@Composable
fun ChooseYourPlanScreen(
    onBack: () -> Unit,
    onContinue: (String) -> Unit,
    viewModel: MemberShipPlansViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    var isLoading by remember {
        mutableStateOf(false)
    }

    var planData by remember {
        mutableStateOf<List<PlanData>>(emptyList())
    }

    var selectedPlan by remember {
        mutableStateOf<PlanData?>(null)
    }

    var invoiceId by remember {
        mutableStateOf("")
    }

    var keyId by remember {
        mutableStateOf("")
    }

    var orderId by remember {
        mutableStateOf("")
    }

    val uiStateMemberShipPlans by viewModel.uiStateMemberShipPlans.collectAsStateWithLifecycle()
    val uiStateMemberShipPlanPurchase by viewModel.uiStateMemberShipPlanPurchase.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getMemberShipPlans()
    }

    LoadingDialog(isLoading = isLoading)

    LaunchedEffect(uiStateMemberShipPlans) {

        when (val state = uiStateMemberShipPlans) {

            is MemberShipPlansUIState.Loading -> {
                isLoading = true
            }

            is MemberShipPlansUIState.Success -> {
                isLoading = false
                planData = state.data
            }

            is MemberShipPlansUIState.Error -> {
                isLoading = false
                ToastUtils.error(context, state.message)
            }

            else -> {
                isLoading = false
            }
        }
    }

    when (val state = uiStateMemberShipPlanPurchase) {

        is MemberShipPlanPurchaseUiState.Loading -> {
            isLoading = true
        }

        is MemberShipPlanPurchaseUiState.Success -> {
            isLoading = false
            invoiceId = state.data.invoiceId
            orderId = state.data.payment.orderId
            keyId = state.data.payment.keyId
        }

        is MemberShipPlanPurchaseUiState.Error -> {
            isLoading = false
            ToastUtils.error(context, state.message)
        }

        else -> {
            isLoading = false
        }
    }

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

            Column(Modifier.fillMaxSize()) {

                LazyColumn(
                    modifier = Modifier.padding(
                        start = 0.dp,
                        top = 16.dp,
                        end = 0.dp,
                        bottom = 16.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (planData.isEmpty()) {
                        item {
                            Text(
                                text = "No matches found",
                                modifier = Modifier.padding(16.dp),
                                color = Black
                            )
                        }
                    } else {
                        items(
                            items = planData,
                            key = { plan -> plan.id }
                        ) { plan ->
                            PlanCard(
                                plan = plan,
                                isSelected = selectedPlan?.id == plan.id,
                                onClick = {
                                    selectedPlan = plan
                                }
                            )
                        }
                    }

                }

                Spacer(Modifier.weight(1f))

                PrimaryButton(
                    "Proceed to payment",
                    onClick = {
                        selectedPlan?.let { plan ->
                            viewModel.membershipPlanPurchase(planId = plan.id)
//                            onContinue(plan.id)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 50.dp
                        ),
                    enabled = selectedPlan != null
                )
            }
        }
    }
}

@Composable
private fun PlanCard(
    plan: PlanData,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                Color(0xFFFFF5F5)
            } else {
                Color.White
            }
        ),
        border = if (isSelected) {
            BorderStroke(1.dp, Maroon)
        } else {
            BorderStroke(1.dp, CardBorder)
        },
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                LabelName(
                    labelName = plan.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Black,
                    modifier = Modifier.padding(0.dp)
                )
                Spacer(Modifier.height(5.dp))
                LabelName(
                    labelName = plan.priceMinorUnits.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Maroon,
                    modifier = Modifier.padding(0.dp)
                )
                Spacer(Modifier.height(5.dp))

                LabelName(
                    labelName = plan.description,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Success,
                    modifier = Modifier.padding(0.dp)
                )
            }
            Icon(
                imageVector = if (isSelected) {
                    Icons.Default.CheckCircle
                } else {
                    Icons.Default.RadioButtonUnchecked
                },
                contentDescription = if (isSelected) {
                    "Selected plan"
                } else {
                    "Select plan"
                },
                tint = if (isSelected) Success else CardBorder
            )
        }
    }
}

@Preview
@Composable
fun ChooseYourPlanScreenPreview() {
    ChooseYourPlanScreen(onBack = {}, onContinue = {})
}