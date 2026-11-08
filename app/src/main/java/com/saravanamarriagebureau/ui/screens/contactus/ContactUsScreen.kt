package com.saravanamarriagebureau.ui.screens.contactus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.ui.components.DividerLine
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ProfileInfoRow
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.Success
import com.saravanamarriagebureau.ui.theme.White

@Composable
fun ContactUsScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
    viewModel: ContactUsViewModel = hiltViewModel()
) {

    val samplePhone = "7416957292"
    val sampleEmail = "saravanamarriage2@gmail.com"

    Column(
        Modifier
            .fillMaxSize()
            .background(Cream),
    ) {
        ScreenHeader("Contact Us", onBack)
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
                        labelName = "Saravana Bhava Marriage Bureau",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Maroon,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(20.dp))

                    ProfileInfoRow(
                        label = stringResource(id = R.string.head_office),
                        value = "Tenali,AndhraPradesh,India"
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.branches),
                        value = "Tenali,Guntur,Hyderabad"
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.mobile_number),
                        value = "7416957292"
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.email),
                        value = "saravanamarriage2@gmail.com"
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    LabelName(
                        labelName = "Contact Us",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Black,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        FilledIconButton(
                            onClick = {
                                viewModel.onCallClick(phoneNumber = samplePhone)
                            },
                            modifier = Modifier.weight(1f),
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Black,      // Sets the background color
                                contentColor = Color.White       // Sets the default tint color for the icon inside
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Call,
                                tint = White,
                                contentDescription = "open Dialer"
                            )
                        }
                        FilledIconButton(
                            onClick = {
                                viewModel.onWhatsAppClick(phoneNumber = samplePhone)
                            },
                            modifier = Modifier.weight(1f),
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Success,      // Sets the background color
                                contentColor = Color.White       // Sets the default tint color for the icon inside
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Whatsapp,
                                tint = White,
                                contentDescription = "whatsapp"
                            )
                        }
                        FilledIconButton(
                            onClick = {
                                viewModel.onEmailClick(email = sampleEmail)
                            },
                            modifier = Modifier.weight(1f),
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.Red,      // Sets the background color
                                contentColor = Color.White       // Sets the default tint color for the icon inside
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Email,
                                tint = White,
                                contentDescription = "email"
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    PrimaryButton(
                        text = stringResource(id = R.string.send_your_enquiry),
                        modifier = Modifier.padding(16.dp),
                        onClick = {
                            onNext()
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ContactUsScreenPreview() {
    ContactUsScreen(onBack = {}, onNext = {})
}