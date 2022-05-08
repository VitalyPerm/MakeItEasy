package com.elvitalya.makeiteasy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.elvitalya.makeiteasy.view.SampleList
import com.elvitalya.makeiteasy.view.login_screen.LoginScreen
import com.elvitalya.makeiteasy.view.login_screen.RegisterScreen
import com.elvitalya.makeiteasy.view.sample_list.SampleData
import com.elvitalya.makeiteasy.view.sample_list.SampleDataDetails
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route
    ) {
        composable(Screens.SampleData.route) {
            SampleList(navController = navController)
        }
        composable("${Screens.SampleDetail.route}/{item}",
            arguments = listOf(
                navArgument("item") {
                    type = NavType.StringType
                }
            )) { backStackEntry ->
            backStackEntry.arguments?.getString("item")?.let { json ->
                val item = Gson().fromJson(json, SampleData::class.java)
                SampleDataDetails(data = item)
            }
        }

        composable(Screens.Login.route, content = {
            LoginScreen(navController = navController)
        })
        composable(Screens.Registration.route, content = {
            RegisterScreen(navController = navController)
        })

        composable(Screens.MainScreen.route, content = {
            MainScreen(navController = navController)
        })
    }
}

@Composable
fun MainScreen(navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            MainItem(name = Screens.Login.route, navController)
        }

        item {
            MainItem(name = Screens.SampleData.route, navController)
        }
    }
}

@Composable
fun MainItem(name: String, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Cyan.copy(alpha = 0.5f))
            .clickable { navController.navigate(name) }
    ) {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}


sealed class Screens(val route: String) {
    object MainScreen : Screens("Main")
    object SampleData : Screens("SampleData")
    object SampleDetail : Screens("SampleDetail")
    object Login : Screens("Login")
    object Registration : Screens("Registration")
}