package com.saravanamarriagebureau.ui.screens.admin.interests

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.ui.components.DividerLine
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.components.ProfileInfoRow
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.util.Constants.NA
import com.saravanamarriagebureau.util.ToastUtils

@Composable
fun AdminInterestDetailsScreen(
    onBack: () -> Unit,
    interestId: String,
    viewModel: AdminInterestsViewModel = hiltViewModel()
) {
    val  context = LocalContext.current

    var isLoading by remember {
        mutableStateOf(false)
    }

    var senderName by remember { mutableStateOf("") }
    var senderDob by remember { mutableStateOf("") }
    var senderGender by remember { mutableStateOf("") }
    var senderCity by remember { mutableStateOf("") }
    var senderDistrict by remember { mutableStateOf("") }
    var senderEducation by remember { mutableStateOf("") }
    var senderProfession by remember { mutableStateOf("") }
    var senderMobileNumber by remember { mutableStateOf("") }
    var senderMaritalStatus by remember { mutableStateOf("") }

    var receiverName by remember { mutableStateOf("") }
    var receiverDob by remember { mutableStateOf("") }
    var receiverGender by remember { mutableStateOf("") }
    var receiverCity by remember { mutableStateOf("") }
    var receiverDistrict by remember { mutableStateOf("") }
    var receiverEducation by remember { mutableStateOf("") }
    var receiverProfession by remember { mutableStateOf("") }
    var receiverMobileNumber by remember { mutableStateOf("") }
    var receiverMaritalStatus by remember { mutableStateOf("") }

    val uiStateAdminInterestsById by viewModel.uiStateAdminInterestsById.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadAdminInterestById(
            profileId = interestId
        )
    }

    LoadingDialog(isLoading = isLoading)

    LaunchedEffect(uiStateAdminInterestsById) {

        when (val state = uiStateAdminInterestsById) {

            is AdminInterestsByIdUIState.Loading -> {
                isLoading = true
            }

            is AdminInterestsByIdUIState.Success -> {
                isLoading = false
                senderName = state.data.sender.fullName
                senderDob = state.data.sender.dateOfBirth ?: NA
                senderGender = state.data.sender.gender
                senderEducation = state.data.sender.education ?: NA
                senderCity = state.data.sender.city ?: NA
                senderMaritalStatus = state.data.sender.maritalStatus ?: NA
                senderProfession = state.data.sender.profession ?: NA

                receiverName = state.data.receiver.fullName
                receiverDob = state.data.receiver.dateOfBirth ?: NA
                receiverGender = state.data.receiver.gender
                receiverEducation = state.data.receiver.education ?: NA
                receiverCity = state.data.receiver.city ?: NA
                receiverMaritalStatus = state.data.receiver.maritalStatus ?: NA
                receiverProfession = state.data.receiver.profession ?: NA
            }

            is AdminInterestsByIdUIState.Error -> {
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
            .verticalScroll(rememberScrollState())
            .background(Cream),
    ) {
        ScreenHeader("Profile", onBack)
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Cream),
            ) {
                Text(
                    text = "Sender",
                    color = Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(15.dp))
                DividerLine()

                ProfileInfoRow(
                    label = stringResource(id = R.string.full_name),
                    value = senderName
                )

                DividerLine()

                ProfileInfoRow(
                    label = stringResource(id = R.string.date_of_birth),
                    value = senderDob
                )

                DividerLine()

                ProfileInfoRow(
                    label = stringResource(id = R.string.gender),
                    value = senderGender
                )

                DividerLine()

                ProfileInfoRow(
                    label = stringResource(id = R.string.profession),
                    value = senderProfession
                )

                DividerLine()

                ProfileInfoRow(
                    label = stringResource(id = R.string.education),
                    value = senderEducation
                )

                DividerLine()

                ProfileInfoRow(
                    label = stringResource(id = R.string.marital_status),
                    value = senderMaritalStatus
                )

                DividerLine()

                ProfileInfoRow(
                    label = stringResource(id = R.string.city),
                    value = senderCity
                )

                Spacer(Modifier.height(15.dp))

                Text(
                    text = "Receiver",
                    color = Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(15.dp))
                DividerLine()

                ProfileInfoRow(
                    label = stringResource(id = R.string.full_name),
                    value = receiverName
                )

                DividerLine()

                ProfileInfoRow(
                    label = stringResource(id = R.string.date_of_birth),
                    value = receiverDob
                )

                DividerLine()

                ProfileInfoRow(
                    label = stringResource(id = R.string.gender),
                    value = receiverGender
                )

                DividerLine()

                ProfileInfoRow(
                    label = stringResource(id = R.string.profession),
                    value = receiverProfession
                )

                DividerLine()

                ProfileInfoRow(
                    label = stringResource(id = R.string.education),
                    value = receiverEducation
                )

                DividerLine()

                ProfileInfoRow(
                    label = stringResource(id = R.string.marital_status),
                    value = receiverMaritalStatus
                )

                DividerLine()

                ProfileInfoRow(
                    label = stringResource(id = R.string.city),
                    value = receiverCity
                )
            }
        }

    }
}