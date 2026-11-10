package com.saravanamarriagebureau.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.data.model.ProfileData
import com.saravanamarriagebureau.ui.components.DividerLine
import com.saravanamarriagebureau.ui.components.ProfileImagePager
import com.saravanamarriagebureau.ui.components.ProfileInfoRow
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.components.VerticalDivider
import com.saravanamarriagebureau.ui.components.calculateAge
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White
import com.saravanamarriagebureau.util.Constants.NA

@Composable
fun ProfileContent(
    profile: ProfileData,
    onBack: () -> Unit,
    onInterest: () -> Unit,
    profileType: String,
    isMatchFixed: Boolean
) {

    Column(
        Modifier
            .fillMaxSize()
            .background(Cream),
    ) {
        ScreenHeader("Profile", onBack)
        Column(
            Modifier
                .padding(16.dp)
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
                    ProfileImagePager(
                        profilePhotos = profile.profilePhotos
                    )

                    Spacer(Modifier.height(10.dp))

                    // ---------------------------------------------------------
                    // Information card
                    // ---------------------------------------------------------

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = profile.fullName ?: NA,
                            color = Maroon,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(0.75f)
                        )
                        val age = if (profile.dateOfBirth.isNullOrEmpty()) {
                            "NA"
                        } else {
                            calculateAge(profile.dateOfBirth).toString()
                        }
                        Row(
                            modifier = Modifier
                                .weight(0.25f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val gender = if (profile.gender == "MALE") {
                                "M"
                            } else {
                                "F"
                            }

                            Text(
                                text = gender,
                                color = Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Spacer(Modifier.width(5.dp))
                            VerticalDivider()
                            Spacer(Modifier.width(5.dp))
                            Text(
                                text = "$age Y",
                                color = Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    ProfileInfoRow(
                        label = stringResource(id = R.string.height),
                        value = profile.height ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.religion),
                        value = profile.religion ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.education),
                        value = profile.education ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.profession),
                        value = profile.profession ?: NA
                    )

//                    if (isMatchFixed) {
//
//                        DividerLine()
//
//                        ProfileInfoRow(
//                            label = stringResource(id = R.string.mobile_number),
//                            value = profile.mobile ?: NA
//                        )
//
//                        DividerLine()
//
//                        ProfileInfoRow(
//                            label = stringResource(id = R.string.place_of_birth),
//                            value = profile.placeOfBirth ?: NA
//                        )
//
//                        DividerLine()
//
//                        ProfileInfoRow(
//                            label = stringResource(id = R.string.native_place),
//                            value = profile.nativePlace ?: NA
//                        )
//
//                        DividerLine()
//
//                        ProfileInfoRow(
//                            label = stringResource(id = R.string.city),
//                            value = profile.city ?: NA
//                        )
//
//                        DividerLine()
//
//                        ProfileInfoRow(
//                            label = stringResource(id = R.string.district),
//                            value = profile.district ?: NA
//                        )
//                    }

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.birth_time),
                        value = profile.birthTime ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.star),
                        value = profile.birthStar ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.rasi),
                        value = profile.rasi ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.place_of_profession),
                        value = profile.workLocation ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.salary),
                        value = profile.income ?: NA
                    )

                    DividerLine()

                    ProfileInfoRow(
                        label = stringResource(id = R.string.family_details),
                        value = profile.familyDetails ?: NA
                    )
                }
            }
        }
    }

}
