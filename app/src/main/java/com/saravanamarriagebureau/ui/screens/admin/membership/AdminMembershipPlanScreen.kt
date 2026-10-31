package com.saravanamarriagebureau.ui.screens.admin.membership

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saravanamarriagebureau.data.model.AddMembershipPlanData
import com.saravanamarriagebureau.ui.components.AdminMembershipCard
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.util.ToastUtils

@Composable
fun AdminMembershipPlanScreen(
    viewModel: AdminMembershipViewModel = hiltViewModel(),
    openEdit: (AddMembershipPlanData) -> Unit
) {

    val context = LocalContext.current
    var isLoading by remember {
        mutableStateOf(false)
    }

    var membershipPlansData by remember {
        mutableStateOf<List<AddMembershipPlanData>>(emptyList())
    }

    val uiStateAdminMembershipPlans by viewModel.uiStateAdminMembershipPlans.collectAsStateWithLifecycle()
    val uiStateDeleteAdminMembershipPlan by viewModel.uiStateDeleteAdminMembershipPlan.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAdminMemberShipPlans()
    }

    LoadingDialog(isLoading = isLoading)

    LaunchedEffect(uiStateAdminMembershipPlans) {

        when (val state = uiStateAdminMembershipPlans) {

            is AdminMembershipPlansUIState.Loading -> {
                isLoading = true
            }

            is AdminMembershipPlansUIState.Success -> {
                isLoading = false
                membershipPlansData = state.data
            }

            is AdminMembershipPlansUIState.Error -> {
                isLoading = false
                ToastUtils.error(context, state.message)
            }

            else -> {
                isLoading = false
            }
        }
    }

    LaunchedEffect(uiStateDeleteAdminMembershipPlan) {

        when (val state = uiStateDeleteAdminMembershipPlan) {

            is DeleteAdminMembershipPlanUIState.Loading -> {
            }

            is DeleteAdminMembershipPlanUIState.Success -> {

                viewModel.resetDeleteInterestState()
                viewModel.getAdminMemberShipPlans()
            }

            is DeleteAdminMembershipPlanUIState.Error -> {

                ToastUtils.error(context, state.message)
            }

            else -> Unit
        }
    }


    Column(
        Modifier
            .fillMaxSize()
            .background(Cream),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(Modifier.padding(horizontal = 16.dp)) {

            LazyColumn(
                modifier = Modifier.padding(start = 0.dp, top = 16.dp, end = 0.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                if (membershipPlansData.isEmpty()) {
                    item {
                        Text(
                            text = "No data found",
                            modifier = Modifier.padding(16.dp),
                            color = Black
                        )
                    }
                } else {
                    items(
                        items = membershipPlansData,
                        key = { profile -> profile.id }
                    ) { profile ->
                        AdminMembershipCard(
                            profile,
                            onEdit = { membership ->
                                openEdit(membership)
                            },
                            onDelete = { id ->
                                viewModel.deleteAdminMemberShipPlanDetails(planId = id)
                            }
                        )
                    }
                }
            }
        }
    }
}