package com.saravanamarriagebureau.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saravanamarriagebureau.ui.components.ProfilePhoto
import com.saravanamarriagebureau.ui.theme.*
import com.saravanamarriagebureau.ui.theme.Success

@Composable
fun AdminPanelScreen() {
    Column(Modifier.fillMaxSize().background(Color(0xFFF3F5F7))) {
        Row(
            Modifier.fillMaxWidth().background(Color(0xFF17263A)).padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Admin Panel", color = androidx.compose.ui.graphics.Color.White, fontWeight = FontWeight.Bold)
            Text("Admin ● Online", color = Success)
        }
        Row(Modifier.fillMaxSize()) {
            Column(
                Modifier.width(110.dp).fillMaxHeight().background(Color(0xFF1B2C40)).padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                listOf("Dashboard", "Profiles", "Video Approval", "Users", "Matches", "Settings", "Logout").forEach {
                    Text(it, color = androidx.compose.ui.graphics.Color.White, style = MaterialTheme.typography.labelSmall)
                }
            }
            LazyColumn(
                Modifier.weight(1f).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        AdminStat("Total Profiles", "248")
                        AdminStat("Pending Videos", "32")
                    }
                }
                item {
                    Text("Video Approval", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                }
                items(demoProfiles.take(2)) { profile ->
                    Card {
                        Row(Modifier.padding(10.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            ProfilePhoto("", Modifier.size(56.dp))
                            Column(Modifier.weight(1f)) {
                                Text(profile.name, fontWeight = FontWeight.Bold)
                                Text("${profile.age} yrs • ${profile.education}")
                            }
                            Button(onClick = {}) { Text("Approve") }
                            OutlinedButton(onClick = {}) { Text("Reject") }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AdminStat(title: String, value: String) {
    Card() {
        Column(Modifier.padding(12.dp)) {
            Text(title, style = MaterialTheme.typography.labelSmall, color = TextSecondary)
            Text(value, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
fun AdminPanelScreenPreview(){
    AdminPanelScreen()
}
