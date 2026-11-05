package com.saravanamarriagebureau.ui.screens.admin.interests

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ProfileInfoRow
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White
import com.saravanamarriagebureau.util.Constants.NA
import com.saravanamarriagebureau.util.ToastUtils
import com.saravanamarriagebureau.util.ValidationUtils

@Composable
fun AdminInterestDetailsScreen(
    onBack: () -> Unit,
    interestId: String,
    viewModel: AdminInterestsViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var isLoading by remember {
        mutableStateOf(false)
    }

    var reason by remember { mutableStateOf("") }
    var reasonError by remember { mutableStateOf<String?>(null) }

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
    val uiStateAdminInterestsApproveOrRejectMatch by viewModel.uiStateAdminInterestsApproveOrRejectMatch.collectAsStateWithLifecycle()
    val interestData =
        (uiStateAdminInterestsById as? AdminInterestsByIdUIState.Success)?.data

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
                senderMobileNumber = state.data.sender.mobile ?: NA

                receiverName = state.data.receiver.fullName
                receiverDob = state.data.receiver.dateOfBirth ?: NA
                receiverGender = state.data.receiver.gender
                receiverEducation = state.data.receiver.education ?: NA
                receiverCity = state.data.receiver.city ?: NA
                receiverMaritalStatus = state.data.receiver.maritalStatus ?: NA
                receiverProfession = state.data.receiver.profession ?: NA
                receiverMobileNumber = state.data.receiver.mobile ?: NA

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
            .background(Cream),
    ) {
        ScreenHeader("Interest Details", onBack)
        Column(
            Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
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
                        .background(White),
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
                        label = stringResource(id = R.string.mobile_number),
                        value = senderMobileNumber
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
                        label = stringResource(id = R.string.mobile_number),
                        value = receiverMobileNumber
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
            if (interestData?.status == "ACCEPTED" && !interestData.isMatchFixed) {
                Column(Modifier.padding(start = 16.dp, end = 16.dp, bottom = 30.dp, top = 20.dp)) {
                    LabelName(
                        labelName = "Reason",
                        fontSize = 14.sp,
                        color = Maroon,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(Modifier.height(5.dp))
                    OutlinedTextField(
                        value = reason,
                        onValueChange = { reason = it },
                        placeholder = { Text("Type your message here...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        minLines = 1,
                        maxLines = 5
                    )
                    Spacer(Modifier.height(15.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PrimaryButton(
                            text = "Match Approve",

                            onClick = {
                                viewModel.adminApproveOrRejectMatch(
                                    interestId = interestId,
                                    isMatchFixed = true,
                                    matchRejectionReason = ""
                                )
                            },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(Modifier.width(10.dp))
                        PrimaryButton(
                            text = "Match Reject",
                            onClick = {
                                reasonError = ValidationUtils.validateField(
                                    field = reason.trim(),
                                    fieldName = "Reason"
                                )
                                if (reasonError != null) {
                                    ToastUtils.error(
                                        context = context,
                                        message = reasonError.toString()
                                    )
                                    return@PrimaryButton
                                }

                                viewModel.adminApproveOrRejectMatch(
                                    interestId = interestId,
                                    isMatchFixed = false,
                                    matchRejectionReason = reason.trim()
                                )

                            },
                            modifier = Modifier.weight(1f)
                        )

                    }
                }
            }
        }

        when (val state = uiStateAdminInterestsApproveOrRejectMatch) {

            AdminInterestsByIdUIState.Loading -> {
                isLoading = true
            }

            is AdminInterestsByIdUIState.Success -> {
                isLoading = false
                onBack()
            }

            is AdminInterestsByIdUIState.Error -> {
                isLoading = false
                ToastUtils.error(context = context, message = state.message)
            }

            else -> {
                isLoading = false
            }

        }
    }
}