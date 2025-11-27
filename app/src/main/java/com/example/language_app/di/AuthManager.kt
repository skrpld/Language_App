package com.example.language_app.di

import android.content.Context
import android.content.SharedPreferences
import com.example.language_app.data.AppDao
import com.example.language_app.data.models.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class AuthManager(
    context: Context,
    private val appDao: AppDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val _isLoggedIn = MutableStateFlow(prefs.getBoolean(KEY_IS_LOGGED_IN, false))
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _currentUserId = MutableStateFlow(prefs.getLong(KEY_USER_ID, -1L))
    val currentUserId: StateFlow<Long> = _currentUserId

    private fun hashPassword(password: String): String {
        return password.hashCode().toString()  //TODO: Replace with actual hashing algorithm
    }

    suspend fun register(firstName: String, lastName: String, email: String, password: String, profilePicturePath: String): AuthResult = withContext(dispatcher) {
        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank()) {
            return@withContext AuthResult.Error("Fields can't be empty")
        }
        val existingUser = appDao.getUserByEmail(email)
        if (existingUser != null) {
            return@withContext AuthResult.Error("Email already exists.")
        }

        val newUser = User(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password,
            profilePicturePath = profilePicturePath
        )
        val userId = appDao.insertUser(newUser)
        if (userId > 0) {
            prefs.edit().putBoolean(KEY_IS_LOGGED_IN, true).commit()
            prefs.edit().putString(KEY_EMAIL, email).commit()
            prefs.edit().putLong(KEY_USER_ID, userId).commit()
            _isLoggedIn.value = true
            _currentUserId.value = userId
            AuthResult.Success("Registration successful!")
        } else {
            AuthResult.Error("Registration failed.")
        }
    }

    suspend fun login(email: String, password: String): AuthResult = withContext(dispatcher) {
        if (email.isBlank() || password.isBlank()) {
            return@withContext AuthResult.Error("email and password cannot be empty.")
        }
        val user = appDao.authenticateUser(email, password)
        if (user != null) {
            prefs.edit().putBoolean(KEY_IS_LOGGED_IN, true).commit()
            prefs.edit().putString(KEY_EMAIL, email).commit()
            prefs.edit().putLong(KEY_USER_ID, user.id).commit()
            _isLoggedIn.value = true
            _currentUserId.value = user.id
            AuthResult.Success("Login successful!")
        } else {
            AuthResult.Error("Invalid email or password.")
        }
    }

    fun logout() {
        prefs.edit().clear().commit()
        _isLoggedIn.value = false
        _currentUserId.value = -1L
    }

    fun getLoggedInEmail(): String? {
        return prefs.getString(KEY_EMAIL, null)
    }

    companion object {
        private const val PREFS_NAME = "auth_prefs"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_EMAIL = "email"
        private const val KEY_USER_ID = "user_id"
    }
}

sealed class AuthResult {
    data class Success(val message: String) : AuthResult()
    data class Error(val message: String) : AuthResult()
}
