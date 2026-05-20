package com.example.it_da.ui.screen.home.mapper

import com.example.it_da.R
import com.example.it_da.domain.model.HomeDashboard
import com.example.it_da.domain.model.HomeNotification
import com.example.it_da.domain.model.HomeNotificationType
import com.example.it_da.ui.screen.home.state.HomeNotificationUiModel
import com.example.it_da.ui.screen.home.state.HomeUiState

// Converts domain home dashboard data into UI state for the Home screen.
fun HomeDashboard.toHomeUiState(): HomeUiState {
    return HomeUiState(
        userName = userName,
        profileImageResId = R.drawable.home_profile_placeholder,
        greetingDescription = greetingDescription,
        projectCount = projectCount,
        recommendedProjects = recommendedProjects,
        participatingProjects = participatingProjects,
        notifications = notifications.map { notification ->
            notification.toHomeNotificationUiModel()
        }
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
