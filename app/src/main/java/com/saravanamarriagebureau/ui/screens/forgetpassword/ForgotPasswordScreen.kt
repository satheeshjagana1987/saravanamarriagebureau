package com.saravanamarriagebureau.ui.screens.forgetpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.LoadingView
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.screens.login.LoginUIState
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.Red
import com.saravanamarriagebureau.ui.theme.Success
import com.saravanamarriagebureau.ui.theme.SuccessSoft
import com.saravanamarriagebureau.ui.theme.White

@Composable
fun ForgotPasswordScreen(
    mobile: String,
    onReset: () -> Unit,
    onBack: () -> Unit,
    viewModel: VerifyResetOTPViewModel = hiltViewModel(),
    resetPasswordViewModel: ResetPasswordViewModel = hiltViewModel(),
) {
    var otp by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val resetPasswordUiState by resetPasswordViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Cream),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(20.dp))
        ScreenHeader(title = stringResource(id = R.string.forgot_password), onBack)
        Spacer(Modifier.height(20.dp))

        Card(colors = CardDefaults.cardColors(containerColor = SuccessSoft)) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Verified",
                    tint = Success,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    "OTP sent to 9999900000",
                    Modifier.padding(start = 10.dp),
                    color = Success,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Spacer(Modifier.height(40.dp))
        Column(Modifier.padding(horizontal = 16.dp)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(White)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {

                    // Mobile Number
                    LabelName(
                        labelName = stringResource(id = R.string.otp),
                        fontSize = 14.sp,
                        color = Maroon,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(Modifier.height(5.dp))

                    OutlinedTextField(
                        value = otp,
                        onValueChange = {
                            otp = it
                        },
                        keyboardOptions = KeyboardOptions(
                            // Changes the on-screen keyboard to a number pad
                            keyboardType = KeyboardType.Number
                        ),
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
                                imageVector = Icons.Outlined.Password,
                                contentDescription = null,
                                tint = Color(0xFF9A9AA5),
                                modifier = Modifier.size(19.dp)
                            )
                        },

                        placeholder = {
                            Text(
                                text = "Enter OTP",
                                color = Color(0xFFB5B5BD),
                                fontSize = 14.sp,
                            )
                        }
                    )

                    Spacer(Modifier.height(20.dp))

                    PrimaryButton(
                        text = stringResource(id = R.string.verify_otp),
                        modifier = Modifier.padding(start = 30.dp, top = 0.dp, end = 30.dp, bottom = 16.dp),
                        onClick = {
                            viewModel.verifyResetOTP(
                                mobile = mobile,
                                otp = otp
                            )
                        }
                    )

                    when (val state = uiState) {

                        VerifyResetOTPPasswordUIState.Idle -> {
                            // Nothing
                        }

                        VerifyResetOTPPasswordUIState.Loading -> {
                            LoadingView()
                        }

                        is VerifyResetOTPPasswordUIState.Success -> {
                            Text(
                                text = state.message ?: "OTP verified successfully"
                            )
                        }

                        is VerifyResetOTPPasswordUIState.Error -> {
                            Text(
                                text = state.message,
                                color = Color.Red
                            )
                        }

                        else -> {}
                    }

                    Spacer(Modifier.height(25.dp))
                    // Password
                    LabelName(
                        labelName = stringResource(id = R.string.new_password),
                        fontSize = 14.sp,
                        color = Maroon,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(Modifier.height(5.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        visualTransformation = if (passwordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFE2E2E8),
                            unfocusedBorderColor = Color(0xFFE2E2E8),
                            cursorColor = Color(0xFF555555),
                            focusedTextColor = Color(0xFF333333),
                            unfocusedTextColor = Color(0xFF333333)
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Password,
                                contentDescription = null,
                                tint = Color(0xFF9A9AA5),
                                modifier = Modifier.size(19.dp)
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    passwordVisible = !passwordVisible
                                }
                            ) {
                                Icon(
                                    imageVector = if (passwordVisible) {
                                        Icons.Outlined.Visibility
                                    } else {
                                        Icons.Outlined.VisibilityOff
                                    },
                                    contentDescription = if (passwordVisible) {
                                        "Hide password"
                                    } else {
                                        "Show password"
                                    },
                                    tint = Color(0xFF55555F),
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        },
                        placeholder = {
                            Text(
                                text = "Enter New Password",
                                color = Color(0xFFB5B5BD),
                                fontSize = 14.sp
                            )
                        }
                    )
                    Spacer(Modifier.height(15.dp))

                    LabelName(
                        labelName = stringResource(id = R.string.confirm_new_password),
                        fontSize = 14.sp,
                        color = Maroon,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(Modifier.height(5.dp))
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        visualTransformation = if (confirmPasswordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFE2E2E8),
                            unfocusedBorderColor = Color(0xFFE2E2E8),
                            cursorColor = Color(0xFF555555),
                            focusedTextColor = Color(0xFF333333),
                            unfocusedTextColor = Color(0xFF333333)
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Password,
                                contentDescription = null,
                                tint = Color(0xFF9A9AA5),
                                modifier = Modifier.size(19.dp)
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    confirmPasswordVisible = !confirmPasswordVisible
                                }
                            ) {
                                Icon(
                                    imageVector = if (confirmPasswordVisible) {
                                        Icons.Outlined.Visibility
                                    } else {
                                        Icons.Outlined.VisibilityOff
                                    },
                                    contentDescription = if (confirmPasswordVisible) {
                                        "Hide password"
                                    } else {
                                        "Show password"
                                    },
                                    tint = Color(0xFF55555F),
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        },
                        placeholder = {
                            Text(
                                text = "Enter Confirm New Password",
                                color = Color(0xFFB5B5BD),
                                fontSize = 14.sp
                            )
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally //,
        ) {
            Text(
                text = "Resend option available after 2mins",
                color = Red,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(Modifier.height(10.dp))
            LabelName(
                labelName = stringResource(id = R.string.resend_otp),
                textDecoration = TextDecoration.Underline, color = Success
            )
        }
        Spacer(Modifier.weight(1f))
        PrimaryButton(
            text = stringResource(id = R.string.reset_password),
            modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 50.dp),
            onClick = {
                resetPasswordViewModel.resetPassword(
                    newPassword = password,
                    confirmPassword = confirmPassword
                )
            }
        )

        when (val state = resetPasswordUiState) {

            ResetPasswordUiState.Idle -> {
                // Nothing
            }

            ResetPasswordUiState.Loading -> {
                LoadingView()
            }

            is ResetPasswordUiState.Success -> {
                Text(
                    text = state.message ?: "Password reset successfully"
                )
                onReset()
            }

            is ResetPasswordUiState.Error -> {
                Text(
                    text = state.message,
                    color = Color.Red
                )
            }

            else -> {}
        }
    }

}

@Preview
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(mobile = "", onReset = {}, onBack = {})
}

