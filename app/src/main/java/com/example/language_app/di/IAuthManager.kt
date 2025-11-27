package com.example.language_app.di

import kotlinx.coroutines.flow.Flow

interface IAuthManager {

    val authState: Flow<AuthState>

    suspend fun login(email: String, password: String): Boolean

    fun startRegistration(firstName: String, lastName: String, email: String)

    suspend fun completeRegistration(password: String): Boolean

    suspend fun logout()

    fun cancelRegistration()
}