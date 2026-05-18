package com.example.it_da.ui.screen.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.ui.theme.DotSans
import com.example.it_da.ui.theme.ItdaGuideGray
import com.example.it_da.ui.theme.ItdaHomeCardBorderGray
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

internal val HomeProjectTitleStartPadding = 15.dp
internal val HomeProjectContentStartPadding = 20.dp
internal val HomeProjectContentEndPadding = 14.dp

// Shows a rounded text badge used for project status and stack labels.
@Composable
fun HomeProjectBadge(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(30.dp),
        color = androidx.compose.ui.graphics.Color.Transparent,
        border = BorderStroke(1.2.dp, ItdaGuideGray)
    ) {
        Text(
            text = text,
            color = ItdaSecondaryTextColor,
            fontFamily = DotSans,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

// Shows the small underlined detail action used inside project cards.
@Composable
fun HomeProjectDetailLink(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(2.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 2.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            color = ItdaSecondaryTextColor,
            fontFamily = DotSans,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            lineHeight = 10.sp,
            textDecoration = TextDecoration.Underline
        )
    }
}

// Provides the border used by project cards without duplicating the color choice.
fun homeProjectCardBorder(): BorderStroke {
    return BorderStroke(1.dp, ItdaHomeCardBorderGray)
}
