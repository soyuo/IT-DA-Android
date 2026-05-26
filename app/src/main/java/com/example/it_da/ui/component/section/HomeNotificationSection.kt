package com.example.it_da.ui.component.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.component.ItdaSectionHeader
import com.example.it_da.ui.component.ItdaUnderlinedTextButton
import com.example.it_da.ui.component.card.HomeNotificationCard
import com.example.it_da.ui.screen.home.state.HomeNotificationUiModel

// Shows the notification summary section and a separate all-notifications action.
@Composable
fun HomeNotificationSection(
    notifications: List<HomeNotificationUiModel>,
    onNotificationClick: (String) -> Unit,
    onViewAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ItdaSectionHeader(
            title = "알림 요약ㆍ확인",
            titleFontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(13.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(13.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            notifications.forEach { notification ->
                HomeNotificationCard(
                    notification = notification,
                    onClick = onNotificationClick
                )
            }

            ItdaUnderlinedTextButton(
                text = "모든 알림 보기",
                onClick = onViewAllClick
            )
        }
    }
}
