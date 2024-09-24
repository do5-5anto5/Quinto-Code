package com.do55anto5.quinto_code.core.navigation.hosts.authentication

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.do55anto5.quinto_code.core.navigation.routes.authentication.AuthenticationRoutes
import com.do55anto5.quinto_code.presenter.screens.authentication.home.HomeAuthenticationScreen
import com.do55anto5.quinto_code.presenter.screens.authentication.login.screen.LoginScreen
import com.do55anto5.quinto_code.presenter.screens.authentication.signup.screen.SignupScreen

@Composable
fun AuthenticationNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = AuthenticationRoutes.Home
    ) {
        composable<AuthenticationRoutes.Home> {
            HomeAuthenticationScreen(
                navigateToLoginScreen = {
                    navHostController.navigate(AuthenticationRoutes.Login)
                },
                navigateToSignUpScreen = {
                    navHostController.navigate(AuthenticationRoutes.SignUp)
                }
            )
        }

        composable<AuthenticationRoutes.SignUp> {
            SignupScreen(
                navigateToLoginScreen = {
                    navHostController.navigate(AuthenticationRoutes.Login)
                },
                onBackPressed = navHostController::popBackStack
            )
        }

        composable<AuthenticationRoutes.Login> {
            LoginScreen(
                navigateToSignupScreen = {
                    navHostController.navigate(AuthenticationRoutes.SignUp)
                },
                onBackPressed = navHostController::popBackStack
            )
        }
    }
}