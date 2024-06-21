package com.amrogad.zerochat
import ChatScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.amrogad.zerochat.ui.theme.ChatAppTheme
import ui.AuthScreen
import viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppTheme {
                val authViewModel: AuthViewModel = AuthViewModel()
                var isAuthenticated by remember { mutableStateOf(authViewModel.isLoggedIn()) }

                if (isAuthenticated) {
                    ChatScreen()
                } else {
                    AuthScreen(onAuthSuccess = { isAuthenticated = true })
                }
            }
        }
    }
}
