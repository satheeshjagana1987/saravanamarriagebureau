package com.saravanamarriagebureau.ui.screens.matches

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saravanamarriagebureau.data.enums.MatchTab
import com.saravanamarriagebureau.data.model.MatchesData
import com.saravanamarriagebureau.ui.components.CustomButton
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.components.ProfileCard
import com.saravanamarriagebureau.ui.screens.interests.SendInterestsUIState
import com.saravanamarriagebureau.ui.screens.profile.ProfileUIState
import com.saravanamarriagebureau.ui.screens.profile.ProfileViewModel
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White
import com.saravanamarriagebureau.util.ToastUtils
import timber.log.Timber

@Composable
fun MatchesScreen(
    onProfile: (MatchesData) -> Unit,
    viewModel: MatchesViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var isLoading by remember {
        mutableStateOf(false)
    }

    var todayMatchesData by remember {
        mutableStateOf<List<MatchesData>>(emptyList())
    }

    var pastMatchesData by remember {
        mutableStateOf<List<MatchesData>>(emptyList())
    }

    var recommendedMatchesData by remember {
        mutableStateOf<List<MatchesData>>(emptyList())
    }

    val uiStateToday by viewModel.uiStateToday.collectAsStateWithLifecycle()
    val uiStatePast by viewModel.uiStatePast.collectAsStateWithLifecycle()
    val uiStateRecommended by viewModel.uiStateRecommended.collectAsStateWithLifecycle()

    val isPaidUser = remember {
        viewModel.isPaidUser()
    }


    val uiStateSendInterest by
    viewModel.uiStateSendInterest.collectAsStateWithLifecycle()

    val uiState by profileViewModel.uiState.collectAsStateWithLifecycle()


    var selectedTab by remember {
        mutableStateOf(MatchTab.TODAY)
    }

    var todayCount by remember {
        mutableStateOf("0")
    }

    var pastCount by remember {
        mutableStateOf("0")
    }
    var recommendedCount by remember {
        mutableStateOf("0")
    }

    var matchProfileId by remember {
        mutableStateOf("")
    }

    val displayedMatches = when (selectedTab) {
        MatchTab.TODAY -> todayMatchesData
        MatchTab.PAST -> pastMatchesData
        MatchTab.RECOMMENDED -> recommendedMatchesData
    }

    LaunchedEffect(Unit) {
        viewModel.getTodayMatches()
    }

    LoadingDialog(isLoading = isLoading)

    LaunchedEffect(uiStateToday) {

        when (val state = uiStateToday) {

            is MatchesUiState.Loading -> {
                isLoading = true
            }

            is MatchesUiState.Success -> {
                isLoading = false
                todayMatchesData = state.data
                todayCount = state.data.size.toString()
            }

            is MatchesUiState.Error -> {
                isLoading = false
                ToastUtils.error(context, state.message)
            }

            else -> {
                isLoading = false
            }
        }
    }
    LaunchedEffect(uiStatePast) {
        when (val state = uiStatePast) {

            is MatchesUiState.Loading -> {
                isLoading = true
            }

            is MatchesUiState.Success -> {
                isLoading = false
                pastMatchesData = state.data
                pastCount = state.data.size.toString()
            }

            is MatchesUiState.Error -> {
                isLoading = false
                ToastUtils.error(context, state.message)
            }

            else -> {
                isLoading = false
            }
        }
    }
    LaunchedEffect(uiStateRecommended) {
        when (val state = uiStateRecommended) {

            is MatchesUiState.Loading -> {
                isLoading = true
            }

            is MatchesUiState.Success -> {
                isLoading = false
                recommendedMatchesData = state.data
                recommendedCount = state.data.size.toString()
            }

            is MatchesUiState.Error -> {
                isLoading = false
                ToastUtils.error(context, state.message)
            }

            else -> {
                isLoading = false
            }
        }
    }


    LaunchedEffect(uiState) {

        when (val state = uiState) {

            is ProfileUIState.Loading -> {
            }

            is ProfileUIState.Success -> {
              val isAdminVerified = state.data.isAdminVerified
                Timber.e("match===matchProfileId===>$matchProfileId")
                Timber.e("match===isAdminVerified===>$isAdminVerified")
                Timber.e("match===isPaidUser===>$isPaidUser")
                if(isAdminVerified) {
                    if (isPaidUser) {
                        viewModel.sendInterest(profileId = matchProfileId)
                    } else {
                        ToastUtils.error(
                            context = context,
                            message = "Please upgrade your plan to access this feature"
                        )
                    }
                } else {
                    ToastUtils.error(
                        context = context,
                        message = "Your Profile still under validation so you can't access this feature"
                    )
                }
            }

            is ProfileUIState.Error -> {
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
                MatchTabButton(
                    text = "Recent",
                    count = todayCount,
                    selected = selectedTab == MatchTab.TODAY,
                    onClick = {
                        if (selectedTab == MatchTab.TODAY) return@MatchTabButton
                        selectedTab = MatchTab.TODAY
                        viewModel.getTodayMatches()
                    },
                    modifier = Modifier.weight(1f)
                )

                MatchTabButton(
                    text = "Past",
                    count = pastCount,
                    selected = selectedTab == MatchTab.PAST,
                    onClick = {
                        if (selectedTab == MatchTab.PAST) return@MatchTabButton
                        selectedTab = MatchTab.PAST
                        viewModel.getPastMatches()
                    },
                    modifier = Modifier.weight(1f)
                )

                MatchTabButton(
                    text = "Recommended",
                    count = recommendedCount,
                    selected = selectedTab == MatchTab.RECOMMENDED,
                    onClick = {
                        if (selectedTab == MatchTab.RECOMMENDED) return@MatchTabButton
                        selectedTab = MatchTab.RECOMMENDED
                        viewModel.getRecommendedMatches()
                    },
                    modifier = Modifier.weight(1f)
                )

            }
            LazyColumn(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (displayedMatches.isEmpty()) {
                    item {
                        Text(
                            text = "No matches found",
                            modifier = Modifier.padding(16.dp),
                            color = Black,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    items(
                        items = displayedMatches,
                        key = { profile -> profile.id }
                    ) { profile ->
                        ProfileCard(
                            profile = profile,
                            onInterest = { matchId ->
                                matchProfileId = matchId
                                profileViewModel.loadProfile()
                            },
                            onOpen = { onProfile(profile) },
                            viewModel = viewModel
                        )
                    }
                }

            }

            LaunchedEffect(uiStateSendInterest) {

                when (val state = uiStateSendInterest) {

                    is SendInterestsUIState.Success -> {

                        when (selectedTab) {

                            MatchTab.TODAY -> {
                                viewModel.getTodayMatches()
                            }

                            MatchTab.PAST -> {
                                viewModel.getPastMatches()
                            }

                            MatchTab.RECOMMENDED -> {
                                viewModel.getRecommendedMatches()
                            }
                        }

                        viewModel.resetSendInterestState()
                    }

                    is SendInterestsUIState.Error -> {
                        ToastUtils.error(context, state.message)
                        viewModel.resetSendInterestState()
                    }

                    else -> Unit
                }
            }
        }
    }

    when (selectedTab) {
        MatchTab.TODAY -> ""
        MatchTab.PAST -> ""
        MatchTab.RECOMMENDED -> ""
    }
}

@Composable
fun MatchTabButton(
    text: String,
    count:String,
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

@Preview
@Composable
fun MatchesScreenPreview() {
    MatchesScreen(onProfile = {})
}