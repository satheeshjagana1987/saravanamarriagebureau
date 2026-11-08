package com.saravanamarriagebureau.ui.screens.contactus

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
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.ui.components.DividerLine
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.components.ProfileInfoRow
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.screens.admin.enquiries.AdminEnquiryUIState
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.White
import com.saravanamarriagebureau.util.Constants.NA
import com.saravanamarriagebureau.util.ToastUtils

@Composable
fun EnquiryDetailsScreen(
    onBack: () -> Unit,
    enquiryId: String,
    viewModel: ContactUsViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var isLoading by remember {
        mutableStateOf(false)
    }

    var name by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var adminReply by remember { mutableStateOf("") }


    val uiStateEnquiryById by viewModel.uiStateEnquiryById.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getEnquiryById(
            enquiryId = enquiryId
        )
    }

    LoadingDialog(isLoading = isLoading)

    LaunchedEffect(uiStateEnquiryById) {

        when (val state = uiStateEnquiryById) {

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
                .padding(16.dp).fillMaxSize()
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
                        .fillMaxSize()
                        .padding(16.dp)
                        .background(White),
                ) {

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
        }
    }
}