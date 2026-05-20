package com.example.it_da.ui.screen.home.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.domain.model.HomeProjectCount
import com.example.it_da.ui.theme.DotSans
import com.example.it_da.ui.theme.ItdaHomeCardBorderGray
import com.example.it_da.ui.theme.ItdaPrimaryTextColor
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

// Shows the user greeting and the project status count summary.
@Composable
fun HomeProfileSummarySection(
    @DrawableRes profileImageResId: Int,
    userName: String,
    greetingDescription: String,
    projectCount: HomeProjectCount,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = profileImageResId),
                contentDescription = "프로필 이미지",
                modifier = Modifier.size(58.dp)
            )

            Spacer(modifier = Modifier.width(13.dp))

            Column {
                Text(
                    text = "안녕하세요, ${userName}님 👋",
                    color = ItdaPrimaryTextColor,
                    fontFamily = DotSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = greetingDescription,
                    color = ItdaSecondaryTextColor,
                    fontFamily = DotSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 15.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        HomeProjectCountCard(projectCount = projectCount)
    }
}

// Displays the three count values that summarize the user's project activity.
@Composable
private fun HomeProjectCountCard(
    projectCount: HomeProjectCount,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(8.dp),
                clip = false
            ),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, ItdaHomeCardBorderGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HomeProjectCountItem(
                label = "지원 중",
                count = projectCount.applyingCount
            )
            HomeProjectCountItem(
                label = "참여 중",
                count = projectCount.participatingCount
            )
            HomeProjectCountItem(
                label = "완료",
                count = projectCount.completedCount
            )
        }
    }
}

// Shows one labeled count inside the project count summary card.
@Composable
private fun HomeProjectCountItem(
    label: String,
    count: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            color = ItdaSecondaryTextColor,
            fontFamily = DotSans,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            lineHeight = 13.sp
        )

        Spacer(modifier = Modifier.height(17.dp))

        Text(
            text = count.toString(),
            color = ItdaPrimaryTextColor,
            fontFamily = DotSans,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 18.sp
        )
    }
}
