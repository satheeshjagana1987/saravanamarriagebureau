package com.saravanamarriagebureau.ui.screens.admin.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.data.model.AdminUserDetails
import com.saravanamarriagebureau.ui.components.DividerLine
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.NetworkImage
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ProfileInfoRow
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White
import com.saravanamarriagebureau.util.Constants.NA
import com.saravanamarriagebureau.util.ToastUtils
import com.saravanamarriagebureau.util.ValidationUtils
import timber.log.Timber

@Composable
fun AdminUserContent(
    profile: AdminUserDetails,
    onBack: () -> Unit,
    viewModel: AdminUsersViewModel
) {

    val context = LocalContext.current

    var isLoading by remember {
        mutableStateOf(false)
    }

    val uiStateByApproveUser by viewModel.uiStateByApproveUser
        .collectAsStateWithLifecycle()

    val uiStateByRejectUser by viewModel.uiStateByRejectUser
        .collectAsStateWithLifecycle()

    var reason by remember { mutableStateOf("") }
    var reasonError by remember { mutableStateOf<String?>(null) }



    when (val state = uiStateByApproveUser) {

        is AdminUserApproveUIState.Loading -> {
            isLoading = true
        }

        is AdminUserApproveUIState.Success -> {
            isLoading = false
            onBack()
            ToastUtils.success(context = context, message = "User Approved")
        }

        is AdminUserApproveUIState.Error -> {
            isLoading = false
            ToastUtils.error(context, state.message)
        }

        else -> {
            isLoading = false
        }
    }

    when (val state = uiStateByRejectUser) {

        is AdminUserRejectUIState.Loading -> {
            isLoading = true
        }

        is AdminUserRejectUIState.Success -> {
            isLoading = false
            onBack()
            ToastUtils.success(context = context, message = "User Rejected")
        }

        is AdminUserRejectUIState.Error -> {
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
    ) {
        ScreenHeader("Profile", onBack)
        Column(
            Modifier.padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(White),
                ) {


                    // ---------------------------------------------------------
                    // Profile image section
                    // ---------------------------------------------------------
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        NetworkImage(
                            url = profile.profilePhoto ?: "",
                            modifier = Modifier.size(120.dp)
                        )
                    }


                    // ---------------------------------------------------------
                    // Information card
                    // ---------------------------------------------------------

                    Spacer(Modifier.height(20.dp))

                    ProfileInfoRow(
                        label = stringResource(id = R.string.full_name),
                        value = profile.fullName
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.mobile_number),
                        value = profile.mobile
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.email),
                        value = profile.email ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.date_of_birth),
                        value = profile.dateOfBirth ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.birth_time),
                        value = profile.profile.birthTime ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.native_place),
                        value = profile.profile.nativePlace ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.city),
                        value = profile.profile.city ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.district),
                        value = profile.profile.district ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.education),
                        value = profile.profile.education ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.profession),
                        value = profile.profile.profession ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.star),
                        value = profile.profile.birthStar ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.rasi),
                        value = profile.profile.rasi ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.place_of_profession),
                        value = profile.profile.workLocation ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.salary),
                        value = profile.profile.income ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.family_details),
                        value = profile.profile.familyDetails ?: NA
                    )
                }
            }
        }
        Timber.e("status==>${profile.status}")

        if (profile.status == "PENDING_APPROVAL") {
            Column(Modifier.padding(start = 16.dp, end = 16.dp, bottom = 30.dp, top = 16.dp)) {
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

                Timber.e("profile ==>  $profile")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 50.dp
                        ),

                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PrimaryButton(
                        text = stringResource(id = R.string.approve),
                        modifier = Modifier.weight(1f),

                        onClick = {
                            viewModel.approveUser(userId = profile.id)
                        }
                    )

                    PrimaryButton(
                        text = stringResource(id = R.string.reject),
                        modifier = Modifier.weight(1f),
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
                            viewModel.rejectUser(
                                userId = profile.id,
                                reason = reason.trim()
                            )
                        }
                    )

                }
            }
        }
    }
}



