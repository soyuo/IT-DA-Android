package com.example.it_da.domain.model

// Represents one notification summary received from the home data source.
data class HomeNotification(
    val id: String,
    val type: HomeNotificationType,
    val message: String,
    val elapsedTime: String
)
