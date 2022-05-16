package com.elvitalya.makeiteasy.view.botton_nav

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class FavoriteScreens(val route: String) {
    object Favorite : FavoriteScreens("Favorite")
    object FavoriteSecond : FavoriteScreens("FavoriteSecond")
}


@Composable
fun FavoriteNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeScreens.Home.route
    ) {
        composable(HomeScreens.Home.route) { FavoriteHome(navController) }
        composable(HomeScreens.HomeSecond.route) { FavoriteSecond() }
    }
}

@Composable
fun FavoriteHome(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = BottomNavigate.Favorite.title,
            style = MaterialTheme.typography.h5,
            color = Color.Green,
            fontFamily = FontFamily.Monospace,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier
                .clickable {
                    navController.navigate(HomeScreens.HomeSecond.route)
                }
        )
    }
}

@Composable
fun FavoriteSecond() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Favorite Second Screen")
    }
}