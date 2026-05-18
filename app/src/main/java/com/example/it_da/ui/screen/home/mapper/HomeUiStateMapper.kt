package com.example.it_da.ui.screen.home.mapper

import com.example.it_da.R
import com.example.it_da.domain.model.HomeDashboard
import com.example.it_da.domain.model.HomeNotification
import com.example.it_da.domain.model.HomeNotificationType
import com.example.it_da.domain.model.HomeParticipatingProject
import com.example.it_da.domain.model.HomeProjectCount
import com.example.it_da.domain.model.HomeRecommendedProject
import com.example.it_da.ui.screen.home.state.HomeNotificationUiModel
import com.example.it_da.ui.screen.home.state.HomeProjectCountUiModel
import com.example.it_da.ui.screen.home.state.HomeUiState
import com.example.it_da.ui.screen.home.state.ParticipatingProjectUiModel
import com.example.it_da.ui.screen.home.state.RecommendedProjectUiModel

// Converts domain home dashboard data into UI state for the Home screen.
fun HomeDashboard.toHomeUiState(): HomeUiState {
    return HomeUiState(
        userName = userName,
        profileImageResId = R.drawable.home_profile_placeholder,
        greetingDescription = greetingDescription,
        projectCount = projectCount.toHomeProjectCountUiModel(),
        recommendedProjects = recommendedProjects.map { project ->
            project.toRecommendedProjectUiModel()
        },
        participatingProjects = participatingProjects.map { project ->
            project.toParticipatingProjectUiModel()
        },
        notifications = notifications.map { notification ->
            notification.toHomeNotificationUiModel()
        }
    )
}

// Converts domain project count data into the count model used by the Home header.
private fun HomeProjectCount.toHomeProjectCountUiModel(): HomeProjectCountUiModel {
    return HomeProjectCountUiModel(
        applyingCount = applyingCount,
        participatingCount = participatingCount,
        completedCount = completedCount
    )
}

// Converts a domain recommended project into the card model used by the Home UI.
private fun HomeRecommendedProject.toRecommendedProjectUiModel(): RecommendedProjectUiModel {
    return RecommendedProjectUiModel(
        id = id,
        title = title,
        recruitingSummary = recruitingSummary,
        statusText = statusText,
        techStacks = techStacks,
        participantSummary = participantSummary
    )
}

// Converts a domain participating project into the card model used by the Home UI.
private fun HomeParticipatingProject.toParticipatingProjectUiModel(): ParticipatingProjectUiModel {
    return ParticipatingProjectUiModel(
        id = id,
        title = title,
        myRole = myRole,
        statusText = statusText,
        teamSummary = teamSummary
    )
}

// Converts a domain notification into the image-backed model used by the Home UI.
private fun HomeNotification.toHomeNotificationUiModel(): HomeNotificationUiModel {
    return HomeNotificationUiModel(
        id = id,
        imageResId = type.toNotificationImageResId(),
        imageDescription = type.toNotificationImageDescription(),
        message = message,
        elapsedTime = elapsedTime
    )
}

// Maps notification type values to drawable resources owned by the UI layer.
private fun HomeNotificationType.toNotificationImageResId(): Int {
    return when (this) {
        HomeNotificationType.MESSAGE -> R.drawable.home_notification_mailbox
        HomeNotificationType.PROJECT_JOIN -> R.drawable.home_notification_laptop
    }
}

// Maps notification type values to accessibility descriptions for notification images.
private fun HomeNotificationType.toNotificationImageDescription(): String {
    return when (this) {
        HomeNotificationType.MESSAGE -> "새 메시지 알림"
        HomeNotificationType.PROJECT_JOIN -> "프로젝트 참여 알림"
    }
}
