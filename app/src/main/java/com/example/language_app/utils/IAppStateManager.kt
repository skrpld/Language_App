package com.example.language_app.utils

import kotlinx.coroutines.flow.Flow

interface IAppStateManager {
    val isOnboardingComplete: Flow<Boolean>
}