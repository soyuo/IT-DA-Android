package com.example.it_da.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.ui.theme.DotSans
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

// Shows a compact underlined text action for secondary navigation inside cards or sections.
@Composable
fun ItdaUnderlinedTextButton(
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
