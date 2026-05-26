package com.example.it_da.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.ui.theme.DotSans
import com.example.it_da.ui.theme.ItdaGuideGray
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

// Displays a rounded outlined badge for compact labels such as status or tech stack text.
@Composable
fun ItdaOutlinedBadge(
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
