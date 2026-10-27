package com.saravanamarriagebureau.ui.screens.admin.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saravanamarriagebureau.data.enums.UsersTab
import com.saravanamarriagebureau.data.model.PendingApprovalUser
import com.saravanamarriagebureau.ui.components.AdminUserCard
import com.saravanamarriagebureau.ui.components.CustomButton
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White
import com.saravanamarriagebureau.util.ToastUtils

@Composable
fun AdminUsersScreen(
    onProfile: (PendingApprovalUser) -> Unit,
    viewModel: AdminUsersViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    var isLoading by remember {
        mutableStateOf(false)
    }

    var adminUsersData by remember {
        mutableStateOf<List<PendingApprovalUser>>(emptyList())
    }
    val uiState by viewModel.uiStateAdminUsers.collectAsStateWithLifecycle()

    var selectedTab by remember {
        mutableStateOf(UsersTab.PENDING)
    }

    LaunchedEffect(Unit) {
        viewModel.getAdminUsers()
    }

    LoadingDialog(isLoading = isLoading)

    LaunchedEffect(uiState) {

        when (val state = uiState) {

            is AdminUsersUIState.Loading -> {
                isLoading = true
            }

            is AdminUsersUIState.Success -> {
                viewModel.resetState()
                isLoading = false
                adminUsersData = state.data
                ToastUtils.success(context = context, message = "Users data fetched successfully")
            }

            is AdminUsersUIState.Error -> {
                isLoading = false
                ToastUtils.error(context, state.message)
            }

            else -> {
                isLoading = false
            }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Cream),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(Modifier.padding(horizontal = 16.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                UserTabButton(
                    text = "Pending",
                    selected = selectedTab == UsersTab.PENDING,
                    onClick = {
                        selectedTab = UsersTab.PENDING
                    },
                    modifier = Modifier.weight(1f)
                )

                UserTabButton(
                    text = "Approved",
                    selected = selectedTab == UsersTab.APPROVED,
                    onClick = {
                        selectedTab = UsersTab.APPROVED
                    },
                    modifier = Modifier.weight(1f)
                )

//                UserTabButton(
//                    text = "Rejected",
//                    selected = selectedTab == UsersTab.REJECTED,
//                    onClick = {
//                        selectedTab = UsersTab.REJECTED
//                    },
//                    modifier = Modifier.weight(1f)
//                )

            }
            LazyColumn(
                modifier = Modifier.padding(start = 0.dp, top = 16.dp, end = 0.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                if (adminUsersData.isEmpty()) {
                    item {
                        Text(
                            text = "No data found",
                            modifier = Modifier.padding(16.dp),
                            color = Black
                        )
                    }
                } else {
                    items(
                        items = adminUsersData,
                        key = { profile -> profile.id }
                    ) { profile ->
                        AdminUserCard(
                            profile,
                            onOpen = { onProfile(profile) })
                    }
                }
            }
        }
    }

    when (selectedTab) {
        UsersTab.PENDING -> ""
        UsersTab.APPROVED -> ""
        UsersTab.REJECTED -> ""
    }
}

@Composable
fun UserTabButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        height = 40.dp,
        width = 0.dp,
        cornerRadius = 5.dp,

        textColor = if (selected) White else Black,

        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,

        backgroundColor = if (selected) Maroon else White,

        borderColor = if (selected) Maroon else Black,

        borderStroke = 0.2.dp
    )
}

@Preview
@Composable
fun AdminUsersScreenPreview(){
    AdminUsersScreen(onProfile = {})
}