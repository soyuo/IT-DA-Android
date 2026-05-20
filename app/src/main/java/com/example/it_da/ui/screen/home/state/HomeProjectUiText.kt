package com.example.it_da.ui.screen.home.state

import com.example.it_da.domain.model.HomeParticipatingProject
import com.example.it_da.domain.model.HomeRecommendedProject

private const val HOME_PROJECT_DETAIL_TEXT = "자세히 보기"

// Provides shared UI-only text for project cards without duplicating the data model.
val HomeRecommendedProject.detailText: String
    get() = HOME_PROJECT_DETAIL_TEXT

// Provides shared UI-only text for project cards without duplicating the data model.
val HomeParticipatingProject.detailText: String
    get() = HOME_PROJECT_DETAIL_TEXT
