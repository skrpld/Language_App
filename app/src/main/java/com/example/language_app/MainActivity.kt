package com.example.language_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.language_app.data.AppDatabase
import com.example.language_app.di.AuthManager
import com.example.language_app.ui.navigation.AppNavGraph
import com.example.language_app.ui.theme.Language_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val appDatabase = remember { AppDatabase.getDatabase(context) }
            val authManager = remember { AuthManager(context, appDatabase.appDao()) }
            val isLoggedIn by authManager.isLoggedIn.collectAsState()

            Language_AppTheme(dynamicColor = false) {
                AppNavGraph(
                    isLoggedIn = isLoggedIn,
                    authManager = authManager
                )
            }
        }
    }
}
