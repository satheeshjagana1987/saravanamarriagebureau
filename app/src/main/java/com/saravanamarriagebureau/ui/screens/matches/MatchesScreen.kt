package com.saravanamarriagebureau.ui.screens.matches

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
import com.saravanamarriagebureau.data.enums.MatchTab
import com.saravanamarriagebureau.data.model.MatchesData
import com.saravanamarriagebureau.ui.components.CustomButton
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.components.ProfileCard
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White
import com.saravanamarriagebureau.util.ToastUtils

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MatchesScreen(
    onProfile: (MatchesData) -> Unit,
    onInterestSent: () -> Unit,
    viewModel: MatchesViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var isLoading by remember {
        mutableStateOf(false)
    }

    var todayMatchesData by remember {
        mutableStateOf<List<MatchesData>>(emptyList())
    }
    val uiStateToday by viewModel.uiStateToday.collectAsStateWithLifecycle()
    val uiStatePast by viewModel.uiStatePast.collectAsStateWithLifecycle()

    var selectedTab by remember {
        mutableStateOf(MatchTab.TODAY)
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
                viewModel.resetState()
                isLoading = false
                todayMatchesData = state.data
                ToastUtils.success(context = context, message = "Matches data fetched successfully")
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

    when (val state = uiStatePast) {

        is MatchesUiState.Loading -> {
            isLoading = true
        }

        is MatchesUiState.Success -> {
            isLoading = false
            todayMatchesData = state.data
            ToastUtils.success(context = context, message = "Matches data fetched successfully")
        }

        is MatchesUiState.Error -> {
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
                MatchTabButton(
                    text = "Recent",
                    selected = selectedTab == MatchTab.TODAY,
                    onClick = {
                        selectedTab = MatchTab.TODAY
                        viewModel.getTodayMatches()
                    },
                    modifier = Modifier.weight(1f)
                )

                MatchTabButton(
                    text = "Past",
                    selected = selectedTab == MatchTab.PAST,
                    onClick = {
                        selectedTab = MatchTab.PAST
                        viewModel.getPastMatches()
                    },
                    modifier = Modifier.weight(1f)
                )

                MatchTabButton(
                    text = "Recommended",
                    selected = selectedTab == MatchTab.RECOMMENDED,
                    onClick = {
                        selectedTab = MatchTab.RECOMMENDED
                    },
                    modifier = Modifier.weight(1f)
                )

            }
            LazyColumn(
                modifier = Modifier.padding(start = 0.dp, top = 16.dp, end = 0.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (todayMatchesData.isEmpty()) {
                    item {
                        Text(
                            text = "No matches found",
                            modifier = Modifier.padding(16.dp),
                            color = Black
                        )
                    }
                } else {
                    items(
                        items = todayMatchesData,
                        key = { profile -> profile.id }
                    ) { profile ->
                        ProfileCard(
                            profile = profile,
                            onInterest = onInterestSent,
                            onOpen = { onProfile(profile) },
                            viewModel = viewModel
                        )
                    }
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
fun MatchesScreenPreview() {
    MatchesScreen(onProfile = {}, onInterestSent = {})
}