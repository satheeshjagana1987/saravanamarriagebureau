package com.saravanamarriagebureau.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saravanamarriagebureau.data.model.Interest
import com.saravanamarriagebureau.data.model.Profile
import com.saravanamarriagebureau.ui.components.InterestCard
import com.saravanamarriagebureau.ui.components.ProfileCard
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White

@Composable
fun InterestsScreen(
    onBack: () -> Unit,
    onInterestItemClick: (Interest) -> Unit,
) {

    var boxBackgroundColor by remember { mutableStateOf(Maroon) }
    var brideTextColor by remember { mutableStateOf(White) }

    val demoInterests = listOf(
        Interest("1", "Pooja S", 26, "B.Tech", false, false, true),
        Interest("2", "Sai Charan", 28, "MBA", false, false, true),
        Interest("3", "Lavanya", 25, "B.Sc", false, false, true)
    )

    Column(
        Modifier
            .fillMaxSize()
            .background(Cream),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(Modifier.padding(horizontal = 16.dp)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        // 2. Change color dynamically on row click
                        boxBackgroundColor = if (boxBackgroundColor == White) {
                            Maroon
                        } else {
                            White
                        }
                    },
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(boxBackgroundColor)
                        .weight(1f)
                        .height(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    TextButton(
                        onClick = {
                            brideTextColor = if (brideTextColor == White) {
                                Maroon
                            } else {
                                White
                            }
                        },
                    ) {
                        Text(
                            "Received",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            color = brideTextColor
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(boxBackgroundColor)
                        .weight(1f)
                        .height(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    TextButton(
                        onClick = {
                            brideTextColor = if (brideTextColor == White) {
                                Maroon
                            } else {
                                White
                            }
                        },
                    ) {
                        Text(
                            "Sent",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            color = brideTextColor
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(boxBackgroundColor)
                        .weight(1f)
                        .height(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    TextButton(
                        onClick = {
                            brideTextColor = if (brideTextColor == White) {
                                Maroon
                            } else {
                                White
                            }
                        },
                    ) {
                        Text(
                            "Match Fixed",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            color = brideTextColor
                        )
                    }
                }

            }
            LazyColumn(
                modifier = Modifier.padding(start = 0.dp, top = 16.dp, end = 0.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(demoInterests) { interest ->
                    InterestCard(
                        interest,
                        onOpen = {onInterestItemClick(interest)})
                }
            }
        }
    }
}

@Preview
@Composable
fun InterestsScreenPreview() {
    InterestsScreen(onBack = {}, onInterestItemClick = {})
}