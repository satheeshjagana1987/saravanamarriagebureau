package com.saravanamarriagebureau.ui.screens.admin.users

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.style.TextAlign
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
    onProfile: (String, Boolean) -> Unit,
    viewModel: AdminUsersViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    var isLoading by remember {
        mutableStateOf(false)
    }

    var adminPendingUsersData by remember {
        mutableStateOf<List<PendingApprovalUser>>(emptyList())
    }

    var adminApprovedUsersData by remember {
        mutableStateOf<List<PendingApprovalUser>>(emptyList())
    }

    var adminRejectedUsersData by remember {
        mutableStateOf<List<PendingApprovalUser>>(emptyList())
    }

    var adminBlockedUsersData by remember {
        mutableStateOf<List<PendingApprovalUser>>(emptyList())
    }

    var selectedTab by remember {
        mutableStateOf(UsersTab.PENDING)
    }

    var pendingCount by remember {
        mutableStateOf("0")
    }

    var approvedCount by remember {
        mutableStateOf("0")
    }

    var rejectedCount by remember {
        mutableStateOf("0")
    }

    var blockedCount by remember {
        mutableStateOf("0")
    }

    val displayedUsers = when (selectedTab) {
        UsersTab.PENDING -> adminPendingUsersData
        UsersTab.APPROVED -> adminApprovedUsersData
        UsersTab.REJECTED -> adminRejectedUsersData
        UsersTab.BLOCKED -> adminBlockedUsersData
    }

    val uiStatePending by viewModel.uiStateAdminPendingUsers.collectAsStateWithLifecycle()
    val uiStateApproved by viewModel.uiStateAdminApprovedUsers.collectAsStateWithLifecycle()
    val uiStateRejected by viewModel.uiStateAdminRejectedUsers.collectAsStateWithLifecycle()
    val uiStateBlocked by viewModel.uiStateAdminBlockedUsers.collectAsStateWithLifecycle()

    var searchQuery by remember {
        mutableStateOf("")
    }

    val filteredUsers = remember(displayedUsers, searchQuery) {

        if (searchQuery.isBlank()) {
            displayedUsers
        } else {
            displayedUsers.filter { user ->

                user.fullName.contains(
                    searchQuery,
                    ignoreCase = true
                ) ||
                        user.mobile.contains(
                            searchQuery,
                            ignoreCase = true
                        ) ||
                        user.id.contains(
                            searchQuery,
                            ignoreCase = true
                        )
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getAdminPendingUsers()
    }

    LoadingDialog(isLoading = isLoading)

    LaunchedEffect(uiStatePending) {

        when (val state = uiStatePending) {

            is AdminUsersUIState.Loading -> {
                isLoading = true
            }

            is AdminUsersUIState.Success -> {
                isLoading = false
                adminPendingUsersData = state.data
                pendingCount = state.data.size.toString()

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

    LaunchedEffect(uiStateApproved) {

        when (val state = uiStateApproved) {

            is AdminUsersUIState.Loading -> {
                isLoading = true
            }

            is AdminUsersUIState.Success -> {
                isLoading = false
                adminApprovedUsersData = state.data
                approvedCount = state.data.size.toString()

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

    LaunchedEffect(uiStateRejected) {

        when (val state = uiStateRejected) {

            is AdminUsersUIState.Loading -> {
                isLoading = true
            }

            is AdminUsersUIState.Success -> {
                isLoading = false
                adminRejectedUsersData = state.data
                rejectedCount = state.data.size.toString()
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

    LaunchedEffect(uiStateBlocked) {

        when (val state = uiStateBlocked) {

            is AdminUsersUIState.Loading -> {
                isLoading = true
            }

            is AdminUsersUIState.Success -> {
                isLoading = false
                adminBlockedUsersData = state.data
                blockedCount = state.data.size.toString()
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
        Column(Modifier.padding(16.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                UserTabButton(
                    text = "Pending",
                    count = pendingCount,
                    selected = selectedTab == UsersTab.PENDING,
                    onClick = {
                        selectedTab = UsersTab.PENDING
                        viewModel.getAdminPendingUsers()
                    },
                    modifier = Modifier.weight(1f)
                )

                UserTabButton(
                    text = "Approved",
                    count = approvedCount,
                    selected = selectedTab == UsersTab.APPROVED,
                    onClick = {
                        selectedTab = UsersTab.APPROVED
                        viewModel.getAdminApprovedUsers()
                    },
                    modifier = Modifier.weight(1f)
                )

                UserTabButton(
                    text = "Rejected",
                    count = rejectedCount,
                    selected = selectedTab == UsersTab.REJECTED,
                    onClick = {
                        selectedTab = UsersTab.REJECTED
                        viewModel.getAdminRejectedUsers()
                    },
                    modifier = Modifier.weight(1f)
                )
                UserTabButton(
                    text = "Blocked",
                    count = blockedCount,
                    selected = selectedTab == UsersTab.BLOCKED,
                    onClick = {
                        selectedTab = UsersTab.BLOCKED
                        viewModel.getAdminBlockedUsers()
                    },
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text("Search by name or mobile")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                searchQuery = ""
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear search"
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                if (filteredUsers.isEmpty()) {
                    item {
                        Text(
                            text = if (searchQuery.isNotBlank()) {
                                "No matching users found"
                            } else {
                                "No users found"
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            color = Black,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    items(
                        items = filteredUsers,
                        key = { profile -> profile.id }
                    ) { profile ->
                        AdminUserCard(
                            profile,
                            onOpen = {
                                if (selectedTab == UsersTab.PENDING) {
                                    onProfile(profile.id, true)
                                } else {
                                    onProfile(profile.id, false)
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    when (selectedTab) {
        UsersTab.PENDING -> ""
        UsersTab.APPROVED -> ""
        UsersTab.REJECTED -> ""
        UsersTab.BLOCKED -> ""
    }
}

@Composable
fun UserTabButton(
    text: String,
    count: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomButton(
        text = "$text  $count",
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
