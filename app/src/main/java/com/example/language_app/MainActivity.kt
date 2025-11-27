package com.example.language_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.language_app.ui.navigation.AppNavHost
import com.example.language_app.ui.screens.auth.AuthViewModel
import com.example.language_app.ui.theme.Language_AppTheme
import com.example.language_app.utils.IAppStateManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appStateManager: IAppStateManager
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Language_AppTheme(dynamicColor = false) {
                AppNavHost(
                    authViewModel = authViewModel,
                    appStateManager = appStateManager
                )
            }
        }
    }
}