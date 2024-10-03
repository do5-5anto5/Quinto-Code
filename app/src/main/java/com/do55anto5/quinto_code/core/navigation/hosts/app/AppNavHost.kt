package com.do55anto5.quinto_code.core.navigation.hosts.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.do55anto5.quinto_code.core.navigation.routes.app.AppRoutes
import com.do55anto5.quinto_code.presenter.screens.app.screen.AppScreen

fun NavGraphBuilder.appNavHost() {
    navigation<AppRoutes.Graph>(
        startDestination = AppRoutes.App
    ) {
        composable<AppRoutes.App> {
            AppScreen()
        }

        composable<AppRoutes.Home> {  }

        composable<AppRoutes.Search> {  }

        composable<AppRoutes.Bag> {  }

        composable<AppRoutes.Hub> {  }

        composable<AppRoutes.Favorite> {  }

        composable<AppRoutes.Notification> {  }

        composable<AppRoutes.Profile> {  }
    }
}