package com.saravanamarriagebureau.ui.screens.userdashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.SecondaryButton
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White

@Composable
fun SearchScreen() {

    var selectedAge by remember {
        mutableStateOf("")
    }

    var age20 by remember { mutableStateOf(false) }
    var age30 by remember { mutableStateOf(false) }
    var age40 by remember { mutableStateOf(false) }

    var underGraduate by remember { mutableStateOf(false) }
    var graduate by remember { mutableStateOf(false) }
    var postGraduate by remember { mutableStateOf(false) }

    var government by remember { mutableStateOf(false) }
    var private by remember { mutableStateOf(false) }
    var business by remember { mutableStateOf(false) }

    var hindu by remember { mutableStateOf(false) }
    var muslim by remember { mutableStateOf(false) }
    var others by remember { mutableStateOf(false) }

    var neverMarried by remember { mutableStateOf(false) }
    var divorced by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .background(Cream),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            Column(
                Modifier
                    .fillMaxSize().padding(16.dp)
                    .background(White),
            ) {
                LabelName(
                    labelName = stringResource(id = R.string.age),
                    fontSize = 14.sp,
                    color = Maroon,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(5.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FilterCheckbox(
                        title = "20 - 30",
                        checked = age20,
                        onCheckedChange = { age20 = it }
                    )
                    FilterCheckbox(
                        title = "30 - 40",
                        checked = age20,
                        onCheckedChange = { age20 = it }
                    )
                    FilterCheckbox(
                        title = "40+",
                        checked = age20,
                        onCheckedChange = { age20 = it }
                    )
                }

                Spacer(Modifier.height(5.dp))

                LabelName(
                    labelName = stringResource(id = R.string.education),
                    fontSize = 14.sp,
                    color = Maroon,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FilterCheckbox(
                        title = "Under Graduate",
                        checked = underGraduate,
                        onCheckedChange = { underGraduate = it }
                    )
                    FilterCheckbox(
                        title = "Graduate",
                        checked = graduate,
                        onCheckedChange = { graduate = it }
                    )
                    FilterCheckbox(
                        title = "Post Graduate",
                        checked = postGraduate,
                        onCheckedChange = { postGraduate = it }
                    )
                }
                Spacer(Modifier.height(5.dp))
                LabelName(
                    labelName = stringResource(id = R.string.profession),
                    fontSize = 14.sp,
                    color = Maroon,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FilterCheckbox(
                        title = "Government",
                        checked = government,
                        onCheckedChange = { government = it }
                    )
                    FilterCheckbox(
                        title = "Private",
                        checked = private,
                        onCheckedChange = { private = it }
                    )
                    FilterCheckbox(
                        title = "Business",
                        checked = business,
                        onCheckedChange = { business = it }
                    )
                }

                Spacer(Modifier.height(5.dp))
                LabelName(
                    labelName = stringResource(id = R.string.religion),
                    fontSize = 14.sp,
                    color = Maroon,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FilterCheckbox(
                        title = "Hindu",
                        checked = hindu,
                        onCheckedChange = { hindu = it }
                    )
                    FilterCheckbox(
                        title = "Muslim",
                        checked = muslim,
                        onCheckedChange = { muslim = it }
                    )
                    FilterCheckbox(
                        title = "Others",
                        checked = others,
                        onCheckedChange = { others = it }
                    )
                }

                Spacer(Modifier.height(5.dp))
                LabelName(
                    labelName = stringResource(id = R.string.marital_status),
                    fontSize = 14.sp,
                    color = Maroon,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FilterCheckbox(
                        title = "Never Married",
                        checked = neverMarried,
                        onCheckedChange = { neverMarried = it }
                    )
                    FilterCheckbox(
                        title = "Divorced",
                        checked = divorced,
                        onCheckedChange = { divorced = it }
                    )
                }

                Spacer(Modifier.weight(1f))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    SecondaryButton(
                        text = stringResource(id = R.string.cancel),
                        onClick = {},
                        modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 50.dp).weight(1f)
                    )

                    PrimaryButton(
                        text = stringResource(id = R.string.search), onClick = {},
                        modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 50.dp).weight(1f)
                    )

                }
            }
        }
    }
}

@Composable
fun FilterCheckbox(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )

        LabelName(
            labelName = title,
            fontSize = 14.sp,
            color = Black,
            fontWeight = FontWeight.Normal
        )
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}