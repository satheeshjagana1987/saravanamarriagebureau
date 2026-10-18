package com.saravanamarriagebureau.ui.screens.changepassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.screens.forgetpassword.ResetPasswordUiState
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.util.ToastUtils
import com.saravanamarriagebureau.util.ValidationUtils

@Composable
fun ChangePasswordScreen(
    onBack: () -> Unit,
    viewModel: ChangePasswordViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var currentPassword by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    var isLoading by remember {
        mutableStateOf(false)
    }
    var currentPasswordError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Cream),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenHeader(title = stringResource(id = R.string.change_password), onBack)
        Spacer(Modifier.height(50.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)                     // 1. Outside Padding (Margin)
                .clip(RoundedCornerShape(16.dp))    // 2. CLIP FIRST (Crucial Step!)
                .background(Color.White),            // 3. Apply background to the clipped shape
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {

                // Mobile Number
                Column {
                    LabelName(labelName = stringResource(id = R.string.current_password), fontSize = 14.sp, color = Maroon, fontWeight = FontWeight.Normal)
                    Spacer(Modifier.height(5.dp))
                    OutlinedTextField(
                        value = currentPassword,
                        onValueChange = {
                            currentPassword = it
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
                                text = "Current Password",
                                color = Color(0xFFB5B5BD),
                                fontSize = 14.sp,
                            )
                        }
                    )
                }
                Spacer(Modifier.height(15.dp))
                // Password
                Column {
                    LabelName(labelName = stringResource(id = R.string.new_password), fontSize = 14.sp, color = Maroon, fontWeight = FontWeight.Normal)
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
                }
                Spacer(Modifier.height(15.dp))
                // Confirm Password
                Column {
                    LabelName(labelName = stringResource(id = R.string.confirm_new_password), fontSize = 14.sp, color = Maroon, fontWeight = FontWeight.Normal)
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

        Spacer(Modifier.weight(1f))
        PrimaryButton(
            text = "Change Password",
            modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 50.dp),
            onClick = {
                currentPasswordError = ValidationUtils.validateField(field = currentPassword.trim(), fieldName = "Current Password")
                passwordError = ValidationUtils.validatePassword(password = password.trim())
                confirmPasswordError = ValidationUtils.validateConfirmPassword(password = password.trim(), confirmPassword = confirmPassword.trim())

                if(currentPasswordError != null) {
                    ToastUtils.error(context = context, message = currentPasswordError.toString())
                    return@PrimaryButton
                }
                if(passwordError != null) {
                    ToastUtils.error(context = context, message = passwordError.toString())
                    return@PrimaryButton
                }
                if(confirmPasswordError != null) {
                    ToastUtils.error(context = context, message = confirmPasswordError.toString())
                    return@PrimaryButton
                }
                isLoading = true
                viewModel.changePassword(
                    oldPassword = currentPassword,
                    newPassword = password,
                    confirmPassword = confirmPassword
                )
            }
        )

        when (val state = uiState) {

            ChangePasswordUIState.Idle -> {
                // Nothing
            }

            ChangePasswordUIState.Loading -> {
                LoadingDialog(
                    isLoading = isLoading,
                    message = "Loading, please wait..."
                )
            }

            is ChangePasswordUIState.Success -> {
                isLoading = false
                LoadingDialog(
                    isLoading = false,
                    message = "Loading, please wait..."
                )
                ToastUtils.success(context = context, message = "Password changed successfully")
                onBack()
            }

            is ChangePasswordUIState.Error -> {
                isLoading = false
                LoadingDialog(
                    isLoading = false,
                    message = "Loading, please wait..."
                )
                ToastUtils.success(context = context, message = state.message)
            }

        }
    }
}

@Preview
@Composable
fun ChangePasswordScreenPreview(){
    ChangePasswordScreen(onBack = {})
}