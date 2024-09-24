package com.do55anto5.quinto_code.core.navigation.routes.authentication

import kotlinx.serialization.Serializable

sealed class AuthenticationRoutes {

    @Serializable
    data object Graph : AuthenticationRoutes()

    @Serializable
    data object Home : AuthenticationRoutes()

    @Serializable
    data object Login : AuthenticationRoutes()

    @Serializable
    data object SignUp : AuthenticationRoutes()

}