package com.saravanamarriagebureau.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White

@Composable
fun RegisterScreen(
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    var profileFor by remember { mutableStateOf("Bride") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var boxBackgroundColor by remember { mutableStateOf(Maroon) }
    var brideTextColor by remember { mutableStateOf(White) }
    var groomTextColor by remember { mutableStateOf(White) }


    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())
        .background(Cream),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenHeader("Create Your Account", onBack)
        Column(Modifier.padding(horizontal = 16.dp)) {
            Row(Modifier.fillMaxWidth()
                .clickable {
                    // 2. Change color dynamically on row click
                    boxBackgroundColor = if (boxBackgroundColor == White) {
                        Maroon
                    } else {
                        White
                    }
                },
                horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(boxBackgroundColor)
                        .weight(1f)
                        .height(40.dp),
                    contentAlignment = Alignment.Center
                ){
                    TextButton(onClick = {
                        brideTextColor = if (brideTextColor == White) {
                            Maroon
                        } else {
                            White
                        }
                    }, ) {
                        Text("Bride", textAlign = TextAlign.Center, fontSize = 14.sp, color = brideTextColor)
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(boxBackgroundColor)
                        .weight(1f)
                        .height(40.dp),
                    contentAlignment = Alignment.Center
                )
                { TextButton(onClick = {
                    groomTextColor = if (groomTextColor == White) {
                        Maroon
                    } else {
                        White
                    }
                }, ) {
                    Text("Groom", textAlign = TextAlign.Center, fontSize = 14.sp, color = groomTextColor)
                }}

            }
            Spacer(Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(White)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    // Full Name
                    Column {
                        Text(
                            text = "Full Name",
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(bottom = 7.dp)
                        )

                        OutlinedTextField(
                            value = name,
                            onValueChange = {
                                name = it
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
                                    text = "Enter Full Name",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )
                    }

                    // Mobile Number
                    Column {
                        Text(
                            text = "Mobile Number",
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
                    }

                    // Email
                    Column {
                        Text(
                            text = "Email",
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(bottom = 7.dp)
                        )

                        OutlinedTextField(
                            value = email,
                            onValueChange = {
                                email = it
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
                                    imageVector = Icons.Outlined.Mail,
                                    contentDescription = null,
                                    tint = Color(0xFF9A9AA5),
                                    modifier = Modifier.size(19.dp)
                                )
                            },
                            placeholder = {
                                Text(
                                    text = "Enter Email",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )
                    }

                    // DOB
                    Column {
                        Text(
                            text = "Date of Birth",
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(bottom = 7.dp)
                        )

                        OutlinedTextField(
                            value = dob,
                            onValueChange = {
                                dob = it
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
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.CalendarMonth,
                                    contentDescription = null,
                                    tint = Color(0xFF9A9AA5),
                                    modifier = Modifier.size(19.dp)
                                )
                            },
                            placeholder = {
                                Text(
                                    text = "DD / MM / YYYY",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )
                    }

                    // Password
                    Column {
                        Text(
                            text = "Password",
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
                                    imageVector = Icons.Outlined.Lock,
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
                                    text = "Enter Password",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )
                    }

                    // Confirm Password
                    Column {
                        Text(
                            text = "Confirm Password",
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
                                    imageVector = Icons.Outlined.Lock,
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
                                    text = "Enter Confirm Password",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            Spacer(Modifier.weight(1f))
            PrimaryButton("Next", onNext)
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(onNext = {}, onBack = {})
}