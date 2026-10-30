package com.saravanamarriagebureau.ui.screens.admin.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.visible
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.ui.components.DividerLine
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.LoadingView
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ProfileInfoRow
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.screens.matches.MatchesUiState
import com.saravanamarriagebureau.ui.screens.profile.ProfileContent
import com.saravanamarriagebureau.ui.screens.profile.ProfileUIState
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.Success
import com.saravanamarriagebureau.ui.theme.Warning
import com.saravanamarriagebureau.util.ToastUtils

@Composable
fun AdminUserDetailsScreen(
    onBack: () -> Unit,
    profileId: String,
    viewModel: AdminUsersViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiStateById
        .collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadProfileById(
            profileId = profileId
        )
    }

    when (val state = uiState) {

        is AdminUserByIdUIState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Cream),
                contentAlignment = Alignment.Center
            ) {
                LoadingView()
            }
        }

        is AdminUserByIdUIState.Success -> {
            AdminUserContent(
                profile = state.data,
                onBack = onBack,
                viewModel = viewModel
            )
        }

        is AdminUserByIdUIState.Error -> {
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
                        viewModel.loadProfileById(profileId)
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

@Preview
@Composable
fun AdminUserDetailsScreenPreview() {
    AdminUserDetailsScreen(onBack = {}, profileId = "")
}