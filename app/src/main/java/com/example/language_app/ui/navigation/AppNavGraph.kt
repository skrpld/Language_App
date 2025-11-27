package com.example.language_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.language_app.AppDestinations
import com.example.language_app.AppNavigation
import com.example.language_app.di.AuthManager
import com.example.language_app.ui.screens.MainScreen
import com.example.language_app.ui.screens.auth.AuthFlow
import com.example.language_app.ui.screens.profile.ProfileScreen // Corrected Import ProfileScreen
import com.example.language_app.ui.screens.games.GameAnimalScreen
import com.example.language_app.ui.screens.games.GameAuditionScreen
import com.example.language_app.ui.screens.games.GameWordScreen

@Composable
fun AppNavGraph(
    isLoggedIn: Boolean,
    navController: NavHostController = rememberNavController(),
    authManager: AuthManager,
    navActions: AppNavigation = remember(navController) {
        AppNavigation(navController)
    }
) {
    val startDestination = if (isLoggedIn) {
        AppDestinations.MAIN_ROUTE
    } else {
        AppDestinations.AUTH_FLOW_ROUTE
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppDestinations.MAIN_ROUTE) {
            MainScreen(navActions = navActions)
        }
        composable(AppDestinations.AUTH_FLOW_ROUTE) {
            AuthFlow(
                authManager = authManager,
                onLoginSuccess = {
                    navController.navigate(AppDestinations.MAIN_ROUTE) {
                        popUpTo(AppDestinations.AUTH_FLOW_ROUTE) { inclusive = true }
                    }
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppDestinations.MAIN_ROUTE) {
            MainScreen(navActions = navActions)
        }
        composable(AppDestinations.PROFILE_ROUTE) {
            ProfileScreen(navActions = navActions)
        }

        composable(AppDestinations.WORD_GAME_ROUTE) {
            GameWordScreen(navActions = navActions)
        }
        composable(AppDestinations.ANIMAL_GAME_ROUTE) {
            GameAnimalScreen(navActions = navActions)
        }
        composable(AppDestinations.AUDITION_GAME_ROUTE) {
            GameAuditionScreen(navActions = navActions)
        }
        composable(AppDestinations.BIG_GAME_ROUTE) {
        }
    }
}
