package com.saravanamarriagebureau.ui.screens.login

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.ui.components.AppLogo
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.LoadingView
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.SecondaryButton
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Gray
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onRegister: () -> Unit,
    onPrivacy: () -> Unit,
    onForgot: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var mobileNumber by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }
    var passwordVisible by remember { mutableStateOf(false) }
    var isCheckedRememberMe by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Cream)
            .padding(
                start = 24.dp, end = 24.dp, top = 40.dp, bottom = 50.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppLogo()
        Spacer(Modifier.height(50.dp))

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
                LabelName(labelName = stringResource(id = R.string.mobile_number), fontSize = 14.sp, color = Maroon, fontWeight = FontWeight.Normal)
                Spacer(Modifier.height(5.dp))
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
                            imageVector = Icons.Outlined.AccountBox,
                            contentDescription = null,
                            tint = Color(0xFF9A9AA5),
                            modifier = Modifier.size(19.dp)
                        )
                    },

                    placeholder = {
                        Text(
                            text = "Enter Mobile Number / Email",
                            color = Color(0xFFB5B5BD),
                            fontSize = 14.sp
                        )
                    }
                )

                Spacer(Modifier.height(15.dp))
                LabelName(labelName = stringResource(id = R.string.password), fontSize = 14.sp, color = Maroon, fontWeight = FontWeight.Normal)
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
                            text = "Enter password",
                            color = Color(0xFFB5B5BD),
                            fontSize = 14.sp
                        )
                    }
                )

                Spacer(Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isCheckedRememberMe,
                            onCheckedChange = null // Handled by the parent Row's toggleable modifier
                        )
                        Spacer(Modifier.width(5.dp))
                        LabelName(labelName = stringResource(id = R.string.remember_me), textAlign = TextAlign.Center)
                    }

                    LabelName(
                        labelName = stringResource(id = R.string.forgot_password),
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.SemiBold,
                        color = Maroon,
                        modifier = Modifier
                            .clickable { onForgot() }
                    )
                }
            }
        }
        Spacer(Modifier.height(30.dp))
        PrimaryButton(stringResource(id = R.string.login),
            onClick = {
                viewModel.login(
                    mobile = mobileNumber,
                    password = password
                )
            })
        Spacer(Modifier.height(20.dp))

        when (val state = uiState) {

            LoginUIState.Idle -> {
                // Nothing
            }

            LoginUIState.Loading -> {
                LoadingView()
            }

            is LoginUIState.Success -> {
                Text(
                    text = state.message ?: "Login successful"
                )
            }

            is LoginUIState.Error -> {
                Text(
                    text = state.message,
                    color = Color.Red
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = 1.dp,
                color = Gray
            )
            Text(
                text = "OR",
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Black,
                fontSize = 12.sp,
                style = MaterialTheme.typography.bodyMedium
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = 1.dp,
                color = Gray
            )
        }
        Spacer(Modifier.height(20.dp))
        SecondaryButton(stringResource(id = R.string.create_new_account), onRegister)
        Spacer(Modifier.height(24.dp))
        Spacer(Modifier.weight(1f))

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                "By continuing, you agree to our",
                fontWeight = FontWeight.Normal,
                color = Black,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            TextButton(onClick = onPrivacy) {
                Text(
                    text = "Terms & Privacy Policy",
                    color = Maroon,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}

fun changeAppLanguage(languageCode: String) {
    val localeList = LocaleListCompat.forLanguageTags(languageCode)
    AppCompatDelegate.setApplicationLocales(localeList)
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLogin = {}, onRegister = {}, onPrivacy = {}, onForgot = {})
}