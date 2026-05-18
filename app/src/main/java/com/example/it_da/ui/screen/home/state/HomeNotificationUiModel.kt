package com.example.it_da.ui.screen.home.state

import androidx.annotation.DrawableRes

// Represents one notification row shown in the home notification summary.
data class HomeNotificationUiModel(
    val id: String,
    @DrawableRes val imageResId: Int,
    val imageDescription: String,
    val message: String,
    val elapsedTime: String
)
