package com.example.it_da.domain.model

// Represents all data needed to render the home dashboard.
data class HomeDashboard(
    val userName: String,
    val greetingDescription: String,
    val projectCount: HomeProjectCount,
    val recommendedProjects: List<HomeRecommendedProject>,
    val participatingProjects: List<HomeParticipatingProject>,
    val notifications: List<HomeNotification>
)
