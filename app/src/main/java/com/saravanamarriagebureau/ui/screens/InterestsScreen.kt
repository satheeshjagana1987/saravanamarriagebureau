package com.saravanamarriagebureau.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saravanamarriagebureau.data.enums.InterestStatus
import com.saravanamarriagebureau.data.enums.InterestTab
import com.saravanamarriagebureau.data.model.Interest
import com.saravanamarriagebureau.data.model.Profile
import com.saravanamarriagebureau.data.model.demoInterests
import com.saravanamarriagebureau.ui.components.CustomButton
import com.saravanamarriagebureau.ui.components.InterestCard
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White

@Composable
fun InterestsScreen(
    onInterestItemClick: (Interest) -> Unit,
    onAcceptRejectButton: () -> Unit,
) {

    var selectedTab by remember {
        mutableStateOf(InterestTab.RECEIVED)
    }

    // Change list based on selected tab
    val filteredInterests = when (selectedTab) {
        InterestTab.RECEIVED -> {
            demoInterests.filter { interest ->
                interest.type == InterestTab.RECEIVED
            }
        }

        InterestTab.SENT -> {
            demoInterests.filter { interest ->
                interest.type == InterestTab.SENT
            }
        }

        InterestTab.MATCH_FIXED -> {
            demoInterests.filter { interest ->
                interest.isMatched
            }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Cream),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(Modifier.padding(horizontal = 16.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InterestTabButton(
                    text = "Received",
                    selected = selectedTab == InterestTab.RECEIVED,
                    onClick = {
                        selectedTab = InterestTab.RECEIVED
                    },
                    modifier = Modifier.weight(1f)
                )

                InterestTabButton(
                    text = "Sent",
                    selected = selectedTab == InterestTab.SENT,
                    onClick = {
                        selectedTab = InterestTab.SENT
                    },
                    modifier = Modifier.weight(1f)
                )

                InterestTabButton(
                    text = "Match Fixed",
                    selected = selectedTab == InterestTab.MATCH_FIXED,
                    onClick = {
                        selectedTab = InterestTab.MATCH_FIXED
                    },
                    modifier = Modifier.weight(1f)
                )

            }
            LazyColumn(
                modifier = Modifier.padding(start = 0.dp, top = 16.dp, end = 0.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    items = filteredInterests,
                    key = { it.id }
                ) { interest ->
                    InterestCard(
                        interest,
                        onOpen = {onInterestItemClick(interest)},
                        onAcceptRejectButton = { onAcceptRejectButton() }
                    )
                }
            }
        }
    }
}

@Composable
fun InterestTabButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        height = 40.dp,
        width = 0.dp,
        cornerRadius = 5.dp,

        textColor = if (selected) White else Black,

        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,

        backgroundColor = if (selected) Maroon else White,

        borderColor = if (selected) Maroon else Black,

        borderStroke = 0.2.dp
    )
}

@Preview
@Composable
fun InterestsScreenPreview() {
    InterestsScreen(onInterestItemClick = {}, onAcceptRejectButton = {})
}