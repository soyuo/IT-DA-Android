package com.example.it_da.ui.screen.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.component.ItdaSectionHeader
import com.example.it_da.ui.screen.home.state.RecommendedProjectUiModel

// Shows the recommended project section with independently clickable project cards.
@Composable
fun RecommendedProjectSection(
    projects: List<RecommendedProjectUiModel>,
    onProjectClick: (String) -> Unit,
    onDetailClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ItdaSectionHeader(
            title = "추천 프로젝트",
            titleFontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(13.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            projects.forEach { project ->
                RecommendedProjectCard(
                    project = project,
                    onProjectClick = onProjectClick,
                    onDetailClick = onDetailClick
                )
            }
        }
    }
}
