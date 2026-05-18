package com.example.it_da.ui.screen.signup.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.R
import com.example.it_da.ui.theme.ItdaTopBarTitleTextColor

private val SignUpTopBarHeight = 72.dp
private val SignUpTopTitleTopPadding = 22.dp
private val SignUpTopTitleWeight = FontWeight(700)

// Draws the sign-up title area and the design line asset below it.
@Composable
fun SignUpTopBar(
    title: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(SignUpTopBarHeight)
    ) {
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = SignUpTopTitleTopPadding),
            color = ItdaTopBarTitleTextColor,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = SignUpTopTitleWeight,
                fontSize = 23.sp,
                lineHeight = 27.sp
            )
        )

        Image(
            painter = painterResource(id = R.drawable.line),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(1.5.dp)
        )
    }
}
