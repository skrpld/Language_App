package com.example.language_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.language_app.domain.AuthManager
import com.example.language_app.ui.MainScreen
import com.example.language_app.ui.auth.AuthFlow
import com.example.language_app.ui.profile.ProfileScreen // Corrected Import ProfileScreen
import com.example.language_app.ui.auth.LoginScreen
import com.example.language_app.ui.auth.SignupNamesScreen
import com.example.language_app.ui.auth.SignupPasswordScreen
import com.example.language_app.ui.games.GameAnimalScreen
import com.example.language_app.ui.games.GameAuditionScreen
import com.example.language_app.ui.games.GameWordScreen

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
