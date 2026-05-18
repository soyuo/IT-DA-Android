package com.example.it_da.domain.model

// Represents the user's project activity counts from the home data source.
data class HomeProjectCount(
    val applyingCount: Int,
    val participatingCount: Int,
    val completedCount: Int
)
