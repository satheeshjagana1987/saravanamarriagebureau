package com.saravanamarriagebureau.ui.screens.contactus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.screens.admin.enquiries.AdminEnquiriesUIState
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White
import com.saravanamarriagebureau.util.Constants
import com.saravanamarriagebureau.util.ToastUtils
import com.saravanamarriagebureau.util.ValidationUtils

@Composable
fun EnquiryFormScreen(
    onBack: () -> Unit,
    viewModel: ContactUsViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var name = remember {
        viewModel.profileName()
    }

    var mobileNumber = remember {
        viewModel.profileMobileNumber()
    }

    var email = remember {
        viewModel.profileMail()
    }

    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    var isLoading by remember {
        mutableStateOf(false)
    }

    val uiStateSubmitEnquiry by viewModel.uiStateSubmitEnquiry.collectAsStateWithLifecycle()

    var subjectError by remember { mutableStateOf<String?>(null) }
    var messageError by remember { mutableStateOf<String?>(null) }

    Column(
        Modifier
            .fillMaxSize()
            .background(Cream),
    ) {
        ScreenHeader("Enquiry Form", onBack)
        Column(
            Modifier
                .padding(16.dp)
                .fillMaxSize()
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
                        .fillMaxSize()
                        .padding(16.dp)
                        .background(White),
                ) {
                    LabelName(
                        labelName = stringResource(id = R.string.full_name),
                        fontSize = 14.sp,
                        color = Maroon,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(Modifier.height(5.dp))
                    OutlinedTextField(
                        value = name ?: "NA",
                        onValueChange = {
                            name = it
                        },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFE2E2E8),
                            unfocusedBorderColor = Color(0xFFE2E2E8),
                            cursorColor = Color(0xFF555555),
                            focusedTextColor = Color(0xFF333333),
                            unfocusedTextColor = Color(0xFF333333)
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.AccountBox,
                                contentDescription = null,
                                tint = Color(0xFF9A9AA5),
                                modifier = Modifier.size(19.dp)
                            )
                        },

                        placeholder = {
                            Text(
                                text = "Enter Full Name",
                                color = Color(0xFFB5B5BD),
                                fontSize = 14.sp
                            )
                        }
                    )

                    Spacer(Modifier.height(10.dp))

                    LabelName(
                        labelName = stringResource(id = R.string.mobile_number),
                        fontSize = 14.sp,
                        color = Maroon,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(Modifier.height(5.dp))
                    OutlinedTextField(
                        value = mobileNumber ?: "NA",
                        onValueChange = {
                            if (it.length <= 10 && it.all(Char::isDigit)) {
                                mobileNumber = it
                            }
                        },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFE2E2E8),
                            unfocusedBorderColor = Color(0xFFE2E2E8),
                            cursorColor = Color(0xFF555555),
                            focusedTextColor = Color(0xFF333333),
                            unfocusedTextColor = Color(0xFF333333)
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Phone,
                                contentDescription = null,
                                tint = Color(0xFF9A9AA5),
                                modifier = Modifier.size(19.dp)
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Enter Mobile Number",
                                color = Color(0xFFB5B5BD),
                                fontSize = 14.sp
                            )
                        }
                    )

                    Spacer(Modifier.height(10.dp))
                    LabelName(
                        labelName = stringResource(id = R.string.email),
                        fontSize = 14.sp,
                        color = Maroon,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(Modifier.height(5.dp))
                    OutlinedTextField(
                        value = email ?: "NA",
                        onValueChange = {
                            email = it
                        },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFE2E2E8),
                            unfocusedBorderColor = Color(0xFFE2E2E8),
                            cursorColor = Color(0xFF555555),
                            focusedTextColor = Color(0xFF333333),
                            unfocusedTextColor = Color(0xFF333333)
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Mail,
                                contentDescription = null,
                                tint = Color(0xFF9A9AA5),
                                modifier = Modifier.size(19.dp)
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Enter Email(Optional)",
                                color = Color(0xFFB5B5BD),
                                fontSize = 14.sp
                            )
                        }
                    )

                    LabelName(
                        labelName = stringResource(id = R.string.subject),
                        fontSize = 14.sp,
                        color = Maroon,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(Modifier.height(5.dp))
                    OutlinedTextField(
                        value = subject,
                        onValueChange = {
                            subject = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFE2E2E8),
                            unfocusedBorderColor = Color(0xFFE2E2E8),
                            cursorColor = Color(0xFF555555),
                            focusedTextColor = Color(0xFF333333),
                            unfocusedTextColor = Color(0xFF333333)
                        ),

                        placeholder = {
                            Text(
                                text = "Enter Subject",
                                color = Color(0xFFB5B5BD),
                                fontSize = 14.sp
                            )
                        }
                    )

                    LabelName(
                        labelName = stringResource(id = R.string.description),
                        fontSize = 14.sp,
                        color = Maroon,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(Modifier.height(5.dp))
                    OutlinedTextField(
                        value = message,
                        onValueChange = {
                            message = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        minLines = 1,
                        maxLines = 5,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFE2E2E8),
                            unfocusedBorderColor = Color(0xFFE2E2E8),
                            cursorColor = Color(0xFF555555),
                            focusedTextColor = Color(0xFF333333),
                            unfocusedTextColor = Color(0xFF333333)
                        ),

                        placeholder = {
                            Text(
                                text = "Enter Description",
                                color = Color(0xFFB5B5BD),
                                fontSize = 14.sp
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    PrimaryButton(
                        text = stringResource(id = R.string.submit),
                        modifier = Modifier.padding(16.dp),
                        onClick = {
                            subjectError = ValidationUtils.validateField(
                                field = subject.trim(),
                                fieldName = "Subject"
                            )
                            if (subjectError != null) {
                                ToastUtils.error(
                                    context = context,
                                    message = subjectError.toString()
                                )
                                return@PrimaryButton
                            }

                            messageError = ValidationUtils.validateField(
                                field = message.trim(),
                                fieldName = "Message"
                            )
                            if (messageError != null) {
                                ToastUtils.error(
                                    context = context,
                                    message = messageError.toString()
                                )
                                return@PrimaryButton
                            }
                            viewModel.submitEnquiry(
                                fullName = name ?: "NA",
                                mobileNumber = mobileNumber ?: "NA",
                                email = email ?: "NA",
                                subject = subject.trim(),
                                message = message.trim()
                            )
                        }
                    )

                    LoadingDialog(isLoading = isLoading)

                    LaunchedEffect(uiStateSubmitEnquiry) {

                        when (val state = uiStateSubmitEnquiry) {

                            is SubmitEnquiryUIState.Loading -> {
                                isLoading = true
                            }

                            is SubmitEnquiryUIState.Success -> {
                                isLoading = false
                                ToastUtils.error(context, state.data.confirmationMessage)
                                onBack()
                            }

                            is SubmitEnquiryUIState.Error -> {
                                isLoading = false
                                ToastUtils.error(context, state.message)
                            }

                            else -> {
                                isLoading = false
                            }
                        }
                    }
                }
            }
        }
    }
}