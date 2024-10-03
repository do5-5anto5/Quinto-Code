package com.do55anto5.quinto_code.core.navigation.hosts.onboarding

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.do55anto5.quinto_code.core.navigation.hosts.app.appNavHost
import com.do55anto5.quinto_code.core.navigation.hosts.authentication.authenticationNavHost
import com.do55anto5.quinto_code.core.navigation.routes.app.AppRoutes
import com.do55anto5.quinto_code.core.navigation.routes.authentication.AuthenticationRoutes
import com.do55anto5.quinto_code.core.navigation.routes.onboarding.OnboardingRoutes
import com.do55anto5.quinto_code.presenter.screens.splash.screen.SplashScreen
import com.do55anto5.quinto_code.presenter.screens.welcome.screen.WelcomeScreen

@Composable
fun OnboardingNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = OnboardingRoutes.Splash
    ) {
        composable<OnboardingRoutes.Splash> {
            SplashScreen(
                navigateToAppScreen = {
                    navHostController.navigate(AppRoutes.Graph) {
                        popUpTo(OnboardingRoutes.Splash) {
                            inclusive = true
                        }
                    }
                },
                navigateToWelcomeScreen = {
                    navHostController.navigate(OnboardingRoutes.Welcome) {
                        popUpTo(OnboardingRoutes.Splash) {
                            inclusive = true
                        }
                    }
                },
                navigateToHomeAuthenticationScreen = {
                    navHostController.navigate(AuthenticationRoutes.Graph) {
                        popUpTo(OnboardingRoutes.Splash) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<OnboardingRoutes.Welcome> {
            WelcomeScreen(
                navigateToHomeAuthenticationScreen = {
                    navHostController.navigate(AuthenticationRoutes.Home) {
                        popUpTo(OnboardingRoutes.Welcome) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        authenticationNavHost(navHostController = navHostController)
        appNavHost()
    }
}