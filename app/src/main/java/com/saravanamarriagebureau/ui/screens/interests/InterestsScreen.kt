package com.saravanamarriagebureau.ui.screens.interests

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
import com.saravanamarriagebureau.data.model.UserInterestsData
import com.saravanamarriagebureau.ui.components.CustomButton
import com.saravanamarriagebureau.ui.components.InterestCard
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White
import com.saravanamarriagebureau.util.ToastUtils

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InterestsScreen(
    onInterestItemClick: (UserInterestsData) -> Unit,
    viewModel: UserInterestsViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    var selectedTab by remember {
        mutableStateOf(InterestTab.RECEIVED)
    }

    var receivedInterests by remember {
        mutableStateOf<List<UserInterestsData>>(emptyList())
    }

    var sentInterests by remember {
        mutableStateOf<List<UserInterestsData>>(emptyList())
    }

    var mutualInterests by remember {
        mutableStateOf<List<UserInterestsData>>(emptyList())
    }


    val displayedInterests = when (selectedTab) {
        InterestTab.RECEIVED -> receivedInterests
        InterestTab.SENT -> sentInterests
        InterestTab.MATCH_FIXED -> mutualInterests
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    val uiStateReceived by viewModel.uiStateReceived.collectAsStateWithLifecycle()
    val uiStateSentInterest by viewModel.uiStateSentInterest.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getReceivedInterests()
    }

    LoadingDialog(isLoading = isLoading)

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
                    text = "Received",
                    selected = selectedTab == InterestTab.RECEIVED,
                    onClick = {
                        selectedTab = InterestTab.RECEIVED
                        viewModel.getReceivedInterests()
                    },
                    modifier = Modifier.weight(1f)
                )

                InterestTabButton(
                    text = "Sent",
                    selected = selectedTab == InterestTab.SENT,
                    onClick = {
                        selectedTab = InterestTab.SENT
                        viewModel.getSentInterests()
                    },
                    modifier = Modifier.weight(1f)
                )

                InterestTabButton(
                    text = "Match Fixed",
                    selected = selectedTab == InterestTab.MATCH_FIXED,
                    onClick = {
                        selectedTab = InterestTab.MATCH_FIXED
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
                        InterestCard(
                            interest = interest,
                            selectedTab = selectedTab,
                            onOpen = { onInterestItemClick(interest) },
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
        LaunchedEffect(uiStateReceived) {
            when (val state = uiStateReceived) {

                is UserInterestsUIState.Loading -> {
                    isLoading = true
                }

                is UserInterestsUIState.Success -> {
                    isLoading = false
                    receivedInterests = state.data
                    ToastUtils.success(
                        context = context,
                        message = "Received Interests fetched successfully"
                    )
                }

                is UserInterestsUIState.Error -> {
                    isLoading = false
                    ToastUtils.error(context, state.message)
                }

                else -> {
                    isLoading = false
                }
            }
        }

        when (val state = uiStateSentInterest) {

            is UserInterestsUIState.Loading -> {
                isLoading = true
            }

            is UserInterestsUIState.Success -> {
                isLoading = false
                sentInterests = state.data
                ToastUtils.success(
                    context = context,
                    message = "Received Interests fetched successfully"
                )
            }

            is UserInterestsUIState.Error -> {
                isLoading = false
                ToastUtils.error(context, state.message)
            }

            else -> {
                isLoading = false
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
fun InterestsScreenPreview() {
    InterestsScreen(onInterestItemClick = {})
}