package com.elvitalya.makeiteasy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elvitalya.makeiteasy.ui.theme.MakeItEasyTheme
import com.elvitalya.makeiteasy.view.login_screen.LoginScreen
import com.elvitalya.makeiteasy.view.login_screen.RegisterScreen


const val LOGIN_REGISTRATION = 1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val screen = LOGIN_REGISTRATION
            MakeItEasyTheme {
                when (screen) {
                    LOGIN_REGISTRATION -> LoginRegistration()
                }
            }
        }
    }
}


@Composable
fun LoginRegistration() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login_page",
        builder = {
            composable("login_page", content = {
                LoginScreen(navController = navController)
            })
            composable("register_page", content = {
                RegisterScreen(navController = navController)
            })
        }
    )
}