package com.do55anto5.quinto_code.core.navigation.hosts.app

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
        composable<AppRoutes.App> {
            AppScreen(
                navigateToProfileScreen = {
                    navHostController.navigate(AppRoutes.Profile)
                }
            )
        }

        composable<AppRoutes.Profile> {
            ProfileScreen(
                navigateBack = { navHostController.navigateUp() }
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