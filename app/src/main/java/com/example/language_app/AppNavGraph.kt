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
import com.example.language_app.ui.profile.ProfileScreen // Corrected Import ProfileScreen
import com.example.language_app.ui.auth.LoginScreen
import com.example.language_app.ui.auth.SignupNamesScreen
import com.example.language_app.ui.auth.SignupPasswordScreen

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
        AppDestinations.LOGIN_ROUTE
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppDestinations.MAIN_ROUTE) {
            MainScreen(navActions = navActions)
        }
        composable(AppDestinations.PROFILE_ROUTE) {
            ProfileScreen(navActions = navActions)
        }
        composable(AppDestinations.LOGIN_ROUTE) {
            LoginScreen(
                onLogin = {
                    navController.navigate(AppDestinations.MAIN_ROUTE) {
                        popUpTo("login_flow") { inclusive = true }
                    }
                },
                onSignUp = {
                    navController.navigate(AppDestinations.MAIN_ROUTE) {
                        popUpTo("login_flow") { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable(AppDestinations.SIGNUP_ROUTE) {
            SignupNamesScreen(
                authManager = authManager,
                onLoginSuccess = { navActions.navigateToMainScreen() },
                onNext = { navController.navigate("signup_password") },
                onBack = { navController.popBackStack() }
            )
        }
        composable("signup_password") {
            SignupPasswordScreen(
                onSignUp = {
                    navController.navigate(AppDestinations.MAIN_ROUTE) {
                        popUpTo("login_flow") { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
