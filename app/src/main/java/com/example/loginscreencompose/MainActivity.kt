package com.example.loginscreencompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.navigation.compose.NavHost
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginscreencompose.ui.theme.LoginScreenComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreenComposeTheme {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF000000),
                                    Color(0xFFAA1F3F),
                                    Color(0xFFDBCA35),
                                    Color(0xFF000000)
                                ),
                                startY = 10f
                            )
                        )
                        .padding(16.dp)
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = "welcomeScreen"
                    ) {
                        composable("welcomeScreen") { WelcomeScreen(navigationController) }
                        composable("loginScreen") { LoginScreen(navigationController) }
                        composable("signUpScreen") { SignUpScreen(navigationController) }
                        composable("forgotPasswordScreen"){ ForgotPasswordScreen()}
                    }
                }
            }
        }
    }
}