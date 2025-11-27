package com.example.language_app.di

import com.example.language_app.data.AppDao
import com.example.language_app.data.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthManager(private val appDao: AppDao) : IAuthManager {
    private var tempRegistrationData: User? = null
    private val _authState = MutableStateFlow<AuthState>(AuthState.LoggedOut)
    override val authState: StateFlow<AuthState> = _authState.asStateFlow()
    /**
     *     --- LOGIN ---
     */
    override suspend fun login(email: String, password: String): Boolean {
        _authState.value = AuthState.Loading

        val user = appDao.getUserByEmailAndPassword(email, password) // TODO("перевести в Hash")

        return if (user != null) {
            _authState.value = AuthState.LoggedIn
            true
        } else {
            _authState.value = AuthState.Error("Неправильный email или пароль.")
            _authState.value = AuthState.LoggedOut
            false
        }
    }
    /**
    *     --- LOGOUT ---
    */
    override suspend fun logout() {
        _authState.value = AuthState.Loading
        _authState.value = AuthState.LoggedOut
    }
    /**
     *     --- RGISTRATION ---
     */
    override fun startRegistration(firstName: String, lastName: String, email: String) {
        // TODO("проверить уникальность email")
        tempRegistrationData = User(
            email = email,
            firstName = firstName,
            lastName = lastName,
            passwordHash = "",
            profilePicturePath = ""
        )
        _authState.value = AuthState.Registering
    }

    override suspend fun completeRegistration(password: String): Boolean {
        val data = tempRegistrationData ?: return false

        _authState.value = AuthState.Loading

        val newUser = data.copy(
            passwordHash = password // TODO("перевести в Hash")
        )

        val newUserId = appDao.insertUser(newUser)

        if (newUserId > 0) {
            tempRegistrationData = null
            _authState.value = AuthState.LoggedIn
            return true
        } else {
            _authState.value = AuthState.Error("Не удалось сохранить пользователя.")
            _authState.value = AuthState.LoggedOut
            return false
        }
    }

    override fun cancelRegistration() {
        tempRegistrationData = null
        _authState.value = AuthState.LoggedOut
    }
}