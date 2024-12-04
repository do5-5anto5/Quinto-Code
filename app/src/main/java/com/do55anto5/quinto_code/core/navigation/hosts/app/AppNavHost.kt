package com.do55anto5.quinto_code.core.navigation.hosts.app

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.do55anto5.quinto_code.core.navigation.routes.app.AppRoutes
import com.do55anto5.quinto_code.presenter.screens.app.screen.AppScreen
import com.do55anto5.quinto_code.presenter.screens.main.profile.screen.ProfileScreen

fun NavGraphBuilder.appNavHost(navHostController: NavHostController) {
    navigation<AppRoutes.Graph>(
        startDestination = AppRoutes.App
    ) {
        composable<AppRoutes.App>(
            enterTransition = {
                scaleIn(
                    animationSpec = spring(
                        stiffness = Spring.StiffnessVeryLow,
                        dampingRatio = Spring.DampingRatioMediumBouncy
                        ),
                    initialScale = 0f,
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                )
            }
        ) { backStackEntry ->
            val fullName = backStackEntry.savedStateHandle.get<String>("fullName")
            AppScreen(
                navigateToProfileScreen = {
                    navHostController.navigate(AppRoutes.Profile)
                },
                fullName = fullName ?: ""
            )
        }

        composable<AppRoutes.Profile>(
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
            ProfileScreen(
                navigateBack = { fullName ->
                    navHostController.previousBackStackEntry?.savedStateHandle?.set("fullName", fullName)
                    navHostController.navigateUp()
                }
            )
        }

        composable<AppRoutes.Home> {  }

        composable<AppRoutes.Search> {  }

        composable<AppRoutes.Bag> {  }

        composable<AppRoutes.Hub> {  }

        composable<AppRoutes.Favorite> {  }

        composable<AppRoutes.Notification> {  }
    }
}