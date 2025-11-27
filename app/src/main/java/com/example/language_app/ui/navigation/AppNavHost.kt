package com.example.language_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.language_app.di.AuthState
import com.example.language_app.ui.screens.auth.AuthViewModel
import com.example.language_app.utils.IAppStateManager

@Composable
fun AppNavHost(
    authViewModel: AuthViewModel,
    appStateManager: IAppStateManager,
    navController: NavHostController = rememberNavController()
) {
    val authState by authViewModel.currentAuthState.collectAsStateWithLifecycle()
    val isOnboardingComplete by appStateManager.isOnboardingComplete.collectAsStateWithLifecycle(initialValue = false)

    val startDestination = when {
        !isOnboardingComplete -> Graph.ONBOARDING
        authState is AuthState.LoggedIn -> Graph.MAIN
        else -> Graph.AUTH
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        route = Graph.ROOT
    ) {
        onboardingGraph(navController)

        authGraph(navController, authViewModel)

        mainGraph(navController)
    }
}