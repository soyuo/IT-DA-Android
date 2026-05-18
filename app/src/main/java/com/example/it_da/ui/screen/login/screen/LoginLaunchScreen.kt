package com.example.it_da.ui.screen.login.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.screen.login.component.LoginBottomGuideText
import com.example.it_da.ui.screen.login.component.LoginIntroTextGroup
import com.example.it_da.ui.theme.ITDATheme

// Draws the first login launch screen and delegates each text area to focused components.
@Composable
fun LoginLaunchScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        LoginIntroTextGroup(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 191.dp)
        )

        LoginBottomGuideText(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 35.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginLaunchScreenPreview() {
    ITDATheme {
        LoginLaunchScreen()
    }
}
