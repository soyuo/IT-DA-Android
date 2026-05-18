package com.example.it_da.domain.model

// Represents a recommended project received from the home data source.
data class HomeRecommendedProject(
    val id: String,
    val title: String,
    val recruitingSummary: String,
    val statusText: String,
    val techStacks: List<String>,
    val participantSummary: String
)
