package com.saravanamarriagebureau.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.saravanamarriagebureau.data.model.Profile
import com.saravanamarriagebureau.ui.components.*
import com.saravanamarriagebureau.ui.theme.*

val demoProfiles = listOf(
    Profile("1", "Pooja S", 26, "5'4\"", "B.Tech", "Software Engineer", "B.Tech"),
    Profile("2", "Sai Charan", 28, "5'8\"", "MBA", "Business", "Degree"),
    Profile("3", "Lavanya", 25, "5'3\"", "B.Sc", "Teacher", "M.Tech")
)

@Composable
fun HomeScreen(
    onMatches: () -> Unit,
    onProfile: () -> Unit,
    onPrivacy: () -> Unit
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(true, onClick = {}, icon = { Icon(Icons.Default.Home, null) }, label = { Text("Home") })
                NavigationBarItem(false, onClick = onMatches, icon = { Icon(Icons.Default.Favorite, null) }, label = { Text("Matches") })
                NavigationBarItem(false, onClick = {}, icon = { Icon(Icons.Default.Save, null) }, label = { Text("Profile") })
                NavigationBarItem(false, onClick = onProfile, icon = { Icon(Icons.Default.Person, null) }, label = { Text("Settings") })
            }
        }
    ) { padding ->
        LazyColumn(
            Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text("Welcome to Anubandham", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text("Real families. Genuine matches.", color = TextSecondary)
            }
            item {
                Card(colors = CardDefaults.cardColors(containerColor = SoftRose)) {
                    Column(Modifier.padding(18.dp)) {
                        Text("Dasara Free Offer", color = Maroon, fontWeight = FontWeight.Bold)
                        Text("Registration ₹0/-", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Black)
                        PrimaryButton("View Plans", onMatches)
                    }
                }
            }
            item {
                OutlinedButton(onClick = onPrivacy, modifier = Modifier.fillMaxWidth()) {
                    Text("Privacy & Security")
                }
            }
            item {
                Text("Recommended Profiles", fontWeight = FontWeight.Bold)
            }
            items(demoProfiles.take(2)) { profile ->
                ProfileCard(profile, onInterest = onMatches, onOpen = onProfile)
            }
        }
    }
}


@Composable
fun AdminVoiceScreen(onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        ScreenHeader("Admin Voice Note", onBack)
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(18.dp)) {
                Text("Admin's Personal Note", color = Maroon, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text("ఈ profile గురించి మా admin team share చేసిన personal note.")
                Spacer(Modifier.height(14.dp))
                Text("▶  ────────●────────  00:07 / 00:45", color = Maroon)
            }
        }
        Spacer(Modifier.height(18.dp))
        ProfileDetails(demoProfiles.first())
    }
}

@Composable
fun PrivacyScreen(onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        ScreenHeader("Privacy & Security", onBack)
        Text("Your Privacy Matters", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        listOf(
            "Phone Number" to "Not visible directly",
            "Home Address" to "Not visible directly",
            "Share Contact" to "Only after mutual interest and admin connect",
            "Screenshot Protection" to "Screens are blocked with watermark"
        ).forEach { (title, desc) ->
            Card(Modifier.fillMaxWidth().padding(vertical = 5.dp)) {
                Column(Modifier.padding(14.dp)) {
                    Text(title, fontWeight = FontWeight.Bold, color = Maroon)
                    Text(desc, color = TextSecondary)
                }
            }
        }
        Spacer(Modifier.height(10.dp))
        Card(colors = CardDefaults.cardColors(containerColor = SuccessSoft)) {
            Text("✓  Your data is safe with us", Modifier.padding(14.dp), color = Success, fontWeight = FontWeight.Bold)
        }
    }
}





@Composable
fun ContactsLockedScreen(onBack: () -> Unit) {
    Column(
        Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Default.Lock, null, tint = Maroon, modifier = Modifier.size(90.dp))
        Text("Contacts Locked", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Contacts will be revealed after successful payment.", textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        Spacer(Modifier.height(20.dp))
        SecondaryButton("Back", onBack)
    }
}

@Composable
private fun ProfileDetails(profile: Profile) {
    Card {
        Column(Modifier.padding(16.dp)) {
            Text("Profile Details", fontWeight = FontWeight.Bold)
            Text("Age: ${profile.age}")
            Text("Height: ${profile.height}")
            Text("Education: ${profile.education}")
            Text("Profession: ${profile.profession}")
            Text("Location: ${profile.location}")
        }
    }
}
