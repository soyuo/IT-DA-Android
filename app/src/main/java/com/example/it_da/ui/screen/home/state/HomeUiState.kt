package com.example.it_da.ui.screen.home.state

import androidx.annotation.DrawableRes
import com.example.it_da.R
import com.example.it_da.domain.model.HomeParticipatingProject
import com.example.it_da.domain.model.HomeProjectCount
import com.example.it_da.domain.model.HomeRecommendedProject

// Holds every dynamic value that the home screen needs to display.
data class HomeUiState(
    val userName: String = "",
    @DrawableRes val profileImageResId: Int = R.drawable.home_profile_placeholder,
    val greetingDescription: String = "",
    val projectCount: HomeProjectCount = HomeProjectCount(
        applyingCount = 0,
        participatingCount = 0,
        completedCount = 0
    ),
    val recommendedProjects: List<HomeRecommendedProject> = emptyList(),
    val participatingProjects: List<HomeParticipatingProject> = emptyList(),
    val notifications: List<HomeNotificationUiModel> = emptyList()
)
