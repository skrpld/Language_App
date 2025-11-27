package com.example.language_app.ui.screens.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.language_app.di.AuthState
import com.example.language_app.di.IAuthManager
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val authManager: IAuthManager) : ViewModel() {

    val currentAuthState: StateFlow<AuthState> = authManager.authState as StateFlow<AuthState>

    var emailInput by mutableStateOf("")
    var firstNameInput by mutableStateOf("")
    var lastNameInput by mutableStateOf("")
    var passwordInput by mutableStateOf("")
    var confirmPasswordInput by mutableStateOf("")

    fun onLoginClicked() {
        viewModelScope.launch {
            authManager.login(emailInput, passwordInput)
        }
    }

    fun onStartRegistrationClicked(firstName: String, lastName: String, email: String) {
        authManager.startRegistration(firstName, lastName, email)
    }

    fun onCompleteRegistrationClicked(password: String) {
        viewModelScope.launch {
            authManager.completeRegistration(password)
        }
    }
}