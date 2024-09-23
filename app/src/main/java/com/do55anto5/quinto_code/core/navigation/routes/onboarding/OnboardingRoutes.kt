package com.do55anto5.quinto_code.core.navigation.routes.onboarding

import kotlinx.serialization.Serializable

sealed class OnboardingRoutes {

    @Serializable
    data object Graph : OnboardingRoutes()

    @Serializable
    data object Splash : OnboardingRoutes()

    @Serializable
    data object Welcome : OnboardingRoutes()

}