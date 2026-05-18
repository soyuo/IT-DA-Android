package com.example.it_da.ui.screen.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.component.ItdaSectionHeader
import com.example.it_da.ui.screen.home.state.ParticipatingProjectUiModel

// Shows the user's participating projects with a card type separate from recommendations.
@Composable
fun ParticipatingProjectSection(
    projects: List<ParticipatingProjectUiModel>,
    onProjectClick: (String) -> Unit,
    onDetailClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ItdaSectionHeader(title = "참여 중인 프로젝트")

        Spacer(modifier = Modifier.height(13.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            projects.forEach { project ->
                ParticipatingProjectCard(
                    project = project,
                    onProjectClick = onProjectClick,
                    onDetailClick = onDetailClick
                )
            }
        }
    }
}
