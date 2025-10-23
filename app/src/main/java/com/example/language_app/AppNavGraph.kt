package com.example.language_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

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
        AppDestinations.LOGIN_ROUTE
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

    }
}