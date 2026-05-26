package com.example.it_da.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.it_da.ui.screen.home.HomeRoute
import com.example.it_da.ui.screen.login.LoginRoute
import com.example.it_da.ui.screen.signup.route.SignUpAccountRoute
import com.example.it_da.ui.screen.signup.route.SignUpAdditionalInfoRoute

// Owns the app navigation graph and connects screen-level navigation events.
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoute.Login.path
    ) {
        composable(AppRoute.Login.path) {
            LoginRoute(
                onSignUpClick = {
                    navController.navigate(AppRoute.SignUpAccount.path)
                },
                onLoginSuccess = {
                    navController.navigate(AppRoute.Home.path)
                },
                onSocialSignUpSuccess = {
                    navController.navigate(AppRoute.SignUpAdditionalInfo.path)
                }
            )
        }

        composable(AppRoute.SignUpAccount.path) {
            SignUpAccountRoute(
                onNextClick = {
                    navController.navigate(AppRoute.SignUpAdditionalInfo.path)
                }
            )
        }

        composable(AppRoute.SignUpAdditionalInfo.path) {
            SignUpAdditionalInfoRoute(
                onNextClick = {
                    navController.navigate(AppRoute.Home.path)
                }
            )
        }

        composable(AppRoute.Home.path) {
            HomeRoute()
        }
    }
}
