package com.example.it_da.ui.screen.home.state

// Represents a project the user is already participating in.
data class ParticipatingProjectUiModel(
    val id: String,
    val title: String,
    val myRole: String,
    val statusText: String,
    val teamSummary: String,
    val detailText: String = "자세히 보기"
)
