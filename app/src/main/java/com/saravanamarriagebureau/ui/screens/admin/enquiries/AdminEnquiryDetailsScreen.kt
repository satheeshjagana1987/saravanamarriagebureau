package com.saravanamarriagebureau.ui.screens.admin.enquiries

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
import androidx.compose.material3.OutlinedTextField
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
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ProfileInfoRow
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.screens.admin.interests.AdminInterestsByIdUIState
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White
import com.saravanamarriagebureau.util.Constants.NA
import com.saravanamarriagebureau.util.ToastUtils
import com.saravanamarriagebureau.util.ValidationUtils

@Composable
fun AdminEnquiryDetailsScreen(
    onBack: () -> Unit,
    enquiryId: String,
    viewModel: AdminEnquiriesViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var isLoading by remember {
        mutableStateOf(false)
    }

    var reason by remember { mutableStateOf("") }
    var reasonError by remember { mutableStateOf<String?>(null) }

    var name by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var adminReply by remember { mutableStateOf("") }


    val uiStateAdminEnquiryById by viewModel.uiStateAdminEnquiryById.collectAsStateWithLifecycle()
    val uiStateAdminEnquiryResolved by viewModel.uiStateAdminEnquiryResolved.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAdminEnquiryById(
            enquiryId = enquiryId
        )
    }

    LoadingDialog(isLoading = isLoading)

    LaunchedEffect(uiStateAdminEnquiryById) {

        when (val state = uiStateAdminEnquiryById) {

            is AdminEnquiryUIState.Loading -> {
                isLoading = true
            }

            is AdminEnquiryUIState.Success -> {
                isLoading = false
                name = state.data.fullName ?: NA
                mobileNumber = state.data.mobileNumber ?: NA
                email = state.data.emailAddress ?: NA
                subject = state.data.subject ?: NA
                message = state.data.message ?: NA
                adminReply = state.data.adminReply ?: NA
                status = state.data.status
            }

            is AdminEnquiryUIState.Error -> {
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
        ScreenHeader("Enquiry Details", onBack)
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

                    Spacer(Modifier.height(15.dp))

                    ProfileInfoRow(
                        label = stringResource(id = R.string.full_name),
                        value = name
                    )

                    DividerLine()


                    ProfileInfoRow(
                        label = stringResource(id = R.string.mobile_number),
                        value = mobileNumber
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.email),
                        value = email
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.subject),
                        value = subject
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.message),
                        value = message
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.admin_reply),
                        value = adminReply
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.status),
                        value = status
                    )

                }
            }
            if (status == "NEW") {
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

                    PrimaryButton(
                        text = "Resolve",
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

                            viewModel.updateAdminEnquiryById(
                                enquiryId = enquiryId,
                                comment = reason.trim()
                            )

                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
        LaunchedEffect(uiStateAdminEnquiryResolved) {
            when (val state = uiStateAdminEnquiryResolved) {

                AdminEnquiryUIState.Loading -> {
                    isLoading = true
                }

                is AdminEnquiryUIState.Success -> {
                    isLoading = false
                    onBack()
                }

                is AdminEnquiryUIState.Error -> {
                    isLoading = false
                    ToastUtils.error(context = context, message = state.message)
                }

                else -> {
                    isLoading = false
                }

            }
        }

    }
}