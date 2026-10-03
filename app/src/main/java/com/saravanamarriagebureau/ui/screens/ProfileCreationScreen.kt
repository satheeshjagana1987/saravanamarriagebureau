package com.saravanamarriagebureau.ui.screens

import android.view.View
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
import androidx.compose.foundation.layout.visible
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Height
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.data.enums.MatchTab
import com.saravanamarriagebureau.data.enums.ProfileTab
import com.saravanamarriagebureau.data.model.Profile
import com.saravanamarriagebureau.ui.components.CustomButton
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCreationScreen(
    onSubmit: () -> Unit,
    onBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var maritalStatus by remember { mutableStateOf("") }
    var education by remember { mutableStateOf("") }
    var profession by remember { mutableStateOf("") }
    var religion by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var boxBackgroundColor by remember { mutableStateOf(Maroon) }
    var brideTextColor by remember { mutableStateOf(White) }
    // Location dropdown
    val options = listOf("Amaravathi, Guntur, Vizag")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    var isVisible by remember { mutableStateOf(false) }

    var selectedTab by remember {
        mutableStateOf(ProfileTab.PERSONAL)
    }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Cream),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(Modifier.padding(horizontal = 16.dp)) {
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProfileTabButton(
                    text = stringResource(id = R.string.personal),
                    selected = selectedTab == ProfileTab.PERSONAL,
                    onClick = {
                        selectedTab = ProfileTab.PERSONAL
                    },
                    modifier = Modifier.weight(1f)
                )

                ProfileTabButton(
                    text = stringResource(id = R.string.professional),
                    selected = selectedTab == ProfileTab.PROFESSIONAL,
                    onClick = {
                        selectedTab = ProfileTab.PROFESSIONAL
                    },
                    modifier = Modifier.weight(1f)
                )

                ProfileTabButton(
                    text = stringResource(id = R.string.photo),
                    selected = selectedTab == ProfileTab.PHOTO,
                    onClick = {
                        selectedTab = ProfileTab.PHOTO
                    },
                    modifier = Modifier.weight(1f)
                )

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
                    Column(
                        modifier = Modifier.visible(visible = true)
                    ) {
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
                            text = "Location",
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(bottom = 7.dp)
                        )

                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = { expanded = !expanded },
                        ) {
                            // 2. The Anchor Text Field (users click this to open the dropdown)
                            OutlinedTextField(
                                // The menuAnchor modifier binds the width and position to the menu layout
                                modifier = Modifier.menuAnchor().fillMaxWidth(),
                                readOnly = true,
                                value = selectedOptionText,
                                onValueChange = {},
                                shape = RoundedCornerShape(12.dp),
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedBorderColor = Color(0xFFE2E2E8),
                                ),
                            )

                            // 3. The actual Popup Dropdown overlay
                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                            ) {
                                options.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        text = { Text(selectionOption) },
                                        onClick = {
                                            selectedOptionText = selectionOption
                                            expanded = false
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                    )
                                }
                            }
                        }
                    }

                    Column {
                        Text(
                            text = "Height",
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(bottom = 7.dp)
                        )

                        OutlinedTextField(
                            value = height,
                            onValueChange = {
                                height = it
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
                                    imageVector = Icons.Outlined.Height,
                                    contentDescription = null,
                                    tint = Color(0xFF9A9AA5),
                                    modifier = Modifier.size(19.dp)
                                )
                            },

                            placeholder = {
                                Text(
                                    text = "Enter Height",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )
                    }

                    Column {
                        Text(
                            text = "Education",
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(bottom = 7.dp)
                        )

                        OutlinedTextField(
                            value = education,
                            onValueChange = {
                                education = it
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
                                    text = "Enter Education",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )
                    }

                    Column {
                        Text(
                            text = "Profession",
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(bottom = 7.dp)
                        )

                        OutlinedTextField(
                            value = profession,
                            onValueChange = {
                                profession = it
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
                                    text = "Enter Profession",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )
                    }

                    Column {
                        Text(
                            text = "Marital Status",
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(bottom = 7.dp)
                        )

                        OutlinedTextField(
                            value = maritalStatus,
                            onValueChange = {
                                maritalStatus = it
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
                                    text = "Enter Marital Status",
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
            PrimaryButton("Submit", onSubmit)
        }
    }
}

@Composable
fun ProfileTabButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        height = 40.dp,
        width = 0.dp,
        cornerRadius = 5.dp,

        textColor = if (selected) White else Black,

        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,

        backgroundColor = if (selected) Maroon else White,

        borderColor = if (selected) Maroon else Black,

        borderStroke = 0.2.dp
    )
}

@Preview
@Composable
fun ProfileCreationScreenPreview(){
    ProfileCreationScreen(onSubmit = {}, onBack = {})
}