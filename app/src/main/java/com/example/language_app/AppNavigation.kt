package com.example.language_app

import androidx.navigation.NavHostController

object AppDestinations {
    const val AUTH_FLOW_ROUTE = "auth_flow"
    const val MAIN_ROUTE = "main"
    const val PROFILE_ROUTE = "profile"
    const val WORD_GAME_ROUTE = "word_game"
    const val ANIMAL_GAME_ROUTE = "animal_game"
    const val AUDITION_GAME_ROUTE = "audition_game"
    const val BIG_GAME_ROUTE = "big_game"

    const val ONBOARDING_ROUTE = "onboarding_screen"
    const val LANGUAGE_SELECTION_ROUTE = "language_selection_screen"

    const val NO_CONNECTION_ROUTE = "no_connection_screen"
    const val SPLASH_ROUTE = "splash_screen"
}

class AppNavigation(
    private val navController: NavHostController
) {
    fun navigateToMainScreen() {
        navController.navigate(AppDestinations.MAIN_ROUTE)
    }
    fun navigateToProfile() {
        navController.navigate(AppDestinations.PROFILE_ROUTE)
    }

    fun navigateToWordGame() {
        navController.navigate(AppDestinations.WORD_GAME_ROUTE)
    }
    fun navigateToAnimalGame() {
        navController.navigate(AppDestinations.ANIMAL_GAME_ROUTE)
    }
    fun navigateToAuditionGame() {
        navController.navigate(AppDestinations.AUDITION_GAME_ROUTE)
    }
    fun navigateToBigGame() {
        navController.navigate(AppDestinations.BIG_GAME_ROUTE)
    }

    fun onBack() {
        navController.popBackStack()
    }
}