package com.example.language_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.language_app.ui.MainScreen
import com.example.language_app.ui.profile.ProfileScreen // Corrected Import ProfileScreen
import com.example.language_app.ui.auth.LoginScreen
import com.example.language_app.ui.auth.SignupNamesScreen
import com.example.language_app.ui.auth.SignupPasswordScreen

@Composable
fun AppNavGraph(
    isLoggedIn: Boolean,
    navController: NavHostController = rememberNavController(),
    navActions: AppNavigation = remember(navController) {
        AppNavigation(navController)
    }
) {
    val startDestination = if (isLoggedIn) {
        AppDestinations.MAIN_ROUTE
    } else {
        "login_flow"
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        loginGraph(
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
            onGoToSignUp = { navController.navigate(AppDestinations.SIGNUP_ROUTE) },
            onGoToPassword = { navController.navigate("signup_password") },
            onBack = { navController.popBackStack() }
        )
        composable(AppDestinations.MAIN_ROUTE) {
            MainScreen(navActions = navActions)
        }
        composable(AppDestinations.PROFILE_ROUTE) {
            ProfileScreen(navActions = navActions)
        }
    }
}

fun NavGraphBuilder.loginGraph(
    onLogin: () -> Unit,
    onSignUp: () -> Unit,
    onGoToSignUp: () -> Unit,
    onGoToPassword: () -> Unit,
    onBack: () -> Unit
) {
    navigation(
        startDestination = AppDestinations.LOGIN_ROUTE,
        route = "login_flow"
    ) {
        composable(AppDestinations.LOGIN_ROUTE) {
            LoginScreen(
                onLogin = onLogin,
                onSignUp = onGoToSignUp,
                onBack = onBack
            )
        }
        composable(AppDestinations.SIGNUP_ROUTE) {
            SignupNamesScreen(
                onNext = onGoToPassword,
                onBack = onBack
            )
        }
        composable("signup_password") {
            SignupPasswordScreen(
                onSignUp = onSignUp,
                onBack = onBack
            )
        }
    }
}