package com.saravanamarriagebureau.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.Success
import com.saravanamarriagebureau.ui.theme.Warning

@Composable
fun HomeScreen(
    onViewPlans: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Cream)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_bg),
            contentDescription = "A descriptive label for accessibility",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // Set sizing or styling
        )
        Spacer(Modifier.height(20.dp))

        Image(
            painter = painterResource(id = R.drawable.splash_bg),
            contentDescription = "A descriptive label for accessibility",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // Set sizing or styling
        )
        Spacer(Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            Column(
                Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LabelName(
                    labelName = stringResource(id = R.string.annual_membership),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Black
                )
                Spacer(Modifier.height(5.dp))
                LabelName(
                    labelName = "₹299 / " + stringResource(id = R.string.annual_membership),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Maroon
                )
                Spacer(Modifier.height(5.dp))
                LabelName(
                    labelName = stringResource(id = R.string.only_for_profile_viewing),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Success
                )
                Spacer(Modifier.height(20.dp))
                PrimaryButton(text = stringResource(id = R.string.view_plans), onClick = {
                    onViewPlans()
                })
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(onViewPlans = {})
}
