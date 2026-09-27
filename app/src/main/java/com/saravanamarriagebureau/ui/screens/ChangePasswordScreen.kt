package com.saravanamarriagebureau.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saravanamarriagebureau.ui.components.AppLogo
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.Red
import com.saravanamarriagebureau.ui.theme.Success

@Composable
fun ChangePasswordScreen(
    onChangePassword: () -> Unit,
    onBack: () -> Unit
) {
    var mobileNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Cream),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenHeader(title = "Change Password", onBack)
        Spacer(Modifier.height(50.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)                     // 1. Outside Padding (Margin)
                .clip(RoundedCornerShape(16.dp))    // 2. CLIP FIRST (Crucial Step!)
                .background(Color.White)             // 3. Apply background to the clipped shape
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                // Mobile Number
                Column {
                    Text(
                        text = "Current Password",
                        fontSize = 14.sp,
                        color = Maroon,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(bottom = 7.dp)
                    )

                    OutlinedTextField(
                        value = mobileNumber,
                        onValueChange = {
                            if (it.length <= 10 && it.all(Char::isDigit)) {
                                mobileNumber = it
                            }
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

                // Password
                Column {
                    Text(
                        text = "New Password",
                        fontSize = 14.sp,
                        color = Maroon,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(bottom = 7.dp)
                    )

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

                // Confirm Password
                Column {
                    Text(
                        text = "Confirm New Password",
                        fontSize = 14.sp,
                        color = Maroon,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(bottom = 7.dp)
                    )

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
            onClick = onChangePassword
        )
    }
}

@Preview
@Composable
fun ChangePasswordScreenPreview(){
    ChangePasswordScreen(onChangePassword = {}, onBack = {})
}