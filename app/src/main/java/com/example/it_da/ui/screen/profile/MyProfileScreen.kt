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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.ui.component.ItdaOutlinedBadge
import com.example.it_da.ui.screen.home.component.HomeBottomNavigationBar
import com.example.it_da.ui.screen.signup.component.SignUpTopBar
import com.example.it_da.ui.theme.DotSans
import com.example.it_da.ui.theme.ItdaGuideGray
import com.example.it_da.ui.theme.ItdaSecondaryTextColor
import com.example.it_da.ui.theme.ItdaWhite
import com.example.it_da.ui.theme.ITDATheme

@Composable
fun MyProfileScreen(
    onHomeTabClick: () -> Unit,
    onExploreTabClick: () -> Unit,
    onCreateProjectClick: () -> Unit,
    onNotificationTabClick: () -> Unit,
    onProfileTabClick: () -> Unit,
    onNotificationSettingsClick: () -> Unit,
    onProjectStatusClick: () -> Unit,
    onSelfIntroductionClick: () -> Unit,
    onPersonalInfoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SignUpTopBar(title = "Project")

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 22.dp, bottom = 84.dp)
            ) {
                MyProfileHeader()
                Spacer(modifier = Modifier.height(14.dp))

                ActionButton(
                    text = "개인 정보 입력",
                    onClick = onPersonalInfoClick
                )

                Spacer(modifier = Modifier.height(22.dp))
                SectionTitle(text = "기술 스택")
                Spacer(modifier = Modifier.height(14.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    ItdaOutlinedBadge(text = "Back-end")
                    ItdaOutlinedBadge(text = "Java")
                    ItdaOutlinedBadge(text = "JavaScript")
                    ItdaOutlinedBadge(text = "Design")
                }

                Spacer(modifier = Modifier.height(24.dp))
                SectionTitle(text = "경력")
                Spacer(modifier = Modifier.height(10.dp))
                EmptyBox(height = 86.dp)

                Spacer(modifier = Modifier.height(20.dp))
                SectionTitle(text = "자기 소개")
                Spacer(modifier = Modifier.height(10.dp))
                EmptyBox(
                    height = 90.dp,
                    text = "자기소개 작성하기",
                    onClick = onSelfIntroductionClick
                )

                Spacer(modifier = Modifier.height(20.dp))
                SectionTitle(text = "활동 요약")
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SummaryCard(value = "2", label = "참여 프로젝트", modifier = Modifier.weight(1f))
                    SummaryCard(value = "3", label = "지원 내역", modifier = Modifier.weight(1f))
                    SummaryCard(value = "1", label = "받은 제안", modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    ActionButton(
                        text = "프로젝트 전체 보기",
                        modifier = Modifier.weight(1.35f),
                        onClick = onProjectStatusClick
                    )
                    ActionButton(
                        text = "설정",
                        modifier = Modifier.weight(1f),
                        onClick = onNotificationSettingsClick
                    )
                }
            }
        }

        HomeBottomNavigationBar(
            onHomeClick = onHomeTabClick,
            onExploreClick = onExploreTabClick,
            onCreateProjectClick = onCreateProjectClick,
            onNotificationClick = onNotificationTabClick,
            onProfileClick = onProfileTabClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
        )
    }
}

@Composable
private fun MyProfileHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(92.dp),
            shape = CircleShape,
            color = Color(0xFFE3E3E3)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Surface(
                    modifier = Modifier.size(38.dp),
                    shape = CircleShape,
                    color = Color(0xFF8F8F8F)
                ) {}
            }
        }

        Spacer(modifier = Modifier.size(20.dp))

        Column {
            Text(
                text = "메타몽",
                fontFamily = DotSans,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                lineHeight = 40.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "SW개발과 · 1학년",
                fontFamily = DotSans,
                fontWeight = FontWeight.Normal,
                fontSize = 23.sp,
                lineHeight = 26.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "가입일 2025년 1월",
                fontFamily = DotSans,
                fontWeight = FontWeight.Normal,
                fontSize = 23.sp,
                lineHeight = 26.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        fontFamily = DotSans,
        fontWeight = FontWeight.Normal,
        fontSize = 27.sp,
        lineHeight = 31.sp,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
private fun EmptyBox(
    height: Dp,
    text: String? = null,
    onClick: (() -> Unit)? = null
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .let { base -> if (onClick != null) base.clickable(onClick = onClick) else base },
        shape = RoundedCornerShape(12.dp),
        color = Color.Transparent,
        border = BorderStroke(1.2.dp, ItdaGuideGray)
    ) {
        if (text != null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    fontFamily = DotSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = ItdaSecondaryTextColor
                )
            }
        }
    }
}

@Composable
private fun SummaryCard(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.height(72.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color.Transparent,
        border = BorderStroke(1.2.dp, ItdaGuideGray)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = value,
                fontFamily = DotSans,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = label,
                fontFamily = DotSans,
                fontWeight = FontWeight.Normal,
                fontSize = 11.sp,
                lineHeight = 13.sp,
                color = ItdaSecondaryTextColor
            )
        }
    }
}

@Composable
private fun ActionButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .height(50.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        color = ItdaWhite,
        border = BorderStroke(1.2.dp, ItdaGuideGray)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = text,
                fontFamily = DotSans,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 16.sp,
                color = ItdaSecondaryTextColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyProfileScreenPreview() {
    ITDATheme {
        MyProfileScreen(
            onHomeTabClick = {},
            onExploreTabClick = {},
            onCreateProjectClick = {},
            onNotificationTabClick = {},
            onProfileTabClick = {},
            onNotificationSettingsClick = {},
            onProjectStatusClick = {},
            onSelfIntroductionClick = {},
            onPersonalInfoClick = {}
        )
    }
}

