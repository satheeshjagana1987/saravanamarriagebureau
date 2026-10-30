package com.saravanamarriagebureau.ui.screens.admin.interests

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.saravanamarriagebureau.data.enums.InterestTab
import com.saravanamarriagebureau.data.enums.MatchTab
import com.saravanamarriagebureau.data.model.AdminInterestsData
import com.saravanamarriagebureau.data.model.Interest
import com.saravanamarriagebureau.data.model.MatchesData
import com.saravanamarriagebureau.data.model.demoInterests
import com.saravanamarriagebureau.ui.components.AdminInterestCard
import com.saravanamarriagebureau.ui.components.CustomButton
import com.saravanamarriagebureau.ui.components.InterestCard
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.screens.matches.MatchesUiState
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White
import com.saravanamarriagebureau.util.ToastUtils

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AdminInterestsScreen(
    onInterestItemClick: (AdminInterestsData) -> Unit,
    viewModel: AdminInterestsViewModel = hiltViewModel()
) {

    var selectedTab by remember {
        mutableStateOf("PENDING")
    }

    val context = LocalContext.current
    var isLoading by remember {
        mutableStateOf(false)
    }

    var pendingData by remember {
        mutableStateOf<List<AdminInterestsData>>(emptyList())
    }

    var acceptedData by remember {
        mutableStateOf<List<AdminInterestsData>>(emptyList())
    }

    val uiStateAdminPendingInterests by viewModel.uiStateAdminPendingInterests.collectAsStateWithLifecycle()
    val uiStateAdminAcceptedInterests by viewModel.uiStateAdminAcceptedInterests.collectAsStateWithLifecycle()

    val displayedInterests = when (selectedTab) {
        "PENDING" -> pendingData
        "ACCEPTED" -> acceptedData
        else -> pendingData
    }

    LaunchedEffect(Unit) {
        viewModel.getAdminPendingInterests()
    }

    LoadingDialog(isLoading = isLoading)

    LaunchedEffect(uiStateAdminPendingInterests) {

        when (val state = uiStateAdminPendingInterests) {

            is AdminInterestsUIState.Loading -> {
                isLoading = true
            }

            is AdminInterestsUIState.Success -> {
                isLoading = false
                pendingData = state.data
            }

            is AdminInterestsUIState.Error -> {
                isLoading = false
                ToastUtils.error(context, state.message)
            }

            else -> {
                isLoading = false
            }
        }
    }

    when (val state = uiStateAdminAcceptedInterests) {

        is AdminInterestsUIState.Loading -> {
            isLoading = true
        }

        is AdminInterestsUIState.Success -> {
            isLoading = false
            acceptedData = state.data
        }

        is AdminInterestsUIState.Error -> {
            isLoading = false
            ToastUtils.error(context, state.message)
        }

        else -> {
            isLoading = false
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
                InterestTabButton(
                    text = "Pending",
                    selected = selectedTab == "PENDING",
                    onClick = {
                        selectedTab = "PENDING"
                        viewModel.getAdminPendingInterests()
                    },
                    modifier = Modifier.weight(1f)
                )

                InterestTabButton(
                    text = "Accepted",
                    selected = selectedTab == "ACCEPTED",
                    onClick = {
                        selectedTab = "ACCEPTED"
                        viewModel.getAdminAcceptedInterests()
                    },
                    modifier = Modifier.weight(1f)
                )

            }
            LazyColumn(
                modifier = Modifier.padding(start = 0.dp, top = 16.dp, end = 0.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                if (displayedInterests.isEmpty()) {
                    item {
                        Text(
                            text = "No Interests found",
                            modifier = Modifier.padding(16.dp),
                            color = Black
                        )
                    }
                } else {
                    items(
                        items = displayedInterests,
                        key = { interest -> interest.id }
                    ) { interest ->
                        AdminInterestCard(
                            interest,
                            onOpen = {onInterestItemClick(interest)},
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InterestTabButton(
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AdminInterestsScreenPreview(){
    AdminInterestsScreen(onInterestItemClick = {})
}