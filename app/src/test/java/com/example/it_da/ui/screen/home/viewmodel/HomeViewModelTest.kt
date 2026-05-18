package com.example.it_da.ui.screen.home.viewmodel

import com.example.it_da.data.repository.HomeRepository
import com.example.it_da.domain.model.HomeDashboard
import com.example.it_da.domain.model.HomeNotification
import com.example.it_da.domain.model.HomeNotificationType
import com.example.it_da.domain.model.HomeParticipatingProject
import com.example.it_da.domain.model.HomeProjectCount
import com.example.it_da.domain.model.HomeRecommendedProject
import com.example.it_da.testing.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun loadsHomeDashboardFromRepositoryIntoUiState() = runTest(
        mainDispatcherRule.testDispatcher
    ) {
        val homeDashboard = HomeDashboard(
            userName = "서버사용자",
            greetingDescription = "서버에서 받은 소개 문구",
            projectCount = HomeProjectCount(
                applyingCount = 7,
                participatingCount = 2,
                completedCount = 4
            ),
            recommendedProjects = listOf(
                HomeRecommendedProject(
                    id = "server-recommended",
                    title = "서버 추천 프로젝트",
                    recruitingSummary = "서버 모집 요약",
                    statusText = "서버 상태",
                    techStacks = listOf("iOS", "Design", "Back-end"),
                    participantSummary = "서버 참여 요약"
                )
            ),
            participatingProjects = listOf(
                HomeParticipatingProject(
                    id = "server-participating",
                    title = "서버 참여 프로젝트",
                    myRole = "서버 역할",
                    statusText = "서버 진행 상태",
                    teamSummary = "서버 팀 요약"
                )
            ),
            notifications = listOf(
                HomeNotification(
                    id = "server-notification",
                    type = HomeNotificationType.MESSAGE,
                    message = "서버 알림",
                    elapsedTime = "방금 전"
                )
            )
        )
        val viewModel = HomeViewModel(StaticHomeRepository(homeDashboard))

        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals("서버사용자", uiState.userName)
        assertEquals("서버에서 받은 소개 문구", uiState.greetingDescription)
        assertEquals(7, uiState.projectCount.applyingCount)
        assertEquals("서버 추천 프로젝트", uiState.recommendedProjects.first().title)
        assertEquals(listOf("iOS", "Design", "Back-end"), uiState.recommendedProjects.first().techStacks)
        assertEquals("서버 참여 프로젝트", uiState.participatingProjects.first().title)
        assertEquals("서버 역할", uiState.participatingProjects.first().myRole)
        assertEquals("서버 팀 요약", uiState.participatingProjects.first().teamSummary)
        assertEquals("서버 알림", uiState.notifications.first().message)
    }

    private class StaticHomeRepository(
        private val homeDashboard: HomeDashboard
    ) : HomeRepository {
        // Returns a fixed dashboard so the ViewModel mapping can be verified deterministically.
        override suspend fun getHomeDashboard(): Result<HomeDashboard> {
            return Result.success(homeDashboard)
        }
    }
}
