package com.saravanamarriagebureau.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenWithHamburger() {
    // 1. Manage the drawer open/close state
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // 2. Wrap everything in a ModalNavigationDrawer
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // This is the actual slide-out menu content
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Menu Header", modifier = Modifier.padding(16.dp))

                NavigationDrawerItem(
                    label = { Text(text = "Contact Us") },
                    selected = true,
                    onClick = {
                        scope.launch { drawerState.close() }
                        // Handle home navigation
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "About Us") },
                    selected = true,
                    onClick = {
                        scope.launch { drawerState.close() }
                        // Handle home navigation
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Settings") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        // Handle settings navigation
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Logout") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        // Handle settings navigation
                    }
                )
            }
        }
    ) {
        // 3. Main Screen layout goes inside the drawer block
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        // The Hamburger Menu Button
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Open Navigation Menu"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            // 4. PLACE YOUR EXISTING VIEW HERE
            ExistingView(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
@Preview
fun MainScreenWithHamburgerPreview(){
    MainScreenWithHamburger()
}

@Composable
fun ExistingView(modifier: Modifier = Modifier) {
    // Place your original view code here
    Text(text = "Your existing view contents...", modifier = modifier)
}