package com.saravanamarriagebureau.ui.screens.invoice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saravanamarriagebureau.data.model.Branches
import com.saravanamarriagebureau.ui.components.DividerLine
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Gray
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White

@Composable
fun MatchFixedScreen(
    onBack: () -> Unit,
    onInvoice: (String) -> Unit
) {

    val branches = listOf(
        Branches(name = "Guntur", price = "50,000"),
        Branches(name = "Hyderabad", price = "1,00,000"),
    )

    // 2. Track the currently selected option in state
    var selectedBranchOption by remember { mutableStateOf(branches[0]) }

    Column(
        Modifier.fillMaxSize()
            .padding(16.dp)
            .background(Cream),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ScreenHeader(title = "Match Fixed", onBack)
        Spacer(Modifier.height(50.dp))
        Text("🤝", style = MaterialTheme.typography.displayLarge)
        Spacer(Modifier.height(20.dp))
        Text("Both families are interested!", fontWeight = FontWeight.SemiBold, color = Black, fontSize = 16.sp)
        Spacer(Modifier.height(20.dp))
        Text("Generate Success Fee Invoice to proceed.", color = Black, fontWeight = FontWeight.Normal, fontSize = 14.sp)
        Spacer(Modifier.height(50.dp))
        Card(Modifier.fillMaxWidth().padding(16.dp),
            // Set your custom background color here
            colors = CardDefaults.cardColors(
                containerColor = White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            shape = RoundedCornerShape(10.dp)) {
            Column {
                Text(
                    text = "Success Fee",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .background(color = Gray)
                        .fillMaxWidth()
                        .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                )
                branches.forEach { branch ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            // Make the whole row clickable for better touch target accessibility
                            .selectable(
                                selected = (branch == selectedBranchOption),
                                onClick = {
                                    selectedBranchOption = branch
                                },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (branch == selectedBranchOption),
                            onClick = null // Handled cleanly by the Row's modifier for accessibility
                        )

                        Text(
                            text = branch.name,
                            fontSize = 14.sp,
                            color = Maroon,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = '₹' + branch.price,
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Black,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                    DividerLine()
                }
            }
        }
        Spacer(Modifier.weight(1f))
        PrimaryButton(text = "Generate Invoice", onClick = {onInvoice("")},
            modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 50.dp))
    }
}

@Preview
@Composable
fun MatchFixedScreenPreview(){
    MatchFixedScreen(onBack = {}, onInvoice = {})
}