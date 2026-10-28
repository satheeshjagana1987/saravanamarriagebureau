package com.saravanamarriagebureau.ui.screens.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saravanamarriagebureau.ui.components.LoadingView
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.theme.Cream
import timber.log.Timber

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileViewScreen(
    onBack: () -> Unit,
    onInterest: () -> Unit,
    profileId: String,
    profileType: String,
    onMatchFixed: () -> Unit = {},
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Timber.e("profileId  => $profileId")
    val uiState by viewModel.uiStateById
        .collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadProfileById(
            profileId = profileId
        )
    }

    when (val state = uiState) {

        is ProfileUIState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Cream),
                contentAlignment = Alignment.Center
            ) {
                LoadingView()
            }
        }

        is ProfileUIState.Success -> {

            ProfileContent(
                profile = state.data,
                onBack = onBack,
                onInterest = onInterest,
                profileType = profileType,
                onMatchFixed = onMatchFixed
            )
        }

        is ProfileUIState.Error -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Cream),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = state.message)

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                PrimaryButton(
                    text = "Retry",
                    onClick = {
                        viewModel.loadProfile()
                    }
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                TextButton(onClick = onBack) {
                    Text("Go back")
                }
            }
        }

        else -> {}
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ProfileViewScreenPreview() {
    ProfileViewScreen(onBack = {}, onInterest = {}, profileId = "", onMatchFixed = {}, profileType = "")
}