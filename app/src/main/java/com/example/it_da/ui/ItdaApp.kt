package com.example.it_da.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.it_da.navigation.AppNavigation
import com.example.it_da.ui.screen.login.screen.LoginLaunchScreen
import kotlinx.coroutines.delay

private const val LoginLaunchDurationMillis = 2_000L
private const val LoginTransitionDurationMillis = 500

// Controls the first app flow by showing the launch screen before the login screen.
@Composable
fun ItdaApp() {
    var isLaunchFinished by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(LoginLaunchDurationMillis)
        isLaunchFinished = true
    }

    Crossfade(
        targetState = isLaunchFinished,
        animationSpec = tween(durationMillis = LoginTransitionDurationMillis),
        label = "Login launch transition"
    ) { launchFinished ->
        if (launchFinished) {
            AppNavigation()
        } else {
            LoginLaunchScreen()
        }
    }
}
