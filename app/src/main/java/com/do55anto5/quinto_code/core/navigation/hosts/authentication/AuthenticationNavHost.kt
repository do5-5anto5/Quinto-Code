package com.do55anto5.quinto_code.core.navigation.hosts.authentication

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.do55anto5.quinto_code.core.navigation.hosts.app.appNavHost
import com.do55anto5.quinto_code.core.navigation.routes.app.AppRoutes
import com.do55anto5.quinto_code.core.navigation.routes.authentication.AuthenticationRoutes
import com.do55anto5.quinto_code.presenter.screens.authentication.forgot_password.screen.ForgotPasswordScreen
import com.do55anto5.quinto_code.presenter.screens.authentication.home.HomeAuthenticationScreen
import com.do55anto5.quinto_code.presenter.screens.authentication.login.screen.LoginScreen
import com.do55anto5.quinto_code.presenter.screens.authentication.signup.screen.SignupScreen

fun NavGraphBuilder.authenticationNavHost(navHostController: NavHostController) {
    navigation<AuthenticationRoutes.Graph>(
        startDestination = AuthenticationRoutes.Home
    ) {
        composable<AuthenticationRoutes.Home>(
            enterTransition = {
                scaleIn(
                    animationSpec = spring(
                        stiffness = Spring.StiffnessVeryLow,
                        dampingRatio = Spring.DampingRatioHighBouncy
                    ),
                    initialScale = 0f,
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                )
            }
        ) {
            HomeAuthenticationScreen(
                navigateToLoginScreen = {
                    navHostController.navigate(AuthenticationRoutes.Login)
                },
                navigateToSignUpScreen = {
                    navHostController.navigate(AuthenticationRoutes.SignUp)
                },
                navigateToAppScreen = {
                    navHostController.navigate(AppRoutes.Graph) {
                        popUpTo(AuthenticationRoutes.Graph) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<AuthenticationRoutes.SignUp>(
            enterTransition = {
                scaleIn(
                    animationSpec = spring(
                        stiffness = Spring.StiffnessVeryLow,
                        dampingRatio = Spring.DampingRatioLowBouncy
                    ),
                    initialScale = 0f,
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                )
            }
        ) {
            SignupScreen(
                navigateToAppScreen = {
                    navHostController.navigate(AppRoutes.Graph) {
                        popUpTo(AuthenticationRoutes.Graph) {
                            inclusive = true
                        }
                    }
                },
                navigateToLoginScreen = {
                    navHostController.navigate(AuthenticationRoutes.Login)
                },
                onBackPressed = navHostController::popBackStack
            )
        }

        composable<AuthenticationRoutes.Login>(
            enterTransition = {
                scaleIn(
                    animationSpec = spring(
                        stiffness = Spring.StiffnessVeryLow,
                        dampingRatio = Spring.DampingRatioLowBouncy
                    ),
                    initialScale = 0f,
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                )
            }
        ) {
            LoginScreen(
                navigateToAppScreen = {
                    navHostController.navigate(AppRoutes.Graph) {
                        popUpTo(AuthenticationRoutes.Graph) {
                            inclusive = true
                        }
                    }
                },
                navigateToSignupScreen = {
                    navHostController.navigate(AuthenticationRoutes.SignUp)
                },
                navigateToForgotScreen = {
                    navHostController.navigate(AuthenticationRoutes.ForgotPassword)
                },
                onBackPressed = navHostController::popBackStack
            )
        }

        composable<AuthenticationRoutes.ForgotPassword>(
            enterTransition = {
                scaleIn(
                    animationSpec = spring(
                        stiffness = Spring.StiffnessVeryLow,
                        dampingRatio = Spring.DampingRatioLowBouncy
                    ),
                    initialScale = 0f,
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                )
            }
        ) {
            ForgotPasswordScreen(
                onBackPressed = navHostController::popBackStack
            )
        }

        appNavHost(navHostController = navHostController)

    }
}