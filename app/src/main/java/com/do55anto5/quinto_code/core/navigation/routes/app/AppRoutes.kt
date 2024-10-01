package com.do55anto5.quinto_code.core.navigation.routes.app

import kotlinx.serialization.Serializable

sealed class AppRoutes {

    @Serializable
    data object Graph : AppRoutes()

    @Serializable
    data object Profile : AppRoutes()

    @Serializable
    data object Home : AppRoutes()

    @Serializable
    data object Search : AppRoutes()

    @Serializable
    data object Bag : AppRoutes()

    @Serializable
    data object Hub : AppRoutes()

    @Serializable
    data object Favorite : AppRoutes()

    @Serializable
    data object Notification : AppRoutes()

}