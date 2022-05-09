package com.elvitalya.makeiteasy.view.botton_nav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elvitalya.makeiteasy.ui.theme.Purple500

sealed class BottomNavigate(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavigate("Home", "Home", Icons.Filled.Home)
    object Favorite : BottomNavigate("Favorite", "Favorite", Icons.Filled.Favorite)
    object Search : BottomNavigate("Search", "Search", Icons.Filled.Search)
    object Setting : BottomNavigate("Setting", "Setting", Icons.Filled.Settings)
    object User : BottomNavigate("User", "User", Icons.Filled.Person)
}


val bottomNavigationItems = listOf(
    BottomNavigate.Home,
    BottomNavigate.Favorite,
    BottomNavigate.Search,
    BottomNavigate.Setting,
    BottomNavigate.User
)


@Composable
fun BottomNavigationScreen() {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Purple500,
                elevation = AppBarDefaults.TopAppBarElevation,
                title = {
                    Text(
                        text = "Make it Easy!",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            )
        },
        bottomBar = {
            NavigationExample(navController, bottomNavigationItems)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomNavigate.Home.route
        ) {
            composable(BottomNavigate.Home.route) {
                BottomHome()
            }
            composable(BottomNavigate.Favorite.route) {
                BottomFavorite()
            }
            composable(BottomNavigate.Search.route) {
                BottomSearch()
            }
            composable(BottomNavigate.Setting.route) {
                BottomSetting()
            }
            composable(BottomNavigate.User.route) {
                BottomUser()
            }
        }
    }
}

@Composable
fun NavigationExample(
    navController: NavController,
    bottomNavigationItems: List<BottomNavigate>
) {
    BottomNavigation {
        bottomNavigationItems.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = null) },
                label = { Text(text = screen.route) },
                selected = false,
                alwaysShowLabel = false,
                onClick = {
                    when (screen.route) {

                        BottomNavigate.Home.title -> navController.navigate(BottomNavigate.Home.route)
                        BottomNavigate.Favorite.title -> navController.navigate(BottomNavigate.Favorite.route)
                        BottomNavigate.Search.title -> navController.navigate(BottomNavigate.Search.route)
                        BottomNavigate.Setting.title -> navController.navigate(BottomNavigate.Setting.route)
                        BottomNavigate.User.title -> navController.navigate(BottomNavigate.User.route)

                    }
                }
            )
        }
    }
}

@Composable
fun BottomHome() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = BottomNavigate.Home.title,
            style = MaterialTheme.typography.h5,
            color = Color.Green,
            fontFamily = FontFamily.Monospace,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    }
}

@Composable
fun BottomFavorite() {
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
            fontSize = 30.sp
        )
    }
}

@Composable
fun BottomSearch() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = BottomNavigate.Search.title,
            style = MaterialTheme.typography.h5,
            color = Color.Green,
            fontFamily = FontFamily.Monospace,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    }
}

@Composable
fun BottomSetting() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = BottomNavigate.Setting.title,
            style = MaterialTheme.typography.h5,
            color = Color.Green,
            fontFamily = FontFamily.Monospace,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    }
}

@Composable
fun BottomUser() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = BottomNavigate.User.title,
            style = MaterialTheme.typography.h5,
            color = Color.Green,
            fontFamily = FontFamily.Monospace,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    }
}