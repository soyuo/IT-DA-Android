package com.example.it_da.ui.screen.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.it_da.R
import com.example.it_da.ui.screen.home.component.HomeBottomNavigationBar
import com.example.it_da.ui.screen.home.component.HomeNotificationSection
import com.example.it_da.ui.screen.home.component.HomeProfileSummarySection
import com.example.it_da.ui.screen.home.component.ParticipatingProjectSection
import com.example.it_da.ui.screen.home.component.RecommendedProjectSection
import com.example.it_da.ui.screen.signup.component.SignUpPrimaryButton
import com.example.it_da.ui.screen.signup.component.SignUpTopBar
import com.example.it_da.ui.screen.home.state.HomeNotificationUiModel
import com.example.it_da.ui.screen.home.state.HomeProjectCountUiModel
import com.example.it_da.ui.screen.home.state.HomeUiState
import com.example.it_da.ui.screen.home.state.ParticipatingProjectUiModel
import com.example.it_da.ui.screen.home.state.RecommendedProjectUiModel
import com.example.it_da.ui.theme.ItdaHomeExploreButtonGray
import com.example.it_da.ui.theme.ITDATheme

// Assembles the home screen from state-driven sections and button callbacks.
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onRecommendedProjectClick: (String) -> Unit,
    onRecommendedProjectDetailClick: (String) -> Unit,
    onParticipatingProjectClick: (String) -> Unit,
    onParticipatingProjectDetailClick: (String) -> Unit,
    onNotificationClick: (String) -> Unit,
    onViewAllNotificationsClick: () -> Unit,
    onExploreProjectsClick: () -> Unit,
    onHomeTabClick: () -> Unit,
    onExploreTabClick: () -> Unit,
    onCreateProjectClick: () -> Unit,
    onNotificationTabClick: () -> Unit,
    onProfileTabClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SignUpTopBar(title = "Home")

            HomeContent(
                uiState = uiState,
                onRecommendedProjectClick = onRecommendedProjectClick,
                onRecommendedProjectDetailClick = onRecommendedProjectDetailClick,
                onParticipatingProjectClick = onParticipatingProjectClick,
                onParticipatingProjectDetailClick = onParticipatingProjectDetailClick,
                onNotificationClick = onNotificationClick,
                onViewAllNotificationsClick = onViewAllNotificationsClick,
                onExploreProjectsClick = onExploreProjectsClick,
                modifier = Modifier.weight(1f)
            )
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

// Lays out the scrollable home content while the bottom navigation remains fixed.
@Composable
private fun HomeContent(
    uiState: HomeUiState,
    onRecommendedProjectClick: (String) -> Unit,
    onRecommendedProjectDetailClick: (String) -> Unit,
    onParticipatingProjectClick: (String) -> Unit,
    onParticipatingProjectDetailClick: (String) -> Unit,
    onNotificationClick: (String) -> Unit,
    onViewAllNotificationsClick: () -> Unit,
    onExploreProjectsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 38.dp)
            .padding(bottom = 80.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        HomeProfileSummarySection(
            profileImageResId = uiState.profileImageResId,
            userName = uiState.userName,
            greetingDescription = uiState.greetingDescription,
            projectCount = uiState.projectCount
        )

        Spacer(modifier = Modifier.height(22.dp))

        RecommendedProjectSection(
            projects = uiState.recommendedProjects,
            onProjectClick = onRecommendedProjectClick,
            onDetailClick = onRecommendedProjectDetailClick
        )

        Spacer(modifier = Modifier.height(28.dp))

        ParticipatingProjectSection(
            projects = uiState.participatingProjects,
            onProjectClick = onParticipatingProjectClick,
            onDetailClick = onParticipatingProjectDetailClick
        )

        Spacer(modifier = Modifier.height(28.dp))

        HomeNotificationSection(
            notifications = uiState.notifications,
            onNotificationClick = onNotificationClick,
            onViewAllClick = onViewAllNotificationsClick
        )

        Spacer(modifier = Modifier.height(28.dp))

        SignUpPrimaryButton(
            enabled = true,
            onClick = onExploreProjectsClick,
            text = "프로젝트 탐색하기",
            containerColor = ItdaHomeExploreButtonGray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    ITDATheme {
        HomeScreen(
            uiState = previewHomeUiState(),
            onRecommendedProjectClick = {},
            onRecommendedProjectDetailClick = {},
            onParticipatingProjectClick = {},
            onParticipatingProjectDetailClick = {},
            onNotificationClick = {},
            onViewAllNotificationsClick = {},
            onExploreProjectsClick = {},
            onHomeTabClick = {},
            onExploreTabClick = {},
            onCreateProjectClick = {},
            onNotificationTabClick = {},
            onProfileTabClick = {}
        )
    }
}

// Supplies preview-only values so the screen can be checked without the ViewModel.
private fun previewHomeUiState(): HomeUiState {
    return HomeUiState(
        userName = "000",
        profileImageResId = R.drawable.home_profile_placeholder,
        greetingDescription = "상상은 여기서 현실이 됩니다.\n당신의 프로젝트와 팀을 찾아보세요",
        projectCount = HomeProjectCountUiModel(
            applyingCount = 3,
            participatingCount = 1,
            completedCount = 1
        ),
        recommendedProjects = listOf(
            RecommendedProjectUiModel(
                id = "recommended-ai-planner",
                title = "AI 기반 학습 플래너 [0부0부]",
                recruitingSummary = "백엔드 개발자 1명 모집",
                statusText = "모집 중",
                techStacks = listOf("Back-end"),
                participantSummary = "IoT과ㆍ2명, SW과 1명 참여"
            ),
            RecommendedProjectUiModel(
                id = "recommended-pokemon",
                title = "닮은 포켓몬 검사 [포켓몬백]",
                recruitingSummary = "iOS 개발자ㆍ1명ㆍ디자이너 1명 모집",
                statusText = "마감 임박",
                techStacks = listOf("iOS", "Design"),
                participantSummary = "IoT과ㆍ2명, SW과 1명 참여"
            )
        ),
        participatingProjects = listOf(
            ParticipatingProjectUiModel(
                id = "participating-dalbal",
                title = "사랑을 이어주는 앱 [달발]",
                myRole = "내 역할 : iOS 개발",
                statusText = "진행 중",
                teamSummary = "팀원 4명ㆍ마감 2026-05-31"
            )
        ),
        notifications = listOf(
            HomeNotificationUiModel(
                id = "notification-message",
                imageResId = R.drawable.home_notification_mailbox,
                imageDescription = "새 메시지 알림",
                message = "지원한 프로젝트에서 새 메시지가 있습니다",
                elapsedTime = "2분전"
            )
        )
    )
}
