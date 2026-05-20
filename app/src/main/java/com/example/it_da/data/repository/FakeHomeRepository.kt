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
                userName = "000님",
                greetingDescription = "당신의 포트폴리오 첫 줄이 바뀝니다.\n당신과 딱 맞는 파트너를 찾아줘요",
                projectCount = HomeProjectCount(
                    applyingCount = 3,
                    participatingCount = 1,
                    completedCount = 1
                ),
                recommendedProjects = listOf(
                    HomeRecommendedProject(
                        id = "recommended-ai-planner",
                        title = "AI 기반 학습 플래너 [0수어부]",
                        recruitingSummary = "백엔드 개발자 1명 모집",
                        statusText = "모집중",
                        techStacks = listOf("Back-end"),
                        participantSummary = "IoT과 2명, SW전공 1명 참여"
                    ),
                    HomeRecommendedProject(
                        id = "recommended-hachiware",
                        title = "하치와레 키우기 [하키]",
                        recruitingSummary = "프론트엔드 개발자 2명 모집",
                        statusText = "모집중",
                        techStacks = listOf("Back-end"),
                        participantSummary = "IoT과 2명, SW전공 1명 참여"
                    ),
                    HomeRecommendedProject(
                        id = "recommended-pokemon",
                        title = "많은 포켓몬 검사 [포켓몬빵]",
                        recruitingSummary = "iOS 개발자 1명, 디자이너 1명 모집",
                        statusText = "마감임박",
                        techStacks = listOf("iOS", "Design"),
                        participantSummary = "IoT과 2명, SW전공 3명 참여"
                    )
                ),
                participatingProjects = listOf(
                    HomeParticipatingProject(
                        id = "participating-dalbal",
                        title = "사람을 이어주는 앱 [달밤]",
                        myRole = "내 역할 : iOS 개발",
                        statusText = "진행중",
                        teamSummary = "팀원 4명 · 마감 2026-05-31"
                    )
                ),
                notifications = listOf(
                    HomeNotification(
                        id = "notification-message",
                        type = HomeNotificationType.MESSAGE,
                        message = "지방화 프로젝트에서 메시지가 도착했습니다",
                        elapsedTime = "2분전"
                    ),
                    HomeNotification(
                        id = "notification-join",
                        type = HomeNotificationType.PROJECT_JOIN,
                        message = "백엔드 개발자 1명이 프로젝트에 합류했습니다",
                        elapsedTime = "2분전"
                    )
                )
            )
        )
    }
}
