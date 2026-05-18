package com.example.it_da.ui.screen.home.state

// Represents a recommended project card whose visible text can come from remote data later.
data class RecommendedProjectUiModel(
    val id: String,
    val title: String,
    val recruitingSummary: String,
    val statusText: String,
    val techStacks: List<String>,
    val participantSummary: String,
    val detailText: String = "자세히 보기"
)
