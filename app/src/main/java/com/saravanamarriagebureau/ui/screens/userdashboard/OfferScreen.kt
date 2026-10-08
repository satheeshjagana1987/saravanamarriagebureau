package com.saravanamarriagebureau.ui.screens.userdashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.saravanamarriagebureau.R

@Composable
fun OfferScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
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
    }
}