package com.example.it_da.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = DotSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = DotSans,
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = DotSans,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 13.sp,
        letterSpacing = 0.sp
    )
)
