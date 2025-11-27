package com.example.language_app.ui.navigation

import androidx.navigation.NavHostController

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