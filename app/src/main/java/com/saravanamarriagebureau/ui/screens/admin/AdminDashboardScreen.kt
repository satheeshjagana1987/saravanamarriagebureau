package com.saravanamarriagebureau.ui.screens.admin

import android.widget.Space
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Interests
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.navigation.Routes
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Gray
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.Red
import com.saravanamarriagebureau.ui.theme.Success
import com.saravanamarriagebureau.ui.theme.TextSecondary
import com.saravanamarriagebureau.ui.theme.Warning
import com.saravanamarriagebureau.ui.theme.White

@Composable
fun AdminDashboardScreen(
    onNavigate: (String) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Cream),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DashboardItem(
                    label = stringResource(id = R.string.users),
                    count = "50",
                    color = Success,
                    icon = Icons.Default.Groups,
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(Routes.Users) }
                )
                DashboardItem(
                    label = stringResource(id = R.string.pending_profiles),
                    count = "20",
                    color = Red,
                    icon = Icons.Default.Group,
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(Routes.PendingProfiles) }
                )

            }
            Spacer(Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                DashboardItem(
                    label = stringResource(id = R.string.pending_payments),
                    count = "10",
                    color = Red,
                    icon = Icons.Default.Payments,
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(Routes.PendingPayments) }
                )

                DashboardItem(
                    label = stringResource(id = R.string.mutual_interests),
                    count = "10",
                    color = Warning,
                    icon = Icons.Default.Interests,
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate(Routes.MutualInterests) }
                )


            }
        }
    }
}

@Composable
fun DashboardItem(
    label: String,
    count: String,
    color: Color,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(120.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                icon,
                null, tint = TextSecondary,
            )
            Spacer(Modifier.height(10.dp))
            LabelName(
                labelName = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Maroon,
                modifier = Modifier.padding(0.dp)
            )
            Spacer(Modifier.height(10.dp))
            LabelName(
                labelName = count,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = color,
                modifier = Modifier.padding(0.dp)
            )


        }
    }
}

@Preview
@Composable
fun AdminDashboardScreenPreview() {
    AdminDashboardScreen(onNavigate = {})
}