package com.example.it_da.ui.screen.home.state

import androidx.annotation.DrawableRes
import com.example.it_da.R

// Holds every dynamic value that the home screen needs to display.
data class HomeUiState(
    val userName: String = "",
    @DrawableRes val profileImageResId: Int = R.drawable.home_profile_placeholder,
    val greetingDescription: String = "",
    val projectCount: HomeProjectCountUiModel = HomeProjectCountUiModel(
        applyingCount = 0,
        participatingCount = 0,
        completedCount = 0
    ),
    val recommendedProjects: List<RecommendedProjectUiModel> = emptyList(),
    val participatingProjects: List<ParticipatingProjectUiModel> = emptyList(),
    val notifications: List<HomeNotificationUiModel> = emptyList()
)
