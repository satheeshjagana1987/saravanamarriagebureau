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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.data.enums.InterestTab
import com.saravanamarriagebureau.data.model.AdminUserDetails
import com.saravanamarriagebureau.data.model.AdminUserDetailsResponse
import com.saravanamarriagebureau.ui.components.DividerLine
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ProfileInfoRow
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.components.calculateAge
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.Red
import com.saravanamarriagebureau.ui.theme.Success
import com.saravanamarriagebureau.ui.theme.Warning
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
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .background(Cream),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF4F4F4))
            ) {

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    // ---------------------------------------------------------
                    // Profile image section
                    // ---------------------------------------------------------
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.profile_pic),
                            contentDescription = "Profile photo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()

                        )

                        // Dark transparent gradient at bottom
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Color.Black.copy(alpha = 0.05f)
                                )
                        )

                        // Top-right circular icon
                        Box(
                            modifier = Modifier
                                .padding(top = 28.dp, end = 18.dp)
                                .size(40.dp)
                                .align(Alignment.TopEnd)
                                .clip(CircleShape)
                                .visible(false)
                                .background(Color.Black.copy(alpha = 0.20f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        // Play button
                        Box(
                            modifier = Modifier
                                .size(92.dp)
                                .align(Alignment.Center)
                                .clip(CircleShape)
                                .visible(false)
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(82.dp)
                                    .clip(CircleShape)
                                    .visible(false)
                                    .background(Color(0xFF142D42)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = "Play video",
                                    tint = Color.White,
                                    modifier = Modifier.size(45.dp)
                                )
                            }
                        }

                        // Family Video label
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(end = 28.dp, bottom = 18.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .visible(false)
                                .background(Color(0xFF22272D).copy(alpha = .92f))
                                .padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(17.dp)
                                )

                                Spacer(modifier = Modifier.width(6.dp))

                                Text(
                                    text = "Family Video",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }

                    // ---------------------------------------------------------
                    // Information card
                    // ---------------------------------------------------------
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-25).dp)
                            .clip(
                                RoundedCornerShape(
                                    topStart = 20.dp,
                                    topEnd = 20.dp
                                )
                            )
                            .background(Color.White)
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 5.dp
                            )
                    ) {

                        DividerLine()

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
            Spacer(Modifier.weight(1f))
            if(profile.status == "PENDING_APPROVAL") {
                OutlinedTextField(
                    value = reason,
                    onValueChange = { reason = it },
                    label = { Text("Reason") },
                    placeholder = { Text("Type your message here...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
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



