package com.saravanamarriagebureau.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saravanamarriagebureau.ui.components.AppLogo
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.Success
import com.saravanamarriagebureau.ui.theme.TextPrimary

@Composable
fun WelcomeScreen(
    onGetStarted: () -> Unit, onLogin: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Cream)
            .padding(
                start = 24.dp, end = 24.dp, top = 40.dp, bottom = 50.dp
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(10.dp))
        AppLogo()
        Spacer(Modifier.height(30.dp))
        Text(
            "Find Your Life Partner with Trusted Families",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary,
            fontSize = 16.sp
        )
        Spacer(Modifier.height(28.dp))
        listOf("Verified Profiles", "Family Videos", "Admin Support", "Safe & Secure").forEach {
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(10.dp)) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Verified",
                        tint = Success,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        it,
                        Modifier.padding(start = 10.dp),
                        color = Maroon,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

            }
        }
        Spacer(Modifier.weight(1f))
        Text(
            "Guntur No.1 Trusted Marriage Bureau",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            color = Success,
            fontSize = 16.sp
        )

        Spacer(Modifier.height(20.dp))

        PrimaryButton("Get Started", onGetStarted)

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                "Already have an account?",
                fontWeight = FontWeight.Normal,
                color = Black,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            TextButton(onClick = onLogin) {
                Text(
                    text = "Login",
                    color = Maroon,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }

    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(onGetStarted = {}, onLogin = {})
}