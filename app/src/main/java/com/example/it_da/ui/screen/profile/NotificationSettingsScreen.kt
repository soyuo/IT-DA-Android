package com.example.it_da.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.ui.theme.DotSans
import com.example.it_da.ui.theme.ItdaGuideGray
import com.example.it_da.ui.theme.ItdaHomeExploreButtonGray
import com.example.it_da.ui.theme.ItdaSecondaryTextColor
import com.example.it_da.ui.theme.ItdaWhite
import com.example.it_da.ui.theme.ITDATheme

@Composable
fun NotificationSettingsScreen(
    onBackClick: () -> Unit,
    onVersionInfoClick: () -> Unit,
    onSignOutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var projectNoticeEnabled by rememberSaveable { mutableStateOf(true) }
    var applicationNoticeEnabled by rememberSaveable { mutableStateOf(true) }
    var messageNoticeEnabled by rememberSaveable { mutableStateOf(true) }
    var marketingNoticeEnabled by rememberSaveable { mutableStateOf(false) }
    var showSignOutDialog by rememberSaveable { mutableStateOf(false) }

    if (showSignOutDialog) {
        AlertDialog(
            onDismissRequest = { showSignOutDialog = false },
            title = {
                Text(
                    text = "로그아웃",
                    fontFamily = DotSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            },
            text = {
                Text(
                    text = "정말 로그아웃하시겠습니까?",
                    fontFamily = DotSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            },
            confirmButton = {
                Text(
                    text = "로그아웃",
                    modifier = Modifier.clickable {
                        showSignOutDialog = false
                        onSignOutClick()
                    },
                    fontFamily = DotSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = ItdaHomeExploreButtonGray
                )
            },
            dismissButton = {
                Text(
                    text = "취소",
                    modifier = Modifier.clickable { showSignOutDialog = false },
                    fontFamily = DotSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = ItdaSecondaryTextColor
                )
            },
            containerColor = ItdaWhite
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(horizontal = 24.dp)
    ) {
        NotificationSettingsTopBar(onBackClick = onBackClick)

        Spacer(modifier = Modifier.height(22.dp))

        SettingItem(
            title = "프로젝트 알림",
            description = "프로젝트 참여/변경 관련 알림을 받습니다.",
            enabled = projectNoticeEnabled,
            onCheckedChange = { projectNoticeEnabled = it }
        )

        SettingItem(
            title = "지원 내역 알림",
            description = "지원 결과와 상태 변경 알림을 받습니다.",
            enabled = applicationNoticeEnabled,
            onCheckedChange = { applicationNoticeEnabled = it }
        )

        SettingItem(
            title = "메시지 알림",
            description = "새 메시지 도착 시 알림을 받습니다.",
            enabled = messageNoticeEnabled,
            onCheckedChange = { messageNoticeEnabled = it }
        )

        SettingItem(
            title = "마케팅 알림",
            description = "이벤트, 신규 기능 안내를 받습니다.",
            enabled = marketingNoticeEnabled,
            onCheckedChange = { marketingNoticeEnabled = it }
        )

        Spacer(modifier = Modifier.height(4.dp))
        VersionInfoMenuItem(onClick = onVersionInfoClick)

        Spacer(modifier = Modifier.height(10.dp))
        LogoutMenuItem(onClick = { showSignOutDialog = true })
    }
}

@Composable
private fun NotificationSettingsTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clickable(onClick = onBackClick),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "<",
                fontFamily = DotSans,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Text(
            text = "알림 설정",
            fontFamily = DotSans,
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
private fun SettingItem(
    title: String,
    description: String,
    enabled: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(12.dp),
        color = ItdaWhite,
        border = BorderStroke(1.dp, ItdaGuideGray.copy(alpha = 0.45f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontFamily = DotSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontFamily = DotSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = ItdaSecondaryTextColor
                )
            }

            Switch(
                checked = enabled,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = ItdaWhite,
                    checkedTrackColor = ItdaHomeExploreButtonGray,
                    uncheckedThumbColor = ItdaWhite,
                    uncheckedTrackColor = Color(0xFFCCCCCC)
                )
            )
        }
    }
}

@Composable
private fun VersionInfoMenuItem(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        color = ItdaWhite,
        border = BorderStroke(1.dp, ItdaGuideGray.copy(alpha = 0.45f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "버전 정보",
                fontFamily = DotSans,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = ">",
                fontFamily = DotSans,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = ItdaSecondaryTextColor
            )
        }
    }
}

@Composable
private fun LogoutMenuItem(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        color = ItdaWhite,
        border = BorderStroke(1.dp, ItdaGuideGray.copy(alpha = 0.45f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "로그아웃",
                fontFamily = DotSans,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NotificationSettingsScreenPreview() {
    ITDATheme {
        NotificationSettingsScreen(
            onBackClick = {},
            onVersionInfoClick = {},
            onSignOutClick = {}
        )
    }
}

