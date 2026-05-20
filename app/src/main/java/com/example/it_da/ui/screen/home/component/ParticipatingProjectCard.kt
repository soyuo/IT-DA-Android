package com.example.it_da.ui.screen.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.domain.model.HomeParticipatingProject
import com.example.it_da.ui.screen.home.state.detailText
import com.example.it_da.ui.theme.DotSans
import com.example.it_da.ui.theme.ItdaPrimaryTextColor
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

// Displays one participating project with role and progress text supplied by state.
@Composable
fun ParticipatingProjectCard(
    project: HomeParticipatingProject,
    onProjectClick: (String) -> Unit,
    onDetailClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 106.dp)
            .clickable {
                onProjectClick(project.id)
            },
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surface,
        border = homeProjectCardBorder()
    ) {
        Column(
            modifier = Modifier.padding(
                top = 13.dp,
                bottom = 12.dp
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = HomeProjectTitleStartPadding,
                        end = HomeProjectContentEndPadding
                    )
            ) {
                Text(
                    text = project.title,
                    color = ItdaPrimaryTextColor,
                    fontFamily = DotSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                HomeProjectBadge(text = project.statusText)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = project.myRole,
                color = ItdaSecondaryTextColor,
                fontFamily = DotSans,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    start = HomeProjectContentStartPadding,
                    end = HomeProjectContentEndPadding
                )
            )

            Spacer(modifier = Modifier.height(36.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = HomeProjectContentStartPadding,
                        end = HomeProjectContentEndPadding
                    )
            ) {
                Text(
                    text = project.teamSummary,
                    color = ItdaSecondaryTextColor,
                    fontFamily = DotSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 11.sp,
                    lineHeight = 11.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                HomeProjectDetailLink(
                    text = project.detailText,
                    onClick = {
                        onDetailClick(project.id)
                    }
                )
            }
        }
    }
}
