package com.example.it_da.data.repository

import com.example.it_da.domain.model.HomeDashboard
import com.example.it_da.domain.model.HomeNotification
import com.example.it_da.domain.model.HomeNotificationType
import com.example.it_da.domain.model.HomeParticipatingProject
import com.example.it_da.domain.model.HomeProjectCount
import com.example.it_da.domain.model.HomeRecommendedProject

class FakeHomeRepository : HomeRepository {
    // Returns sample home data through the same repository boundary that a server implementation will use.
    override suspend fun getHomeDashboard(): Result<HomeDashboard> {
        return Result.success(
            HomeDashboard(
                userName = "000",
                greetingDescription = "상상은 여기서 현실이 됩니다.\n당신의 프로젝트와 팀을 찾아보세요",
                projectCount = HomeProjectCount(
                    applyingCount = 3,
                    participatingCount = 1,
                    completedCount = 1
                ),
                recommendedProjects = listOf(
                    HomeRecommendedProject(
                        id = "recommended-ai-planner",
                        title = "AI 기반 학습 플래너 [0부0부]",
                        recruitingSummary = "백엔드 개발자 1명 모집",
                        statusText = "모집 중",
                        techStacks = listOf("Back-end"),
                        participantSummary = "IoT과ㆍ2명, SW과 1명 참여"
                    ),
                    HomeRecommendedProject(
                        id = "recommended-hachiware",
                        title = "하지와레 키우기 [하키]",
                        recruitingSummary = "프론트엔드 개발자 2명 모집",
                        statusText = "모집 중",
                        techStacks = listOf("Back-end"),
                        participantSummary = "IoT과ㆍ2명, SW과 1명 참여"
                    ),
                    HomeRecommendedProject(
                        id = "recommended-pokemon",
                        title = "닮은 포켓몬 검사 [포켓몬백]",
                        recruitingSummary = "iOS 개발자ㆍ1명ㆍ디자이너 1명 모집",
                        statusText = "마감 임박",
                        techStacks = listOf("iOS", "Design"),
                        participantSummary = "IoT과ㆍ2명, SW과 1명 참여"
                    )
                ),
                participatingProjects = listOf(
                    HomeParticipatingProject(
                        id = "participating-dalbal",
                        title = "사랑을 이어주는 앱 [달발]",
                        myRole = "내 역할 : iOS 개발",
                        statusText = "진행 중",
                        teamSummary = "팀원 4명ㆍ마감 2026-05-31"
                    )
                ),
                notifications = listOf(
                    HomeNotification(
                        id = "notification-message",
                        type = HomeNotificationType.MESSAGE,
                        message = "지원한 프로젝트에서 새 메시지가 있습니다",
                        elapsedTime = "2분전"
                    ),
                    HomeNotification(
                        id = "notification-join",
                        type = HomeNotificationType.PROJECT_JOIN,
                        message = "백엔드 개발자 1명이 프로젝트에 합류 하였습니다",
                        elapsedTime = "2분전"
                    )
                )
            )
        )
    }
}
