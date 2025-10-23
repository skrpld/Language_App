package com.example.language_app.domain

import android.content.Context
import android.content.SharedPreferences
import com.example.language_app.data.AppDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthManager(
    context: Context,
    private val appDao: AppDao,
) {
    private val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)

    private val _isLoggedIn = MutableStateFlow(prefs.getBoolean("isLoggedIn", false))
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn
}