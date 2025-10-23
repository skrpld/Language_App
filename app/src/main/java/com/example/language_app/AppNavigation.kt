package com.example.language_app

import androidx.navigation.NavHostController

object AppDestinations {
    const val MAIN_ROUTE = "main_ROUTE"
    const val LOGIN_ROUTE = "login_screen"
    const val SIGNUP_ROUTE = "signup_screen"
    const val PROFILE_ROUTE = "profile_screen"

    const val ONBOARDING_ROUTE = "onboarding_screen"
    const val LANGUAGE_SELECTION_ROUTE = "language_selection_screen"

    const val NO_CONNECTION_ROUTE = "no_connection_screen"
    const val SPLASH_ROUTE = "splash_screen"
}

class AppNavigation(
    private val navController: NavHostController
) {

}