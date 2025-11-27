package com.example.language_app.ui.screens.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.language_app.di.AuthManager

@Composable
fun AuthFlow(
    authManager: AuthManager,
    onLoginSuccess: () -> Unit,
    onBack: () -> Unit
) {
    var currentScreen by remember { mutableStateOf<AuthScreen>(AuthScreen.Login) }
    val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(authManager))

    // Слушаем успешную аутентификацию
    val message by authViewModel.message.collectAsState()
    LaunchedEffect(message) {
        if (message?.contains("success", ignoreCase = true) == true) {
            onLoginSuccess()
        }
    }

    when (currentScreen) {
        AuthScreen.Login -> {
            LoginScreen(
                authManager = authManager,
                onLoginSuccess = onLoginSuccess,
                onSignUp = { currentScreen = AuthScreen.SignupNames },
                onBack = onBack
            )
        }
        AuthScreen.SignupNames -> {
            SignupNamesScreen(
                authManager = authManager,
                onLoginSuccess = onLoginSuccess,
                onNext = { currentScreen = AuthScreen.SignupPassword },
                onBack = { currentScreen = AuthScreen.Login }
            )
        }
        AuthScreen.SignupPassword -> {
            SignupPasswordScreen(
                authManager = authManager,
                onLoginSuccess = onLoginSuccess,
                onBack = { currentScreen = AuthScreen.SignupNames }
            )
        }
    }
}

sealed class AuthScreen {
    object Login : AuthScreen()
    object SignupNames : AuthScreen()
    object SignupPassword : AuthScreen()
}